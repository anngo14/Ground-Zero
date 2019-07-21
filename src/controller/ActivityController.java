package controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.User;

public class ActivityController implements Controller, Initializable{

	private User user;
	
	@FXML
	private Label nameLabel;
	@FXML
	private Label activityLabel;
	
	public ActivityController(User u)
	{
		user = u;
	}
	@FXML
	public void backToHome()
	{
		MainController.getInstance().changeView(ViewType.LOGIN, Optional.of(user));
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		nameLabel.setText(user.getName());
		activityLabel.setStyle("-fx-font-family: 'Quicksand', sans-serif; -fx-font-weight: bold;");
	}
	
}
