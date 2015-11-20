package com.github.serializable.service.junit;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.github.serializable.collection.file.FileRepository;
import com.github.serializable.collection.storage.StorageRepository;
import com.github.serializable.exceptions.PriceOutOfBoundsException;
import com.github.serializable.passwordvalidation.PasswordRequirmentsNotMet;
import com.github.serializable.service.ECommerceService;
import com.github.serializable.service.Order;
import com.github.serializable.service.Product;
import com.github.serializable.service.User;

public class OrderTestManual
{
	@Rule
	public ExpectedException rule = ExpectedException.none();
	
	private ECommerceService eCom;
	
	@Before
	public void init()
	{
		StorageRepository<User> userRep = null;
		StorageRepository<Product> productRep = null;
		StorageRepository<Order> orderRep = null;
		try
		{
			// create file repositories
			userRep = new FileRepository<>("TestRepository/User/");
			orderRep = new FileRepository<>("TestRepository/Order/");
			productRep = new FileRepository<>("TestRepository/Product/");
		}
		catch (IOException e)
		{
			System.err.println("Failed to create files properly");
			e.printStackTrace();
		}
		eCom = new ECommerceService(userRep, productRep, orderRep);
	}
	
	@Test
	public void cannotHaveOrdersThatAreMoreThan50k() throws PasswordRequirmentsNotMet
	{
		rule.expect(PriceOutOfBoundsException.class);
		
		User user = new User("_","_","_");
		eCom.add(user);
		Product product = new Product("_", "_", 50000);
		eCom.add(product);
		
		Order order = new Order(user);
		order.addProduct(product);
		
		Global.eCom.add(order);
	}
	
	@Test
	public void verifyThatTotalCostForOrderIsCorrect() throws PasswordRequirmentsNotMet
	{
		User user = new User("_","_","_");
		eCom.add(user);
		Order order = new Order(user);
		double expectedCost = 0;
		for (int i=0;i<100;i++)
		{
			Product product = new Product("_","_",Math.random()*10);
			double cost = product.getPrice();
			expectedCost += cost;
			
			order.addProduct(product);
		}
		assertEquals(expectedCost, order.getTotalCost(), 0.0000001);
	}
}
