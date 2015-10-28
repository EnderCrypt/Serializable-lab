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
		user.addOrder(new Order(1)
				.add(apple));
		/*TODO: 
		 * make ECommerceService add Order, Product and User to its internal repository
		 * 
		 *  
		 *  
		 */

		System.out.println(eCom);

	}

}
