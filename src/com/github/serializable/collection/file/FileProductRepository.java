package com.github.serializable.collection.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;
import java.util.HashSet;

import com.github.serializable.collection.ProductRepository;
import com.github.serializable.collection.data.Product;

/**
 * handles product objects by saving/loading them to files using serialization
 */
public class FileProductRepository implements ProductRepository
{
	private File saveDirectory; 
	private Set<Product> productSet = new HashSet<Product>();
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
		if(!productSet.add(product))
		{
			throw new RuntimeException("Could not add product: " + product.toString());
		}
	}

	@Override
	public void updateProduct(Product product)
	{
		if(productSet.contains(product))
		{
			productSet.remove(product);
			productSet.add(product);
		}
	}

	@Override
	public void readProduct() throws FileNotFoundException, IOException, ClassNotFoundException, ClassCastException
	{
		if (productSet.size() > 0)
		{
			throw new RuntimeException("Data has already been read");
		}
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveDirectory+"data")))
		{
			productSet = (Set<Product>) ois.readObject();
		}
		
	}

	@Override
	public void deleteProduct(Product product)
	{
		if(productSet.contains(product))
		{
			if(!productSet.remove(product))
			{
				throw new RuntimeException("Could not remove product: " + product.toString());
			}
		}
	}
	
	@Override
	public void requestSave() 
	{
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(saveDirectory)))
		{
			out.writeObject(productSet);
		}
		catch(IOException e)
		{
			e.getMessage();
			e.printStackTrace();
		}
	}

}
