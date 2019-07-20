package gateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.MainController;
import controller.ViewType;
import model.User;

public class UserTableGateway {
	
	private Connection connection;
	private static UserTableGateway instance = null;

	public UserTableGateway()
	{
		
	}
	public static UserTableGateway getInstance()
	{
		if(instance == null)
		{
			instance = new UserTableGateway();
		}
		return instance;
	}
	public ArrayList<User> getAllUsers()
	{
		ArrayList<User> users = new ArrayList<User>();
		PreparedStatement preparedStatement = null;
		try {
			String query = "SELECT * FROM \"User\" WHERE \"id\" >= ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, -1);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{ 
				users.add(new User(resultSet.getString("name")
						, resultSet.getString("image")
						, resultSet.getInt("id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	public void saveUser(User u)
	{
		PreparedStatement preparedStatement = null;
		try {
			String query = "INSERT INTO \"User\" (name, image) VALUES (?,?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, u.getName());
			preparedStatement.setString(2, u.getImgSrc());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		MainController.getInstance().changeView(ViewType.SWITCHUSER);
	}
	public Connection getConnection()
	{
		return connection;
	}
	
	public void setConnection(Connection connection)
	{
		this.connection = connection;
	}

}
