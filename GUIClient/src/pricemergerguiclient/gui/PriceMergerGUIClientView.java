/*
 * ООО "ТК ЭЛЬДОРАДО"
 * Витебск 2015  
 * Автор: Снаров И.А.
 */
package pricemergerguiclient.gui;

import java.io.File;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import pricemerger.core.Status;
import pricemergerguiclient.model.PriceMergerGUIClientModel;
import static pricemergerguiclient.PriceMergerGUIClient.resources;
import static pricemergerguiclient.PriceMergerGUIClient.controller;
import pricemergerguiclient.model.configuration.ConfigurationError;

/**
 * Класс, реализующий данные и логику представления клиентской программы. Методы этого класса должны вызываться только методами
 * класса контроллер
 *
 * @author snarov
 */
public class PriceMergerGUIClientView {

	private final Stage appStage;

	private final Label priceFileNameLabel;
	private final ProgressBar mergingProgressBar;
	private final Button processBtn;

	private boolean processingState;

	public PriceMergerGUIClientView(Stage appStage) {
		this.appStage = appStage;

		Scene scene = appStage.getScene();

		priceFileNameLabel = (Label) scene.lookup("#priceFileNameLabel");
		mergingProgressBar = (ProgressBar) scene.lookup("#mergingProgressBar");
		processBtn = (Button) scene.lookup("#processBtn");
	}

	/**
	 * Выводит на экран диалоговое окно выбора файла и возвращает хэндлер файла обратно в контроллер
	 *
	 * @return
	 */
	public File showFileDialog() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(resources.getString("fileDialogTitle"));

		for (String extension : PriceMergerGUIClientModel.SUPPORTED_FILE_EXTENSIONS) {
			String extensionDescriptionName = extension + "Descr";
			String extensionDescription = resources.getString(extensionDescriptionName);
			fileChooser.getExtensionFilters().add(new ExtensionFilter(extensionDescription, "*." + extension));
		}

		File selectedFile = fileChooser.showOpenDialog(appStage);

		return selectedFile;
	}

	/**
	 * Этот метод вызывается после того, как файл был успешно считан. После успешного считывания файла в главном окне отображается
	 * его имя и кнопка запуска процесса становится активной
	 *
	 * @param fileName
	 */
	public void fileReaded(String fileName) {
		priceFileNameLabel.setText(fileName);
		processBtn.setDisable(false);
	}

	/**
	 * Выводит диалоговое окно, уведомляющее об ошибке
	 *
	 * @param errText Текст уведомления об ошибке
	 */
	public void showError(String errText) {
		Alert errorDialog = new Alert(Alert.AlertType.ERROR, errText);
		String localizedErrorWord = resources.getString("error");
		errorDialog.setHeaderText(localizedErrorWord);
		errorDialog.setTitle(localizedErrorWord);
		errorDialog.getDialogPane().setMinHeight(Control.USE_PREF_SIZE);
		errorDialog.showAndWait();
	}

	/**
	 * Уведомляет пользователя об особом типе ошибки - ошибке конфигурации
	 *
	 * @param configurationError
	 */
	public void showConfigurationError(ConfigurationError configurationError) {
		StringBuilder errMsgBuilder = new StringBuilder();

		String wrongValueMsg = resources.getString("wrongFieldValue");

		for (String fieldName : configurationError.getFieldByState(ConfigurationError.Incorrectness.WRONG_VALUE)) {
			errMsgBuilder.append('\u2022');
			String localizedFieldName = resources.getString(fieldName);
			errMsgBuilder.append(wrongValueMsg).append(' ').append('\'').append(fieldName).append('\'').append('\n');
		}

		String notSetMsg = resources.getString("notSetFieldValue");

		for (String fieldName : configurationError.getFieldByState(ConfigurationError.Incorrectness.NOT_SET)) {
			errMsgBuilder.append('\u2022');
			switch (fieldName) {
				case "matchingMode":
					errMsgBuilder.append(resources.getString("matchingModeNotSet")).append('\n');
					break;
				case "mutableFields":
					errMsgBuilder.append(resources.getString("mutableFieldsNotSet")).append('\n');
					break;
				default:
					String localizedFieldName = resources.getString(fieldName);
					errMsgBuilder.append(notSetMsg).append(' ').append('\'').append(fieldName).append('\'').append('\n');
					break;
			}
		}

		showError(errMsgBuilder.toString());
	}

	/**
	 * Выводит диалоговое окно с сообщением
	 *
	 * @param infoText
	 */
	public void showInformation(String infoText) {
		Alert infoDialog = new Alert(Alert.AlertType.INFORMATION, infoText);
		String localizedInfoWord = resources.getString("info");
		infoDialog.setHeaderText(localizedInfoWord);
		infoDialog.setTitle(localizedInfoWord);
		infoDialog.getDialogPane().setMinHeight(Control.USE_PREF_SIZE);
		infoDialog.showAndWait();
	}

	/**
	 * Переводит представление в состояние ожидания результатов вычислений: появляется полоса прогресса и кнопка запуска
	 * становится кнопкой отмены.
	 *
	 * @param observable свойство, с которым нужно связать свойство
	 */
	public void switchToProcessingState(ReadOnlyDoubleProperty observable) {
		if (!processingState) {
			mergingProgressBar.setManaged(true);
			mergingProgressBar.setVisible(true);
			mergingProgressBar.progressProperty().bind(observable);

			processBtn.setText(resources.getString("cancel"));
			processBtn.setOnAction(controller::handleCancelBtnPressed);
		}
	}

	/**
	 * Переводит представление в основное состояние: убирается полоса прогресса и кнопка отмены становится кнопкой запуска.
	 */
	public void switchToMainState() {
		if (!processingState) {
			mergingProgressBar.setManaged(false);
			mergingProgressBar.setVisible(false);
			mergingProgressBar.progressProperty().unbind();
			mergingProgressBar.setProgress(0);

			processBtn.setText(resources.getString("process"));
			processBtn.setOnAction(controller::handleProcessBtnPressed);
		}
	}

	public void showResult(Status status) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
