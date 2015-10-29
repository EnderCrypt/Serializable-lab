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
public class FileProductRepository extends FileRepoAbstract<Product> implements ProductRepository
{
	
	public FileProductRepository(String directory) throws IOException
	{
		super(directory);
	}

	@Override
	public void createProduct(Product product)
	{
		if (!set.add(product))
		{
			throw new RuntimeException("Could not add product: " + product.toString()
										+ "\n Make sure argument has not already been created (on disk)");
		}
	}

	@Override
	public void updateProduct(Product product)
	{
		if (set.contains(product))
		{
			set.remove(product);
			set.add(product);
		}
		else
		{
			throw new RuntimeException("Product set does not contain specific product! " + product.toString());
		}
	}

	@Override
	public void readAll() //Reads the data from Product/data
	{
		if (set.size() > 0)
		{
			throw new RuntimeException("Data has already been read");
		}
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFile)))
		{
			set = (Set<Product>) ois.readObject();
		}

		// Catch
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	@Override
	public void deleteProduct(Product product)
	{
		if (set.contains(product))
		{
			if (!set.remove(product))
			{
				throw new RuntimeException("Could not remove product: " + product.toString());
			}
		}
		else
		{
			throw new RuntimeException("Product set does not contain specified product!");
		}
	}
}
