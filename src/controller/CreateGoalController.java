package controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import gateway.GoalTableGateway;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import model.Goal;
import model.User;

public class CreateGoalController implements Controller, Initializable{

	private User user;
	private final String[] typeOptions = {"Finance", "Fitness", "Productivity", "Other"};
	private final String[] statusOptions = {"Active", "Inactive"};
	@FXML
	ComboBox<String> typeCombo;
	@FXML
	ComboBox<String> statusCombo;
	@FXML
	ImageView image;
	@FXML
	DatePicker startDate;
	@FXML
	DatePicker endDate;
	@FXML
	TextField nameText;
	@FXML
	TextField goalText;
	@FXML
	TextArea descriptionText;
	@FXML
	Button saveButton;
	
	public CreateGoalController(User u)
	{
		user = u;
	}
	public static final LocalDate NOW_LOCAL_DATE (){
		String date = new SimpleDateFormat("MM-dd-yyyy").format(Calendar.getInstance().getTime());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		LocalDate localDate = LocalDate.parse(date, formatter);
		return localDate;
	}
	@FXML
	public void backToHome()
	{
		MainController.getInstance().changeView(ViewType.LOGIN, Optional.of(user), Optional.empty());
	}
	@FXML
	public void cancelAction()
	{
		MainController.getInstance().changeView(ViewType.ACTIVITY, Optional.of(user), Optional.empty());
	}
	@FXML
	public void saveGoal()
	{
		int status = getStatus();
		String image = getImage();
		Date start = java.sql.Date.valueOf(startDate.getValue());
		Date end = java.sql.Date.valueOf(endDate.getValue());

		Goal temp = new Goal(nameText.getText(), descriptionText.getText(), image, goalText.getText(), status, 0, start, end, user.getId());
		GoalTableGateway.getInstance().saveGoal(user, temp);
		MainController.getInstance().changeView(ViewType.ACTIVITY, Optional.of(user), Optional.empty());
	}
	public int getStatus()
	{
		int status = 0;
		if(statusCombo.getValue().equals("Active"))
		{
			status = 0;
		}
		else if(statusCombo.getValue().equals("Inactive"))
		{
			status = 1;
		}
		return status;
	}
	public String getImage()
	{
		String image = "";
		if(typeCombo.getValue().equals("Finance"))
		{
			image = "/resources/cash-icon.png";
		}
		else if(typeCombo.getValue().equals("Fitness"))
		{
			image = "/resources/fitnessicon.png";
		}
		else if(typeCombo.getValue().equals("Productivity"))
		{
			image = "/resources/productivityicon.png";
		}
		else if(typeCombo.getValue().equals("Other"))
		{
			image = "/resources/etcicon.png";
		}
		return image;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		typeCombo.getItems().addAll(typeOptions);
		statusCombo.getItems().addAll(statusOptions);
		startDate.setValue(NOW_LOCAL_DATE());
		endDate.setValue(NOW_LOCAL_DATE());
		typeCombo.valueProperty().addListener(new CreateGoalChangeListener(image));
		nameText.focusedProperty().addListener(new NodeChangeListener(nameText));
		goalText.focusedProperty().addListener(new NodeChangeListener(goalText));
		descriptionText.focusedProperty().addListener(new NodeChangeListener(descriptionText));
		nameText.textProperty().addListener(new CreateUserChangeListener(saveButton));
	}

}
