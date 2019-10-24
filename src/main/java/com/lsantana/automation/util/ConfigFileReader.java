package com.lsantana.automation.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

public class ConfigFileReader {

	private Properties properties;
	private String propertyFilePath;

	public ConfigFileReader(String filePath) {
		propertyFilePath = filePath;

		FileInputStream reader = null;
		try {
			reader = new FileInputStream(new File(propertyFilePath));
			properties = new Properties();

			try {
				properties.load(new InputStreamReader(reader, Charset.forName("UTF-8")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Properties file not found at path : " + propertyFilePath);
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException ignore) {
			}
		}
	}

	public String getPropertyByKey(String key) {
		try {
			String prop = properties.getProperty(key);

			if (prop != null)
				return prop;
			else
				throw new RuntimeException(
						"Property not specified in the " + propertyFilePath + " file for the Key: " + key);
		} catch (Exception e) {
			throw new RuntimeException(
					"Property not specified in the " + propertyFilePath + " file for the Key: " + key);
		}
	}
}
