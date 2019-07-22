package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Goal;
import model.User;

public class ActivityController implements Controller, Initializable{

	private User user;
	private ArrayList<Goal> goalList;
	
	@FXML
	Label nameLabel;
	@FXML
	Label activityLabel;
	@FXML
	Label zeroLabel;
	@FXML
	ListView<Goal> list;
	@FXML
	Button activeButton;
	@FXML
	Button inactiveButton;
	@FXML
	Button allButton;
	
	public ActivityController(User u)
	{
		user = u;
		goalList = new ArrayList<Goal>();
	}
	@FXML
	public void backToHome()
	{
		MainController.getInstance().changeView(ViewType.LOGIN, Optional.of(user));
	}
	@FXML
	public void showActive()
	{
		activeButton.setOpacity(1);
		inactiveButton.setOpacity(0.5);
		allButton.setOpacity(0.5);
		if(goalList.size() == 0)
		{
			zeroLabel.setOpacity(1);
			zeroLabel.setStyle("-fx-font-family: 'Quicksand', sans-serif;");
			list.setOpacity(0);
		}
	}
	@FXML
	public void showInactive()
	{
		activeButton.setOpacity(0.5);
		inactiveButton.setOpacity(1);
		allButton.setOpacity(0.5);
		if(goalList.size() == 0)
		{
			zeroLabel.setOpacity(1);
			zeroLabel.setStyle("-fx-font-family: 'Quicksand', sans-serif;");
			list.setOpacity(0);
		}
	}
	@FXML
	public void showAll()
	{
		activeButton.setOpacity(0.5);
		inactiveButton.setOpacity(0.5);
		allButton.setOpacity(1);
		if(goalList.size() == 0)
		{
			zeroLabel.setOpacity(1);
			zeroLabel.setStyle("-fx-font-family: 'Quicksand', sans-serif;");
			list.setOpacity(0);
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		nameLabel.setText(user.getName());
		activityLabel.setStyle("-fx-font-family: 'Quicksand', sans-serif; -fx-font-weight: bold;");
		showActive();
		
	}
	
}
