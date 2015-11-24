package com.github.serializable.service.mocktest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.core.IsEqual.*;
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
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Mock(name="userRep")
	private StorageRepository<User> userRepMock;
	@Mock(name="orderRep")
	private StorageRepository<Order> orderRepMock;
	@Mock(name="prodRep")
	private StorageRepository<Product> prodRepMock;
	
	@InjectMocks
	private ECommerceService eCom = new ECommerceService(userRepMock, prodRepMock, orderRepMock);
	private String validUsername = "JohnDoe";
	private String validPassword = "A_12";
	private String validEmail = "john@doe.anon";
	private Product validProduct = new Product("fallout4", "sämsta spelet euw", 10.0);

	
	//password requirements:
	//at least 1 capital letter, at least 2 numbers and at least 1 special char
	@Test
	public void passwordMeetsRequirementsNumbers() throws PasswordRequirmentsNotMet
	{
		//1 capital (check), 1 special (check) but not 2 numbers (!!!)
		User userNoNumbers = new User(validUsername, "A_", validEmail);
		thrown.expect(PasswordRequirmentsNotMet.class);
		thrown.expectMessage(equalTo("Does not have atleast two numbers"));
		eCom.add(userNoNumbers);
	}
	
	@Test
	public void passwordMeetsRequirementsCapital() throws PasswordRequirmentsNotMet
	{
		User userNoCapital = new User(validUsername, "a_", validEmail);
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
		thrown.expectMessage(equalTo("Cannot contain more than " + ECommerceService.MAX_USERNAME_LENGTH + " characters"));
		eCom.add(userLongUsername);
	}
	
	@Test
	public void canCreateNewProduct()
	{
		/*	currently, IDs are not assigned because the classes that handles assignment is mocked
			mocked classes only retain non-embodied methods (in this case we mock an interface)
			therefore getId() will always be -1 but that will do for now */
		
		//setup mock
		//ensure that when we call prodRepMock.getUnitById, null will be returned because
		//null ensures the object has not already been stored and only through null shall 
		//eCom call prodRep.createUnit
		when(prodRepMock.getUnitById(validProduct.getId())).thenReturn(null);
		eCom.add(validProduct); 
		
		when(prodRepMock.getUnitById(validProduct.getId())).thenReturn(validProduct);
		Product prodInStorage = eCom.getProductById(validProduct.getId());

		//assert equality
		assertThat(prodInStorage, equalTo(validProduct));
		//ensure eCom invokes/calls the createUnit and getUnitById methods on the interface
		verify(prodRepMock).createUnit(validProduct);
		//this needs to verify a call twice due to eCom.add AND eCom.getProductId both calls for this method
		verify(prodRepMock, times(2)).getUnitById(validProduct.getId()); 
	}
	
	@Test
	public void canDeleteProduct()
	{
		//TODO: make check to see if object really got deleted and not just if the repository invoked the method
		//setup mock object to make sure that whichever object calling prodRep.getUnitById (eCom.remove())
		//shall assume validProduct is returned
		when(prodRepMock.getUnitById(validProduct.getId())).thenReturn(validProduct);
		eCom.remove(validProduct);
		
		//verify that the productRepository has its method deleteUnit called
		verify(prodRepMock).deleteUnit(validProduct);
	}
	
	@Test
	public void canUpdateProduct()
	{
		//setup mock for initial creation of validProduct
		when(prodRepMock.getUnitById(validProduct.getId())).thenReturn(null);
		eCom.add(validProduct);
		
		assertThat(validProduct.getPrice(), equalTo(10.0));
		
		//verify creation of first object
		verify(prodRepMock).createUnit(validProduct);
		
		//recreate validProduct with new arguments (15.0 as price than 10.0 as prior)
		validProduct = new Product(validProduct.getProductName(), validProduct.getProductDescription(), 15.0);
		when(prodRepMock.getUnitById(validProduct.getId())).thenReturn(validProduct);
		when(prodRepMock.updateUnit(validProduct)).thenReturn(validProduct);
		Product updatedProduct = eCom.add(validProduct);
		
		assertThat(updatedProduct.getPrice(), equalTo(15.0));
		
		//verify eCom called on updateUnit because unit expected to have already been created
		verify(prodRepMock).updateUnit(validProduct);
	}
	
	@Test
	public void canRetrieveAllProducts()
	{
		//TODO: make sure getAllProducts method exists in eCom and invokes getAllUnits from the specified
		//		(mocked) repository
	}

}
