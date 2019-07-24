package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import gateway.GoalTableGateway;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
		goalList.clear();
		list.getItems().clear();
		goalList = GoalTableGateway.getInstance().getActiveGoals(user);
		ObservableList<Goal> olist = FXCollections.observableArrayList(goalList);
		list.setItems(olist);
		activeButton.setOpacity(1);
		inactiveButton.setOpacity(0.5);
		allButton.setOpacity(0.5);
		if(goalList.size() == 0)
		{
			zeroLabel.setOpacity(1);
			zeroLabel.setStyle("-fx-font-family: 'Quicksand', sans-serif;");
			list.setOpacity(0);
		}
		else
		{
			zeroLabel.setOpacity(0);
			list.setOpacity(1);
		}
	}
	@FXML
	public void showInactive()
	{
		goalList.clear();
		list.getItems().clear();
		goalList = GoalTableGateway.getInstance().getInactiveGoals(user);
		ObservableList<Goal> olist = FXCollections.observableArrayList(goalList);
		list.setItems(olist);
		activeButton.setOpacity(0.5);
		inactiveButton.setOpacity(1);
		allButton.setOpacity(0.5);
		if(goalList.size() == 0)
		{
			zeroLabel.setOpacity(1);
			zeroLabel.setStyle("-fx-font-family: 'Quicksand', sans-serif;");
			list.setOpacity(0);
		}
		else
		{
			zeroLabel.setOpacity(0);
			list.setOpacity(1);
		}
	}
	@FXML
	public void showAll()
	{
		goalList.clear();
		list.getItems().clear();
		goalList = GoalTableGateway.getInstance().getAllGoals(user);
		ObservableList<Goal> olist = FXCollections.observableArrayList(goalList);
		list.setItems(olist);
		activeButton.setOpacity(0.5);
		inactiveButton.setOpacity(0.5);
		allButton.setOpacity(1);
		if(goalList.size() == 0)
		{
			zeroLabel.setOpacity(1);
			zeroLabel.setStyle("-fx-font-family: 'Quicksand', sans-serif;");
			list.setOpacity(0);
		}
		else
		{
			zeroLabel.setOpacity(0);
			list.setOpacity(1);
		}
	}
	@FXML
	public void showFinished()
	{
		
	}
	@FXML
	public void addNewGoal()
	{
		MainController.getInstance().changeView(ViewType.CREATEGOAL, Optional.of(user));
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		nameLabel.setText(user.getName());
		activityLabel.setStyle("-fx-font-family: 'Quicksand', sans-serif; -fx-font-weight: bold;");
		showActive();
		
	}
	
}
