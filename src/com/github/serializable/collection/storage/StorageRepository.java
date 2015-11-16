package com.github.serializable.collection.storage;

public interface StorageRepository<T>
{
	/**
	 * Creates unit with specified type (by implementation) and adds it to the specified repository.
	 * @param <T> unit
	 */
	public void createUnit(T unit);
	public void updateUnit(T unit);
	public void deleteUnit(T unit);
	public T getUnitById(int id);
//	public int getNewId();
}