package com.github.serializable.service;

import java.io.Serializable;

/**
 * this is the public user object that will be saved/loaded by the application
 */
public class User extends Id implements Serializable
{
	private static final long serialVersionUID = -4836684853320153893L;
	private String username;
	private String password;
	private String email;
	private int userId;

	User(String username, String password, String email)
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
	
	public int getUserId()
	{
		return userId;
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
			if(super.equals(otherUser))
			{
				return true;//returns true if same ID as by Id.class
			}
			return getUsername().equalsIgnoreCase(otherUser.getUsername()) 
					|| getEmail().equalsIgnoreCase(otherUser.getEmail());
		}
		return false;
	}

	
	@Override
	public String toString()
	{
		return username + ":" + email;
	}

}
