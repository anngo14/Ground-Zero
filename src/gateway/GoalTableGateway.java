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
			//String query = "SELECT * FROM \"Goal\" WHERE \"id\" >= ? AND \"userid\" = ? AND \"status\" = ?";
			String query = "SELECT * FROM Goal WHERE id >= ? AND userid = ? AND status = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, -1);
			preparedStatement.setInt(2, u.getId());
			preparedStatement.setInt(3, 0);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{ 
				Goal temp = new Goal(resultSet.getString("name")
						, resultSet.getString("description")
						, resultSet.getString("image")
						, resultSet.getString("goal")
						, resultSet.getInt("status")
						, resultSet.getInt("count")
						, resultSet.getDate("startgoal")
						, resultSet.getDate("endgoal")
						, resultSet.getInt("userid"));
				temp.setId(getGoalId(temp, u));
				active.add(temp);
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
			//String query = "SELECT * FROM \"Goal\" WHERE \"id\" >= ? AND \"userid\" = ? AND \"status\" = ?";
			String query = "SELECT * FROM Goal WHERE id >= ? AND userid = ? AND status = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, -1);
			preparedStatement.setInt(2, u.getId());
			preparedStatement.setInt(3, 1);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{ 
				Goal temp = new Goal(resultSet.getString("name")
						, resultSet.getString("description")
						, resultSet.getString("image")
						, resultSet.getString("goal")
						, resultSet.getInt("status")
						, resultSet.getInt("count")
						, resultSet.getDate("startgoal")
						, resultSet.getDate("endgoal")
						, resultSet.getInt("userid"));
				temp.setId(getGoalId(temp, u));
				inactive.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inactive;
	}
	public ArrayList<Goal> getCompletedGoals(User u)
	{
		ArrayList<Goal> complete = new ArrayList<Goal>();
		PreparedStatement preparedStatement = null;
		try {
			//String query = "SELECT * FROM \"Goal\" WHERE \"id\" >= ? AND \"userid\" = ? AND \"status\" = ?";
			String query = "SELECT * FROM Goal WHERE id >= ? AND userid = ? AND status = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, -1);
			preparedStatement.setInt(2, u.getId());
			preparedStatement.setInt(3, 2);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{ 
				Goal temp = new Goal(resultSet.getString("name")
						, resultSet.getString("description")
						, resultSet.getString("image")
						, resultSet.getString("goal")
						, resultSet.getInt("status")
						, resultSet.getInt("count")
						, resultSet.getDate("startgoal")
						, resultSet.getDate("endgoal")
						, resultSet.getInt("userid"));
				temp.setId(getGoalId(temp, u));
				complete.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return complete;
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
				Goal temp = new Goal(resultSet.getString("name")
						, resultSet.getString("description")
						, resultSet.getString("image")
						, resultSet.getString("goal")
						, resultSet.getInt("status")
						, resultSet.getDouble("count")
						, resultSet.getDate("startgoal")
						, resultSet.getDate("endgoal")
						, resultSet.getInt("userid"));
				temp.setId(getGoalId(temp, u));
				goals.add(temp);
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
			//String query = "INSERT INTO \"Goal\" (name, description, image, goal, status, count, startgoal, endgoal, userid) VALUES (?,?,?,?,?,?,?,?,?)";
			String query = "INSERT INTO Goal (name, description, image, goal, status, count, startgoal, endgoal, userid) VALUES (?,?,?,?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, g.getName());
			preparedStatement.setString(2, g.getDescription());
			preparedStatement.setString(3, g.getImgSrc());
			preparedStatement.setString(4, g.getGoal());
			preparedStatement.setInt(5, g.getStatus());
			preparedStatement.setDouble(6, g.getCount());
			preparedStatement.setDate(7, (Date) g.getStart());
			preparedStatement.setDate(8, (Date) g.getEnd());
			preparedStatement.setInt(9, u.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public int getGoalId(Goal g, User u)
	{
		int goalid = 0;
		PreparedStatement preparedStatement = null;
		try {
			String query = "SELECT \"id\" FROM \"Goal\" WHERE \"name\" = ? AND \"description\" = ? AND \"image\" = ? AND \"status\" = ? AND \"goal\" = ? AND \"endgoal\" = ? AND \"userid\" = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, g.getName());
			preparedStatement.setString(2, g.getDescription());
			preparedStatement.setString(3, g.getImgSrc());
			preparedStatement.setInt(4, g.getStatus());
			preparedStatement.setString(5, g.getGoal());
			preparedStatement.setDate(6, (Date) g.getEnd());
			preparedStatement.setInt(7, u.getId());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{ 
				goalid = resultSet.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return goalid;
	}
	public void deleteGoal(Goal g)
	{
		PreparedStatement preparedStatement = null;
		try {
			//String query = "DELETE FROM \"Goal\" WHERE \"id\" = ?";
			String query = "DELETE FROM Goal WHERE id = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, g.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void updateGoal(Goal g)
	{
		PreparedStatement preparedStatement = null;
		try {
			//String query = "UPDATE \"Goal\" SET \"status\" = ?, \"count\" = ? WHERE \"id\" = ?";
			String query = "UPDATE Goal SET status = ?, count = ? WHERE id = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, g.getStatus());
			preparedStatement.setDouble(2, g.getCount());
			preparedStatement.setInt(3, g.getId());
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
