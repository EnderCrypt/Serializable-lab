package com.github.serializable.collection;

import java.util.Set;

import com.github.serializable.collection.data.User;

/**
 * this interface allows you to change how ECommerce saves your user objects, by for example file serialization or database
 */
public interface UserRepository
{
	public void createUser(User user);
	public void updateUser(User user);
	public void readAll();
	public void deleteUser(User user);
	public void requestSave();
	public Set<User> getSet();
}
