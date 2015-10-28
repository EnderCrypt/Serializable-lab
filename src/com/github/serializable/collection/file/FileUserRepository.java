package com.github.serializable.collection.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
		if(!userSet.add(user))
		{
			throw new RuntimeException("Could not add user: " + user.toString());
		}
				
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
	public Set<User> readUser()
	{
		return new HashSet<>(userSet);
	}

	@Override
	public void deleteUser(User user)
	{
		if(userSet.contains(user))
		{
			if(!userSet.remove(user))
			{
				throw new RuntimeException("Could not remove user! Make sure user already exists.");
			}
		}
		
	}
	
	@Override
	public void requestSave()
	{
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(saveDirectory)))
		{
			out.writeObject(userSet);
		} 
		catch(IOException e)
		{
			e.getMessage();
			e.printStackTrace();		
		}
	}

}
