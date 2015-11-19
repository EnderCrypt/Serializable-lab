package com.github.serializable.service.mocktest;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;
import static org.hamcrest.core.IsEqual.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


import com.github.serializable.collection.storage.StorageRepository;
import com.github.serializable.passwordvalidation.PasswordRequirmentsNotMet;
import com.github.serializable.service.ECommerceService;
import com.github.serializable.service.Order;
import com.github.serializable.service.Product;
import com.github.serializable.service.User;

@RunWith(MockitoJUnitRunner.class)
public class ECommerceServiceTest
{
	@Mock(name="userRep")
	private StorageRepository<User> userRep;
	@Mock(name="orderRep")
	private StorageRepository<Order> orderRep;
	@Mock(name="prodRep")
	private StorageRepository<Product> prodRep;
	
	@InjectMocks
	private ECommerceService eCom;
	
	private String validPassword = "A_12";
	private String validEmail = "john@doe.anon";
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	
	
	//password requirements:
	//at least 1 capital letter, at least 2 numbers and at least 1 special char
	@Test
	public void passwordMeetsRequirementsNumbers() throws PasswordRequirmentsNotMet
	{
		//1 capital (check), 1 special (check) but not 2 numbers (!!!)
		User userNoNumbers = new User("JohnDoe", "A_", validEmail);
		thrown.expect(PasswordRequirmentsNotMet.class);
		thrown.expectMessage(equalTo("Does not have atleast two numbers"));
		eCom.add(userNoNumbers);
	}
	
	@Test
	public void passwordMeetsRequirementsCapital() throws PasswordRequirmentsNotMet
	{
		User userNoCapital = new User("JohnDoe", "a_", validEmail);
		thrown.expect(PasswordRequirmentsNotMet.class);
		thrown.expectMessage(equalTo("Does not have atleast one capital letter"));
		eCom.add(userNoCapital);
	}
	
	@Test
	public void passwordMeetsRequirementsSpecial() throws PasswordRequirmentsNotMet
	{
		User userNoSpecial = new User("JohnDoe", "A12", validEmail);
		thrown.expect(PasswordRequirmentsNotMet.class);
		thrown.expectMessage(equalTo("Does not have atleast one special character"));
		eCom.add(userNoSpecial);
	}
	
	@Test
	public void usernameContainsWithinBounds() throws PasswordRequirmentsNotMet
	{
		User userLongUsername = new User("qoiheqoijencqebvqbuequevoqwveou", validPassword, validEmail);
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(equalTo("Cannot contain more than " + eCom.MAX_USERNAME_LENGTH + " characters"));
		eCom.add(userLongUsername);
	}
	
	@Test
	public void canCreateNewProduct()
	{
		/*
		Product newProd = new Product("fallout4", "sämsta spelet euw", 10.0);
		when(prodRep.getUnitById(newProd.getId())).thenReturn(newProd);
		Product existingProd = prodRep.getUnitById(newProd.getId());
		
		assertEquals(existingProd, equalTo(newProd));
		*/
	}

}
