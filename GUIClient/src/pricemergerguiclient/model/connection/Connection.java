/*
 * ООО "ТК ЭЛЬДОРАДО"
 * Витебск 2015
 * Автор: Снаров И.А.
 */
package pricemergerguiclient.model.connection;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import pricemerger.core.IPriceMergerCore;
import pricemergercd.daemon.IConnectionDispatcher;
import pricemergerguiclient.PriceMergerGUIClient;

/**
 * Класс, обеспечивающий соединение с сервером. С его помощью происходят все взаимодействия с сервером: запуск и прерывание
 * процедуры слияния, передача параметров, получение уведомлений об ошибках, уведомление о прогрессе.
 *
 * @author snarov
 */
public class Connection {

	static final String DEFAULT_HOST = "127.0.0.1";
	static final int DEFAULT_PORT = 1099;
	static final String SERVER_STUB_NAME = "connectionDispatcher";

	private IConnectionDispatcher serverConnectionDispatcher;
	private IPriceMergerCore server;	//ссылка на объект (stub), представляющий собой сервер

	public Connection() {
		this(DEFAULT_HOST, DEFAULT_PORT);
	}

	public Connection(String host, int port) {
		try {
			Registry registry = LocateRegistry.getRegistry(host, port);
			serverConnectionDispatcher = (IConnectionDispatcher) registry.lookup(SERVER_STUB_NAME);
		} catch (RemoteException | NotBoundException ex) {
			Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
			System.err.println(ex.getMessage());
			PriceMergerGUIClient.view.showError(PriceMergerGUIClient.resources.getString("connectionError"));
		}
	}

	/**
	 * Проходит аутентификацию с указанным именем и паролем, и если аутентификация пройдена получает ссылку на рабочий объект
	 * сервера
	 *
	 * @param username
	 * @param password
	 * @return true, если удалось подключиться и получить ссылку на исполняющий объект, false в случае неудачи
	 */
	public boolean connect(String username, char[] password) {
		if (serverConnectionDispatcher != null) {
			try {
				server = serverConnectionDispatcher.connect(username, password);
			} catch (RemoteException ex) {
				Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
				System.err.println(ex.getMessage());
			}
		}

		return server != null;
	}

	public IPriceMergerCore getServer() {
		return server;
	}
}
