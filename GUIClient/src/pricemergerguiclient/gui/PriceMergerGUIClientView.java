/*
 * ООО "ТК ЭЛЬДОРАДО"
 * Витебск 2015  
 * Автор: Снаров И.А.
 */
package pricemergerguiclient.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.stage.Stage;
import pricemergerguiclient.PriceMergerGUIClient;

/**
 *
 * @author snarov
 */
public class PriceMergerGUIClientView {
	private final Stage appStage;
	
	public PriceMergerGUIClientView(Stage appStage){
		this.appStage = appStage;
	}
	
	/**
	 * Выводит диалоговое окно уведомляющее об ошибке
	 * @param errText Текст уведомления об ошибке
	 */
	public void showError(String errText){
		Alert errorDialog = new Alert(Alert.AlertType.ERROR, errText);
		String localizedErrorWord = PriceMergerGUIClient.resources.getString("error");
		errorDialog.setHeaderText(localizedErrorWord);
		errorDialog.setTitle(localizedErrorWord);
		errorDialog.getDialogPane().setMinHeight(Control.USE_PREF_SIZE);
		errorDialog.showAndWait();
	}
}
