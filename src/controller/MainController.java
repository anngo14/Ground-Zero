package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainController implements Initializable, Controller{

	private static MainController instance = null;
	
	@FXML
	Label nameLabel;
	@FXML
	StackPane panel;
	
	public MainController()
	{
		
	}
	public static MainController getInstance()
	{
		if(instance == null)
		{
			instance = new MainController();
		}
		return instance;
	}
	public void changeView(ViewType view)
	{
		String viewName = "";
		Controller controller = null;
		
		switch(view)
		{
			case SWITCHUSER:
			{
				viewName = "/view/SwitchUserView.fxml";
				controller = new SwitchUserController();
				break;
			}
			case LOGIN:
			{
				viewName = "/view/LoginView.fxml";
				controller = new MainController();
				break;
			}
		}
		try {
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource(viewName));
			loader.setController(controller);
			StackPane newPane = loader.load();
			panel.getChildren().setAll(newPane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML
	public void loginAction()
	{
		changeView(ViewType.SWITCHUSER);
	}
	@FXML
	public void quitApplication()
	{
		System.exit(0);
	}
	@FXML
	public void switchUser()
	{
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
