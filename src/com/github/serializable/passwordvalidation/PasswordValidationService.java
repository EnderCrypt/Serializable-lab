package com.github.serializable.passwordvalidation;

public class PasswordValidationService
{
	private PasswordValidator passwordValidator;

	public PasswordValidationService(PasswordValidator passwordValidator)
	{
		this.passwordValidator = passwordValidator;
	}

	public synchronized void validate(String password) throws PasswordRequirmentsNotMet
	{
		passwordValidator.reset();
		for (int i = 0; i < password.length(); i++)
		{
			passwordValidator.register(password.charAt(i));
		}
		passwordValidator.validate();
	}
	
	
}
