package com.github.serializable.service;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.github.serializable.collection.file.FileRepository;
import com.github.serializable.collection.storage.StorageRepository;
import com.github.serializable.passwordvalidation.PasswordRequirmentsNotMet;

public class IdObjectTest
{
	private static ECommerceService eCom;
	
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
	public void checkThatUserGetsIdWhenAdded() throws PasswordRequirmentsNotMet
	{
		User user = new User("_","A_12","_");
		assertEquals(user.getId(), -1);
		eCom.add(user);
		assertNotEquals(user.getId(), -1);
	}
	
	@Test
	public void checkThatProductGetsIdWhenAdded() throws PasswordRequirmentsNotMet
	{
		Product product = new Product("_","A_12",10);
		assertEquals(product.getId(), -1);
		eCom.add(product);
		assertNotEquals(product.getId(), -1);
	}
	
	@Test
	public void checkThatOrderGetsIdWhenAdded() throws PasswordRequirmentsNotMet
	{
		Product product = new Product("_","A_12",10);
		
		User user = new User("_","A_12","_");
		eCom.add(user);
		
		Order order = new Order(user);
		order.addProduct(product);
		assertEquals(order.getId(), -1);
		eCom.add(order);
		assertNotEquals(order.getId(), -1);
	}

}
