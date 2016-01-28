/*
 * ООО "ТК ЭЛЬДОРАДО"
 * Витебск 2015
 * Автор: Снаров И.А.
 */
package pricemerger.core.readers;

import java.io.InputStream;

/**
 * Класс, производящий чтение входных данных из файла
 *
 * @author kiskin
 */
public abstract class MergeFileReader implements MergeReader {

	protected InputStream iStream;

	MergeFileReader() {
	}

	MergeFileReader(InputStream stream) {
		this.iStream = stream;
	}

	/**
	 * @param iStream the iStream to set
	 */
	private void setiStream(InputStream iStream) {
		this.iStream = iStream;
	}
}
