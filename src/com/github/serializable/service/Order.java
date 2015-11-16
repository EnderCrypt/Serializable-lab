package com.github.serializable.service;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * this is the public order object that will be saved/loaded by the application
 */

public class Order extends Id implements Serializable
{
	private static final long serialVersionUID = -3520138447015383264L;
	private int buyerId;
	Set<Integer> products = new HashSet<>();
	
	public Order addProduct(List<Product> productList)
	{
		for (Product product : productList)
		{
			addProduct(product);
		}
		return this;
	}
	
	public Order addProduct(Product product)
	{
		products.add(product.getId());
		return this;
	}
	
	public int getBuyerId()
	{
		return buyerId;
	}
	

	@Override
	public String toString()
	{
		return getId() + " - " + (products.toString().isEmpty() //if products are null or showing empty array
				? "Order " + getId() + " does not contain any products!" //if true
						: products.toString()); //if false
	}
}
