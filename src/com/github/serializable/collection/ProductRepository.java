package com.github.serializable.collection;

/**
 * this interface allows you to change how ECommerce saves your product objects, by for example file serialization or database
 */
public interface ProductRepository
{
	public void createProduct();
	public void updateProduct();
	public void readProduct();
	public void deleteProduct();
}
