package com.github.serializable.collection;


/**
 * the main ECommerce object, handles order/product/users and checks that all is fine, then saves using the selected repository type
 */
public class ECommerceService
{
	UserRepository userRepository;
	ProductRepository productRepository;
	OrderRepository orderRepository;
	
	int orderIdCounter = 0;
	int productIdCounter = 0;
	int userIdCounter = 0;
	
	public ECommerceService(UserRepository userRepository, ProductRepository productRepository, OrderRepository orderRepository)
	{
		this.userRepository = userRepository;
		this.productRepository = productRepository;
		this.orderRepository = orderRepository;
		
	}
}
