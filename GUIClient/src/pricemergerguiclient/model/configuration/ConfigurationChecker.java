/*
 * ООО "ТК ЭЛЬДОРАДО"
 * Витебск 2015
 * Автор: Снаров И.А.
 */
package pricemergerguiclient.model.configuration;

/**
 * Класс, ответственный за определение корректного состояния объектов класса Configuration
 *
 * @author snarov
 */
public class ConfigurationChecker {

	//вручную задаем кол-во полей выражающих адрес ячейки или столбца. Используется в качестве значения начальной емкости для
	//объекта HashMap
	static final short CONFIG_CELL_FIELD_COUNT = 9;

	//регулярные выражения, соответствующие корректным данным в полях
	static final String CELL_ADDR_REGEX = "^(R\\d+C\\d+|[A-Z]+\\d+)$";
	static final String COL_NUM_REGEX = "^(\\d+|[A-Z]+)$";

	ConfigurationError configurationError;

//	private static HashMap<String, String> getParamToErrMap(Configuration configuration) {
//		return new HashMap<String, String>(CONFIG_CELL_FIELD_COUNT) {
//			{
//				put(configuration.mergePriceRangeFrom, "incorrectValueMergePriceRangeFrom");
//				put(configuration.mergePriceRangeTo, "incorrectValueMergePriceRangeTo");
//				put(configuration.mergePriceCategoryColNum, "incorrectValueMergePriceCategoryColNum");
//				put(configuration.mergePriceBrandColNum, "incorrectValueMergePriceBrandColNum");
//				put(configuration.mergePriceModelColNum, "incorrectValueMergePriceRangeFrom");
//				put(configuration.mergePriceArticleColNum, "incorrectValueMergePriceModelColNum");
//				put(configuration.mergePriceCountColNum, "incorrectValueMergePriceCountColNum");
//				put(configuration.mergePriceCostColNum, "incorrectValueMergePriceCostColNum");
//				put(configuration.mergePriceDateColNum, "incorrectValueMergePriceDateColNum");
//
//			}
//		};
//	}

	/**
	 * Этот умопомрачительный по своему содержанию метод проверяет текущую конфигурацию на согласованность и корректность
	 *
	 * @param configuration
	 */
	public void checkConfСorrectness(Configuration configuration) {

		configurationError = new ConfigurationError();

//		//отображение, связывающее строковый параметр конфигурации и название ошибки.
//		HashMap<String, String> paramToErrMsgMap = getParamToErrMap(configuration);

		if (!"".equals(configuration.mergePriceRangeFrom) && !configuration.mergePriceRangeFrom.matches(CELL_ADDR_REGEX)) {
			configurationError.addField("mergePriceRangeFrom", ConfigurationError.Incorrectness.WRONG_VALUE);
		}
		if (!"".equals(configuration.mergePriceRangeTo) && !configuration.mergePriceRangeTo.matches(CELL_ADDR_REGEX)) {
			configurationError.addField("mergePriceRangeTo", ConfigurationError.Incorrectness.WRONG_VALUE);
		}

		if (configuration.matchingMode != null) {

			if (configuration.matchingMode == Configuration.MatchingMode.ARTICLE) {
				if ("".equals(configuration.mergePriceArticleColNum)) {
					configurationError.addField("mergePriceArticleColNum", ConfigurationError.Incorrectness.NOT_SET);
				} else if (!configuration.mergePriceArticleColNum.matches(COL_NUM_REGEX)) {
					configurationError.addField("mergePriceArticleColNum", ConfigurationError.Incorrectness.WRONG_VALUE);
				}
			}

			if ((configuration.matchingMode == Configuration.MatchingMode.MODEL
					|| configuration.matchingMode == Configuration.MatchingMode.MODELBRAND
					|| configuration.matchingMode == Configuration.MatchingMode.MODELBRANDCATEGORY)) {
				if ("".equals(configuration.mergePriceModelColNum)) {
					configurationError.addField("mergePriceModelColNum", ConfigurationError.Incorrectness.NOT_SET);
				} else if (!configuration.mergePriceModelColNum.matches(COL_NUM_REGEX)) {
					configurationError.addField("mergePriceModelColNum", ConfigurationError.Incorrectness.WRONG_VALUE);
				}
			}

			if ((configuration.matchingMode == Configuration.MatchingMode.MODELBRAND
					|| configuration.matchingMode == Configuration.MatchingMode.MODELBRANDCATEGORY)) {
				if ("".equals(configuration.mergePriceBrandColNum)) {
					configurationError.addField("mergePriceBrandColNum", ConfigurationError.Incorrectness.NOT_SET);
				} else if (!configuration.mergePriceBrandColNum.matches(COL_NUM_REGEX)) {
					configurationError.addField("mergePriceBrandColNum", ConfigurationError.Incorrectness.WRONG_VALUE);
				}
			}

			if (configuration.matchingMode == Configuration.MatchingMode.MODELBRANDCATEGORY) {
				if ("".equals(configuration.mergePriceCategoryColNum)) {
					configurationError.addField("mergePriceCategoryColNum", ConfigurationError.Incorrectness.NOT_SET);
				} else if (!configuration.mergePriceCategoryColNum.matches(COL_NUM_REGEX)) {
					configurationError.addField("mergePriceCategoryColNum", ConfigurationError.Incorrectness.WRONG_VALUE);
				}
			}
		} else {
			configurationError.addField("matchingMode", ConfigurationError.Incorrectness.NOT_SET);
		}

		if (configuration.mutableArticle || configuration.mutableCost || configuration.mutableCount || configuration.mutableDate) {

			if (configuration.mutableArticle) {
				if ("".equals(configuration.mergePriceArticleColNum)) {
					configurationError.addField("mergePriceArticleColNum", ConfigurationError.Incorrectness.NOT_SET);
				} else if (!configuration.mergePriceArticleColNum.matches(COL_NUM_REGEX)) {
					configurationError.addField("mergePriceArticleColNum", ConfigurationError.Incorrectness.WRONG_VALUE);
				}
			}

			if (configuration.mutableCost) {
				if ("".equals(configuration.mergePriceCostColNum)) {
					configurationError.addField("mergePriceCostColNum", ConfigurationError.Incorrectness.NOT_SET);
				} else if (!configuration.mergePriceCostColNum.matches(COL_NUM_REGEX)) {
					configurationError.addField("mergePriceCostColNum", ConfigurationError.Incorrectness.WRONG_VALUE);
				}
			}

			if (configuration.mutableCount) {
				if ("".equals(configuration.mergePriceCountColNum)) {
					configurationError.addField("mergePriceCountColNum", ConfigurationError.Incorrectness.NOT_SET);
				} else if (!configuration.mergePriceCountColNum.matches(COL_NUM_REGEX)) {
					configurationError.addField("mergePriceCountColNum", ConfigurationError.Incorrectness.WRONG_VALUE);
				}
			}

			if (configuration.mutableDate) {
				if ("".equals(configuration.mergePriceDateColNum)) {
					configurationError.addField("mergePriceDateColNum", ConfigurationError.Incorrectness.NOT_SET);
				} else if (!configuration.mergePriceDateColNum.matches(COL_NUM_REGEX)) {
					configurationError.addField("mergePriceDateColNum", ConfigurationError.Incorrectness.WRONG_VALUE);
				}
			}

		} else {
			configurationError.addField("mutableFields", ConfigurationError.Incorrectness.NOT_SET);
		}
	}

	/**
	 *
	 * @return объект ConfigurationError с информацией об ошибке, либо null, если в конфигурации нету ошибок
	 */
	public ConfigurationError getError() {
		return configurationError.getErrorFieldsCount() > 0 ? configurationError : null;
	}

}
