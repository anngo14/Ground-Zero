package controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Goal;
import model.User;

public class MainController implements Initializable, Controller{

	private static MainController instance = null;
	private User user;
	
	@FXML
	Label nameLabel;
	@FXML
	StackPane panel;
	@FXML
	ImageView userImg;
	
	public MainController()
	{
		
	}
	public MainController(User u)
	{
		setUser(u);
	}
	public static MainController getInstance()
	{
		if(instance == null)
		{
			instance = new MainController();
		}
		return instance;
	}
	public void changeView(ViewType view, Optional<User> user, Optional<Goal> goal)
	{
		String viewName = "";
		Controller controller = null;
		
		switch(view)
		{
			case SWITCHUSER:
			{
				viewName = "/view/SwitchUserView.fxml";
				controller = new SwitchUserController(user.get());
				break;
			}
			case LOGIN:
			{
				viewName = "/view/LoginView.fxml";
				controller = new MainController(user.get());
				break;
			}
			case CREATEUSER:
			{
				viewName = "/view/CreateUserView.fxml";
				controller = new CreateUserController(user.get());
				break;
			}
			case ACTIVITY:
			{
				viewName = "/view/ActivityView.fxml";
				controller = new ActivityController(user.get());
				break;
			}
			case CREATEGOAL:
			{
				viewName = "/view/CreateGoalView.fxml";
				controller = new CreateGoalController(user.get());
				break;
			}
			case GOALDETAILED:
			{
				viewName = "/view/GoalDetailedView.fxml";
				controller = new GoalDetailedController(user.get(), goal.get());
				break;
			}
		}
		try {
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource(viewName));
			loader.setController(controller);
			StackPane newPane = loader.load();
			panel.getChildren().setAll(newPane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML
	public void loginAction()
	{
		changeView(ViewType.ACTIVITY, Optional.of(user), Optional.empty());
	}
	@FXML
	public void quitApplication()
	{
		System.exit(0);
	}
	@FXML
	public void switchUser()
	{
		changeView(ViewType.SWITCHUSER, Optional.of(user), Optional.empty());
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		nameLabel.setText(user.getName());
		if(!user.getImgSrc().equals("resources/rounded-512.png"))
		{
			try {
				userImg.setImage(new Image(new File(user.getImgSrc()).toURI().toURL().toExternalForm()));
			} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}
