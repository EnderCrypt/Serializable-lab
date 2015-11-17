package com.github.serializable.exceptions;

@SuppressWarnings("serial")
public class ArgumentIdException extends RuntimeException
{

	public ArgumentIdException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ArgumentIdException(String message)
	{
		super(message);
	}

	public ArgumentIdException(Throwable cause)
	{
		super(cause);
	}

}
