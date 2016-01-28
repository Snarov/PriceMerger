/*
 * ООО "ТК ЭЛЬДОРАДО"
 * Витебск 2015
 * Автор: Снаров И.А.
 */
package pricemerger.core.data;

import java.util.Date;
import pricemerger.core.Configuration.MatchingMode;
import pricemerger.util.Currency;
import pricemerger.util.Match;


/**
 * Базовый класс для классов, представляющих информацию о товарной позиции
 *
 * @author kiskin
 */
abstract public class ProductRecord {

	/**
	 * Товарное предложение из таблицы. Является логически выделенной частью из записи в таблице.
	 * Содержит уникальный артикул поставщика
	 * @author kiskin
	 */
	public static class Offer {
	
		String article;
		float cost;
		Currency currency = Currency.USD; //для начала все будет в долларах
		long count;
		Date date;

		public Offer(String article, float cost, long count, Date date) {
			this.article = article;
			this.cost = cost;
			this.count = count;
			this.date = date;
		}

		public Offer(String article, float cost, Currency currency, long count, Date date) {
			this.article = article;
			this.cost = cost;
			this.currency = currency;
			this.count = count;
			this.date = date;
		}
		
	}

	private final long id;
	private final String Article;
	private final String category;
	private final String brand;
	private final String model;

	public ProductRecord(long id, String Article, String category, String brand, String model) {
		this.id = id;
		this.Article = Article;
		this.category = category;
		this.brand = brand;
		this.model = model;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the Article
	 */
	public String getArticle() {
		return Article;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Один из основных методов бизнес-логики. Используется для сопоставления позиций.
	 *
	 * @param pr Товарная позиция для сравнения
	 * @param mm Режим сравнения
	 * @return the match rate
	 */
	public Match.MatchRate compare(ProductRecord pr, MatchingMode mm) {
		//TODO implement
		return new Match.MatchRate(0f, 0f);
	}
}
