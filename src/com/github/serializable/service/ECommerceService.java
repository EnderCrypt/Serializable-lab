package com.github.serializable.service;

import java.util.List;
import java.util.Set;

import com.github.serializable.collection.data.Order;
import com.github.serializable.collection.data.Product;
import com.github.serializable.collection.data.User;
import com.github.serializable.collection.storage.StorageRepository;

/**
 * the main ECommerce object, handles order/product/users and checks that all is
 * fine, then saves using the selected repository type
 *
 * TODO: rewrite whole ECommerce.
 * - all other files are complete as it is only the ecommerce class that needs to handle logics
 *
 */
public class ECommerceService
{
	StorageRepository<User> userRepository;
	StorageRepository<Product> productRepository;
	StorageRepository<Order> orderRepository;

	// experimental
	Set<User> internalUserSet;// = new HashSet<>();
	Set<Product> internalProductSet;// = new HashSet<>();
	Set<Order> internalOrderSet;// = new HashSet<>();

	public ECommerceService(StorageRepository<User> userRepository, StorageRepository<Product> productRepository, StorageRepository<Order> orderRepository)
	{
		this.userRepository = userRepository;
		this.productRepository = productRepository;
		this.orderRepository = orderRepository;
		loadData();
		internalUserSet = userRepository.getSet();
		internalOrderSet = orderRepository.getSet();
		internalProductSet = productRepository.getSet();
	}
	
	private void loadData()
	{
		productRepository.readAll();
		orderRepository.readAll();
		userRepository.readAll();
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
			userRepository.createUnit(user);
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
			orderRepository.createUnit(order);
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
			productRepository.createUnit(product);
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
