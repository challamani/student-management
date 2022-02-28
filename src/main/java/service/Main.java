package service;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.util.logging.Logger;


public class Main extends Application {
	private final static Logger logger = Logger.getLogger(Application.class.getName());

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/template/login.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception ex) {
			logger.warning("failed at loading login application {}"+ ex.getMessage());
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
