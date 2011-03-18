package org.deaygo.util.encryption.exceptions;

public class EncryptionFailedException extends Exception
{
	private static final long	serialVersionUID	= -8894434793389108979L;

	public EncryptionFailedException(final String string)
	{
		super(string);
	}

	public EncryptionFailedException(final String string, final Exception e)
	{
		super(string, e);
	}
}
