package com.github.serializable.collection.file;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import com.github.serializable.collection.OrderRepository;
import com.github.serializable.collection.data.Order;

/**
 * handles order objects by saving/loading them to files using serialization
 */
public class FileOrderRepository implements OrderRepository
{
	private File saveDirectory;
	private Set<Order> orderSet = new HashSet<Order>();
	
	public FileOrderRepository(String directory)
	{
		// init variables
		saveDirectory = new File(directory);
		// create directory
		if (!saveDirectory.exists())
		{
			if (!saveDirectory.mkdirs()) // for example sending in the directory "#€&&()&=" into the constructor fails to create a dir
			{
				throw new RuntimeException("Failed to create directory");
			}
		}
	}

	@Override
	public void createOrder(Order order)
	{
		
		
	}

	@Override
	public void updateOrder(Order order)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readOrder(Order order)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteOrder(Order order)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void requestSave()
	{
		// TODO Auto-generated method stub
		
	}
}
