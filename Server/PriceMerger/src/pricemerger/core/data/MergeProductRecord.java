/*
 * ООО "ТК ЭЛЬДОРАДО"
 * Витебск 2015
 * Автор: Снаров И.А.
 */
package pricemerger.core.data;

import java.util.Date;
import pricemerger.core.Configuration;
import pricemerger.util.Currency;
import pricemerger.util.Match;

/**
 * Класс, представляющий информацию о товарной позиции в основной таблице для слияния
 *
 * @author kiskin
 */
public class MergeProductRecord extends ProductRecord {

	private static String shipperName;
	
	private final Offer offer;
	

	public MergeProductRecord(long id, String article, String category, String brand, String model,
			float cost, long count, Date date) {
		super(id, article, category, brand, model);
		
		offer = new Offer(article, cost, count, date);
	}
	
	public MergeProductRecord(long id, String Article, String category, String brand, String model, Offer offer) {
		super(id, Article, category, brand, model);
		
		this.offer = offer;
	}
		
	/**
	 * @return the shipper
	 */
	public String getShipperName() {
		return shipperName;
	}

	@Override
	public Match.MatchRate compare(ProductRecord pr, Configuration.MatchingMode mm){
		if(Configuration.MatchingMode.ARTICLE == mm){
			//TODO implement
			return super.compare(pr, mm);
		}else{
			return super.compare(pr, mm);
		}
	}
}
