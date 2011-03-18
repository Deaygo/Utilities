package org.deaygo.util.string;

public class Padding
{
	public static String padString16(final String text)
	{
		final int mod = 16 - (text.length() % 16);
		String empty = "";
		for (int i = 0; i < mod; i++)
		{
			empty = empty.concat(" ");
		}
		return text.concat(empty);
	}
}
