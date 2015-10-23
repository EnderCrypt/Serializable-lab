package com.github.serializable.collection;

/**
 * this interface allows you to change how ECommerce saves your order objects, by for example file serialization or database
 */
public interface OrderRepository
{
	public void createOrder();
	public void updateOrder();
	public void readOrder();
	public void deleteOrder();
}
