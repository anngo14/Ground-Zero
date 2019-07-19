package main;


import java.net.URL;
import java.sql.Connection;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

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

	private static final String DB_URL = "jdbc:mysql://ec2-18-218-115-196.us-east-2.compute.amazonaws.com/phpmyadmin";
	private static final String DB_USER = "phpmyadmin";
	private static final String DB_PASSWORD = "runescape14";
	
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
	public MysqlDataSource establishDataSource()
	{
		MysqlDataSource ds = new MysqlDataSource();
		ds.setURL(DB_URL);
		ds.setUser(DB_USER);
		ds.setPassword(DB_PASSWORD);
		return ds;
	}
	@Override
	public void init() throws Exception
	{
		super.init();
		Connection connection = establishDataSource().getConnection();
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
