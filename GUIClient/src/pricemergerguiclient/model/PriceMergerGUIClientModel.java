/*
 * ООО "ТК ЭЛЬДОРАДО"
 * Витебск 2015
 * Автор: Снаров И.А.
 */
package pricemergerguiclient.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import pricemergerguiclient.model.configuration.ConfigurationChecker;
import pricemergerguiclient.model.connection.Connection;
import java.lang.reflect.Field;
import java.rmi.RemoteException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import pricemerger.core.Configuration;
import pricemerger.core.Status;
import static pricemergerguiclient.PriceMergerGUIClient.controller;
import pricemergerguiclient.model.configuration.ConfigurationError;

/**
 * Класс-контейнер для классов, составляющих логику клиентского приложения
 *
 * @author snarov
 */
public class PriceMergerGUIClientModel {

	private static final short PROGRESS_UPDATE_INTERVAL = 1000; // мс

	private final Connection connection;
	private final Configuration configuration;
	private byte[] priceFile;

	public static final String[] SUPPORTED_FILE_EXTENSIONS = {
		"xlsx"
	};

	public PriceMergerGUIClientModel() {
		connection = new Connection();
		configuration = new Configuration();
	}

	/**
	 * @param file
	 * @return true, если чтение прошло успешно, и false, если не очень.
	 */
	public boolean readFile(File file) {
		boolean successfully = false;

		if (file.exists()) {
			priceFile = new byte[(int) file.length()];	//будем надеяться, что файлы размером больше 2 ГиБ(примерно) нам не встретятся
			try (FileInputStream fis = new FileInputStream(file)) {
				fis.read(priceFile);
				successfully = true;
			} catch (FileNotFoundException ex) {	//этого исключения не должно возникнуть
			} catch (IOException ex) {
				Logger.getLogger(PriceMergerGUIClientModel.class.getName()).log(Level.SEVERE, null, ex);
				System.err.println("File input error" + ex.getMessage());
			}
		}

		return successfully;
	}

	/**
	 * Метод устанавливает значение для параметра конфигурации
	 *
	 * @param paramName
	 * @param paramValue
	 * @return true, если новое значение задано, false, если не удалось задать значение для поля (обычно либо поля не существует,
	 * либо неверный тип значения)
	 */
	public boolean setConfParameter(String paramName, Object paramValue) {
		try {
			Field changingField = Configuration.class.getDeclaredField(paramName);
			changingField.setAccessible(true);

			//Если мы устанавливаем строковое значение для поля типа перечисления, то сначала нужно получить соответствующее
			//значение перечисления
			if (changingField.getType() == Configuration.MatchingMode.class && paramValue instanceof String) {
				changingField.set(configuration, Configuration.MatchingMode.valueOf((String) paramValue));
			} // или если тип устанавливаемого значения совпадает с типом поля в классе Configuration
			else if (changingField.getType() == paramValue.getClass()) {
				changingField.set(configuration, paramValue);
			} else { // иначе изменить значение поля невозможно
				return false;
			}
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
			Logger.getLogger(PriceMergerGUIClientModel.class.getName()).log(Level.SEVERE, null, ex);
			System.err.println("Error changing configuration parameter:" + ex.getMessage());
			return false;
		}

		return true;
	}

	/**
	 * Метод, служащий для проверки корректности конфигурации перед отправкой ее на сервер
	 *
	 * @return информация об ошибке либо null, если данные конфигурации корректны
	 */
	public ConfigurationError confCheck() {
		ConfigurationChecker configurationChecker = new ConfigurationChecker();
		configurationChecker.checkConfСorrectness(configuration);
		return configurationChecker.getError();
	}

	/**
	 * Метод, служащий для проверки корректности файла перед отправкой его на сервер
	 *
	 * @return true, если файл в порядке, иначе false
	 */
	public boolean fileCheck() {
		return priceFile.length > 0; //в будущем можно добавить проверку и посложнее
	}

	/**
	 * Запускает выполнение процедуры слияния, для чего выполняет подключение к серверу и вызывает на сервере процедуру,
	 * непосредственно выполняющую слияние, и затем получает от сервера результат.
	 */
	public void process() {
		if (connection.connect("user", new char[]{'p', 'a', 's', 's'})) {

			//Задача исполняется в фоновом режиме, чтобы не подвис GUI
			Task<Status> mergeTask = new Task<Status>() {
				@Override
				public Status call() {
					Status status = null;
					try {

						status = connection.getServer().merge(configuration, priceFile);
					} catch (RemoteException ex) {
						Logger.getLogger(PriceMergerGUIClientModel.class.getName()).log(Level.SEVERE, null, ex);
						System.err.println("Error starting server process:" + ex.getMessage());
					}

					return status;
				}

				/**
				 * Как только начинается выполнение процесса слияния на сервере содержимое основного окна клиентской программы
				 * изменяет свое состояние.
				 */
				@Override
				public void running() {
					//меняем состояние представления на "работающее"
					controller.serverProcessRunning(workDoneProperty());

					Timer updateProgressTimer = new Timer(true); //создаем таймер, поток которого является потоком-демоном
					updateProgressTimer.schedule(new TimerTask() {
						/**
						 * Изменяет значение свойства workDone внешней задачи mergeTask
						 */
						@Override
						public void run() {
							try {
								updateProgress(connection.getServer().getProgress(), 1.);
							} catch (RemoteException ex) {
								Logger.getLogger(PriceMergerGUIClientModel.class.getName()).log(Level.SEVERE, null, ex);
								System.err.println("Retrieving progress error:" + ex.getMessage());
							}
						}
					},
							0,
							PROGRESS_UPDATE_INTERVAL
					);

				}

				@Override
				public void failed() {
					controller.handleModelError("connectionError");
				}

				@Override
				public void succeeded() {
					controller.serverProcessFinished(getValue());
				}

			};

			Thread mergeTaskThread = new Thread(mergeTask);
			mergeTaskThread.setDaemon(true);
			mergeTaskThread.start();

		} else {
			controller.handleModelError("authentificationFailed");
		}
	}

	public void cancel() {
		try {
			connection.getServer().cancel();
		} catch (RemoteException ex) {
			Logger.getLogger(PriceMergerGUIClientModel.class.getName()).log(Level.SEVERE, null, ex);
			System.err.println("Cannot cancel server process" + ex.getMessage());
		}
	}
}
