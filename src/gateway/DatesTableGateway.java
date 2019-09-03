package gateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

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
				dates.add(resultSet.getDate("update"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dates;
	}
	public void saveUpdate(Goal g, double c, Date d)
	{
		PreparedStatement preparedStatement = null;
		try {
			//String query = "INSERT INTO \"Dates\" (count, update, goalid) VALUES (?,?,?)";
			String query = "INSERT INTO Dates (count, update, goalid) VALUES (?,?,?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setDouble(1, c);
			preparedStatement.setDate(2, (java.sql.Date) d);
			preparedStatement.setInt(3, g.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public double getCount(Date d, Goal g)
	{
		double count = 0;
		PreparedStatement preparedStatement = null;
		try {
			//String query = "SELECT \"count\" FROM \"Dates\" WHERE \"update\" = ? AND \"goalid\" = ?";
			String query = "SELECT count FROM Dates WHERE update = ? AND goalid = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setDate(1, (java.sql.Date) d);
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
