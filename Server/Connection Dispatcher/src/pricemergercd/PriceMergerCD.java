/*
 * ООО "ТК ЭЛЬДОРАДО"
 * Витебск 2015
 * Автор: Снаров И.А.
 */
package pricemergercd;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jfork.nproperty.ConfigParser;
import pricemergercd.daemon.ConnectionDispatcher;
import pricemergercd.daemon.IConnectionDispatcher;

/**
 * Консольное приложение-демон, являющееся частью сервера PriceMerger. Диспетчер подключений обеспечивает клиентам возможность
 * работать с сервером PriceMerger. Диспетчер постоянно прослушивает порт, ожидая подключений клиентов. При подключении очередного
 * клиента диспетчер запускает серверный процесс и передает ссылку на него клиенту, затем клиент и сервер работают независимо от
 * диспетчера подключений. Диспетчер подключений производит аутентификацию пользователя и ведет логирование входящих подключений.
 *
 * @author snarov
 */
public class PriceMergerCD {

	private static final String CONF_FILE_PATH = "etc/pricemerger.conf";

	public static AppConfig appConfig;
	public static ConnectionDispatcher scd;

	public static void main(String[] argv) {

		appConfig = new AppConfig();
		try {
			ConfigParser.parse(appConfig, CONF_FILE_PATH);
		} catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException | IOException ex) {
			Logger.getLogger(PriceMergerCD.class.getName()).log(Level.SEVERE, null, ex);
			System.err.println("Error reading config: " + ex.getMessage());
		}

		try {

			scd = new ConnectionDispatcher();

			//Запускаем rmiregistry дабы обеспечить работу rmi
			Registry registry = LocateRegistry.createRegistry(appConfig.getRegistryPort());

			//Регистрируем диспетчер в созданном registry
			IConnectionDispatcher connectionDispatcherStub
					= (IConnectionDispatcher) UnicastRemoteObject.exportObject(scd, appConfig.getDispatcherPort());
			registry.bind(appConfig.getDispatcherRegistryBindName(), connectionDispatcherStub);
		} catch (RemoteException | AlreadyBoundException ex) {
			Logger.getLogger(PriceMergerCD.class.getName()).log(Level.SEVERE, null, ex);
			System.err.println("Error on daemon start up:" + ex.getMessage());
		} catch (SQLException ex) {
			Logger.getLogger(PriceMergerCD.class.getName()).log(Level.SEVERE, null, ex);
			System.err.println("Error on daemon start up. MySQL connection error:" + ex.getMessage());
		}
	}
}
