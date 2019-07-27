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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
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
		MainController.getInstance().changeView(ViewType.LOGIN, Optional.of(user), Optional.empty());
	}
	@FXML
	public void showActive()
	{
		list.getItems().clear();
		goalList = GoalTableGateway.getInstance().getActiveGoals(user);
		setUpScene();
		ObservableList<Goal> olist = FXCollections.observableArrayList(goalList);
		list.setItems(olist);
		activeButton.setOpacity(1);
		inactiveButton.setOpacity(0.5);
		allButton.setOpacity(0.5);
	}
	@FXML
	public void showInactive()
	{
		list.getItems().clear();
		goalList = GoalTableGateway.getInstance().getInactiveGoals(user);
		setUpScene();
		ObservableList<Goal> olist = FXCollections.observableArrayList(goalList);
		list.setItems(olist);
		activeButton.setOpacity(0.5);
		inactiveButton.setOpacity(1);
		allButton.setOpacity(0.5);
		setUpScene();
	}
	@FXML
	public void showAll()
	{
		list.getItems().clear();
		goalList = GoalTableGateway.getInstance().getAllGoals(user);
		setUpScene();
		ObservableList<Goal> olist = FXCollections.observableArrayList(goalList);
		list.setItems(olist);
		activeButton.setOpacity(0.5);
		inactiveButton.setOpacity(0.5);
		allButton.setOpacity(1);
	}
	public void setUpScene()
	{
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
		MainController.getInstance().changeView(ViewType.CREATEGOAL, Optional.of(user), Optional.empty());
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		nameLabel.setText(user.getName());
		list.setCellFactory(new Callback<ListView<Goal>, ListCell<Goal>>() {

			@Override
			public ListCell<Goal> call(ListView<Goal> arg0) {
				// TODO Auto-generated method stub
				return new GoalFormatCell();
			}
			
		});
		list.setStyle("-fx-control-inner-background: 10%;");
		list.setOnMousePressed(new GoalMouseEventHandler(list, user));
		activityLabel.setStyle("-fx-font-family: 'Quicksand', sans-serif; -fx-font-weight: bold;");
		showActive();
		
	}
	
}
