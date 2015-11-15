package com.github.serializable.passwordvalidation;

@SuppressWarnings("serial")
public class PasswordRequirmentsNotMet extends Exception
{
	public PasswordRequirmentsNotMet()
	{
		super();
	}
	
	public PasswordRequirmentsNotMet(String string)
	{
		super(string);
	}

}
