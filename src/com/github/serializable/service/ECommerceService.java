package com.github.serializable.service;


import java.util.Set;

import com.github.serializable.collection.storage.StorageRepository;
import com.github.serializable.exceptions.PriceOutOfBoundsException;
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
	private static final int MAX_COST = 50000;
	
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
		
		//Order with empty set of products
		if(order.productIdSet.isEmpty())
		{
			throw new NullPointerException("Order must contain atleast one product");
		}
		
//		Order value over 50k
		if(order.getTotalCost() >= MAX_COST)
		{
			throw new PriceOutOfBoundsException("Order price must be under " + MAX_COST); //skapa exception klassen oxå, av runtime exception
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
		if(product.getProductName() == null || product.getProductName().isEmpty())
		{
			throw new NullPointerException("Product name must not be empty or null");
		}
		
		if(product.getProductDescription() == null || product.getProductDescription().isEmpty())
		{
			throw new NullPointerException("Product description must not be empty or null");
		}
		
		if(product.getPrice() <= 0 || product.getPrice() >= MAX_COST)
		{
			throw new PriceOutOfBoundsException("Price must be more than 0 or under " + MAX_COST);
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
	
	/**
	 * 
	 * By retrieving the buyer id of each order in the orderRep, consolidate every assigned order and return a new list containing every order
	 *  for the user in argument
	 *  
	 *  So by example:
	 *  <p> {@code ECommerceService eCom = new ECommerceService(..., ..., ...) }
	 *  <p> {@code User user = new User(); } 
	 *  <p> {@code Order order1 = new Order(user); }
	 *  <p> {@code Order order2 = new Order(user); }
	 *  <p> {@code Order order3 = new Order(user); }
	 *  <p> {@code eCom.add(order1); eCom.add(order2); eCom.add(order3); } 
	 *  <p> {@code eCom.getAllOrders(user) //<- returns hashSet }
	 *  
	 * @param user
	 * @return new set of Order objects by getting every order object associated with user
	 */
	public Set<Order> getAllOrders(User user)
	{
		//TODO: Not yet finished todo by Edward
		return null; //new HashSet<>(ordersFromUser);
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
