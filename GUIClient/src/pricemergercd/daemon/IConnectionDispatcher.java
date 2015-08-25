/*
 * ООО "ТК ЭЛЬДОРАДО"
 * Витебск 2015
 * Автор: Снаров И.А.
 */
package pricemergercd.daemon;

import java.rmi.Remote;
import java.rmi.RemoteException;
import pricemerger.core.IPriceMergerCore;

/**
 * Интерфейс, реализуемый диспетчером подкючений
 *
 * @author snarov
 */
public interface IConnectionDispatcher extends Remote {

	/**
	 * Осуществляет подключение клиента к серверу. С помощью этого метода клиент проходит аутентификацию и получает ссылку на
	 * серверный процесс. Диспетчер является связующим звеном между клиентом и сервером и ему неинтересен тип объекта-ссылки на
	 * сервер. Поэтому возвращаемое значение имеет тип Object. Соединение не является безопасным, так как пароль пользователя
	 * передается открыто. В финальной версии будет добавлено шифрование или даже поддержка SSL или TLS.
	 *
	 * @param username
	 * @param password
	 * @return ссылка на серверный процесс, либо null если аутентификация не пройдена
	 * @throws RemoteException
	 */
	IPriceMergerCore connect(String username, char[] password) throws RemoteException;

}
