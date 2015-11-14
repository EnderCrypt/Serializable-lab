package com.github.serializable.collection.data;

import java.io.Serializable;
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
	
	public Order addProduct(Product product)
	{
		products.add(product.getProductID());
		return this;
	}
	
	public String getBuyer()
	{
		return buyer;
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
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if(obj instanceof Order)
		{
			//enda som gör orders logiskt lika är deras order index.
			//två objekt kan inte ha samma id men kan ha samma buyer
			Order otherOrder = (Order) obj;
			return this.orderIndex == otherOrder.orderIndex; 
		}
		return true;
	
	}
	@Override
	public String toString()
	{
		return orderIndex + " - " + (products.toString().isEmpty() //if products are null or showing empty array
				? "Order " + orderIndex + " does not contain any products!" //if true
						: products.toString()); //if false
	}
}
