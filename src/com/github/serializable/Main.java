package com.github.serializable;

import java.io.IOException;

import com.github.serializable.collection.ECommerceService;
import com.github.serializable.collection.OrderRepository;
import com.github.serializable.collection.ProductRepository;
import com.github.serializable.collection.UserRepository;
import com.github.serializable.collection.data.Order;
import com.github.serializable.collection.data.Product;
import com.github.serializable.collection.data.User;
import com.github.serializable.collection.file.FileOrderRepository;
import com.github.serializable.collection.file.FileProductRepository;
import com.github.serializable.collection.file.FileUserRepository;

public class Main
{

	public static void main(String[] args)
	{
		// create file repositories
		UserRepository userRep = null;
		ProductRepository productRep = null;
		OrderRepository orderRep = null;
		try
		{
			userRep = new FileUserRepository("Repository/User/");
			orderRep = new FileOrderRepository("Repository/Order/");
			productRep = new FileProductRepository("Repository/Product/");
		}
		catch (IOException e)
		{
			System.err.println("Failed to create files properly");
			e.printStackTrace();
		}

		ECommerceService eCom = new ECommerceService(userRep, productRep, orderRep);

		Product apple = new Product("apple", "u hungry?", 0);

		User user = new User("name", "1234", "sgtjggtqwfyurdfdrg@no");
		user.addOrder(new Order()
				.add(apple));

		/*
		 * TODO: - fill datamembers (Order, Product, User, etc.) with relevant
		 * info (incl. hashCode&equals) - note: Order is a collection of
		 * products by single user (OR a single product by single user) - embody
		 * CRUD methods in repo-interface classes (FileUserRepo etc) - add to
		 * internal collection/array - delete element by premade method or
		 * selfmade indexing replacing element with null - update element by
		 * indexing collection/array and replace current with new - retrieve
		 * data by returning the internal data in new collection/array - fill
		 * ECommerceService to validate argument data* and if all checks, create
		 * physically to disk - If data exists on disk, ECommerceService may
		 * return String(Builder) value of contents to console in, and only in,
		 * Main class (!!!) ex. ECommerceService.printDiskData()
		 *
		 * * = (remains to be defined)
		 */

		/* EXAMPLE */
		// Product energyDrink = new Product();
		// userRep.createUser(user);
		// userRep.createUserList(userList);

		// productRep.createProduct(energyDrink);

		// orderRep.createOrder(productRep);
	}

}
