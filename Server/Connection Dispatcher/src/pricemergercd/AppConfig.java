/*
 * ООО "ТК ЭЛЬДОРАДО"
 * Витебск 2015
 * Автор: Снаров И.А.
 */
package pricemergercd;

import jfork.nproperty.Cfg;

/**
 * Класс, хранящий настройки выполнения приложения. ( Например порты для прослушивания, константы... ). Каждый параметр настроек
 * хранится в полях этого класса, которые имеют примитивный тип. Для занесения значений в поля класса из файла конфигурации
 * используется библиотека nProperty: <a href='http://habrahabr.ru/post/194658/'>http://habrahabr.ru/post/194658</a>.
 *
 * @author snarov
 */
public class AppConfig {

	@Cfg
	private int registryPort = 1099;
	@Cfg
	private int dispatcherPort = 1717;
	@Cfg
	private int DBMSPort = 3306;
	@Cfg
	private String dispatcherRegistryBindName = "connectionDispatcher";
	@Cfg
	private String DBMSHost = "localhost";
	@Cfg
	private String DBMSUser = "PriceMerger";
	@Cfg
	private String DBMSPassword;
	@Cfg
	private String DBName = "Pricemerger";
	@Cfg
	private String usersTableName = "Users";

	/**
	 * @return the registryPort
	 */
	public int getRegistryPort() {
		return registryPort;
	}

	/**
	 * @return the dispatcherPort
	 */
	public int getDispatcherPort() {
		return dispatcherPort;
	}

	/**
	 * @return the DBMSPort
	 */
	public int getDBMSPort() {
		return DBMSPort;
	}

	/**
	 * @return the dispatcherRegistryBindName
	 */
	public String getDispatcherRegistryBindName() {
		return dispatcherRegistryBindName;
	}

	/**
	 * @return the DBMSHost
	 */
	public String getDBMSHost() {
		return DBMSHost;
	}

	/**
	 * @return the DBMSUser
	 */
	public String getDBMSUser() {
		return DBMSUser;
	}

	/**
	 * @return the DBMSPassword
	 */
	public String getDBMSPassword() {
		return DBMSPassword;
	}

	/**
	 * @return the DBName
	 */
	public String getDBName() {
		return DBName;
	}

	/**
	 * @return the usersTableName
	 */
	public String getUsersTableName() {
		return usersTableName;
	}

}
