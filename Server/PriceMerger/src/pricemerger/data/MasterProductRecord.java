/*
 * ООО "ТК ЭЛЬДОРАДО"
 * Витебск 2015
 * Автор: Снаров И.А.
 */
package pricemerger.data;

import java.util.Date;
import java.util.HashMap;
import pricemerger.core.Configuration;
import pricemerger.util.MatchRate;

/**
 * Класс, представляющий информацию о товарной позиции в основной таблице.
 *
 * @author kiskin
 */
public class MasterProductRecord extends ProductRecord {

	private String masterArticle;
	private HashMap<String, String> shippersArticles = new HashMap<>();

	/**
	 *
	 * @param masterArticle
	 * @param category
	 * @param brand
	 * @param model
	 * @param price
	 * @param count
	 * @param date
	 * @param shippersNames ключи ассоциативного массива shippersArticles
	 * @param shippersArticleValues соответствующие ключам значения для ассоциативного массива shippersArticles
	 */
	public MasterProductRecord(long id, String masterArticle, String category, String brand, String model, float price,
			int count, Date date, String[] shippersNames, String[] shippersArticleValues) {
		super(id, category, brand, model, price, count, date);
		this.masterArticle = masterArticle;
	}

	/**
	 * @return the masterArticle
	 */
	public String getMasterArticle() {
		return masterArticle;
	}

	/**
	 * @return the shippersArticles
	 */
	public HashMap<String, String> getShippersArticles() {
		return shippersArticles;
	}

	@Override
	public MatchRate compare(ProductRecord pr, Configuration.MatchingMode mm) {
		if (Configuration.MatchingMode.ARTICLE == mm) {
			//TODO implement
			return super.compare(pr, mm);
		} else {
			return super.compare(pr, mm);
		}
	}

}
