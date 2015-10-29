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


/*STATUS: 29/10-15 01:50
 * Endast via EComService får vi säga åt FileXxxRepository att spara till minnet. 
 * Anledning för icke-tomma Repository/Xxx/data filer är pga att en tom HashSet läggs
 *  på disk automatiskt (via klass implementationerna, FileXxxRepository).
 * 
 * TODO: 
 * - finish eComService methods
 * 		- implement "business-logic" methods, e.g. restrictions/accessibility (read doc)
 * 		-  (?)make eComService validate argument data and if all checks, send to FileXxxRepo for writing to disk
 * 
 */

public class Main
{

	public static void main(String[] args)
	{
		UserRepository userRep = null;
		ProductRepository productRep = null;
		OrderRepository orderRep = null;
		try
		{
			// create file repositories
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


		User user1 = new User("name", "1234", "sgtjggtqwfyurdfdrg@no");
		User user2 = new User("fred", "wreck", "ragtaragta@yes");
		Product apple = new Product("apple", "maggie smith brand", 0);
		Order fridayShopping = new Order(1);
		
		fridayShopping.addProduct(apple);
		user1.addOrder(fridayShopping);
		
		//Creates to repository data (abstract)
		productRep.createProduct(apple);
		orderRep.createOrder(fridayShopping);
		userRep.createUser(user1);
		userRep.createUser(user2);
		
//		eCom.validateOrders(orderRep); //makes sure an order contains at least one product, is put by a user and has a unique ID.
//		eCom.openForSuggestions();
		System.out.println(eCom);

	}

}
