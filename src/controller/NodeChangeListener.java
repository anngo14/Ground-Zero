package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;

public class NodeChangeListener implements ChangeListener<Boolean>{
	
	private Node node;
	public NodeChangeListener(Node n)
	{
		node = n;
	}

	@Override
	public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
		if(arg2)
		{
			node.setOpacity(1);
		}
		else
		{
			node.setOpacity(0.5);
		}
	}

}
