package controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import gateway.DatesTableGateway;
import gateway.GoalTableGateway;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
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
	private ArrayList<Date> dList;
	private double count;
	
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
	LineChart<?,?> line;
	@FXML
	ImageView image;
	@FXML
	Button saveButton;
    @FXML
    CategoryAxis x;
    @FXML
    NumberAxis y;
	
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
		String text = countText.getText().replaceAll("[a-zA-Z]", "");
		if(text.equals(""))
		{
			count = 0;
		}
		else 
		{
			count = Double.parseDouble(text);
		}
		DatesTableGateway.getInstance().saveUpdate(goal, goal.getCount()+count, java.sql.Date.valueOf(NOW_LOCAL_DATE()));
		dList = DatesTableGateway.getInstance().getDates(goal);
		Series series = new Series();
		series.setName("Today's Progress");
		for(Date d: dList)
		{
			series.getData().add(new Data(d.toString(), DatesTableGateway.getInstance().getCount(d, goal)));
		}
		line.getData().add(series);

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
		String text = countText.getText().replaceAll("[a-zA-Z]", "");
		if(text.equals(""))
		{
			count = 0;
		}
		else 
		{
			count = Double.parseDouble(text);
		}
		double temp = goal.getCount() + count;
		if(temp < 0)
		{
			temp = 0;
		}
		goal.setStatus(getStatusInt());
		goal.setCount(temp);
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
		dList = DatesTableGateway.getInstance().getDates(goal);
		String[] options = {"Active", "Inactive", "Finished"};
		statusCombo.getItems().addAll(options);
		nameLabel.setText(goal.getName());
		startGoal.setText(goal.getStart().toString());
		endGoal.setText(goal.getEnd().toString());
		Image img = new Image(goal.getImgSrc(), 128, 128, false, false);
		image.setImage(img);
		statusCombo.setValue(getStatus());
		
		if(goal.getStatus() == 2)
		{
			updateButton.setDisable(true);
			countText.setDisable(true);
			statusCombo.setDisable(true);
			saveButton.setDisable(true);
		}
		countText.textProperty().addListener(new CreateUserChangeListener(saveButton));
		statusCombo.valueProperty().addListener(new GoalDetailedChangeListener(saveButton));
		Series series = new Series();
		series.setName("Goal Progress");
		for(Date d: dList)
		{
			series.getData().add(new Data(d.toString(), DatesTableGateway.getInstance().getCount(d, goal)));
		}
		//Test Data
		/*series.getData().add(new Data("2019-29-07", 4));
		series.getData().add(new Data("2019-31-07", 7));
		series.getData().add(new Data("2019-02-08", 1));
		series.getData().add(new Data("2019-12-08", 12));*/

		line.getData().add(series);
		
	}

}
