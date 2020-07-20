package config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import test.addAndDeleteAddress;


public class PropertiesFile {

	static Properties prop = new Properties();
	static String projectPath = System.getProperty("user.dir");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getProperties();
		setProperties();


	}

	public static void getProperties() {

		try {


			InputStream input = new FileInputStream(projectPath+"/src/test/java/config/config.properties");
			prop.load(input);
			String browser = prop.getProperty("browser");
			System.out.println("Browser is "+browser);
			addAndDeleteAddress.browserName=browser;
		} catch (Exception exp) {
			System.out.println("Exception Message:"+exp.getMessage());
			System.out.println("Exception Cause:"+exp.getCause());
			exp.printStackTrace();
		}
	}

	public static void setProperties() {

		try {
			OutputStream output = new FileOutputStream(projectPath+"/src/test/java/config/config.properties");
			prop.setProperty("browser", "Chrome");
			prop.store(output, null);
			
		} catch (Exception exp) {
			System.out.println("Exception Message:"+exp.getMessage());
			System.out.println("Exception Cause:"+exp.getCause());
			exp.printStackTrace();
		}
	}
}
