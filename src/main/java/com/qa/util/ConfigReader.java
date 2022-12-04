package com.qa.util;

//import jdk.dynalink.beans.StaticClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	public static Properties prop;

	/**
	 * This method is used to load the properties from config.properties file
	 * @return it returns Properties prop object
	 */
	public static Properties init_prop() {

		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("src/test/resources/TestCaseData/CommonData.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	public static String getData(String data) throws IOException {
		init_prop();
		data = prop.getProperty(data);
		return data;
	}
}