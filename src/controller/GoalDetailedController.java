package controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import gateway.DatesTableGateway;
import gateway.GoalTableGateway;
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
	
	private int count;
	
	public GoalDetailedController(User u, Goal g)
	{
		count = 0;
		user = u;
		goal = g;
	}
	public static final LocalDate NOW_LOCAL_DATE (){
		String date = new SimpleDateFormat("MM-dd-yyyy").format(Calendar.getInstance().getTime());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		LocalDate localDate = LocalDate.parse(date, formatter);
		return localDate;
	}
	@FXML
	public void backToActivity()
	{
		MainController.getInstance().changeView(ViewType.ACTIVITY, Optional.of(user), Optional.empty());
	}
	@FXML
	public void updateCount()
	{
		String text = countText.getText().replaceAll("\\D", "");
		if(text.equals(""))
		{
			count = 0;
		}
		else 
		{
			count = Integer.parseInt(text);
		}
		DatesTableGateway.getInstance().saveUpdate(goal, count, java.sql.Date.valueOf(NOW_LOCAL_DATE()));
	}
	@FXML
	public void deleteAction()
	{
		DatesTableGateway.getInstance().deleteDates(goal);
		GoalTableGateway.getInstance().deleteGoal(goal);
		MainController.getInstance().changeView(ViewType.ACTIVITY, Optional.of(user), Optional.empty());
	}
	@FXML
	public void saveAction()
	{
		String text = countText.getText().replaceAll("\\D", "");
		if(text.equals(""))
		{
			count = 0;
		}
		else 
		{
			count = Integer.parseInt(text);
		}
		goal.setStatus(getStatusInt());
		goal.setCount(count);
		GoalTableGateway.getInstance().updateGoal(goal);
		MainController.getInstance().changeView(ViewType.ACTIVITY, Optional.of(user), Optional.empty());
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
	public int getStatusInt()
	{
		int temp = 0;
		switch(statusCombo.getValue())
		{
			case "Active":
				temp = 0;
				break;
			case "Inactive":
				temp = 1;
				break;
			case "Finished":
				temp = 2;
				break;
		}
		return temp;
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
		countText.textProperty().addListener(new CreateUserChangeListener(saveButton));
		statusCombo.valueProperty().addListener(new GoalDetailedChangeListener(saveButton));
		
	}

}
