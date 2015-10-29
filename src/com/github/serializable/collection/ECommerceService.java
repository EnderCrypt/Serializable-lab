package com.github.serializable.collection;


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
		System.out.println(productRepository);
		System.out.println(orderRepository);
		System.out.println(userRepository);
	}
	
}
