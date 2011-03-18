package org.deaygo.util.encryption.types;

import java.util.Map;

import org.deaygo.util.encryption.exceptions.MissingRequiredInfoException;

public interface TypeInterface
{
	String decrypt(String encryptedText);

	String encrypt(String plainText);

	void setAdditionalInfomation(Map<String, String> additionalInfo) throws MissingRequiredInfoException;
}
