package controller;

import java.io.File;
import java.net.MalformedURLException;

import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import model.User;

public class UserFormatCell extends ListCell<User>{
	public UserFormatCell() {}
	
	@Override
	protected void updateItem(User u, boolean b) {
		super.updateItem(u, b);
		Image img = null;
		if(u == null)
		{
			setText(null);
		}
		else{
			if(!u.getImgSrc().equals("resources/rounded-512.png"))
			{
				try {
					img = new Image(new File(u.getImgSrc()).toURI().toURL().toExternalForm());
				} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
				img = new Image(u.getImgSrc(), 80, 80, false, false);
			}
			ImageView imgView = new ImageView(img);
			setGraphic(imgView);
			setText(" " + u.getName());
			setFont(new Font(26));
			setTextFill(Color.LIGHTGRAY);
		}
	}
		
}
