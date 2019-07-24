package controller;

import java.io.File;
import java.net.MalformedURLException;

import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.Goal;
import model.User;

public class GoalFormatCell extends ListCell<Goal>{

	public GoalFormatCell() {}
	
	@Override
	protected void updateItem(Goal g, boolean b) {
		super.updateItem(g, b);
		Image img = null;
		if(g == null)
		{
			setText(null);
			setGraphic(null);
		}
		else{
			img = new Image(g.getImgSrc(), 80, 80, false, false);
			ImageView imgView = new ImageView(img);
			setGraphic(imgView);
			setText(" " + g.getName() + "\n " + g.getDescription());
			setFont(new Font(26));
			setTextFill(Color.web("#3b444b"));
		}
	}
}
