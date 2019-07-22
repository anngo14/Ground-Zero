package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class CreateUserChangeListener implements ChangeListener<String>{

	private Button node;
	
	public CreateUserChangeListener(Node n)
	{
		node = (Button) n;
	}
	@Override
	public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
		if(arg2.matches("^\\s*$"))
		{
			node.setDisable(true);
		}
		else
		{
			node.setDisable(false);
		}
	}

}
