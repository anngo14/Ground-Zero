package gateway;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Goal;
import model.User;

public class GoalTableGateway {

	private Connection connection;
	private static GoalTableGateway instance = null;

	public GoalTableGateway()
	{
		
	}
	public static GoalTableGateway getInstance()
	{
		if(instance == null)
		{
			instance = new GoalTableGateway();
		}
		return instance;
	}
	public ArrayList<Goal> getActiveGoals(User u)
	{
		ArrayList<Goal> active = new ArrayList<Goal>();
		PreparedStatement preparedStatement = null;
		try {
			String query = "SELECT * FROM \"Goal\" WHERE \"id\" >= ? AND \"userid\" = ? AND \"status\" = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, -1);
			preparedStatement.setInt(2, u.getId());
			preparedStatement.setInt(3, 0);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{ 
				active.add(new Goal(resultSet.getString("name")
						, resultSet.getString("description")
						, resultSet.getString("image")
						, resultSet.getInt("goal")
						, resultSet.getInt("status")
						, resultSet.getInt("count")
						, resultSet.getDate("start")
						, resultSet.getDate("end")
						, resultSet.getInt("userid")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return active;
	}
	public ArrayList<Goal> getInactiveGoals(User u)
	{
		ArrayList<Goal> inactive = new ArrayList<Goal>();
		PreparedStatement preparedStatement = null;
		try {
			String query = "SELECT * FROM \"Goal\" WHERE \"id\" >= ? AND \"userid\" = ? AND \"status\" = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, -1);
			preparedStatement.setInt(2, u.getId());
			preparedStatement.setInt(3, 1);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{ 
				inactive.add(new Goal(resultSet.getString("name")
						, resultSet.getString("description")
						, resultSet.getString("image")
						, resultSet.getInt("goal")
						, resultSet.getInt("status")
						, resultSet.getInt("count")
						, resultSet.getDate("start")
						, resultSet.getDate("end")
						, resultSet.getInt("userid")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inactive;
	}
	public ArrayList<Goal> getAllGoals(User u)
	{
		ArrayList<Goal> goals = new ArrayList<Goal>();
		PreparedStatement preparedStatement = null;
		try {
			String query = "SELECT * FROM \"Goal\" WHERE \"id\" >= ? AND \"userid\" = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, -1);
			preparedStatement.setInt(2, u.getId());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{ 
				goals.add(new Goal(resultSet.getString("name")
						, resultSet.getString("description")
						, resultSet.getString("image")
						, resultSet.getInt("goal")
						, resultSet.getInt("status")
						, resultSet.getInt("count")
						, resultSet.getDate("start")
						, resultSet.getDate("end")
						, resultSet.getInt("userid")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return goals;
	}
	public void saveGoal(User u, Goal g)
	{
		PreparedStatement preparedStatement = null;
		try {
			String query = "INSERT INTO \"Goal\" (name, description, image, goal, status, count, start, end, userid) VALUES (?,?,?,?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, u.getName());
			preparedStatement.setString(2, u.getImgSrc());
			preparedStatement.setString(3, u.getImgSrc());
			preparedStatement.setInt(4, g.getGoal());
			preparedStatement.setInt(5, g.getStatus());
			preparedStatement.setInt(6, g.getCount());
			preparedStatement.setDate(7, (Date) g.getStart());
			preparedStatement.setDate(8, (Date) g.getEnd());
			preparedStatement.setInt(9, u.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
