/*
 * ООО "ТК ЭЛЬДОРАДО"
 * Витебск 2015
 * Автор: Снаров И.А.
 */
package pricemergerguiclient.model.configuration;

import java.io.Serializable;

/**
 * Класс, представляющий настройки выполнения процедуры слияния. Состояние экземпляра задается с помощью средств gui и далее
 * экземпляр подлежит отправке на сервер. Поля, содержащие номера столбцов имеют тип String, т.к. в API для идентификации ячеек
 * используется строковое представление, и значения в свойствах элементов gui также хранятся в виде String. Имена текстовых полей
 * этого класса такие же, как и у соответствующих элементов gui, только без постфикса 'TextField'. Имена булевых полей этого
 * класса такие же, как и у соответствующих элементов gui, только без постфикса 'СВ'.
 *
 * @author snarov
 */
public class Configuration implements Serializable {

	public static enum MatchingMode {

		ARTICLE, MODEL, MODELBRAND, MODELBRANDCATEGORY
	};

	String mergePriceRangeFrom = "";
	String mergePriceRangeTo= "";
	String mergePriceCategoryColNum = "";
	String mergePriceBrandColNum = "";
	String mergePriceModelColNum = "";
	String mergePriceArticleColNum = "";
	String mergePriceCountColNum = "";
	String mergePriceCostColNum = "";
	String mergePriceDateColNum = "";

	Boolean mutableArticle = false;
	Boolean mutableCount = false;
	Boolean mutableCost = false;
	Boolean mutableDate = false;

	MatchingMode matchingMode = MatchingMode.ARTICLE; // по умолчанию
}
