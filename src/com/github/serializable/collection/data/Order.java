package com.github.serializable.collection.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * this is the public order object that will be saved/loaded by the application
 */

public class Order implements Serializable
{
	private static final long serialVersionUID = -3520138447015383264L;
	private String buyer;
	private int orderIndex;
	Set<Integer> products = new HashSet<>();
	
	public Order(User user)
	{
		buyer = user.getUsername();
		orderIndex = user.newOrderId();
	}
	public Order addProduct(List<Product> productList)
	{
		for (Product product : productList)
		{
			addProduct(product);
		}
		return this;
	}
	public String getBuyer()
	{
		return buyer;
	}
	public Order addProduct(Product product)
	{
		products.add(product.getProductID());
		return this;
	}
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + orderIndex;
		result = prime * result + ((buyer == null) ? 0 : buyer.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Order other = (Order) obj;
		if (orderIndex != other.orderIndex) return false;
		if (buyer == null)
		{
			if (other.buyer != null) return false;
		}
		else if (!buyer.equals(other.buyer)) return false;
		return true;
	}
}
