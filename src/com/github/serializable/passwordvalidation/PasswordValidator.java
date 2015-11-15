package com.github.serializable.passwordvalidation;

public interface PasswordValidator
{

	public void reset();
	public void register(char c) throws PasswordRequirmentsNotMet;
	public void validate() throws PasswordRequirmentsNotMet;

}
