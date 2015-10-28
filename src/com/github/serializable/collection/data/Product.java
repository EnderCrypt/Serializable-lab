package com.github.serializable.collection.data;

import java.io.Serializable;

/**
 * this is the public product object that will be saved/loaded by the
 * application
 */
public class Product implements Serializable
{
	private static final long serialVersionUID = 525630403812799901L;
	private String name;
	private String productDescription;
	private int productID;

	public Product(String name, String productDescription, int productID)
	{
		this.name = name;
		this.productDescription = productDescription;
		this.productID = productID;
	}

	public String getProduct()
	{
		return name;
	}

	public String getProductDescription()
	{
		return productDescription;
	}

	public int getProductID()
	{
		return productID;
	}

	@Override
	public int hashCode()
	{
		//final int prime = 31;
		//int result = 1;
		//result = prime * result + productID;
		//return result;
		return productID * 37;
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
		if (obj instanceof Product) 
		{
			Product otherProduct = (Product) obj;
			return this.productID == otherProduct.getProductID();
		}
		return false;
	}

}
