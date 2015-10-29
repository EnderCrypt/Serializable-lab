package com.github.serializable.collection.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Set;

import com.github.serializable.collection.UserRepository;
import com.github.serializable.collection.data.User;

/**
 * handles user objects by saving/loading them to files using serialization
 */
public class FileUserRepository extends FileRepoAbstract<User> implements UserRepository
{
	public FileUserRepository(String directory) throws IOException
	{
		super(directory);
	}

	@Override
	public void createUser(User user)
	{
		if(!set.add(user))
		{
			throw new RuntimeException("Could not add user: " + user.toString()
										+ "\n Make sure argument has not already been created (on disk)");
		}
	}

	@Override
	public void updateUser(User user)
	{
		if(set.contains(user))
		{
			set.remove(user);
			set.add(user);
		}
		else
		{
			throw new RuntimeException("User set does not contain specific user!: " + user.toString());
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
			set = (Set<User>) ois.readObject();
		}
		
		//Catch
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
	public void deleteUser(User user)
	{
		if(set.contains(user))
		{
			if(!set.remove(user))
			{
				throw new RuntimeException("Could not remove user! Make sure user already exists.");
			}
		}
		else
		{
			throw new RuntimeException("User set does not contain specific user!");
		}
		
	}

}
