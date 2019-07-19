package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class MainController implements Initializable{

	private static MainController instance = null;
	
	@FXML
	Label nameLabel;
	
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
	@FXML
	public void loginAction()
	{
		
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
