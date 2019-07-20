package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import gateway.UserTableGateway;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.User;

public class SwitchUserController implements Controller, Initializable{
	
	private ArrayList<User> ulist;
	@FXML
	ListView<User> list;
	@FXML
	Label zeroLabel;
	
	public SwitchUserController()
	{
		ulist = new ArrayList<User>();
	}
	@FXML
	public void addUser()
	{
		MainController.getInstance().changeView(ViewType.CREATEUSER);
	}
	@FXML
	public void backToHome()
	{
		MainController.getInstance().changeView(ViewType.LOGIN);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ulist = UserTableGateway.getInstance().getAllUsers();
		ObservableList<User> olist = FXCollections.observableArrayList(ulist);

		if(ulist.size() > 0)
		{
			zeroLabel.setOpacity(0);
		}
		list.setItems(olist);
	}
}
