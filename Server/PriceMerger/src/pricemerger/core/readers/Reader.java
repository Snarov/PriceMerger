/*
 * ООО "ТК ЭЛЬДОРАДО"
 * Витебск 2015
 * Автор: Снаров И.А.
 */
package pricemerger.core.readers;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import pricemerger.data.MergeProductRecord;

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

	protected InputStream stream;

	Reader(InputStream stream) {
		this.stream = stream;
	}

	/**
	 * Метод, считывающий данные из файла и формирующий таблицу для слияния.
	 * 
	 * @param columnMapping отображение, отвечающий за сопоставление номеров столбцов с полями данных в таблице
	 * @param from Диапазон - от
	 * @param to диапазон - до
	 * @return Таблица для слияния (сырая)
	 */
	abstract ArrayList<MergeProductRecord> read(final HashMap<String, String> columnMapping,
			final String from,
			final String to);
}