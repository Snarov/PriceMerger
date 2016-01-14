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
 *
 * @author kiskin
 */
public class XLSXReader extends Reader{

	public XLSXReader(InputStream stream) {
		super(stream);
	}
	
	@Override
	ArrayList<MergeProductRecord> read() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}
