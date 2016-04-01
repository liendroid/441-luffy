package network;

public class User {
	private String username;
	private String password;
	private int portNumber;
	
	public User()
	{
		portNumber = 0;
	}
	
	public User(String user, String pw)
	{
		portNumber = 0;
		username = user;
		password = pw;
	}
	
	public int getPortNumber()
	{
		return portNumber;
	}
	
	public void setPortNumber(int port)
	{
		portNumber = port;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setUsername(String user)
	{
		username = user;
	}
	
	public void setPassword(String pw)
	{
		password = pw;
	}
	
}
