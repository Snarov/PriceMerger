/*
 * ООО "ТК ЭЛЬДОРАДО"
 * Витебск 2015
 * Автор: Снаров И.А.
 */
package pricemerger.core.readers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import pricemerger.core.data.MasterProductRecord;
import pricemerger.core.data.ProductRecord;
import pricemerger.util.Currency;

/**
 *
 * @author kiskin
 */
public class MasterMySQLReader extends MasterSQLReader {

	private static final String QUERY_PRODUCTS = "CALL get_products(?)"; //вызывается хранимая процедура, принимающая имя пользователя и на основе
	//настроек пользователя возвращающая полный набор его записей о товарах.
	private static final String QUERY_OFFERS = "CALL get_offers(?, ?)"; //вызывается хранимая процедура, принимающая имя пользователя и id товара
	// и на основе настроек пользователя возвращающая полный набор его записей о предложениях включая имя поставщика
	private static final String QUERY_OFFERS_COUNT = "CALL get_offers_count(?, ?, ?)"; // //вызывается хранимая процедура, принимающая имя пользователя и id товара
	// и на основе настроек пользователя возвращающая в OUT параметре количество предложений по данному товару

	public MasterMySQLReader(String url, Properties props) {
		super(url, props);
	}

	@Override
	public ArrayList<MasterProductRecord> readAll(String userName) {

		ArrayList<MasterProductRecord> masterTable = new ArrayList<>();

		CallableStatement statementProd = null;
		ResultSet rsProd = null;
		try (Connection connection = DriverManager.getConnection(url, props)) {
			statementProd = connection.prepareCall(QUERY_PRODUCTS); //Сначала получаем список всех товаров конкретного пользователя
			statementProd.setString(1, userName);
			rsProd = statementProd.executeQuery();
			
			CallableStatement statementOffer = connection.prepareCall(QUERY_OFFERS); //Затем список всех предложений по каждому товару
			statementOffer.setString(1, userName);
			
			CallableStatement statementOffersCount = connection.prepareCall(QUERY_OFFERS_COUNT); //и количество таких предложений
			statementOffersCount.setString(1, userName);
			statementOffersCount.registerOutParameter(3, java.sql.Types.INTEGER);
			
			while(rsProd.next()){
				//вытягиваем инфу о товаре
				long productId = rsProd.getLong("id");
				String article = rsProd.getString("article");
				String category = rsProd.getString("category_name");
				String brand = rsProd.getString("brand_name");
				String model = rsProd.getString("model");
				
				//выясняем кол-во предложений
				statementOffersCount.setLong(2, productId);
				statementOffersCount.execute();
				int offersCount = statementOffersCount.getInt(2);
								
				statementOffer.setLong(2, productId);
				ResultSet rsOffer = statementOffer.executeQuery();
				
				String[] shippersNames = new String[offersCount];
				ProductRecord.Offer[] offers = new ProductRecord.Offer[offersCount];
				
				int curElemNum = 0; //номер обрабатываемой записи о предложении товара
				while(rsOffer.next()){
					//вытягиваем инфу о каждом предложении
					String shipperName = rsOffer.getString("shipper_name");
					
					String shipperArticle = rsOffer.getString("article");
					float cost = rsOffer.getFloat("cost");
					Currency currency = Currency.valueOf(rsOffer.getString("currency_name"));
					long count = rsOffer.getLong("count");
					Date date = rsOffer.getDate("date");
					
					shippersNames[curElemNum] = shipperName;
					offers[curElemNum++] = new ProductRecord.Offer(shipperArticle, cost, currency, count, date);
				}
				
				masterTable.add(new MasterProductRecord(productId, article, category, brand, model, shippersNames, offers));
			}

		} catch (SQLException ex) {
			//TODO handle
		}
		
		return masterTable;
	}

}
