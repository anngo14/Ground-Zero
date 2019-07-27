package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class GoalDetailedChangeListener implements ChangeListener<String>{

	private Button node;
	public GoalDetailedChangeListener(Node n)
	{
		node = (Button) n;
	}
	@Override
	public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
		if(!arg2.equals(arg1))
		{
			node.setDisable(false);
		}
		else
		{
			node.setDisable(true);
		}
	}

}
