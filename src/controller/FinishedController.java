package controller;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import gateway.GoalTableGateway;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import model.Goal;
import model.User;

public class FinishedController implements Initializable, Controller{

	private User user;
	private ArrayList<Goal> goalList;
	
	@FXML
	Label nameLabel;
	@FXML
	Label completedLabel;
	@FXML
	ImageView image;
	@FXML
	ListView<Goal> list;
	@FXML
	Label zeroLabel;
	
	public FinishedController(User u)
	{
		user = u;
		goalList = new ArrayList<Goal>();
	}
	public void showComplete()
	{
		list.getItems().clear();
		goalList = GoalTableGateway.getInstance().getCompletedGoals(user);
		setUpScene();
		ObservableList<Goal> olist = FXCollections.observableArrayList(goalList);
		list.setItems(olist);
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
	public void backToHome()
	{
		MainController.getInstance().changeView(ViewType.LOGIN, Optional.of(user), Optional.empty());
	}
	@FXML
	public void backToActivity()
	{
		MainController.getInstance().changeView(ViewType.ACTIVITY, Optional.of(user), Optional.empty());
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		nameLabel.setText(user.getName());
		if(!user.getImgSrc().equals("resources/rounded-512.png"))
		{
			try {
				image.setImage(new Image(new File(user.getImgSrc()).toURI().toURL().toExternalForm()));
			} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		list.setCellFactory(new Callback<ListView<Goal>, ListCell<Goal>>() {

			@Override
			public ListCell<Goal> call(ListView<Goal> arg0) {
				// TODO Auto-generated method stub
				return new GoalFormatCell();
			}
			
		});
		list.setStyle("-fx-control-inner-background: 10%;");
		list.setOnMousePressed(new GoalMouseEventHandler(list, user));
		completedLabel.setStyle("-fx-font-family: 'Quicksand', sans-serif; -fx-font-weight: bold;");
		showComplete();
	}

}
