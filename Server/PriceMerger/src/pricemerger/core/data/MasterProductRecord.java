/*
 * ООО "ТК ЭЛЬДОРАДО"
 * Витебск 2015
 * Автор: Снаров И.А.
 */
package pricemerger.core.data;

import java.util.HashMap;
import pricemerger.core.Configuration;
import pricemerger.util.Match;

/**
 * Класс, представляющий информацию о товарной позиции в основной таблице.
 *
 * @author kiskin
 */
public class MasterProductRecord extends ProductRecord {

	private String endStatelabel; //поле, содержащее метку, добавляемую к записи по завершении процедуры слияния.

	private HashMap<String, String> shippersArticles;
	private HashMap<String, Offer> offers; //предложения поставщиков по товару. key - имяпоставщика. value - предложение

	/**
	 * 
	 * @param id
	 * @param Article
	 * @param category
	 * @param brand
	 * @param model 
	 * @param shippersNames ключи ассоциативного массива shippersArticles
	 * @param shippersArticleValues соответствующие ключам значения для ассоциативного массива shippersArticles
	 * @param offers список существующих предложений по данному товару
	 * 
	 */
	public MasterProductRecord(long id, String Article, String category, String brand, String model,
			String[] shippersNames, String[] shippersArticleValues, final Offer[] offers) {
		super(id, Article, category, brand, model);
		
		shippersArticles = new HashMap<String, String>(){
			{
				for(int i = 0; i < shippersNames.length && i < shippersArticleValues.length; i++){
					put(shippersNames[i], shippersArticleValues[i]);
				}
			}
		};
		
		this.offers = new HashMap<String, Offer>(){
			{
				for(int i = 0; i < shippersNames.length && i < offers.length; i++){
					put(shippersNames[i], offers[i]);
				}
			}
		};
	}
	
		
	/**
	 * @return the endStatelabel
	 */
	public String getEndStatelabel() {
		return endStatelabel;
	}

	/**
	 * @param endStatelabel the endStatelabel to set
	 */
	public void setEndStatelabel(String endStatelabel) {
		this.endStatelabel = endStatelabel;
	}
	
	/**
	 * @return the shippersArticles
	 */
	public HashMap<String, String> getShippersArticles() {
		return shippersArticles;
	}

	@Override
	public Match.MatchRate compare(ProductRecord pr, Configuration.MatchingMode mm) {
		if (Configuration.MatchingMode.ARTICLE == mm) {
			//TODO implement
			return super.compare(pr, mm);
		} else {
			return super.compare(pr, mm);
		}
	}

}
