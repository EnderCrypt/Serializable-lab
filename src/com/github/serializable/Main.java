package com.github.serializable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.github.serializable.collection.data.Order;
import com.github.serializable.collection.data.Product;
import com.github.serializable.collection.data.User;
import com.github.serializable.collection.file.FileOrderRepository;
import com.github.serializable.collection.file.FileProductRepository;
import com.github.serializable.collection.file.FileUserRepository;
import com.github.serializable.collection.storage.StorageRepository;
import com.github.serializable.service.ECommerceService;


/*STATUS: 29/10-15 01:50
 * Endast via EComService får vi säga åt FileXxxRepository att spara till minnet. 
 * Anledning för icke-tomma Repository/Xxx/data filer är pga att en tom HashSet läggs
 *  på disk automatiskt (via klass implementationerna, FileXxxRepository).
 * 
 * TODO: 
 * - finish eComService methods
 * 		 
 * 		
 */

public final class Main
{

	public static void main(String[] args)
	{
		StorageRepository<User> userRep = null;
		StorageRepository<Product> productRep = null;
		StorageRepository<Order> orderRep = null;
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
		
		
		User user = new User("Eduard", "slinkydinky", "eduardDestroyer@free6.com");
		Order order = new Order(user);
		List<Product> prodList = new ArrayList<Product>();
		prodList.add(new Product("apple", "green freshly picked grannie smith", 0));
		prodList.add(new Product("carrot", "rich orange newly picked", 1));
		prodList.add(new Product("MacBookPro2015", "i5 2560k, 8gb RAM, 256GB SSD, big edition", 2));
		prodList.add(new Product("sourcream", "vibrant white", 3));
		

		order.addProduct(prodList);
		eCom.add(user);
		eCom.add(order);
		eCom.add(prodList);
		
		
		System.out.println(eCom);
	}

}
