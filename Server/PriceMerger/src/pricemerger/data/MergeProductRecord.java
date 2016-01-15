/*
 * ООО "ТК ЭЛЬДОРАДО"
 * Витебск 2015
 * Автор: Снаров И.А.
 */
package pricemerger.data;

import java.util.Date;
import pricemerger.core.Configuration;
import pricemerger.util.Currency;
import pricemerger.util.MatchRate;

/**
 * Класс, представляющий информацию о товарной позиции в основной таблице для слияния
 *
 * @author kiskin
 */
public class MergeProductRecord extends ProductRecord {

	private static String shipperName;
	
	private final Offer offer;
	

	public MergeProductRecord(long id, String Article, String category, String brand, String model,
			float cost, Currency currency, long count, Date date) {
		super(id, Article, category, brand, model);
		
		offer = new Offer(cost, currency, count, date);
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
	public MatchRate compare(ProductRecord pr, Configuration.MatchingMode mm){
		if(Configuration.MatchingMode.ARTICLE == mm){
			//TODO implement
			return super.compare(pr, mm);
		}else{
			return super.compare(pr, mm);
		}
	}
}
