package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import gateway.UserTableGateway;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import model.User;

public class SwitchUserController implements Controller, Initializable{
	
	private ArrayList<User> ulist;
	private User user;
	
	@FXML
	ListView<User> list;
	@FXML
	Label zeroLabel;
	
	public SwitchUserController()
	{
		ulist = new ArrayList<User>();
	}
	public SwitchUserController(User u)
	{
		user = u;
		ulist = new ArrayList<User>();
	}
	@FXML
	public void addUser()
	{
		MainController.getInstance().changeView(ViewType.CREATEUSER, Optional.of(user));
	}
	@FXML
	public void backToHome()
	{
		MainController.getInstance().changeView(ViewType.LOGIN, Optional.of(user));
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ulist = UserTableGateway.getInstance().getAllUsers();
		ObservableList<User> olist = FXCollections.observableArrayList(ulist);

		if(ulist.size() > 0)
		{
			zeroLabel.setOpacity(0);
		}
		list.setCellFactory(new Callback<ListView<User>, ListCell<User>>() {
			@Override
			public ListCell<User> call(ListView<User> arg0) {
				// TODO Auto-generated method stub
				return new UserFormatCell();
			}
			
		});
		list.setOnMousePressed(new UserMouseEventHandler(list));
		list.setItems(olist);
	}
}
