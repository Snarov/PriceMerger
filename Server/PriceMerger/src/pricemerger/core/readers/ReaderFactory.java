/*
 * ООО "ТК ЭЛЬДОРАДО"
 * Витебск 2015
 * Автор: Снаров И.А.
 */
package pricemerger.core.readers;

import java.util.HashMap;


/**
 * Простой класс для получения ридеров для заданного формата
 * @author kiskin
 */
public class ReaderFactory {
	private static final HashMap<String, Class> classMaping = new HashMap<String, Class>(){
		{
			try {
				put("xlsx", Class.forName("pricemerger.core.readers.XLSXReader"));
			} catch (ClassNotFoundException ex) {
				//TODO output error msg
			}
		}
	};
	
	/**
	 * Возвращает объект ридера соответствующего класса
	 * @param fileFormat
	 * @return объект класса Reader или null в случае ошибки
	 */
	public static Reader getReader(String fileFormat){
		Reader reader = null;
		try {
			reader = (Reader)classMaping.get(fileFormat).newInstance();
		} catch (InstantiationException ex ) {
			//TODO handle
		} catch (IllegalAccessException ex) {
			//TODO handle
		}
		
		return reader;
	}
}
