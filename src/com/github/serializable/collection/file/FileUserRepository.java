package com.github.serializable.collection.file;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import com.github.serializable.collection.UserRepository;
import com.github.serializable.collection.data.User;

/**
 * handles user objects by saving/loading them to files using serialization
 */
public class FileUserRepository implements UserRepository
{
	private Set<User> userSet = new HashSet<User>();
	private File saveDirectory; 
	public FileUserRepository(String directory)
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
	public void createUser(User user)
	{
		userSet.add(user);
	}

	@Override
	public void updateUser(User user)
	{
		if(userSet.contains(user))
		{
			userSet.remove(user);
			userSet.add(user);
		}
		else
		{
			throw new RuntimeException("The repository does not contain user: " + user.toString());
		}
	}

	@Override
	public void readUser(User user)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(User user)
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void requestSave()
	{
		// TODO Auto-generated method stub
		
	}

}
