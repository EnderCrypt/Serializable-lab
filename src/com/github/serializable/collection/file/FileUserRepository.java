package com.github.serializable.collection.file;

import java.io.File;

import com.github.serializable.collection.UserRepository;

/**
 * handles user objects by saving/loading them to files using serialization
 */
public class FileUserRepository implements UserRepository
{
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
	public void createUser()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readUser()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser()
	{
		// TODO Auto-generated method stub
		
	}

}
