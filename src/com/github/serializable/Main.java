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
		
		System.out.println(eCom);
		
		//User user = new User("sfsdsdf", "4tey4", "@s@@@");
		//eCom.add(user);
	}

}
