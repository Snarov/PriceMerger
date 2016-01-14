/*
 * ООО "ТК ЭЛЬДОРАДО"
 * Витебск 2015
 * Автор: Снаров И.А.
 */
package pricemerger.data;

import java.util.Date;
import pricemerger.core.Configuration;
import pricemerger.util.MatchRate;

/**
 * Класс, представляющий информацию о товарной позиции в основной таблице для слияния
 *
 * @author kiskin
 */
public class MergeProductRecord extends ProductRecord {

	private String shipper;
	private String article;

	public MergeProductRecord(String shipper, String article, String category, String brand, String model, float price, int count, Date date) {
		super(category, brand, model, price, count, date);
		this.shipper = shipper;
		this.article = article;
	}
	

	/**
	 * @return the article
	 */
	public String getArticle() {
		return article;
	}

	/**
	 * @return the shipper
	 */
	public String getShipper() {
		return shipper;
	}
	
	@Override
	public MatchRate compare(ProductRecord pr, Configuration.MatchingMode mm){
		if(Configuration.MatchingMode.ARTICLE == mm){
			//TODO implement
			return super.compare(pr, mm);
		}else{
			return super.compare(pr, mm);
		}
	}
}
