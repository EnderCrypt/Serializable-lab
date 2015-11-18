package com.github.serializable.collection.file;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.github.serializable.collection.storage.StorageRepository;
import com.github.serializable.service.Id;

/**
 * handles order objects by saving/loading them to files using serialization
 */
public class FileRepository<T extends Id> extends FileRepoAbstract<T> implements StorageRepository<T>
{	
	
	public FileRepository(String directory) throws IOException
	{
		super(directory);
	}

	
	@Override
	public void createUnit(T unit)
	{
		if(!unit.hasId())
		{
			unit.setId(nextId());
		}
		set.add(unit);
	}

	@Override
	public void updateUnit(T unit)
	{
		if (set.contains(unit))
		{
			set.remove(unit);
			set.add(unit);
		}
	}

	@Override
	public void deleteUnit(T unit)
	{
		set.remove(unit);
	}
	
	@Override
	public T getUnitById(int id)
	{
		//TODO: by shitvard
		//if set contains unit.getId
		//return the unit with id
		return null;
	}
	
	@Override
	public Set<T> getAllUnits()
	{
		return new HashSet<T>(set);
	}
}
