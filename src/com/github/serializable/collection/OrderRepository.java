package com.github.serializable.collection;

import com.github.serializable.collection.data.Order;

/**
 * this interface allows you to change how ECommerce saves your order objects, by for example file serialization or database
 */
public interface OrderRepository
{
	public void createOrder(Order order);
	public void updateOrder(Order order);
	public void readAll();
	public void deleteOrder(Order order);
	public void requestSave();
}
