package main;


import java.net.URL;

import controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Driver extends Application{

	public static void main(String[] args)
	{
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		URL url = this.getClass().getResource("/view/LoginView.fxml");
		FXMLLoader loader = new FXMLLoader(url);
		MainController maincontroller = MainController.getInstance();
		loader.setController(maincontroller);
		Parent root = loader.load();
		Scene scene = new Scene(root, 700, 900);
		primaryStage.setScene(scene);
		primaryStage.getIcons().add(new Image("resources/space_08-512.png"));
		primaryStage.setTitle("Ground Zero");
		primaryStage.show();
	}

}
