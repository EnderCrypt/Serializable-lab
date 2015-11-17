package com.github.serializable.service;


import java.util.HashSet;
import java.util.Set;

import com.github.serializable.collection.storage.StorageRepository;
import com.github.serializable.passwordvalidation.PasswordRequirmentsNotMet;
import com.github.serializable.passwordvalidation.PasswordValidationService;
import com.github.serializable.passwordvalidation.PasswordValidator;
import com.github.serializable.passwordvalidation.validators.DefaultPasswordValidator;

/**
 * the main ECommerce object, handles order/product/users and checks that all is
 * fine, then saves using the selected repository type
 *
 * TODO: rewrite whole ECommerce.
 * - all other files are complete as it is only the ecommerce class that needs to handle logics
 * -addSet of user/prod/order -- TIMMIE
 * -add single of user/p/o -- TIMMIE
 * -
 *
 */
public class ECommerceService
{
	private StorageRepository<User> userRep;
	private StorageRepository<Product> prodRep;
	private StorageRepository<Order> orderRep;
	
	private PasswordValidationService passwordValidator;
	
	private static final int MAX_USERNAME_LENGTH = 30;
	
	public ECommerceService(StorageRepository<User> userRep, StorageRepository<Product> prodRep, StorageRepository<Order> orderRep)
	{
		this.userRep = userRep;
		this.prodRep = prodRep;
		this.orderRep = orderRep;
		changePasswordValidator(new DefaultPasswordValidator());
	}
	
	public void changePasswordValidator(PasswordValidator passwordValidater)
	{
		passwordValidator = new PasswordValidationService(passwordValidater);
	}
	
	//--Users
	public void add(User user) throws PasswordRequirmentsNotMet
	{
		if(user.getUsername().length() > MAX_USERNAME_LENGTH)
		{
			throw new IllegalArgumentException("Cannot contain more than " + MAX_USERNAME_LENGTH + " characters");
		}
		passwordValidator.validate(user.getPassword()); // will throw exceptions if password requirments arent met
		userRep.createUnit(user);
	}
	
	public User newUser(String name, String password, String email) throws PasswordRequirmentsNotMet
	{
		User user = new User(name, password, email);
		add(user);
		return user;
	}
	public void addAll(User[] userList) throws PasswordRequirmentsNotMet
	{
		//add every user from list to userRep
		for(User user : userList)
		{
			add(user);
		}
	}
	
	//--Orders
	public void add(Order order) 
	{
		//add order to orderRep
		/*
		 * check to be made:
		 * - order cannot be created with no user
		 * - order cannot be created with no products contained
		 * - order cannot be created with a total value of >50k
		 * - order cannot be created with products without ID
		 */
		
		//Order with no user ID
		if(order.getBuyerId() == -1)
		{
			// throw new InvalidUserIDException(); ????
		}
		
		//Order with no ID
		if(order.products.isEmpty())
		{
			throw new IllegalArgumentException("Order must contain atleast one product");
		}
		
		//Order value over 50k
		//if(order.addProduct(product))
		{
			// Throw new orderValueException(); ?????
		}
		
		//Order with no product ID
		//if(order.addProduc)
		{
			
		}
		
		orderRep.createUnit(order);
	}
	public void addAll(Order[] orderList)
	{
		for(Order order : orderList)
		{
			add(order);
		}
	}
	
	//--Products
	public void add(Product product)
	{
		if(product.hasId() == false)
		{
			//product with no id error
		}
		
		if(product.getProductName().isEmpty())
		{
			//no product name error
		}
		
		if(product.getProductDescription().isEmpty())
		{
			//no product description error
		}
		
		if(product.getPrice() <= 0)
		{
			// price error
		}
		prodRep.createUnit(product);
	}
	public void addAll(Product[] productList)
	{
		//add every products from list to prodRep
		for(Product product : productList)
		{
			add(product);
		}
	}
	
	public Set<Order> getAllOrders(User user)
	{
		//TODO: Not yet finished
		// - user shall return 
		return new HashSet<>();
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Users:");
		sb.append(userRep);
		sb.append("\n");

		sb.append("Orders:");
		sb.append(orderRep);
		sb.append("\n");

		sb.append("Products:");
		sb.append(prodRep);
		sb.append("\n");

		return sb.toString();
	}
}
