package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CreateGoalChangeListener implements ChangeListener<String>{

	private ImageView image;
	public CreateGoalChangeListener(ImageView img) {
		image = img;
	}
	
	@Override
	public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
		if(arg2.equals("Finance"))
		{
			image.setImage(new Image("/resources/cash-icon.png"));
		}
		else if(arg2.equals("Fitness"))
		{
			image.setImage(new Image("/resources/fitnessicon.png"));
		}
		else if(arg2.equals("Productivity"))
		{
			image.setImage(new Image("/resources/productivityicon.png"));
		}
		else
		{
			image.setImage(new Image("/resources/etcicon.png"));
		}
	}

}
