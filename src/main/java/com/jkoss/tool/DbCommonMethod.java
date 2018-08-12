package com.jkoss.tool;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class DbCommonMethod implements Serializable {
	
	public static java.util.Random rdm = new java.util.Random();
	/**
	 * SimpleDateFormat("yyyy-MM-dd");
	 */
	public static SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public static SimpleDateFormat isoFormatMth = new SimpleDateFormat("yyyy-MM");
	/**
	 * SimpleDateFormat("yyyyMMdd");
	 */
	public static SimpleDateFormat iso0Format = new SimpleDateFormat("yyyyMMdd");
	/**
	 * SimpleDateFormat("HH:mm:ss");
	 */
	public static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	
	
	public static  SimpleDateFormat sdf01 = new SimpleDateFormat("yyyy.MM.dd");
	public static  SimpleDateFormat sdf02 = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
	public static  SimpleDateFormat sdf03 = new SimpleDateFormat("yyyy-MM月-dd");
	public static  SimpleDateFormat sdf04 = new SimpleDateFormat("mm:ss");
	public static  SimpleDateFormat sdf05 = new SimpleDateFormat("MM月dd日"); 
	public static  SimpleDateFormat sdf06 = new SimpleDateFormat("yyyy-MM-dd HH"); 
	public static  SimpleDateFormat sdf07 = new SimpleDateFormat("HH:mm"); 
	public static  SimpleDateFormat sdf08 = new SimpleDateFormat("yyyy-MM-dd EEE");
	/**
	 * SimpleDateFormat("HHmmss");
	 */
	public static SimpleDateFormat time2Format = new SimpleDateFormat("HHmmss");
	/**
	 * SimpleDateFormat("HHmmssSSS");
	 */
	public static SimpleDateFormat time3Format = new SimpleDateFormat(
			"HHmmssSSS");
	/**
	 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
	 */
	public static SimpleDateFormat iso2Format = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss.SSSSSS");
	/**
	 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	 */
	public static SimpleDateFormat iso1Format = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss.SSS");
	/**
	 * SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	 */
	public static SimpleDateFormat iso3Format = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss");
	/**
	 * SimpleDateFormat("yyyyMMddHHmmssSSS");
	 */
	public static SimpleDateFormat iso4Format = new SimpleDateFormat(
			"yyyyMMddHHmmssSSS");
	public static SimpleDateFormat iso5Format = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	
	public static String DRL_WORKING_FOLDER = "DRL_WORKING_FOLDER";

	public static Double MAX_AMT_15 = 9999999999999.99;

	public static Double MAX_AMT_18 = 9999999999999999.99;

	public static List<String> FS_MODS = new ArrayList<String>();

	public static  DecimalFormat df0 = new DecimalFormat("#");
	public static  DecimalFormat df1 = new DecimalFormat("#.0");
	public static  DecimalFormat df2 = new DecimalFormat("#.00");
	public static  DecimalFormat df3 = new DecimalFormat("#.000");
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String trimString(Object str) {
		if (str != null) {
			return trimSuffix(str.toString());
		}
		return null;
	}

	 
	public static String trimStringDecimal(Object str) {
		if (str instanceof Date) {
			return time2Format.format((Date) str);
		}
		if (str != null) {
			if (str.toString().trim().length() > 0) {
				return trimSuffix(str.toString());
			} else {
				return "0";
			}
		}
		return null;
	}

	 
	public static int compareTo(Date date1, Date date2) {
		if (date1 == null) {
			return date2 == null ? 0 : -1;
		} else {
			return date1.compareTo(date2);
		}
	}
 
	public static int compareTo(BigDecimal dec1, BigDecimal dec2) {
		if (dec1 == null) {
			return dec2 == null ? 0 : -1;
		} else {
			return dec1.compareTo(dec2);
		}
	}

	 
	public static boolean isBlank(String str) {
		return str == null || str.length() == 0 || " ".equals(str);
	}

	 
	public static boolean isBlank(BigDecimal str) {
		return str == null || str.doubleValue() == 0;
	}

 
	public static boolean isBlank(double str) {
		return str == 0;
	}

	 
	public static int compareTo(String str1, String str2) {
		if (str1 == null) {
			if (str2 == null) {
				return 0;
			} else {
				return isBlank(str2) ? 0 : -1;
			}
		} else {
			if (str1.length() == 0) {
				return isBlank(str2) ? 0 : -1;
			}
			if (str2 == null || isBlank(str2)) {
				return 1;
			}
			return str1.compareTo(str2);
		}
	}

 
	public static String trimSuffix(String s) {

		int len = s.length();
		char[] val = s.toCharArray();
		while (len > 0 && (val[len - 1] <= ' ')) {
			len--;
		}
		return ((len > 0) && len <= s.length()) ? s.substring(0, len) : s;
	}

	 
	public static String intToString(int i, int length) {
		String result = String.valueOf(i);
		try {
			while (result.length() < length) {
				result = "0" + result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	 
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("^[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	 
	public static boolean isDate(String strDate, String dateFormat) {
		SimpleDateFormat df = new SimpleDateFormat(dateFormat);
		try {
			Date tmpDate = df.parse(strDate);
			String strTempDate = df.format(tmpDate);
			if (!strTempDate.equals(strDate)) {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	 
	public static String strToL(String str, int length) {
		try {
			if (str == null) {
				str = "";
			}
			if (str.length() > length) {
				str = str.substring(0, length);
			}
			while (str.length() < length) {
				str = str + " ";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	
	//生成可查询的月份
 
	
	//取得每一周的第一天和最后一天
	public static Date[] inWeek( Date date ) {
		 Date[] datas = new Date[2];
		 Calendar c = Calendar.getInstance();
		 c.setTime(date);
		 int wk = c.get(Calendar.DAY_OF_WEEK);
		 if(wk>1) {
			 c.add(Calendar.DAY_OF_MONTH, -(wk-2));
			 datas[0]= c.getTime();
			 c.add(Calendar.DAY_OF_MONTH, 6);
			 datas[1]= c.getTime(); 
		 }else {
			 //处理周日
			 datas[1]= c.getTime(); 
			 c.add(Calendar.DAY_OF_MONTH, -6);
			 datas[0]= c.getTime(); 
		 }
		
		
		return datas;
	}
	
	//计算本月的工作日
	public static int getMthWorkDay(java.util.Calendar cal) {
		
		 int count = 0;

		 int nextM =cal.get(Calendar.MONTH)+1;
		 while (cal.get(Calendar.MONTH) <nextM) {
			int day = cal.get(Calendar.DAY_OF_WEEK);
			if (!(day == Calendar.SUNDAY || day == Calendar.SATURDAY)) {
				count++;
			}
			cal.add(Calendar.DATE, 1);
		}
		 
		return count;
	}
	
	public static float  round(float f,int num) {
	       switch (num) {
		case 0:
			return  Float.parseFloat(df0.format(f)) ;
		case 1:
			return  Float.parseFloat(df1.format(f)) ;
		case 2:
			return  Float.parseFloat(df2.format(f)) ;
		default:
			return  Float.parseFloat(df3.format(f)) ;
		}
		   
	 }
	
	public static float  calTax(float sal) {
               //工资、起征线、社保个人  
	        float qzLine =3500f; 
	        float 计税 = sal - qzLine ;
	        float 应交税 =0f;
	         //
	        if(计税 <0){
	      	  return 0;
	        }else if(计税 <1500){
	                      应交税=计税*0.03f;
	        }else if(计税 >= 1500 && 计税< 4500 ){
	                           应交税=计税*0.1f-105;
	        }else if(计税 >= 4500 && 计税< 9000 ){
	                       应交税=计税*0.2f-555;
	        }else if(计税 >= 9000 && 计税< 35000 ){
	            应交税=计税*0.25f-1005;
	        }else if(计税 >= 35000 && 计税< 55000 ){
	            应交税=计税*0.3f-2755;
	        }else if(计税 >= 55000 && 计税< 80000 ){
	            应交税=计税*0.35f-5505;
	        }else if(计税 >=  80000 ){
	            应交税=计税*0.45f-13505;
	        }
	        return  round(应交税,2);
	 }

}
