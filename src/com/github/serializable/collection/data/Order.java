package com.github.serializable.collection.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * this is the public order object that will be saved/loaded by the application
 */
public class Order implements Serializable
{
	private static final long serialVersionUID = -3520138447015383264L;
	private List<Product> products = new ArrayList<Product>();
	private User user;
	private Product product;
	private int orderId;
	
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
	public int getOrderId()
	{
		return orderId;
	}
	
	@Override
	public int hashCode()
	{
		return orderId * 37;
	}
	
	@Override
	public String toString()
	{
		return ""+ orderId;
	}
}
