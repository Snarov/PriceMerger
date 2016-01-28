/*
 * ООО "ТК ЭЛЬДОРАДО"
 * Витебск 2015
 * Автор: Снаров И.А.
 */
package pricemerger.core.readers;

import java.util.ArrayList;
import pricemerger.core.data.MasterProductRecord;

/**
 * Класс, считывающий данные основного прайса.
 *
 * @author kiskin
 */
public interface MasterReader {

	/**
	 * считывает все строки из текущего основного прайса, принадлежашие userName
	 *
	 * @param userName
	 * @return основная таблица. Пустой массив если ничего не прочитано
	 */
	ArrayList<MasterProductRecord> readAll(String userName);
}
