/*
 * ООО "ТК ЭЛЬДОРАДО"
 * Витебск 2015
 * Автор: Снаров И.А.
 */
package pricemerger.core;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import jfork.nproperty.ConfigParser;

/**
 * Класс представляет собой центральную часть всей программной системы управления прайсами - ядро, производящее сопоставление и
 * слияние
 *
 * @author snarov
 */
public class PriceMergerCore implements IPriceMergerCore {

	/**
	 * Класс содержит непользовательские настройки работы ядра priceMerger. Эти настройки задаются в конфигурационном файле на
	 * сервере. Каждый параметр настроек хранится в полях этого класса, которые имеют примитивный тип. Для занесения значений в
	 * поля класса из файла конфигурации используется библиотека nProperty:
	 * <a href='http://habrahabr.ru/post/194658/'>http://habrahabr.ru/post/194658</a>.
	 *
	 * @author snarov
	 */
	private static class CoreSettings {

	}

	private static final String CONF_FILE_PATH = "etc/pricemerger.conf";

	private final CoreSettings coreSettings = new CoreSettings();
	
	//прогресс выполнения задачия слияния [0, 1]
	private volatile float progress = 0f;

	public PriceMergerCore() {
		try {
			ConfigParser.parse(coreSettings, CONF_FILE_PATH);
		} catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException | IOException ex) {
			Logger.getLogger(PriceMergerCore.class.getName()).log(Level.SEVERE, null, ex);
			System.err.println("Error reading config: " + ex.getMessage());
		}
	}

	@Override
	public Status merge(Configuration configuration, byte[] file) throws RemoteException {
		while(progress < 1);
		
		UnicastRemoteObject.unexportObject(this, true);		//иначе процесс останется "висеть"
		return new Status();
		
	}

	@Override
	public float getProgress() throws RemoteException {
		return progress += .1;
	}

	@Override
	public void cancel() throws RemoteException {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

}
