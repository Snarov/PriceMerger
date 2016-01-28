/*
 * ООО "ТК ЭЛЬДОРАДО"
 * Витебск 2015
 * Автор: Снаров И.А.
 */
package pricemerger.core.readers;

import java.util.Properties;

/**
 * Класс для считывания данных основного прайса из реляционных БД
 *
 * @author kiskin
 */
public abstract class MasterSQLReader implements MasterReader {

	protected String url;
	protected Properties props;

	public MasterSQLReader(String url, Properties props) {
		this.url = url;
		this.props = props;
	}

}
