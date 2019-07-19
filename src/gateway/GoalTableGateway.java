package gateway;

import java.sql.Connection;

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
	public Connection getConnection()
	{
		return connection;
	}
	
	public void setConnection(Connection connection)
	{
		this.connection = connection;
	}

}
