/*
 * ООО "ТК ЭЛЬДОРАДО"
 * Витебск 2015
 * Автор: Снаров И.А.
 */
package pricemerger.data;

import java.util.Date;
import pricemerger.core.Configuration.MatchingMode;
import pricemerger.util.MatchRate;

/**
 * Базовый класс для классов, представляющих информацию о товарной позиции
 * @author kiskin
 */
abstract public class ProductRecord {
	private final String category;
	private final String brand;
	private final String model;
	private final float price;
	private final int count;
	private final Date date;

	public ProductRecord(String category, String brand, String model, float price, int count, Date date) {
		this.category = category;
		this.brand = brand;
		this.model = model;
		this.price = price;
		this.count = count;
		this.date = date;
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
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * Один из основных методов бизнес-логики. Используется для сопоставления позиций.
	 * @param pr Товарная позиция для сравнения
	 * @param mm Режим сравнения
	 * @return the match rate
	 */
	public MatchRate compare(ProductRecord pr, MatchingMode mm){
		//TODO implement
		return new MatchRate(0f, 0f);
	}
	
}
