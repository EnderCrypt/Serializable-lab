package com.github.serializable.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.github.serializable.exceptions.InvalidIDException;

/**
 * this is the public order object that will be saved/loaded by the application
 */

public class Order extends Id implements Serializable
{
	private static final long serialVersionUID = -3520138447015383264L;
	private int buyerId;
	private double totalCost = 0;
	List<Integer> productIdSet = new ArrayList<>();

	public Order(User user)
	{
		if (!user.hasId())
		{
			throw new InvalidIDException("User has no Id!");
		}
		buyerId = user.getId();
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
		productIdSet.add(product.getId());
		totalCost += product.getPrice();
		return this;
	}

	public int getBuyerId()
	{
		return buyerId;
	}

	public double getTotalCost()
	{
		return totalCost;
	}

	@Override
	public String toString()
	{
		return getId() + " - " + productIdSet.toString();
	}
}
