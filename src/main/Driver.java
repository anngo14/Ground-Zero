package main;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
import model.User;

public class Driver extends Application{

	private static final String DB_URL = "jdbc:postgresql://localhost:5432/Ground Zero";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "password";
	private final File defaultUser = new File("C:\\GroundZero\\Users\\lastUser.txt");
    private final File directory = new File ("C:\\GroundZero\\Users");
	private User lastUser;
	
	public static void main(String[] args)
	{
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		checkDirectory();
		lastUser = getLastUser();
		URL url = this.getClass().getResource("/view/LoginView.fxml");
		FXMLLoader loader = new FXMLLoader(url);
		MainController maincontroller = MainController.getInstance();
		loader.setController(maincontroller);
		MainController.getInstance().setUser(lastUser);

		Parent root = loader.load();
		Scene scene = new Scene(root, 700, 900);
		scene.getStylesheets().add("https://fonts.googleapis.com/css?family=Quicksand:400,700&display=swap");
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
	public void checkDirectory()
	{
		FileOutputStream output = null;
		if(!directory.exists())
		{
			directory.mkdirs();
		}
		
		try {
			if(!defaultUser.exists())
			{
				User initial = new User();
				defaultUser.createNewFile();
				output = new FileOutputStream(defaultUser);	
				byte[] content = initial.toString().getBytes();
				output.write(content);
				output.flush();
				output.close();
			}
		}catch (IOException e)
		{
			e.printStackTrace();
		} finally {
			try {
				if(output != null)
				{
					output.close();
				}
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	public User getLastUser()
	{
		User temp = null;
		FileInputStream input = null;
		try {
			input = new FileInputStream(defaultUser);
			InputStreamReader isr = new InputStreamReader(input);
			BufferedReader buffer = new BufferedReader(isr);
            StringBuilder builder = new StringBuilder();
            String text;
            
            while((text = buffer.readLine()) != null) {
                builder.append(text);
            }
            String[] users = builder.toString().split("\n");
            for(String s: users)
            {
            	String[] individual = s.split("@@");
            	temp = new User(individual[0], individual[1], Integer.parseInt(individual[2]));
            }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}

}
