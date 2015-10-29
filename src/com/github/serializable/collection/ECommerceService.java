package com.github.serializable.collection;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.github.serializable.collection.data.Order;
import com.github.serializable.collection.data.Product;
import com.github.serializable.collection.data.User;

/**
 * the main ECommerce object, handles order/product/users and checks that all is
 * fine, then saves using the selected repository type
 */
public class ECommerceService
{
	UserRepository userRepository;
	ProductRepository productRepository;
	OrderRepository orderRepository;

	// experimental
	Set<User> internalUserSet;// = new HashSet<>();
	Set<Product> internalProductSet;// = new HashSet<>();
	Set<Order> internalOrderSet;// = new HashSet<>();

	public ECommerceService(UserRepository userRepository, ProductRepository productRepository, OrderRepository orderRepository)
	{
		this.userRepository = userRepository;
		this.productRepository = productRepository;
		this.orderRepository = orderRepository;
		loadData();
		internalUserSet = userRepository.getSet();
		internalOrderSet = orderRepository.getSet();
		internalProductSet = productRepository.getSet();
	}
	
	public boolean add(User user)
	{
		if (internalUserSet.contains(user))
		{
			return false;
		}
		else
		{
			internalUserSet.add(user);
			userRepository.createUser(user);
			userRepository.requestSave();
			return true;
		}
	}
	
	public boolean add(Order order)
	{
		if (internalOrderSet.contains(order))
		{
			return false;
		}
		else
		{
			internalOrderSet.add(order);
			orderRepository.createOrder(order);
			orderRepository.requestSave();
			return true;
		}
	}
	
	public boolean add(Product product)
	{
		if (internalProductSet.contains(product))
		{
			return false;
		}
		else
		{
			internalProductSet.add(product);
			productRepository.createProduct(product);
			productRepository.requestSave();
			return true;
		}
	}
	public boolean add(List<Product> products)
	{
		for(Product prod : products)
		{
			add(prod);
		}
		return true;
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
