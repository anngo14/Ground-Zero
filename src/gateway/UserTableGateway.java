package gateway;

import java.sql.Connection;

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
	public Connection getConnection()
	{
		return connection;
	}
	
	public void setConnection(Connection connection)
	{
		this.connection = connection;
	}

}
