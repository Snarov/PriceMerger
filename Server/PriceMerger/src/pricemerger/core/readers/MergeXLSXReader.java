/*
 * ООО "ТК ЭЛЬДОРАДО"
 * Витебск 2015
 * Автор: Снаров И.А.
 */
package pricemerger.core.readers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pricemerger.core.data.MergeProductRecord;
import pricemerger.core.data.ProductRecord.Offer;

/**
 * Производит чтение из Excel таблицы формата xlsx
 *
 * @author kiskin
 */
public class MergeXLSXReader extends MergeFileReader {

	public MergeXLSXReader(InputStream stream) {
		super(stream);
	}

	@Override
	public ArrayList<MergeProductRecord> read(final HashMap<ColumnNames, String> columnMapping,
			final int from,
			final int to) {
		ArrayList<MergeProductRecord> rawTable = new ArrayList<>();

		if (from < to) { //если диапазон корректный
			try {
				XSSFWorkbook workbook = new XSSFWorkbook(iStream);
				XSSFSheet sheet = workbook.getSheetAt(0); //Пока что обрабатывается только первый лист

				AtomicInteger readedCount = new AtomicInteger(0);	//платформа не дает изменять значение переменной внутри лямбды.
				for (int rowNum = from; rowNum <= to; rowNum++) {
					XSSFRow row = sheet.getRow(rowNum);

					if (row != null) {
						HashMap<ColumnNames, Object> readBuffer = new HashMap<>();

						columnMapping.forEach((ColumnNames colName, String colNumAlpha26) -> {
							int colNum = CellReference.convertColStringToIndex(colNumAlpha26);
							if (colNum >= 0 && colNum >= row.getFirstCellNum() && colNum < row.getLastCellNum()) {
								XSSFCell cell = row.getCell(colNum);

								switch (cell.getCellType()) {
									case Cell.CELL_TYPE_STRING:
										readBuffer.put(colName, cell.getStringCellValue());
										break;
									case Cell.CELL_TYPE_NUMERIC:
										if (colName == ColumnNames.DATE) {
											readBuffer.put(colName, cell.getDateCellValue());
										} else {
											readBuffer.put(colName, cell.getNumericCellValue());
										}
										break;
								}

								Offer offer = new Offer((String) readBuffer.get(ColumnNames.ARTICLE),
										((Double) readBuffer.get(ColumnNames.COST)).floatValue(),
										((Double) readBuffer.get(ColumnNames.COUNT)).intValue(),
										(Date) readBuffer.get(ColumnNames.DATE));

								MergeProductRecord rawRecord = new MergeProductRecord(
										readedCount.incrementAndGet(),
										(String) readBuffer.get(ColumnNames.ARTICLE),
										(String) readBuffer.get(ColumnNames.CATEGORY),
										(String) readBuffer.get(ColumnNames.BRAND),
										(String) readBuffer.get(ColumnNames.MODEL),
										offer
								);
								
								rawTable.add(rawRecord);
							}
						});
					}

				}

			} catch (IOException ex) {
				//TODO handle exception
			}
		}

		return rawTable;
	}

}
