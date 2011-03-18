package org.deaygo.util.encryption;

import java.util.HashMap;
import java.util.Map;

import org.deaygo.util.encryption.exceptions.DecryptionFailedException;
import org.deaygo.util.encryption.exceptions.EncryptionFailedException;
import org.deaygo.util.encryption.types.AESCBC;
import org.deaygo.util.encryption.types.TypeInterface;

public class EncryptionManager
{
	@SuppressWarnings({ "rawtypes", "serial" })
	private static HashMap<String, Class>	registeredTypes	= new HashMap<String, Class>()
															{
																{
																	put("AESCBC", AESCBC.class);
																}
															};

	@SuppressWarnings("rawtypes")
	private static boolean isTypeInterface(final Class clazz)
	{
		for (final Class c : clazz.getInterfaces())
		{
			if (c.getName() == TypeInterface.class.getName())
			{
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	public static boolean registerType(final String typeName, final Class clazz)
	{
		if (registeredTypes.containsKey(typeName))
		{
			return false;
		}

		if (!isTypeInterface(clazz))
		{
			return false;
		}

		return true;
	}

	public EncryptionManager()
	{
		//
	}

	public String decryptString(final String type, final String encryptedText, final Map<String, String> additionalParams)
			throws DecryptionFailedException
	{
		if (!registeredTypes.containsKey(type))
		{
			throw new DecryptionFailedException("The requested type (" + type + ") is not registered.");
		}
		TypeInterface crypt = null;

		try
		{
			crypt = (TypeInterface) registeredTypes.get(type).newInstance();
			crypt.setAdditionalInfomation(additionalParams);
		}
		catch (final Exception e)
		{
			e.printStackTrace();
			throw new DecryptionFailedException("Could not instantiate the encryption class for type " + type, e);
		}

		return crypt.decrypt(encryptedText);
	}

	public String encryptString(final String type, final String plainText, final Map<String, String> additionalParams)
			throws EncryptionFailedException
	{
		if (!registeredTypes.containsKey(type))
		{
			throw new EncryptionFailedException("The requested type (" + type + ") is not registered.");
		}

		TypeInterface crypt = null;

		try
		{
			crypt = (TypeInterface) registeredTypes.get(type).newInstance();
			crypt.setAdditionalInfomation(additionalParams);
		}
		catch (final Exception e)
		{
			e.printStackTrace();
			throw new EncryptionFailedException("Could not instantiate the encryption class for type " + type, e);
		}

		return crypt.encrypt(plainText);
	}
}
