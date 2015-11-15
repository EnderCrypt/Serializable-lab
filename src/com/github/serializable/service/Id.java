package com.github.serializable.service;

public class Id
{
	private int id = -1;
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	
	public boolean hasId()
	{
		return id>0;
	}
	
	@Override
	public int hashCode()
	{
		return getId() * 37;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (obj instanceof Id) 
		{
			Id otherId = (Id) obj;
			return this.getId() == otherId.getId();
		}
		return false;
	}
}