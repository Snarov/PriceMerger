/*
 * ООО "ТК ЭЛЬДОРАДО"
 * Витебск 2015 * 
 * Автор: Снаров И.А.
 */

package pricemergerguiclient;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Класс представляет само приложение. Структура данных класса соответсвует структуре приложения.
 * 
 * @author snarov
 */
public class PriceMergerGUIClient extends Application {
	
	
	private final static String DEF_LANG = "ru";
	private final static String DEF_LOCATION = "RU";
	private static final String LOCALE_PATH = "pricemergerguiclient/bundles/locale";
	
	private ResourceBundle resources = ResourceBundle.getBundle(LOCALE_PATH, new Locale(DEF_LANG, DEF_LOCATION));
	
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("gui/PriceMergerGUIClient.fxml"), resources);
		
		Scene scene = new Scene(root);
		
		stage.setScene(scene);
		stage.show();
	}
}
