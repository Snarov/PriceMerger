/*
 * ООО "ТК ЭЛЬДОРАДО"
 * Витебск 2015
 * Автор: Снаров И.А.
 */
package pricemergercd.daemon;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс, предоставляющий методы для проведения аутентификация подключающихся пользователей. Данные всех пользователей хранятся в
 * БД.
 *
 * @author snarov
 */
class Authentificator {

	private static final String CONNECTION_URL_TEMPLATE = "jdbc:mysql://%s:%d/%s";
	private static final String USER_GET_SQL = "SELECT password FROM ? WHERE name=?";

	private final Connection DBConnection;
	private final PreparedStatement userGetStatement;

	public Authentificator(
			String DBMSHost,
			int DBMSPort,
			String DBMSUser,
			String DBMSPassword,
			String DBName,
			String tableName
	) throws SQLException {
		String connectionURL = String.format(CONNECTION_URL_TEMPLATE, DBMSHost, DBMSPort, DBName);
		DBConnection = DriverManager.getConnection(connectionURL, DBMSUser, DBMSPassword);

		userGetStatement = DBConnection.prepareStatement(USER_GET_SQL);
		userGetStatement.setString(1, tableName);
	}

	public boolean authentificate(String username, char[] password) {

		boolean authentificated = false;

		try {
			userGetStatement.setString(2, username);

			ResultSet result = userGetStatement.executeQuery();
			if (result.getRow() != 0) {
				byte[] currentPassword = result.getBytes(1);
				authentificated = comparePasswords(currentPassword, password);
			}
		} catch (SQLException ex) {
			Logger.getLogger(Authentificator.class.getName()).log(Level.SEVERE, null, ex);
			System.err.println("Query processing error: " + ex.getMessage());
		}

		return authentificated;
	}

	/**
	 * Производит сравнение двух паролей.
	 *
	 * @param currentPassword зашифрованный истинный пароль пользователя
	 * @param receivedDassword незашифрованный пароль, принятый от клиента
	 * @return true, если пароли совпадают, иначе false
	 */
	private boolean comparePasswords(byte[] currentPassword, char[] receivedDassword) {
		//TODO сравнение паролей
		return true;
	}
}
