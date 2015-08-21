/*
 * ООО "ТК ЭЛЬДОРАДО"
 * Витебск 2015
 * Автор: Снаров И.А.
 */
package pricemergerguiclient.model.configuration;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Класс, предоставляющий информацию об ошибке конфигурации
 *
 * @author snarov
 */
public class ConfigurationError {

	public static enum Incorrectness {

		NOT_SET, WRONG_VALUE
	}

	//хранит состояние, описывающее корректность, для каждого поля
	private final HashMap<String, Incorrectness> fieldStates = new HashMap<>();

	/**
	 * Добавляет новую запись об ошибке в поле. Если запись о поле уже существует, то добавление не происходит
	 *
	 * @param fieldName
	 * @param fieldState
	 */
	void addField(String fieldName, Incorrectness fieldState) {
		fieldStates.putIfAbsent(fieldName, fieldState);
	}

	public int getErrorFieldsCount() {
		return fieldStates.size();
	}

	/**
	 * производит выборку из fieldStates
	 *
	 * @param fieldState
	 * @return имена полей, имеющих определенное состояние
	 */
	public String[] getFieldByState(Incorrectness fieldState) {
		ArrayList<String> fieldNames = new ArrayList<>();

		fieldStates.forEach((key, value) -> {
			if (value == fieldState) {
				fieldNames.add(key);
			}
		});
		
		String[] retval = new String[fieldNames.size()];
		fieldNames.toArray(retval);
		
		return retval;
	}

}
