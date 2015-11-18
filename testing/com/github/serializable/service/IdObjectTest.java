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
	@Before
	public void init()
	{
		Global.init();
	}
	
	@Test
	public void checkThatUserGetsIdWhenAdded() throws PasswordRequirmentsNotMet
	{
		User user = Global.generateFreeUser();
		assertEquals(user.getId(), -1);
		Global.eCom.add(user);
		assertNotEquals(user.getId(), -1);
	}
	
	@Test
	public void checkThatProductGetsIdWhenAdded() throws PasswordRequirmentsNotMet
	{
		Product product = Global.generateFreeProduct();
		assertEquals(product.getId(), -1);
		Global.eCom.add(product);
		assertNotEquals(product.getId(), -1);
	}
	
	@Test
	public void checkThatOrderGetsIdWhenAdded() throws PasswordRequirmentsNotMet
	{
		Product product = Global.generateProduct();
		User user = Global.generateUser();
		Order order = Global.generateFreeOrder(user);
		order.addProduct(product);
		
		assertEquals(order.getId(), -1);
		Global.eCom.add(order);
		assertNotEquals(order.getId(), -1);
	}

}
