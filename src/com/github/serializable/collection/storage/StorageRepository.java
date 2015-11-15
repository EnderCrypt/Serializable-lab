package com.github.serializable.collection.storage;

public interface StorageRepository<T>
{
	public void createUnit(T unit);
	public void updateUnit(T unit);
	public void deleteUnit(T unit);
	public T getUnitById(int id);
}