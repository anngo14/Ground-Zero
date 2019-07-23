package controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import model.User;

public class CreateGoalController implements Controller, Initializable{

	private User user;
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
		String[] typeOptions = {"Finance", "Fitness", "Productivity", "Other"};
		String[] statusOptions = {"Active", "Inactive"};
		typeCombo.getItems().addAll(typeOptions);
		statusCombo.getItems().addAll(statusOptions);
		typeCombo.valueProperty().addListener(new CreateGoalChangeListener(image));
		startDate.setValue(NOW_LOCAL_DATE());
		endDate.setValue(NOW_LOCAL_DATE());
		nameText.focusedProperty().addListener(new NodeChangeListener(nameText));
		goalText.focusedProperty().addListener(new NodeChangeListener(goalText));
		descriptionText.focusedProperty().addListener(new NodeChangeListener(descriptionText));
	}

}
