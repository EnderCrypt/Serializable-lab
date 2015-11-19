package com.github.serializable.collection.storage;

public interface StorageRepository<T>
{
	/**
	 * Creates <tt>unit</tt> with specified type (by implementation) and adds it to the specified repository.
	 * @param <T> unit
	 */
	public void createUnit(T unit);
	/**
	 * Updates by removing existing <tt>unit</tt> and re-adding the <tt>unit</tt>
	 * @param <T> unit
	 */
	public void updateUnit(T unit);
	/**
	 * Checks if the <tt>unit</tt> exists and removes it from the repository
	 * @param unit
	 */
	public void deleteUnit(T unit);
	/**
	 * Retrieves the <tt>unit</tt> from the repository by looking for matching ID in the repository set
	 * @param unit.getId
	 * @return <T> unit
	 */
	public T getUnitById(int id);
	
	
}