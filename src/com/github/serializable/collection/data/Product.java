package com.github.serializable.collection.data;

import java.io.Serializable;

/**
 * this is the public product object that will be saved/loaded by the application
 */
public class Product implements Serializable
{
	private static final long serialVersionUID = 525630403812799901L;
	
	private String name;
	private int productID;
	
	public Product(String name, int productID)
	{
		this.name = name;
		this.productID = productID;
	}
	public String toString()
	{
		return "Product: "+name+" ("+productID+")";
	}

}
