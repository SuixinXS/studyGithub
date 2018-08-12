package com.jkoss.cvt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
 

//自定义转换器
public class DateConvertor implements Converter<String, Date> {
	SimpleDateFormat smt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat smt2 = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	public Date convert(String arg0) {
		// <yyyy-MM-dd HH:mm:ss>   <yyyy-MM-dd>

	
		try {
			if(arg0==null || arg0.length()<9) {
				return null;
			}else {
				if ( arg0.length() < 11) {
					return smt2.parse(arg0);
				}else if ( arg0.length() < 18) {
					return smt.parse(arg0+":00");
				}else   {
					return smt.parse(arg0);
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
		
		return null;
	}

}
