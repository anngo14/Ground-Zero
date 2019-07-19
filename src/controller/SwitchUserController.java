package controller;

import javafx.fxml.FXML;

public class SwitchUserController implements Controller{

	public SwitchUserController()
	{
		
	}
	@FXML
	public void addUser()
	{
		
	}
	@FXML
	public void backToHome()
	{
		MainController.getInstance().changeView(ViewType.LOGIN);
	}
}
