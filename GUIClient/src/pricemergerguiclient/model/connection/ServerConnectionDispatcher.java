/*
 * ООО "ТК ЭЛЬДОРАДО"
 * Витебск 2015
 * Автор: Снаров И.А.
 */
package pricemergerguiclient.model.connection;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Интерфейс, используемый для вызова методотов удаленного диспетчера подключений
 * 
 * @author snarov
 */
public interface ServerConnectionDispatcher extends Remote{
	Server connect(String username, char[] password) throws RemoteException;
}
