package com.microservices.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader 
{
	Properties pro;
	
	public ConfigReader() throws IOException
	{
		try 
		{
			File src = new File(".//propertyfiles//uat.properties");
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
	} 
	
	public String getPMPUrl() {
		return pro.getProperty("pmp.url");
		
	}
	public String getPMPUsername() {
		return pro.getProperty("pmp.admin.username");
		
	}
	public String getPMPPassword() {
		return pro.getProperty("pmp.admin.password");
		
	}
	public String getPPUrl() {
		return pro.getProperty("pp.url");
		
	}
	public String getPPUsername() {
		return pro.getProperty("pp.username");
		
	}
	public String getPPPassword() {
		return pro.getProperty("pp.password");
		
	}
	
}
