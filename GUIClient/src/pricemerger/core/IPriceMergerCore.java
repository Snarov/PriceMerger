/*
 * ООО "ТК ЭЛЬДОРАДО"
 * Витебск 2015
 * Автор: Снаров И.А.
 */
package pricemerger.core;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Интерфейс, используемый для взаимодействия с сервером.
 *
 * @author snarov
 */
public interface IPriceMergerCore extends Remote {

	/**
	 * Основная процедура ядра программы, производящая добавление прайса поставщика в основной прайс пользователя.
	 *
	 * @param configuration
	 * @param file Файл, содержащий прайс поставщика
	 * @return объект типа Status, содержащий информацию о результате выполнения либо об ошибке.
	 * @throws java.rmi.RemoteException
	 */
	Status merge(Configuration configuration, byte[] file) throws RemoteException;

	/**
	 * Позволяет получить информацию о прогрессе выполнения слияния
	 *
	 * @return степень завершенности процесса. Имеет диапазон от 0 до 1.
	 * @throws java.rmi.RemoteException
	 */
	float getProgress() throws RemoteException;

	/**
	 * Прерывает выполнение процесса слияния на сервере
	 *
	 * @throws RemoteException
	 */
	void cancel() throws RemoteException;
}
