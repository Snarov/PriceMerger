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
public class MergeFileReaderFactory {
	private static final HashMap<String, Class> classMaping = new HashMap<String, Class>(){
		{
			try {
				put("xlsx", Class.forName("pricemerger.core.readers.MergeXLSXReader"));
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
	public static MergeReader getReader(String fileFormat){
		fileFormat = fileFormat.toLowerCase();
		
		MergeReader reader = null;
		try {
			reader = (MergeReader)classMaping.get(fileFormat).newInstance();
		} catch (InstantiationException ex ) {
			//TODO handle
		} catch (IllegalAccessException ex) {
			//TODO handle
		}
		
		return reader;
	}
}
