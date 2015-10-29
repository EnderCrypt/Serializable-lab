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
	private int orderId;
	
	public Order(int id)
	{
		orderId = id;
	}
	public Order addProducts(List<Product> productList)
	{
		products.addAll(productList);
		return this;
	}
	public Order addProduct(Product product)
	{
		products.add(product);
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
	public boolean equals(Object obj)
	{
		if(this == obj)
		{
			return true;
		}
		if(obj == null)
		{
			return false;
		}
		if(obj instanceof Order)
		{
			Order otherOrder = (Order) obj;
			return this.getOrderId() == otherOrder.getOrderId();
		}
		return false;
	}
	
	@Override
	public String toString()
	{
		return orderId+" - "+products.toString(); 
	}
}
