package com.github.serializable.service;

import java.util.HashSet;
import java.util.Set;

import com.github.serializable.collection.storage.StorageRepository;
import com.github.serializable.exceptions.InvalidIDException;
import com.github.serializable.exceptions.PriceOutOfBoundsException;
import com.github.serializable.passwordvalidation.PasswordRequirmentsNotMet;
import com.github.serializable.passwordvalidation.PasswordValidationService;
import com.github.serializable.passwordvalidation.PasswordValidator;
import com.github.serializable.passwordvalidation.validators.DefaultPasswordValidator;

/**
 * the main ECommerce object, handles order/product/users and checks that all is
 * fine, then saves using the selected repository type
 *
 * TODO: rewrite whole ECommerce. - all other files are complete as it is only
 * the ecommerce class that needs to handle logics -
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

	// --Users
	public User add(User user) throws PasswordRequirmentsNotMet
	{
		if (user.getUsername().length() > MAX_USERNAME_LENGTH)
		{
			throw new IllegalArgumentException("Cannot contain more than " + MAX_USERNAME_LENGTH + " characters");
		}
		passwordValidator.validate(user.getPassword()); // will throw exceptions
														// if password
														// requirments arent met
		return userRep.createUnit(user);
	}

	public User[] addAll(User[] userList) throws PasswordRequirmentsNotMet
	{
		User[] newList = new User[userList.length];
		int i = 0;
		for (User user : userList)
		{
			newList[i] = add(user);
			i++;
		}
		return newList;
	}

	// --Orders
	public Order add(Order order)
	{
		// add order to orderRep
		/*
		 * check to be made: - order cannot be created with no user - order
		 * cannot be created with no products contained - order cannot be
		 * created with a total value of >50k - order cannot be created with
		 * products without ID
		 */

		// Order with no user ID
		if (order.getBuyerId() == -1)
		{
			throw new InvalidIDException("Order is missing valid buyer ID");
		}

		// Order with empty set of products
		if (order.productIdSet.isEmpty())
		{
			throw new NullPointerException("Order must contain atleast one product");
		}

		// Order value over 50k
		if (order.getTotalCost() >= MAX_COST)
		{
			throw new PriceOutOfBoundsException("Order price must be under " + MAX_COST);
		}

		return orderRep.createUnit(order);
	}

	public Order[] addAll(Order[] orderList)
	{
		Order[] newList = new Order[orderList.length];
		int i = 0;
		for (Order order : orderList)
		{
			newList[i] = add(order);
			i++;
		}
		return newList;
	}

	// --Products
	public Product add(Product product)
	{
		if (product.getProductName() == null || product.getProductName().isEmpty())
		{
			throw new NullPointerException("Product name must not be empty or null");
		}

		if (product.getProductDescription() == null || product.getProductDescription().isEmpty())
		{
			throw new NullPointerException("Product description must not be empty or null");
		}

		if (product.getPrice() <= 0 || product.getPrice() >= MAX_COST)
		{
			throw new PriceOutOfBoundsException("Price must be more than 0 or under " + MAX_COST);
		}

		return prodRep.createUnit(product);
	}

	public Product[] addAll(Product[] productList)
	{
		Product[] newList = new Product[productList.length];
		int i = 0;
		for (Product product : newList)
		{
			newList[i] = add(product);
			i++;
		}
		return newList;
	}

	/**
	 * 
	 * By retrieving the buyer id of each order in the orderRep, consolidate
	 * every assigned order and return a new list containing every order for the
	 * user in argument
	 * 
	 * So by example:
	 * <p>
	 * {@code ECommerceService eCom = new ECommerceService(..., ..., ...) }
	 * <p>
	 * {@code User user = new User(); }
	 * <p>
	 * {@code Order order1 = new Order(user); }
	 * <p>
	 * {@code Order order2 = new Order(user); }
	 * <p>
	 * {@code Order order3 = new Order(user); }
	 * <p>
	 * {@code eCom.add(order1); eCom.add(order2); eCom.add(order3); }
	 * <p>
	 * {@code eCom.getAllOrders(user) //<- returns hashSet }
	 * 
	 * @param user
	 * @return new set of Order objects by getting every order object associated
	 *         with user
	 */
	public Set<Order> getAllOrders(User user)
	{
		Set<Order> orders = new HashSet<>();
		for(int orderId : user.getOrderIdSet())
		{
			orders.add(orderRep.getUnitById(orderId));
		}
		return orders; 
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
