package com.github.serializable.collection.data;

import java.io.Serializable;
import java.util.List;

/**
 * this is the public order object that will be saved/loaded by the application
 */
public class Order implements Serializable
{
	private static final long serialVersionUID = -3520138447015383264L;
	private  List<Product> products;
	
	public Order()
	{
		
	}
	public Order add(List<Product> list)
	{
		products.addAll(list);
		return this;
	}
	public Order add(Product prod)
	{
		products.add(prod);
		return this;
	}
}
