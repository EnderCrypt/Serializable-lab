package com.github.serializable.collection;

import java.util.HashSet;
import java.util.Set;

import com.github.serializable.collection.data.Order;
import com.github.serializable.collection.data.Product;
import com.github.serializable.collection.data.User;

/**
 * the main ECommerce object, handles order/product/users and checks that all is fine, then saves using the selected repository type
 */
public class ECommerceService
{
	UserRepository userRepository;
	ProductRepository productRepository;
	OrderRepository orderRepository;
	
	//experimental
	Set<User> internalUserSet = new HashSet<User>();
	Set<Product> internalProductSet = new HashSet<Product>();

	public ECommerceService(UserRepository userRepository, ProductRepository productRepository, OrderRepository orderRepository)
	{
		this.userRepository = userRepository;
		this.productRepository = productRepository;
		this.orderRepository = orderRepository;
		loadData();
	}
	private void loadData()
	{
		productRepository.readAll();
		orderRepository.readAll();
		userRepository.readAll();
	}
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Users:");
		sb.append(userRepository);
		sb.append("\n");
		
		sb.append("Orders:");
		sb.append(orderRepository);
		sb.append("\n");
		
		sb.append("Products:");
		sb.append(productRepository);
		sb.append("\n");
		
		return sb.toString();
	}
	
}
