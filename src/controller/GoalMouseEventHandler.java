package controller;

import java.util.Optional;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import model.Goal;
import model.User;

public class GoalMouseEventHandler implements EventHandler<MouseEvent>{

	private ListView<Goal> node;
	private User user;
	public GoalMouseEventHandler(Node n, User u)
	{
		node = (ListView) n;
		user = u;
	}
	@Override
	public void handle(MouseEvent event) {
		if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
			Goal select = node.getSelectionModel().getSelectedItem();
			MainController.getInstance().changeView(ViewType.GOALDETAILED, Optional.of(user), Optional.of(select));
		}		
	}

}
