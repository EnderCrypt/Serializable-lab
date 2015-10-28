package com.github.serializable.collection.data;

import java.io.Serializable;
import java.util.List;

/**
 * this is the public order object that will be saved/loaded by the application
 */
public class Order implements Serializable
{
	private static final long serialVersionUID = -3520138447015383264L;
	private List<Product> products;
	private User user;
	private Product product;
	
	public Order(List<Product> products, User user)
	{
		this.products = products;
		this.user = user;
	}
	
	public Order(Product product, User user)
	{
		this.product = product;
		this.user = user;
	}

}
