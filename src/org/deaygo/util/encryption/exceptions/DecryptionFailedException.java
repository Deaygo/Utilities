package org.deaygo.util.encryption.exceptions;

public class DecryptionFailedException extends Exception
{
	private static final long	serialVersionUID	= -5781384917177993953L;

	public DecryptionFailedException(final String string)
	{
		super(string);
	}

	public DecryptionFailedException(final String string, final Exception e)
	{
		super(string, e);
	}

}
