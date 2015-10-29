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
public class FileOrderRepository implements OrderRepository
{
	private File saveDirectory;
	private File saveFile;
	private Set<Order> orderSet = new HashSet<Order>();

	public FileOrderRepository(String directory) throws IOException
	{
		// init variables
		saveDirectory = new File(directory);
		saveFile = new File(saveDirectory + "/data");
		// create directory
		if (!saveDirectory.exists())
		{
			if (!saveDirectory.mkdirs()) // for example sending in the directory
											// "#€&&()&=" into the constructor
											// fails to create a dir
			{
				throw new RuntimeException("Failed to create directory");
			}
		}
		if (saveFile.createNewFile())
		{
			requestSave();
		}
	}

	@Override
	public void createOrder(Order order)
	{
		if (!orderSet.add(order))
		{
			throw new RuntimeException("Could not add order: " + order.toString()
										+ "\n Make sure argument has not already been created (on disk)");
		}
	}

	@Override
	public void updateOrder(Order order)
	{
		if (orderSet.contains(order))
		{
			orderSet.remove(order);
			orderSet.add(order);
		}
		else
		{
			throw new RuntimeException("Order set does not contain specific order! " + order.toString());
		}

	}

	@Override
	public void readAll()
	{
		if (orderSet.size() > 0)
		{
			throw new RuntimeException("Data has already been read");
		}
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFile)))
		{
			orderSet = (Set<Order>) ois.readObject(); //reads whole set as object
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
		if (orderSet.contains(order))
		{
			if (!orderSet.remove(order))
			{
				throw new RuntimeException("Could not remove order! Make sure order already exists.");
			}
		}
		else
		{
			throw new RuntimeException("Order set does not contain specific order!");
		}
	}

	@Override
	public void requestSave()
	{
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(saveFile)))
		{
			out.writeObject(orderSet);
		}
		catch (IOException e)
		{
			e.getMessage();
			e.printStackTrace();
		}
	}
	
	@Override
	public Set<Order> getSet()
	{
		return new HashSet<Order>(orderSet);
	}
	
	@Override
	public String toString()
	{
		return orderSet.toString();
	}
}
