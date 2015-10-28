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

import com.github.serializable.collection.UserRepository;
import com.github.serializable.collection.data.User;

/**
 * handles user objects by saving/loading them to files using serialization
 */
public class FileUserRepository implements UserRepository
{
	private File saveDirectory; 
	private File saveFile;
	private Set<User> userSet = new HashSet<User>();
	public FileUserRepository(String directory) throws IOException
	{
		// init variables
		saveDirectory = new File(directory);
		saveFile = new File(directory+"/data");
		// create directory
		if (!saveDirectory.exists())
		{
			if (!saveDirectory.mkdirs()) // for example sending in the directory "#€&&()&=" into the constructor fails to create a dir
			{
				throw new RuntimeException("Failed to create directory");
			}
		}
		if(saveFile.createNewFile())
		{
			requestSave();
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
			throw new RuntimeException("User set does not contain specific user!: " + user.toString());
		}
	}

	@Override
	public void readAll()
	{
		if (userSet.size() > 0)
		{
			throw new RuntimeException("Data has already been read");
		}
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFile)))
		{
			userSet = (Set<User>) ois.readObject();
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
		if(userSet.contains(user))
		{
			if(!userSet.remove(user))
			{
				throw new RuntimeException("Could not remove user! Make sure user already exists.");
			}
		}
		else
		{
			throw new RuntimeException("User set does not contain specific user!");
		}
		
	}
	
	@Override
	public void requestSave()
	{
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(saveFile)))
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
