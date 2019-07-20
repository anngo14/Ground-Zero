package controller;

import java.io.File;

import gateway.UserTableGateway;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.User;

public class CreateUserController implements Controller{

	private FileChooser fileChoose = new FileChooser();
	
	@FXML
	StackPane panel;
	@FXML
	TextField nameText;
	@FXML
	TextField imgText;
	
	public CreateUserController()
	{
		
	}
	@FXML
	public void backToHome()
	{
		MainController.getInstance().changeView(ViewType.LOGIN);
	}
	@FXML
	public void saveUser()
	{
		User temp = new User();
		temp.setName(nameText.getText());
		if(!imgText.getText().equals(""))
		{
			temp.setImgSrc(imgText.getText());;
		}
		UserTableGateway.getInstance().saveUser(temp);
		MainController.getInstance().changeView(ViewType.SWITCHUSER);
	}
	@FXML
	public void uploadImg()
	{
		Stage stage = (Stage) panel.getScene().getWindow();
		File file = fileChoose.showOpenDialog(stage);
		if(file != null) 
		{
			imgText.setText(file.getAbsolutePath());
		}
	}
}
