/*
 * ООО "ТК ЭЛЬДОРАДО"
 * Витебск 2015
 * Автор: Снаров И.А.
 */
package pricemergerguiclient.model;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс-контейнер для классов, составляющих логику клиентского приложения
 *
 * @author snarov
 */
public class PriceMergerGUIClientModel {

	private final Connector connector;
	private final Configuration configuration;

	public PriceMergerGUIClientModel() {
		connector = new Connector();
		configuration = new Configuration();
	}

	/**
	 * Метод устанавливает значение для параметра конфигурации
	 *
	 * @param paramName
	 * @param paramValue
	 * @return true, если новое значение задано, false, если не удалось задать значение для поля (обычно либо поля не существует,
	 * либо неверный тип значения)
	 */
	public boolean setConfParameter(String paramName, Object paramValue) {
		try {
			Field changingField = Configuration.class.getDeclaredField(paramName);

			//Если мы устанавливаем строковое значение для поля типа перечисления, то сначала нужно получить соответствующее
			//значение перечисления
			if (changingField.getType() == Configuration.MatchingMode.class && paramValue instanceof String) {
				changingField.set(configuration, Configuration.MatchingMode.valueOf((String)paramValue));
			} // или если тип устанавливаемого значения совпадает с типом поля в классе Configuration
			else if(changingField.getType() == paramValue.getClass()){
				changingField.set(configuration, paramValue);
			}else{ // иначе изменить значение поля невозможно
				return false;
			}
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
			Logger.getLogger(PriceMergerGUIClientModel.class.getName()).log(Level.SEVERE, null, ex);
			System.err.println("Ошибка изменения поля конфигурации:" + ex.getMessage());
			return false;
		}
		
		return true;
	}

}
