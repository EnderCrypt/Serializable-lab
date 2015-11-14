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
	private String email;
	private int orderIndex = 0;

	public User(String username, String password, String email)
	{
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public String getUsername()
	{
		return username;
	}

	public String getPassword()
	{
		return password;
	}

	public String getEmail()
	{
		return email;
	}
	
	int newOrderId()
	{
		return ++orderIndex;
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
			return username.equals(otherUser.username) || email.equals(otherUser.email);
		}
		return false;
	}

	@Override
	public int hashCode()
	{
		int result = 1;
		result += username.hashCode() * 37;

		return result;
	}
	
	@Override
	public String toString()
	{
		return username + ":" + email;
	}

}
