package com.github.serializable.collection.data;

import java.io.Serializable;

/**
 * this is the public user object that will be saved/loaded by the application
 */
public class User implements Serializable
{
	private static final long serialVersionUID = -4836684853320153893L;
	private String username;
	private String password;
	private String eMail;

	public User(String username, String password, String eMail)
	{
		this.username = username;
		this.password = password;
		this.eMail = eMail;
	}

	public String getUsername()
	{
		return username;
	}

	public String getPassword()
	{
		return password;
	}

	public String geteMail()
	{
		return eMail;
	}

	@Override
	public boolean equals(Object other)
	{
		if (this == other)
		{
			return true;
		}
		if (other instanceof User)
		{
			User otherUser = (User) other;
			return username.equals(otherUser.username) && eMail.equals(otherUser.eMail);
		}
		return false;
	}

	@Override
	public int hashCode()
	{
		int result = 1;
		result += username.hashCode() * 37;
		result += eMail.hashCode() * 37;

		return result;
	}

}
