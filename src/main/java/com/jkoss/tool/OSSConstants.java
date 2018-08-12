package com.jkoss.tool;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

 
public  class OSSConstants {

 
	
	static{
		 Properties props = new Properties();
		 try {
			props.load(OSSConstants.class.getClassLoader().getResourceAsStream("db.properties"));
 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
