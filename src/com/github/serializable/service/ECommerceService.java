package com.github.serializable.service;


import com.github.serializable.collection.storage.StorageRepository;

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
	private static final int MAX_USERNAME_LENGTH = 30;
	
	public ECommerceService(StorageRepository<User> userRep, StorageRepository<Product> prodRep, StorageRepository<Order> orderRep)
	{
		this.userRep = userRep;
		this.prodRep = prodRep;
		this.orderRep = orderRep;
	}
	
	//--Users
	public void add(User user)
	{
		if(user.getUsername().length() > MAX_USERNAME_LENGTH)
		{
			throw new IllegalArgumentException("Cannot contain more than " + MAX_USERNAME_LENGTH + " characters");
		}
		//TODO: password criteria
		userRep.createUnit(user);
	}
	
	public User newUser(String name, String password, String email)
	{
		User user = new User(name, password, email);
		add(user);
		return user;
	}
	public void addAll(User[] userList)
	{
		//add every user from list to userRep
	}
	
	//--Orders
	public void add(Order order)
	{
		//add order to orderRep
	}
	public void addAll(Order[] orderList)
	{
		//add every order from list to orderRep
	}
	
	//--Products
	public void add(Product product)
	{
		//add product to prodRep
	}
	public void addAll(Product productList)
	{
		//add every products from list to prodRep
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