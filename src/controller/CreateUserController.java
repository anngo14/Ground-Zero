package controller;

import javafx.fxml.FXML;

public class CreateUserController implements Controller{

	public CreateUserController()
	{
		
	}
	@FXML
	public void backToHome()
	{
		MainController.getInstance().changeView(ViewType.LOGIN);
	}
	@FXML
	public void saveUser()
	{
		
	}
	@FXML
	public void uploadImg()
	{
		
	}
}
