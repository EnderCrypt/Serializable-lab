package com.github.serializable.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.github.serializable.exceptions.PriceOutOfBoundsException;

public class OrderTest
{
	@Rule
	public ExpectedException rule = ExpectedException.none();
	
	@Before
	public void init()
	{
		Global.init();
	}
	
	@Test
	public void cannotHaveOrdersThatAreMoreThan50k()
	{
		rule.expect(PriceOutOfBoundsException.class);
		
		User user = Global.generateUser();
		Product product = new Product(Global.generateString(), Global.generateString(), 50000);
		Global.eCom.add(product);
		
		Order order = Global.generateFreeOrder(user);
		order.addProduct(product);
		
		Global.eCom.add(order);
	}
	
	@Test
	public void verifyThatTotalCostForOrderIsCorrect()
	{
		User user = Global.generateUser();
		Order order = Global.generateFreeOrder(user);
		double expectedCost = 0;
		for (int i=0;i<100;i++)
		{
			Product product = Global.generateProduct();
			double cost = product.getPrice();
			expectedCost += cost;
			
			order.addProduct(product);
		}
		assertEquals(expectedCost, order.getTotalCost(), 0.0000001);
	}
}
