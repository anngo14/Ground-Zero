package controller;

import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Goal;
import model.User;

public class GoalDetailedController implements Initializable, Controller{

	private User user; 
	private Goal goal;
	
	@FXML
	Label nameLabel;
	@FXML
	ComboBox<String> statusCombo;
	@FXML
	Label startGoal;
	@FXML
	Label endGoal;
	@FXML
	TextField countText;
	@FXML
	Button updateButton;
	@FXML
	LineChart<Date, Integer> line;
	@FXML
	ImageView image;
	@FXML
	Button saveButton;
	
	public GoalDetailedController(User u, Goal g)
	{
		user = u;
		goal = g;
	}
	@FXML
	public void backToActivity()
	{
		MainController.getInstance().changeView(ViewType.ACTIVITY, Optional.of(user), Optional.empty());
	}
	@FXML
	public void updateCount()
	{
		
	}
	@FXML
	public void deleteAction()
	{
		
	}
	@FXML
	public void saveAction()
	{
		
	}
	public String getStatus()
	{		
		String output = "";
		switch(goal.getStatus())
		{
			case 0:
				output = "Active";
				break;
			case 1:
				output = "Inactive";
				break;
			case 2:
				output = "Finished";
				break;
		}
		return output;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		String[] options = {"Active", "Inactive", "Finished"};
		statusCombo.getItems().addAll(options);
		nameLabel.setText(goal.getName());
		startGoal.setText(goal.getStart().toString());
		endGoal.setText(goal.getEnd().toString());
		Image img = new Image(goal.getImgSrc(), 128, 128, false, false);
		image.setImage(img);
		statusCombo.setValue(getStatus());
		
	}

}
