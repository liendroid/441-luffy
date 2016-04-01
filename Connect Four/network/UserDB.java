package network;

import network.User;

public class UserDB {

	private User[] db;
	
	public UserDB()
	{
		db = new User[4];
		db[0] = new User("Dan", "sucks");
		db[1] = new User("Cat", "cat");
		db[2] = new User("Food", "0000");
		db[3] = new User("Bloop", "1111");
	}
	
	public User[] getUserDB()
	{
		return db;
	}
}
