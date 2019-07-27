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
			String status = getStatus(g);
			String type = getType(g);
			img = new Image(g.getImgSrc(), 110, 110, false, false);
			ImageView imgView = new ImageView(img);
			setGraphic(imgView);
			setText(" " + g.getName() + "\n " + type + " | " + status + " | " + g.getGoal() + "\n " + g.getDescription());
			setFont(new Font(26));
			setTextFill(Color.web("#3b444b"));
		}
	}
	public String getType(Goal g)
	{
		String type = "";
		if(g.getImgSrc().equals("/resources/cash-icon.png"))
		{
			type = "Finance";
		}
		else if(g.getImgSrc().equals("/resources/fitnessicon.png"))
		{
			type = "Fitness";
		}
		else if(g.getImgSrc().equals("/resources/productivityicon.png"))
		{
			type = "Productivity";
		}
		else if(g.getImgSrc().equals("/resources/etcicon.png"))
		{
			type = "Other";
		}
		return type;
	}
	public String getStatus(Goal g)
	{
		String status = "";
		if(g.getStatus() == 0)
		{
			status = "Active";
		}
		else if(g.getStatus() == 1)
		{
			status = "Inactive";
		}
		else if(g.getStatus() == 2)
		{
			status = "Finished";
		}
		return status;
	}
}
