package com.github.serializable.collection.data;

import java.io.Serializable;

/**
 * this is the public product object that will be saved/loaded by the
 * application
 */
public class Product implements Serializable
{
	private static final long serialVersionUID = 525630403812799901L;

	private String product;
	private String productDescription;
	private String productID;

	public Product(String product, String productDescription, String productID)
	{
		this.product = product;
		this.productDescription = productDescription;
		this.productID = productID;
	}

	public String getProduct()
	{
		return product;
	}

	public String getProductDescription()
	{
		return productDescription;
	}

	public String getProductID()
	{
		return productID;
	}

	@Override
	public boolean equals(Object other)
	{
		if (this == other)
		{
			return true;
		}
		if (other instanceof Product)
		{
			Product otherProduct = (Product) other;
			return product.equals(otherProduct.product) && productDescription.equals(otherProduct.productDescription)
					&& productID.equals(otherProduct.productID);
		}
		return false;
	}

	@Override
	public int hashCode()
	{
		int result = 1;
		result += product.hashCode() * 37;
		result += productDescription.hashCode() * 37;
		result += productID.hashCode() * 37;

		return result;
	}

}
