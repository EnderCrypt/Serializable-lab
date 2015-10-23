package com.github.serializable.collection.file;

import java.io.File;

import com.github.serializable.collection.OrderRepository;

/**
 * handles order objects by saving/loading them to files using serialization
 */
public class FileOrderRepository implements OrderRepository
{
	private File saveDirectory; 
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
	public void createOrder()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateOrder()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readOrder()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteOrder()
	{
		// TODO Auto-generated method stub
		
	}

}
