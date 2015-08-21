/*
 * ООО "ТК ЭЛЬДОРАДО"
 * Витебск 2015
 * Автор: Снаров И.А.
 */
package pricemergerguiclient.controller;

import java.io.File;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import pricemergerguiclient.model.Status;
import static pricemergerguiclient.PriceMergerGUIClient.view;
import static pricemergerguiclient.PriceMergerGUIClient.model;
import static pricemergerguiclient.PriceMergerGUIClient.resources;
import pricemergerguiclient.model.configuration.ConfigurationError;

/**
 *
 * @author snarov
 */
public class PriceMergerGUIClientController {

	@FXML
	private void handleOpenFile() {

		File file = view.showFileDialog();

		if (file != null) {
			if (model.readFile(file)) {
				view.fileReaded(file.getName());
			}else{
				view.showError(resources.getString("fileReadError"));
			}
		}
	}

	@FXML
	private void handleFileExit() {
		System.exit(0);
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
		if (!model.setConfParameter(paramName, newValue)) {
			property.setValue(oldValue);
			String errorMessage = resources.getString("fieldSetError");
			view.showError(errorMessage);
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
		if (!model.setConfParameter(paramName, cb.isSelected())) {
			cb.setSelected(!cb.isSelected());
			String errorMessage = resources.getString("fieldSetError");
			view.showError(errorMessage);
		}
	}

	@FXML
	private void handleMatchingModeRBAction(ActionEvent event) {
		RadioButton rb = (RadioButton) event.getTarget();
		//Имена полей перечисления в классе конфигурации такие же, как и у соответствующих элементов gui, только без постфикса 'RB'.
		String valueName = rb.getId().replace("RB", "").toUpperCase();
		//если не удалось изменить значение то выводим уведомление об ошибке и откатываем изменения в gui
		if (!model.setConfParameter("matchingMode", valueName)) {
			rb.setSelected(false);
			String errorMessage = resources.getString("fieldSetError");
			view.showError(errorMessage);
		}
	}

	@FXML
	private void handleOpenFileBtnPressed(ActionEvent event) {
		handleOpenFile();
	}

	public void handleProcessBtnPressed(ActionEvent event) {
		if (!model.fileCheck()) {
			view.showError(resources.getString("fileNotSet"));
			return;
		}

		ConfigurationError configurationError = model.confCheck();
		if (configurationError != null) {
			view.showConfigurationError(configurationError);
			return;
		}

		model.process();
	}

	public void handleCancelBtnPressed(ActionEvent event) {
		model.cancel();
	}

	/**
	 * Обрабатывает ошибку, возникшую в модели. Является дополнительным интерфейсом для уведомления о возникновении ошибок и
	 * используется тогда, когда применение обычного способа (посрадством возвращаемых значений) невозможно или затруднено.
	 *
	 * @param errorMsg
	 */
	public void handleModelError(String errorMsg) {
		view.showError(resources.getString(errorMsg));
	}

	public void serverProcessRunning(ReadOnlyDoubleProperty workDoneProperty) {
		view.switchToProcessingState(workDoneProperty);
	}

	public void serverProcessFinished(Status status) {
		view.switchToMainState();
		view.showResult(status);

	}
}
