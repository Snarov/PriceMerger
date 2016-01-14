/*
 * ООО "ТК ЭЛЬДОРАДО"
 * Витебск 2015
 * Автор: Снаров И.А.
 */
package pricemerger.core.readers;

import java.io.InputStream;
import java.util.ArrayList;
import pricemerger.data.MergeProductRecord;

/**
 * Базовый класс, производящий чтение входных файлов.
 * @author kiskin
 */
public abstract class Reader {
	protected InputStream stream;

	Reader(InputStream stream){
		this.stream = stream;
	}
	
	abstract ArrayList<MergeProductRecord> read();
}
