package com.github.serializable.collection.file;

import java.io.File;

import com.github.serializable.collection.ProductRepository;
import com.github.serializable.collection.data.Product;

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
	public void createProduct(Product product)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateProduct(Product product)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readProduct(Product product)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProduct(Product product)
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void requestSave() 
	{
		// TODO Auto-generated method stub
		
	}

}
