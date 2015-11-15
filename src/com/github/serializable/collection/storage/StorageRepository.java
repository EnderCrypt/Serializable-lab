package com.github.serializable.collection.storage;

import java.util.Set;

public interface StorageRepository<T>
{
	public void createUnit(T unit);
	public void updateUnit(T unit);
	public void readAll();
	public void deleteUnit(T unit);
	public void requestSave();
	public Set<T> getSet();
}
