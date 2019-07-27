package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import model.User;

public class UserMouseEventHandler implements EventHandler<MouseEvent>{

	private ListView<User> node;
	private FileOutputStream output;
    private final File defaultUser = new File("C:\\GroundZero\\Users\\lastUser.txt");
	
	public UserMouseEventHandler(Node n)
	{
		node = (ListView) n;
	}
	@Override
	public void handle(MouseEvent event) {
		if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
			User select = node.getSelectionModel().getSelectedItem();
			MainController.getInstance().changeView(ViewType.LOGIN, Optional.of(select), Optional.empty());
			try {
				output = new FileOutputStream(defaultUser);
				byte[] content = select.toString().getBytes();
				output.write(content);
				output.flush();
				output.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
		}		
	}

}
