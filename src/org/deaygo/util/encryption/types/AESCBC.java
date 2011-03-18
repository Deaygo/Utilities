package org.deaygo.util.encryption.types;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.deaygo.util.encryption.exceptions.MissingRequiredInfoException;
import org.deaygo.util.string.Padding;
import org.iharder.util.Base64;

public class AESCBC implements TypeInterface
{
	private String					secretKey	= "1234567890abcde";

	private String					ivKey		= "fedcba9876543210";

	private final SecretKeySpec		keySpec		= new SecretKeySpec(secretKey.getBytes(), "AES");

	private final IvParameterSpec	ivSpec		= new IvParameterSpec(ivKey.getBytes());

	@Override
	public String decrypt(final String encryptedText)
	{
		String decrypted = null;

		try
		{
			final Cipher cipherDc = Cipher.getInstance("AES/CBC/NoPadding");

			cipherDc.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

			final byte[] encryptedBytes = Base64.decode(encryptedText);

			final byte[] outText = cipherDc.doFinal(encryptedBytes);

			decrypted = new String(outText).trim();
		}
		catch (final NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		catch (final NoSuchPaddingException e)
		{
			e.printStackTrace();
		}
		catch (final InvalidKeyException e)
		{
			e.printStackTrace();
		}
		catch (final InvalidAlgorithmParameterException e)
		{
			e.printStackTrace();
		}
		catch (final IllegalBlockSizeException e)
		{
			e.printStackTrace();
		}
		catch (final BadPaddingException e)
		{
			e.printStackTrace();
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}

		return decrypted;
	}

	@Override
	public String encrypt(final String plainText)
	{
		final String padded = Padding.padString16(plainText);
		String encrypted = null;

		try
		{
			final Cipher cipherEc = Cipher.getInstance("AES/CBC/NoPadding");

			cipherEc.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

			final byte[] encryptedBytes = cipherEc.doFinal(padded.getBytes());

			encrypted = Base64.encodeBytes(encryptedBytes);
		}
		catch (final NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		catch (final NoSuchPaddingException e)
		{
			e.printStackTrace();
		}
		catch (final InvalidKeyException e)
		{
			e.printStackTrace();
		}
		catch (final InvalidAlgorithmParameterException e)
		{
			e.printStackTrace();
		}
		catch (final IllegalBlockSizeException e)
		{
			e.printStackTrace();
		}
		catch (final BadPaddingException e)
		{
			e.printStackTrace();
		}

		return encrypted;
	}

	public String getIvKey()
	{
		return ivKey;
	}

	public String getSecretKey()
	{
		return secretKey;
	}

	@Override
	public void setAdditionalInfomation(final Map<String, String> additionalInfo) throws MissingRequiredInfoException
	{
		if (additionalInfo.containsKey("secretKey"))
		{
			setSecretKey(additionalInfo.get("secretKey"));
		}
		else
		{
			throw new MissingRequiredInfoException("You are missing the required information of 'secretKey'.");
		}

		if (additionalInfo.containsKey("ivKey"))
		{
			setSecretKey(additionalInfo.get("ivKey"));
		}
		else
		{
			throw new MissingRequiredInfoException("You are missing the required information of 'ivKey'.");
		}

	}

	public void setIvKey(final String ivKey)
	{
		this.ivKey = ivKey;
	}

	public void setSecretKey(final String secretKey)
	{
		this.secretKey = secretKey;
	}
}
