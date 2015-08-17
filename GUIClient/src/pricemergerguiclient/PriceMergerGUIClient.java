/*
 * ООО "ТК ЭЛЬДОРАДО"
 * Витебск 2015 * 
 * Автор: Снаров И.А.
 */
package pricemergerguiclient;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pricemergerguiclient.controller.PriceMergerGUIClientController;
import pricemergerguiclient.gui.PriceMergerGUIClientView;
import pricemergerguiclient.model.PriceMergerGUIClientModel;

/**
 * Класс представляет само приложение. Структура данных класса соответсвует структуре приложения.
 *
 * @author snarov
 */
public class PriceMergerGUIClient extends Application {

	private final static String DEF_LANG = "ru";
	private final static String DEF_LOCATION = "RU";
	private static final String LOCALE_PATH = "pricemergerguiclient/bundles/locale";

	//основные структурные части клиентского приложения
	public static PriceMergerGUIClientView view;
	public static PriceMergerGUIClientController controller;
	public static PriceMergerGUIClientModel model = new PriceMergerGUIClientModel();
	public static ResourceBundle resources = ResourceBundle.getBundle(LOCALE_PATH, new Locale(DEF_LANG, DEF_LOCATION));

	@Override
	public void start(Stage stage) throws Exception {

		try {
			Parent root = FXMLLoader.load(getClass().getResource("gui/PriceMergerGUIClient.fxml"), resources);

			Scene scene = new Scene(root);

			stage.setScene(scene);
			stage.show();
						
		} catch (IOException ex) {
			System.err.printf("Error during load resources: %s\n", ex.getMessage());
			System.exit(1);
		}
		
		view = new PriceMergerGUIClientView(stage);
	}

}
