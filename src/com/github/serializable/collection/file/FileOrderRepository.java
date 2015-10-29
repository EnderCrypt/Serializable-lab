package com.github.serializable.collection.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

import com.github.serializable.collection.OrderRepository;
import com.github.serializable.collection.data.Order;

/**
 * handles order objects by saving/loading them to files using serialization
 */
public class FileOrderRepository extends FileRepoAbstract<Order> implements OrderRepository
{
	
	public FileOrderRepository(String directory) throws IOException
	{
		super(directory);
	}

	@Override
	public void createOrder(Order order)
	{
		if (!set.add(order))
		{
			throw new RuntimeException("Could not add order: " + order.toString()
										+ "\n Make sure argument has not already been created (on disk)");
		}
	}

	@Override
	public void updateOrder(Order order)
	{
		if (set.contains(order))
		{
			set.remove(order);
			set.add(order);
		}
		else
		{
			throw new RuntimeException("Order set does not contain specific order! " + order.toString());
		}

	}

	@Override
	public void readAll()
	{
		if (set.size() > 0)
		{
			throw new RuntimeException("Data has already been read");
		}
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFile)))
		{
			set = (Set<Order>) ois.readObject(); //reads whole set as object
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
	public void deleteOrder(Order order)
	{
		if (set.contains(order))
		{
			if (!set.remove(order))
			{
				throw new RuntimeException("Could not remove order! Make sure order already exists.");
			}
		}
		else
		{
			throw new RuntimeException("Order set does not contain specific order!");
		}
	}
}
