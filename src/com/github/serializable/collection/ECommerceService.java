package com.github.serializable.collection;

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
		//TODO: remove sysout
		/*
		System.out.println(productRepository);
		System.out.println(orderRepository);
		System.out.println(userRepository);
		*/
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
	
	public void add(User user)
	{
		
	}
	
	public void add(Product product)
	{
		
	}
	
	public void add(Order order)
	{
		
	}
}
