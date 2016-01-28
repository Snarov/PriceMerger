/*
 * ООО "ТК ЭЛЬДОРАДО"
 * Витебск 2015
 * Автор: Снаров И.А.
 */
package pricemerger.core.readers;

import java.util.HashMap;

/**
 * Простой класс для получения ридеров для заданной СУБД
 * @author kiskin
 */
public class MasterSQLReaderFactory {
	private static final HashMap<String, Class> classMaping = new HashMap<String, Class>(){
		{
			try {
				put("mysql", Class.forName("pricemerger.core.readers.MasterMySQLReader"));
			} catch (ClassNotFoundException ex) {
				//TODO output error msg
			}
		}
	};
	
	/**
	 * Возвращает объект ридера соответствующего класса
	 * @param DBMSName
	 * @return объект класса Reader или null в случае ошибки
	 */
	public static MergeReader getReader(String DBMSName){
		DBMSName = DBMSName.toLowerCase();
		
		MergeReader reader = null;
		try {
			reader = (MergeReader)classMaping.get(DBMSName).newInstance();
		} catch (InstantiationException ex ) {
			//TODO handle
		} catch (IllegalAccessException ex) {
			//TODO handle
		}
		
		return reader;
	}
}
