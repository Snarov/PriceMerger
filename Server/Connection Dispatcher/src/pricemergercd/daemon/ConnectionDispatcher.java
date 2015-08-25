/*
 * ООО "ТК ЭЛЬДОРАДО"
 * Витебск 2015
 * Автор: Снаров И.А.
 */
package pricemergercd.daemon;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.rmi.server.RemoteServer;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pricemerger.core.IPriceMergerCore;
import static pricemergercd.PriceMergerCD.appConfig;

/**
 * Основной ( и видимо единственный) модуль демона - сам диспетчер подключений
 *
 * @author snarov
 */
public class ConnectionDispatcher extends RemoteServer implements IConnectionDispatcher {

	private static final String SERVER_CORE_RUN_COMMAND = "java -jar PriceMerger.jar --remote";
	private static final String SERVER_CORE_STUB_CLASS_NAME = "IPriceMerger";

	private final Authentificator authentificator;

	public ConnectionDispatcher() throws SQLException {
		this.authentificator = new Authentificator(
				appConfig.getDBMSHost(),
				appConfig.getDBMSPort(),
				appConfig.getDBMSUser(),
				appConfig.getDBMSPassword(),
				appConfig.getDBName(),
				appConfig.getTableName()
		);
	}

	@Override
	public IPriceMergerCore connect(String username, char[] password) {

		//TODO добавить логирование доступа
		IPriceMergerCore serverStub = null;

		//если аутентификация завершилась успешно, то запускаем для подключившегося пользователя новый процесс, в котором
		//будет происходить слияние.
		if (authentificator.authentificate(username, password)) {

			ObjectInputStream ois = null;

			try {
				Process coreProc = Runtime.getRuntime().exec(SERVER_CORE_RUN_COMMAND);

				ois = new ObjectInputStream(coreProc.getInputStream());

				//загружаем класс stub'а
				Class.forName("SERVER_CORE_STUB_CLASS_NAME");
				serverStub = (IPriceMergerCore) ois.readObject();

			} catch (IOException ex) {
				Logger.getLogger(ConnectionDispatcher.class.getName()).log(Level.SEVERE, null, ex);
				System.err.println("Starting core process error: " + ex.getMessage());
			} catch (ClassNotFoundException ex) {
				Logger.getLogger(ConnectionDispatcher.class.getName()).log(Level.SEVERE, null, ex);
				System.err.println("Error receving server stub: " + ex.getMessage());

			}

		}
		return serverStub;
	}
}
