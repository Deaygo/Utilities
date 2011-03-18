package org.deaygo.util.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public abstract class PropertiesFileManager
{

	private static String				confPath	= "";
	protected final static Properties	defaults	= new Properties();
	protected final static Properties	properties	= new Properties(defaults);

	public static String get(final String key)
	{
		return properties.getProperty(key);
	}

	public static boolean getAsBoolean(final String key)
	{
		final String str = get(key);
		if (str == null)
		{
			return false;
		}
		return Boolean.valueOf(str).booleanValue();
	}

	public static double getAsDouble(final String key)
	{
		final String str = get(key);
		if (str == null)
		{
			return 0.00;
		}
		return Double.parseDouble(str);
	}

	public static int getAsInt(final String key)
	{
		final String str = get(key);
		if (str == null)
		{
			return 0;
		}
		return Integer.parseInt(str);
	}

	public static String getConfPath()
	{
		return confPath;
	}

	public static void init()
	{
		setConfPath("application.properties");
	}

	public static boolean load()
	{
		try
		{
			properties.load(new FileInputStream(getConfPath()));
			return true;
		}
		catch (final FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}

		return false;
	}

	public static boolean save()
	{
		try
		{
			properties.store(new FileOutputStream("conf/server.properties"), null);
			return true;
		}
		catch (final FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public static void set(final String key, final String value)
	{
		properties.setProperty(key, value);
	}

	public static void setConfPath(final String path)
	{
		confPath = path;
	}

	public HashMap<String, String> getKeysAsHashMap(final String... keys)
	{
		final HashMap<String, String> map = new HashMap<String, String>();

		for (final String s : keys)
		{
			final String val = get(s);
			if (val != null)
			{
				map.put(s, val);
			}
		}

		return map;
	}
}
