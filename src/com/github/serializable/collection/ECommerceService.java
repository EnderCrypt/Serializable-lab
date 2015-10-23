package com.github.serializable.collection;

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
	}

}
