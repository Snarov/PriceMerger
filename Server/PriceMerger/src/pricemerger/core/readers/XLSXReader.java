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
 *
 * @author kiskin
 */
public class XLSXReader extends Reader{

	public XLSXReader(InputStream stream) {
		super(stream);
	}
	
	@Override
	ArrayList<MergeProductRecord> read(final HashMap<String, String> columnMapping,
			final String from,
			final String to) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}
