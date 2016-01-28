/*
 * ООО "ТК ЭЛЬДОРАДО"
 * Витебск 2015
 * Автор: Снаров И.А.
 */
package pricemerger.core.readers;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import pricemerger.core.data.MergeProductRecord;

/**
 * Базовый класс, производящий чтение входных файлов.
 *
 * @author kiskin
 */
public abstract class Reader {

	public enum ColumnNames {
		CATEGORY,
		BRAND,
		MODEL,
		ARTICLE,
		COUNT,
		COST,
		DATE,
	}

	protected InputStream iStream;

	Reader(){}
	
	Reader(InputStream stream) {
		this.iStream = stream;
	}
	
	/**
	 * @param iStream the iStream to set
	 */
	private void setiStream(InputStream iStream) {
		this.iStream = iStream;
	}

	/**
	 * Метод, считывающий данные из файла и формирующий таблицу для слияния.
	 * 
	 * @param columnMapping отображение, отвечающий за сопоставление номеров столбцов с полями данных в таблице
	 * @param from Диапазон - от какой строки (номер)
	 * @param to Диапазон - до какой строки (номер)
	 * @return Таблица для слияния (сырая)
	 */
	abstract ArrayList<MergeProductRecord> read(final HashMap<ColumnNames, String> columnMapping,
			final int from,
			final int to);
}
