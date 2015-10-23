package com.github.serializable.collection;

/**
 * this interface allows you to change how ECommerce saves your user objects, by for example file serialization or database
 */
public interface UserRepository
{
	public void createUser();
	public void updateUser();
	public void readUser();
	public void deleteUser();
}
