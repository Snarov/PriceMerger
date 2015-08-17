/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pricemergerguiclient.controller;

import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import pricemergerguiclient.PriceMergerGUIClient;

/**
 *
 * @author snarov
 */
public class PriceMergerGUIClientController {

	@FXML
	private void handleFileOpenMaster() {
	}

	@FXML
	private void handleFileOpenMerge() {
	}

	@FXML
	private void handleFileExit() {
	}

	/**
	 * Записывает изменение настроек выполнения
	 *
	 * @param property Текстовое поле элемента gui, которое было изменено
	 * @param oldValue
	 * @param newValue
	 */
	@FXML
	private void handleTextFieldTextChange(StringProperty property, String oldValue, String newValue) {
		TextField textField = (TextField) property.getBean();
		//Имена полей класса конфигурации такие же, как и у соответствующих элементов gui, только без постфикса 'TextField'.
		String paramName = textField.getId().replace("TextField", "");
		//если не удалось изменить значение то выводим уведомление об ошибке и откатываем изменения в gui
		if (!PriceMergerGUIClient.model.setConfParameter(paramName, newValue)) {
			property.setValue(oldValue);
			String errorMessage = PriceMergerGUIClient.resources.getString("fieldSetError");
			PriceMergerGUIClient.view.showError(errorMessage);
		}

	}

	/**
	 * Записывает изменения настроек выполнения
	 *
	 * @param event
	 */
	@FXML
	private void handleMutableFieldCBAction(ActionEvent event) {
		CheckBox cb = (CheckBox) event.getTarget();
		//Имена полей класса конфигурации такие же, как и у соответствующих элементов gui, только без постфикса 'CB'.
		String paramName = cb.getId().replace("CB", "");
		//если не удалось изменить значение то выводим уведомление об ошибке и откатываем изменения в gui
		if (!PriceMergerGUIClient.model.setConfParameter(paramName, cb.isSelected())) {
			cb.setSelected(!cb.isSelected());
			String errorMessage = PriceMergerGUIClient.resources.getString("fieldSetError");
			PriceMergerGUIClient.view.showError(errorMessage);
		}
	}

	@FXML
	private void handleMatchingModeRBAction(ActionEvent event) {
		RadioButton rb = (RadioButton) event.getTarget();
		//Имена полей перечисления в классе конфигурации такие же, как и у соответствующих элементов gui, только без постфикса 'RB'.
		String valueName = rb.getId().replace("RB", "").toUpperCase();
		//если не удалось изменить значение то выводим уведомление об ошибке и откатываем изменения в gui
		if (!PriceMergerGUIClient.model.setConfParameter("matchingMode", valueName)) {
			rb.setSelected(false);
			String errorMessage = PriceMergerGUIClient.resources.getString("fieldSetError");
			PriceMergerGUIClient.view.showError(errorMessage);
		}
	}
}
