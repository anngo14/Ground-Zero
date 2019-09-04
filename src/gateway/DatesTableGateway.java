package gateway;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Goal;

public class DatesTableGateway {

	private Connection connection;
	private static DatesTableGateway instance = null;
	
	public DatesTableGateway()
	{
		
	}
	public static DatesTableGateway getInstance()
	{
		if(instance == null)
		{
			instance = new DatesTableGateway();
		}
		return instance;
	}
	public ArrayList<Date> getDates(Goal g)
	{
		ArrayList<Date> dates = new ArrayList<Date>();
		PreparedStatement preparedStatement = null;
		try {
			//String query = "SELECT * FROM \"Dates\" WHERE \"goalid\" = ?";
			String query = "SELECT * FROM Dates WHERE goalid = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, g.getId());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{ 
				dates.add(resultSet.getDate("updategoal"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dates;
	}
	public void saveUpdate(Goal g, double c, java.util.Date start)
	{
		System.out.println(g);
		System.out.println("" + c);
		System.out.println("" + start);
		PreparedStatement preparedStatement = null;
		try {
			//String query = "INSERT INTO Dates (count, update, goalid) VALUES (?,?,?)";
			String query = "INSERT INTO Dates (count, updategoal, goalid) VALUES (?,?,?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setDouble(1, c);
			preparedStatement.setDate(2, (Date) start);
			preparedStatement.setInt(3, g.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public double getCount(java.util.Date d, Goal g)
	{
		double count = 0;
		PreparedStatement preparedStatement = null;
		try {
			//String query = "SELECT \"count\" FROM \"Dates\" WHERE \"update\" = ? AND \"goalid\" = ?";
			String query = "SELECT count FROM Dates WHERE updategoal = ? AND goalid = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setDate(1, (Date) d);
			preparedStatement.setInt(2, g.getId());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{ 
				count = resultSet.getDouble("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	public void deleteDates(Goal g)
	{
		PreparedStatement preparedStatement = null;
		try {
			//String query = "DELETE FROM \"Dates\" WHERE \"goalid\" = ?";
			String query = "DELETE FROM Dates WHERE goalid = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, g.getId());
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
