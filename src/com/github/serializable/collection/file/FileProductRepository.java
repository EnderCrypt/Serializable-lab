package com.github.serializable.collection.file;

import java.io.File;

import com.github.serializable.collection.ProductRepository;

/**
 * handles product objects by saving/loading them to files using serialization
 */
public class FileProductRepository implements ProductRepository
{
	private File saveDirectory; 
	public FileProductRepository(String directory)
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
	public void createProduct()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateProduct()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readProduct()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProduct()
	{
		// TODO Auto-generated method stub
		
	}

}
