package com.github.serializable.collection.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

public abstract class FileRepoAbstract<T>
{
	private File saveDirectory; 
	protected File saveFile;
	protected Set<T> set = new HashSet<>();
	public FileRepoAbstract(String directory) throws IOException
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

	public void requestSave()
	{
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(saveFile)))
		{
			out.writeObject(set);
		} 
		catch(IOException e)
		{
			e.getMessage();
			e.printStackTrace();		
		}
	}
	
	public Set<T> getSet()
	{
		return new HashSet<T>(set);
	}
	
	@Override
	public String toString()
	{
		return set.toString();
	}
}
