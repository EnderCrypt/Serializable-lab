package com.github.serializable.collection;

import java.util.Set;

import com.github.serializable.collection.data.Product;

/**
 * this interface allows you to change how ECommerce saves your product objects, by for example file serialization or database
 */
public interface ProductRepository
{
	public void createProduct(Product product);
	public void updateProduct(Product product);
	public void readAll();
	public void deleteProduct(Product product);
	public void requestSave();
	public Set<Product> getSet();
}
