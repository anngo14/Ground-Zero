package controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.User;

public class CreateGoalController implements Controller, Initializable{

	private User user;
	
	public CreateGoalController(User u)
	{
		user = u;
	}
	@FXML
	public void backToHome()
	{
		MainController.getInstance().changeView(ViewType.LOGIN, Optional.of(user));
	}
	@FXML
	public void cancelAction()
	{
		MainController.getInstance().changeView(ViewType.ACTIVITY, Optional.of(user));
	}
	@FXML
	public void saveGoal()
	{
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
