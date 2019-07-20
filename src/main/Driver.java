package main;


import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;

import controller.MainController;
import gateway.GoalTableGateway;
import gateway.UserTableGateway;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Driver extends Application{

	private static final String DB_URL = "jdbc:postgresql://localhost:5432/Ground Zero";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "password";
	
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
	
	@Override
	public void init() throws Exception
	{
		super.init();
		Class.forName("org.postgresql.Driver");
		Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		UserTableGateway.getInstance().setConnection(connection);
		GoalTableGateway.getInstance().setConnection(connection);
	}
	@Override 
	public void stop() throws Exception
	{
		super.stop();
		UserTableGateway.getInstance().getConnection().close();
		GoalTableGateway.getInstance().getConnection().close();
	}

}
