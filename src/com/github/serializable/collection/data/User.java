package com.github.serializable.collection.data;

import java.io.Serializable;

/**
 * this is the public user object that will be saved/loaded by the application
 */
public class User implements Serializable
{
	private static final long serialVersionUID = -4836684853320153893L;
	
	private String name;
	private String password;
	
	public User(String name, String password)
	{
		this.name = name;
		this.password = password;
	}
	
	public String toString()
	{
		return "User name: " + this.name + "User Password " + this.password;
	}

}
