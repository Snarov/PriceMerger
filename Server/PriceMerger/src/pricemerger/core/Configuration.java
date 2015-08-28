/*
 * ООО "ТК ЭЛЬДОРАДО"
 * Витебск 2015
 * Автор: Снаров И.А.
 */
package pricemerger.core;

import java.io.Serializable;

/**
 * Класс, представляющий пользовательские настройки выполнения процедуры слияния с файлом прайса. Поля, содержащие номера столбцов
 * имеют тип String, т.к. в API для идентификации ячеекиспользуется строковое представление, и значения в свойствах элементов gui
 * также хранятся в виде String. Имена текстовых полей этого класса такие же, как и у соответствующих элементов gui, только без
 * постфикса 'TextField'. Имена булевых полей этого класса такие же, как и у соответствующих элементов gui, только без постфикса
 * 'СВ'.
 *
 * @author snarov
 */
public class Configuration implements Serializable {

	public static enum MatchingMode {

		ARTICLE, MODEL, MODELBRAND, MODELBRANDCATEGORY
	};

	public String mergePriceRangeFrom = "";
	public String mergePriceRangeTo = "";
	public String mergePriceCategoryColNum = "";
	public String mergePriceBrandColNum = "";
	public String mergePriceModelColNum = "";
	public String mergePriceArticleColNum = "";
	public String mergePriceCountColNum = "";
	public String mergePriceCostColNum = "";
	public String mergePriceDateColNum = "";

	public Boolean mutableArticle = false;
	public Boolean mutableCount = false;
	public Boolean mutableCost = false;
	public Boolean mutableDate = false;

	public MatchingMode matchingMode = MatchingMode.ARTICLE; // по умолчанию
}
