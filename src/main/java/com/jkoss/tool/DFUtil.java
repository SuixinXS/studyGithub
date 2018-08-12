package com.jkoss.tool;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Months;
import org.joda.time.Seconds;
import org.joda.time.Years;
import org.joda.time.format.ISODateTimeFormat;

/**
 * @class ��������
 * 
 * @author Administrator
 * 
 */
public abstract class DFUtil extends DbCommonMethod {

	/**
	 * �ж�List��ûֵ
	 * 
	 * @param list
	 * @return
	 */
	public static Boolean checkList(List list) {

		return (list != null || list.size() > 0);
	}

	/**
	 * ͨ����������javaBean
	 * 
	 * @param beanObj
	 */
	public static Object cloneJavaBean(Object beanObj) {
		try {
			Method cloneMethod = beanObj.getClass().getMethod("clone");
			Object javaBean = cloneMethod.invoke(beanObj);
			return javaBean;
		} catch (Exception ex) {
			//
		}
		return null;
	}

	/**
	 * ����String ����.
	 * 
	 * @param dao
	 * @return
	 */
	public static int size(String dao) {
		if (dao != null) {
			return dao.length();
		}
		return 0;
	}

	/**
	 * ����BigDecimal ����.
	 * 
	 * @param dao
	 * @return
	 */
	public static int size(BigDecimal dao) {
		if (dao != null) {
			return 17;
		}
		return 0;
	}

	/**
	 * �������ͽ�λ.
	 * 
	 * @param d
	 *            ����λ������
	 * @param len
	 *            ����
	 * @param prec
	 *            ��Ҫ��ȷ��С�����Ժ�λ��
	 * @return
	 */
	public static double dech(double d, int len, int prec) {
		BigDecimal bigDecimal = new BigDecimal(d);
		if (bigDecimal != null) {
			bigDecimal = bigDecimal.setScale(prec, RoundingMode.HALF_UP);
		}
		return bigDecimal.doubleValue();
	}

	/**
	 * �ַ���ת��Ϊ����.
	 * 
	 * @param ss
	 * @return
	 */
	public static BigDecimal toBigDecimal(String ss) {
		BigDecimal ret = null;
		if (ss != null && ss.trim().length() > 0) {
			try {
				ret = new BigDecimal(ss.trim());
			} catch (Exception ex) {
			}
		}
		return ret;
	}

	/**
	 * ������ֱ�ӵ����ڲ�,�·�.
	 * 
	 * *MSECONDS|*SECONDS|*MINUTES|*HOURS|*DAYS|*MONTHS|*YEARS
	 * 
	 * @param tDate
	 *            ���ڸ�ʽ"20150404"
	 * @param txDate
	 *            ���ڸ�ʽ"20150404"
	 * @param type
	 *            type ѡ�� DAYS||MONTHS||YEARS
	 * @return
	 */
	public static int diff(String tDate, String txDate, String type) {
		if (txDate != null && tDate != null) {
			DateTime txDT = DateTime.parse(txDate,
					ISODateTimeFormat.basicDate());
			DateTime tDT = DateTime.parse(tDate, ISODateTimeFormat.basicDate());
			if (type.toUpperCase().endsWith("DAYS")) {
				return Days.daysBetween(txDT, tDT).getDays();
			} else if (type.toUpperCase().endsWith("MONTHS")) {
				return Months.monthsBetween(txDT, tDT).getMonths();
			} else if (type.toUpperCase().endsWith("YEARS")) {
				return Years.yearsBetween(txDT, tDT).getYears();
			} else if (type.toUpperCase().endsWith("YEARS")) {
				return Years.yearsBetween(txDT, tDT).getYears();
			}
		}
		return 0;
	}

	/**
	 * ���ڼӷ�.
	 * 
	 * @param txDate
	 * @param proi
	 *            Ϊ����ʱΪ����
	 * @param type
	 *            *DAYS|*MONTHS|*YEARS
	 * @return
	 */
	public static Date plus(Date txDate, int proi, String type) {
		if (txDate != null) {
			DateTime txDT = new DateTime(txDate);
			if (type.toUpperCase().endsWith("DAYS")) {
				return txDT.plusDays(proi).toDate();
			} else if (type.toUpperCase().endsWith("MONTHS")) {
				return txDT.plusMonths(proi).toDate();
			} else if (type.toUpperCase().endsWith("YEARS")) {
				return txDT.plusYears(proi).toDate();
			}
		}
		return txDate;
	}

	/**
	 * ���ڼӷ�
	 * 
	 * @param txDate
	 *            ���ڸ�ʽ"20150404"
	 * @param proi
	 *            DAYS|���ϼ���|MONTHS|���ϼ���|YEARS|���ϼ���
	 * @param type
	 *            ѡ�� DAYS||MONTHS||YEARS
	 * @return
	 */
	public static String plus(String txDate, int proi, String type) {
		if (txDate != null) {
			DateTime txDT = DateTime.parse(txDate,
					ISODateTimeFormat.basicDate());
			if (type.toUpperCase().endsWith("DAYS")) {
				return txDT.plusDays(proi).toString(
						ISODateTimeFormat.basicDate());
			} else if (type.toUpperCase().endsWith("MONTHS")) {
				return txDT.plusMonths(proi).toString(
						ISODateTimeFormat.basicDate());
			} else if (type.toUpperCase().endsWith("YEARS")) {
				return txDT.plusYears(proi).toString(
						ISODateTimeFormat.basicDate());
			}
		}
		return txDate;
	}

	/**
	 * ���ڼӷ�
	 * 
	 * @param txDate
	 *            ���ڸ�ʽ"20150404"
	 * @param proi
	 *            DAYS|���ϼ���|MONTHS|���ϼ���|YEARS|���ϼ���
	 * @param type
	 *            ѡ�� DAYS||MONTHS||YEARS
	 * @return
	 */
	public static Date plusDate(String txDate, int proi, String type) {
		if (txDate != null) {
			DateTime txDT = DateTime.parse(txDate,
					ISODateTimeFormat.basicDate());
			if (type.toUpperCase().endsWith("DAYS")) {
				return txDT.plusDays(proi).toDate();
			} else if (type.toUpperCase().endsWith("MONTHS")) {
				return txDT.plusMonths(proi).toDate();
			} else if (type.toUpperCase().endsWith("YEARS")) {
				return txDT.plusYears(proi).toDate();
			}
			return txDT.toDate();
		}
		return null;
	}

	/**
	 * ���ڼӷ�
	 * 
	 * @param txDate
	 *            ���ڸ�ʽ"20150404"
	 * @param proi
	 *            DAYS|���ϼ���|MONTHS|���ϼ���|YEARS|���ϼ���
	 * @param type
	 *            ѡ�� DAYS||MONTHS||YEARS
	 * @return
	 */
	public static String plus(double txDate, int proi, String type) {
		return plus((int) txDate, proi, type);
	}

	/**
	 * ���ڼӷ�
	 * 
	 * @param txDate
	 *            ���ڸ�ʽ"20150404"
	 * @param proi
	 *            DAYS|���ϼ���|MONTHS|���ϼ���|YEARS|���ϼ���
	 * @param type
	 *            ѡ�� DAYS||MONTHS||YEARS
	 * @return
	 */
	public static String plus(int txDate, int proi, String type) {
		return plus("" + txDate, proi, type);
	}

	/**
	 * ���ڼӷ�
	 * 
	 * @param txDate
	 *            ���ڸ�ʽ"20150404"
	 * @param proi
	 *            DAYS|���ϼ���|MONTHS|���ϼ���|YEARS|���ϼ���
	 * @param type
	 *            ѡ�� DAYS||MONTHS||YEARS
	 * @return
	 */
	public static String plus(BigDecimal txDate, int proi, String type) {
		if (txDate != null) {
			return plus(txDate.intValue(), proi, type);
		}
		return null;
	}

	/**
	 * 
	 * ���ڼӷ� 2��������ӣ������Ҫ���ڼӷ��Ļ����밴�ո�ʽ�����ڡ�20140202����������Ҫ���һ���£�����Ҫ��100
	 * ������������밴��ͨ�ӷ�ʹ��
	 * 
	 * @param txDate
	 * @param proi
	 *            // BigDecimal(int) ����һ�����в�����ָ������ֵ�Ķ��� //BigDecimal(double)
	 *            ����һ�����в�����ָ��˫����ֵ�Ķ��� //BigDecimal(long) ����һ�����в�����ָ��������ֵ�Ķ���
	 *            //BigDecimal(String) ����һ�����в�����ָ�����ַ�����ʾ����ֵ�Ķ���
	 * @return
	 */
	public static BigDecimal plus(BigDecimal txDate, BigDecimal proi) {
		if (txDate == null) {
			txDate = new BigDecimal(0);
		}
		if (proi == null) {
			proi = new BigDecimal(0);
		}
		return txDate.add(proi);
	}

	/**
	 * ���ڼ��� 2��������ӣ������Ҫ���ڼ����Ļ����밴�ո�ʽ�����ڡ�20140202����������Ҫ��һ���£�����Ҫ��100 ������������밴��ͨ����ʹ��
	 * 
	 * @param txDate
	 * @param proi
	 *            // BigDecimal(int) ����һ�����в�����ָ������ֵ�Ķ��� //BigDecimal(double)
	 *            ����һ�����в�����ָ��˫����ֵ�Ķ��� //BigDecimal(long) ����һ�����в�����ָ��������ֵ�Ķ���
	 *            //BigDecimal(String) ����һ�����в�����ָ�����ַ�����ʾ����ֵ�Ķ���
	 * @return
	 */
	public static BigDecimal subtract(BigDecimal txDate, BigDecimal proi) {
		if (txDate == null) {
			txDate = new BigDecimal(0);
		}
		if (proi == null) {
			proi = new BigDecimal(0);
		}
		return txDate.subtract(proi);
	}

	/**
	 * ������ֱ�ӵ����ڲ�,�·�.
	 * 
	 * @param tDate
	 * @param txDate
	 * @param type
	 *            ѡ�� DAYS||MONTHS||YEARS||HOURS||MSECONDS||MINUTES||SECONDS
	 * @return
	 */
	public static int diff(Date tDate, Date txDate, String type) {
		if (txDate != null && tDate != null) {
			DateTime txDT = new DateTime(txDate);
			DateTime tDT = new DateTime(tDate);
			if (type != null) {
				if (type.toUpperCase().endsWith("DAYS")
						|| type.toUpperCase().endsWith("D")) {
					return Days.daysBetween(txDT, tDT).getDays();
				} else if (type.toUpperCase().endsWith("MONTHS")) {
					return Months.monthsBetween(txDT, tDT).getMonths();
				} else if (type.toUpperCase().endsWith("YEARS")
						|| type.toUpperCase().endsWith("Y")) {
					return Years.yearsBetween(txDT, tDT).getYears();
				} else if (type.toUpperCase().endsWith("HOURS")
						|| type.toUpperCase().endsWith("H")) {
					return Hours.hoursBetween(txDT, tDT).getHours();
				} else if (type.toUpperCase().endsWith("MSECONDS")) {
					return Seconds.secondsBetween(txDT, tDT).getSeconds();
				} else if (type.toUpperCase().endsWith("MINUTES")) {
					return Minutes.minutesBetween(txDT, tDT).getMinutes();
				} else if (type.toUpperCase().endsWith("SECONDS")
						|| type.toUpperCase().endsWith("S")) {
					return Seconds.secondsBetween(txDT, tDT).getSeconds();
				}
			}
		}
		return 0;
	}

	/**
	 * ��int[]��ȡString��int[]���Ⱦ���Ҫ��ȡ���ٴΣ�int[1]���ǽ�ȡ�ַ����ĳ���
	 * 
	 * @see int[]a={1,2,3}; String s[] = toArray("sssslllppoojjj", a); for
	 *      (String string : s) { System.out.println(string.toString()); } ���s
	 *      ss sll
	 * @param source
	 *            String
	 * @param lens
	 *            int[]
	 * @return
	 */
	public static String[] toArray(String source, int[] lens) {
		if (source == null || lens.length == 0) {
			return new String[0];
		}

		int sLen = source.length();
		int splitLen = lens.length;
		String[] subStrings = new String[splitLen];
		for (int i = 0, j = 0; i < splitLen; i++) {
			if (sLen >= j + lens[i]) {
				subStrings[i] = source.substring(j, j + lens[i]);
			} else {
				subStrings[i] = source.substring(j, sLen);
				break;
			}

			j += lens[i];
		}

		return subStrings;
	}

	/**
	 * ���ַ���ת��Ϊbyte[]
	 * 
	 * @param s
	 * @param len
	 *            byte����
	 * @param charset
	 *            �ַ���
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static List<byte[]> toByteArray(String s, int len, String charset)
			throws UnsupportedEncodingException {
		return toByteArray(s, len, charset, 1);
	}

	/**
	 * ���ַ���ת��Ϊbyte[]
	 * 
	 * @param s
	 * @param len
	 *            byte����
	 * @param charset
	 *            �ַ���
	 * @param agliment
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static List<byte[]> toByteArray(String s, int len, String charset,
			int agliment) throws UnsupportedEncodingException {
		List<byte[]> target = new ArrayList<byte[]>();
		if (s != null) {
			byte[] bytes = s.getBytes(charset);
			byte[] buf = new byte[len];
			int length = bytes.length;
			int j = 0;
			if (agliment == 0) { // �Ҷ���
				int i = length - 1;
				for (; i >= 0; i--) {
					if (j == len) {
						target.add(0, buf);
						j = 0;
						buf = new byte[len];
					}
					j++;
					buf[len - j] = bytes[i];
				}
				// ǰ׺�ӿո�
				j++;
				if (len > j) {
					for (; j <= len; j++) {
						buf[len - j] = ' ';
					}
					target.add(0, buf);
				}
			} else {
				int i = 0;
				for (; i < length; i++) {
					if (j == len) {
						target.add(0, buf);
						j = 0;
						buf = new byte[len];
					}
					buf[j] = bytes[i];
					j++;
				}

				// ��׺�ӿո�
				if (len > j) {
					for (; j < len; j++) {
						buf[j] = ' ';
					}
					target.add(0, buf);
				}
			}
		}
		return target;
	}

	/**
	 * �ѳ��ַ����������.��ÿ���ֶγ���Ϊlen
	 * 
	 * @param source
	 *            String
	 * @param len
	 *            ָ������
	 * @return
	 */
	public static String[] toArray(String source, int len) {
		if (source != null) {
			int length = source.length();
			String[] strings = new String[0];
			StringBuffer buffer = new StringBuffer();

			for (int i = 0, j = 0; i < length; i++) {
				if (j < len) {
					buffer.append(source.charAt(i));
				} else {
					String[] tmp = new String[strings.length + 1];
					System.arraycopy(strings, 0, tmp, 0, strings.length);
					tmp[strings.length] = buffer.toString();
					strings = tmp;
					buffer = new StringBuffer();
					buffer.append(source.charAt(i));
					j = 0;
				}
				j++;
			}

			String[] tmp = new String[strings.length + 1];
			System.arraycopy(strings, 0, tmp, 0, strings.length);
			tmp[strings.length] = buffer.toString();
			strings = tmp;
			return strings;
		}
		return new String[0];
	}

	/**
	 * �������ͽ�λ.
	 * 
	 * @param bigDecimal
	 *            ����λ������
	 * @param len
	 *            ����
	 * @param prec
	 *            ��Ҫ��ȷ��С�����Ժ�λ��
	 * @return
	 */
	public static BigDecimal dech(BigDecimal bigDecimal, int len, int prec) {
		if (bigDecimal != null) {
			bigDecimal = bigDecimal.setScale(prec, RoundingMode.HALF_UP);
		}
		return bigDecimal;
	}

	/**
	 * �������ͽ�λ..
	 * 
	 * @param bigDecimal
	 *            ����
	 * @param len
	 * @param prec
	 *            ��bigDecimal�����С���㣬�����Ӧ���ȵ�0
	 * @return
	 */
	public static BigDecimal dec(Object bigDecimal, int len, int prec) {
		if (bigDecimal != null && bigDecimal.toString().trim().length() > 0) {
			BigDecimal dec = new BigDecimal(bigDecimal.toString().trim());
			return dec.setScale(prec, RoundingMode.HALF_DOWN);
		} else {
			return new BigDecimal(0);
		}
	}

	/**
	 * �������ͽ�λ.ISO.
	 * 
	 * @param bigDecimal
	 * @param iso
	 * @return
	 */
	public static BigDecimal dec(Object bigDecimal, String iso) {
		if (bigDecimal instanceof Date) {
			String dateDec = char2(bigDecimal, iso);
			return new BigDecimal(dateDec);
		}
		return new BigDecimal(0);
	}

	/**
	 * �������ͽ�λ..
	 * 
	 * @param bigDecimal
	 * @param len
	 * @param prec
	 * @return
	 */
	// public static double dec(Object bigDecimal, int len, int prec) {
	// if (bigDecimal != null) {
	// return dech(new BigDecimal(bigDecimal.toString()), len,
	// prec).doubleValue();
	// } else {
	// return 0;
	// }
	// }
	/**
	 * ת��Ϊ�ַ���.
	 * 
	 * @param obj
	 * @return
	 */
	public static String char2(Object obj, int len, int prec) {
		if (obj instanceof Double || obj instanceof Integer
				|| obj instanceof Long || obj instanceof Float
				|| obj instanceof BigDecimal) {
			BigDecimal bigDecimal;
			if (obj instanceof Double) {
				bigDecimal = new BigDecimal((Double) obj);
			} else if (obj instanceof Integer) {
				bigDecimal = new BigDecimal((Integer) obj);
			} else if (obj instanceof Float) {
				bigDecimal = new BigDecimal((Float) obj);
			} else if (obj instanceof Long) {
				bigDecimal = new BigDecimal((Long) obj);
			} else {
				bigDecimal = (BigDecimal) obj;
			}
			bigDecimal = bigDecimal.setScale(prec, RoundingMode.HALF_UP);
			return bigDecimal.toString();
		}
		return char2(obj);
	}

	/**
	 * ת��Ϊ�ַ���.
	 * 
	 * @param obj
	 * @return
	 */
	public static String char2(double obj) {
		return "" + obj;
	}

	/**
	 * ת��Ϊ�ַ���.
	 * 
	 * @param obj
	 * @return
	 */
	public static String char2(int obj) {
		return "" + obj;
	}

	/**
	 * ת��Ϊ�ַ���.
	 * 
	 * @param obj
	 * @return
	 */
	public static String char2(long obj) {
		return "" + obj;
	}

	/**
	 * ת��Ϊ�ַ���.
	 * 
	 * @param obj
	 * @return
	 */
	public static String char2(float obj) {
		return "" + obj;
	}

	/**
	 * ת��Ϊ�ַ���.
	 * 
	 * @param obj
	 * @return
	 */
	public static String char2(Object obj) {
		if (obj instanceof Double || obj instanceof Integer
				|| obj instanceof Long) {
			BigDecimal bigDecimal;
			if (obj instanceof Double) {
				bigDecimal = new BigDecimal((Double) obj);
				return bigDecimal.toString();
			} else if (obj instanceof Integer) {
				return ((Integer) obj).toString();
			} else {
				return ((Long) obj).toString();
			}
		}
		if (obj != null) {
			return obj.toString();
		}
		return "";
	}

	/**
	 * ��ʽ������
	 * 
	 * @param obj
	 *            ����
	 * @param format
	 * @ISO0 SimpleDateFormat("yyyyMMdd");
	 * @ISO SimpleDateFormat("yyyy-MM-dd");
	 * @ISO2 SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
	 * @ISO1 SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	 * @ISO3 SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	 * @return
	 */
	public static String char2(Object obj, String format) {
		if (obj != null) {
			if (obj instanceof Date && format != null) {
				if (format.toUpperCase().endsWith("ISO0")) {
					return iso0Format.format((Date) obj);
				} else if (format.toUpperCase().endsWith("ISO")) {
					return isoFormat.format((Date) obj);
				} else if (format.toUpperCase().endsWith("ISO2")) {
					return iso2Format.format((Date) obj);
				} else if (format.toUpperCase().endsWith("ISO1")) {
					return iso1Format.format((Date) obj);
				} else if (format.toUpperCase().endsWith("ISO3")) {
					return iso3Format.format((Date) obj);
				}
			}
			return char2(obj);
		}
		return "";
	}

	/**
	 * ��ȡ�� ���� ״̬.
	 * 
	 * @param objBean
	 * @return
	 */
	public static String status(Object objBean) {
		return "0";
	}

	// /**
	// * ��ȡ��ǰʱ��.
	// */
	// public static String time() {
	// return timeFormat.format(new Date());
	// }
	/**
	 * ��ȡ��ǰʱ��. ʱ�������
	 * 
	 * 
	 */
	public static Date time() {
		return new Date();
	}

	/**
	 * ��ȡ��ǰʱ��. 6λ�ַ���
	 * 
	 * ��: 213301
	 * 
	 * @return
	 */
	public static String time2() {
		return time2Format.format(new Date());
	}

	/**
	 * ��ȡ��ǰ����. 8λ�ַ���
	 * 
	 * ��: 20140601
	 * 
	 * @return
	 */
	public static String date1() {
		return iso0Format.format(new Date());
	}

	/**
	 * ��ȡ��ǰ����. 10λ�ַ���
	 * 
	 * ��: 2014-06-01
	 * 
	 * @return
	 */
	public static String date2() {
		return isoFormat.format(new Date());
	}

	/**
	 * ��ȡ��ǰʱ��. 8λ�ַ���
	 * 
	 * ��: 21:33:01
	 * 
	 * @return
	 */
	public static String time3() {
		return timeFormat.format(new Date());
	}

	/**
	 * �����ڸ�ʽ. HH:mm:ss ת��Ϊ HHmmss����������
	 * 
	 * ��: 21:33:01 ת��Ϊ 213301
	 * 
	 * @param time3
	 *            ��: 21:33:01
	 * @return
	 */
	public static int time2int(String time3) {
		if (time3 != null) {
			return int2(time3.replaceAll(":", ""));
		}
		return 0;
	}

	/**
	 * date�� ���ڼ�
	 * 
	 * @param date
	 * 
	 * @return
	 */
	public static int week(String date) {
		DateTime dateTime = DateTime.parse(date, ISODateTimeFormat.basicDate());
		return dateTime.dayOfWeek().get();
	}

	/**
	 * ��ʽ������.
	 * 
	 * @param date
	 * @param format
	 *            �Զ����ʽ����ʽ
	 * @return
	 */
	public static Date date(Object date, String format) {
		if (date instanceof Date) {
			return (Date) date;
		}
		if (date instanceof BigDecimal) {
			return date(date.toString(), format);
		}
		if (date instanceof Integer) {
			return date(date.toString(), format);
		}

		if (date != null) {
			return date(date.toString(), format);
		}

		return null;
	}

	/**
	 * ��ʽ������.
	 * 
	 * @param date
	 * @param fmt
	 *            ISO0||ISO||ISO2||ISO1||ISO3
	 * @return
	 */
	public static Date date(String date, String format) {
		try {
			if (format != null && date != null) {
				if (format.toUpperCase().endsWith("ISO0")) {
					return iso0Format.parse(date);
				} else if (format.toUpperCase().endsWith("ISO")) {
					return isoFormat.parse(date);
				} else if (format.toUpperCase().endsWith("ISO2")) {
					return iso2Format.parse(date);
				} else if (format.toUpperCase().endsWith("ISO1")) {
					return iso1Format.parse(date);
				} else if (format.toUpperCase().endsWith("ISO3")) {
					return iso3Format.parse(date);
				}
			}
		} catch (ParseException e) {
			// e.printStackTrace();
		}
		return null;

	}

	/**
	 * ��ʽ������. �磺20150202
	 * 
	 * @param date
	 * 
	 * @return
	 */
	// public static Date date(BigDecimal date, String fmt) {//�����ѱ���д
	public static Date date(BigDecimal date) {
		if (date != null) {
			return date(date.intValue());
		}
		return null;
	}

	/**
	 * ��ʽ������. �磺20150202
	 * 
	 * @param date
	 * 
	 * @return
	 */
	public static Date date(int date) {
		try {
			return iso0Format.parse("" + date);
		} catch (ParseException e) {
			// e.printStackTrace();
		}
		return null;
	}

	/**
	 * ȥ���ַ����ո�.
	 */
	public static String trim(Object object) {
		if (object != null) {
			return object.toString().trim();
		}
		return "";
	}

	/**
	 * ��ֵ��䣬���������Ҷ���.
	 * 
	 * �ַ���ǰ�油�ո�
	 * 
	 * @param str
	 * @param len
	 * @return
	 */
	public static String evalr(String str, int len) {
		return StringUtil.fillStartBlank(str, len);
	}

	/**
	 * ��ֵ��䣬�������������.
	 * 
	 * �ַ������油�ո�
	 * 
	 * @param str
	 * @param len
	 * @return
	 */
	public static String evall(String str, int len) {
		return StringUtil.fillEndBlank(str, len);
	}

	/**
	 * ��ֵ��䣬���������Ҷ���.
	 * 
	 * �ַ���ǰ�油0
	 * 
	 * @param str
	 * @param len
	 * @return
	 */
	public static String evalr(BigDecimal bigDecimal, int len) {
		return StringUtil.fillStartBlank(bigDecimal.toString(), len, '0');
	}

	/**
	 * ��ֵ��䣬���������Ҷ���.
	 * 
	 * �ַ���ǰ�油0
	 * 
	 * @param str
	 * @param len
	 * @return
	 */
	public static String evalr(int bigDecimal, int len) {
		return StringUtil.fillStartBlank("" + bigDecimal, len, '0');
	}

	/**
	 * ��ֵ��䣬���������Ҷ���.
	 * 
	 * �ַ���ǰ�油0
	 * 
	 * @param str
	 * @param len
	 * @return
	 */
	public static String evalr(long bigDecimal, int len) {
		return StringUtil.fillStartBlank("" + bigDecimal, len, '0');
	}

	/**
	 * ���ַ����е�ĳ���ַ�������һ�ַ��滻.
	 * 
	 * @param str
	 * @param len
	 * @return
	 */
	public static String xlate(String src, String tag, String replace) {
		if (replace != null && src != null && tag != null) {
			int length = replace.length();
			String s = "";
			for (int i = 0; i < length; i++) {
				char charAt = replace.charAt(i);
				int indexOf = src.indexOf(charAt);
				System.out.println(indexOf);
				if (indexOf > -1 && tag.length() > indexOf) {
					s += tag.substring(indexOf, indexOf + 1);
				} else {
					s += charAt;
				}
			}
			return s;
		}
		return replace;
	}

	/**
	 * ��ȡ�ֶ�.
	 * 
	 * @param source
	 * @param start
	 *            ��ʼλ��
	 * @param len
	 *            ����
	 * @return
	 */
	public static String subst(String source, int start, int len) {
		if (len > 0) {
			if (source != null && source.length() > (start - 2)) {
				if (source.length() > (start - 2 + len)) {
					return source.substring(start - 1, start - 1 + len);
				} else {
					return source.substring(start - 1);
				}
			}
			return "";
		} else {
			return subst(source, start);
		}
	}

	/**
	 * ��ȡ�ֶ�.
	 * 
	 * @param source
	 * @param start
	 *            ��ʼλ��
	 * @param len
	 *            ����
	 * @return
	 */
	public static String subst(String source, double start, int len) {
		return subst(source, (int) start, len);
	}

	/**
	 * ת��Ϊlong����
	 * 
	 * @param bigDecimal
	 * @return
	 */
	public static long long2(Object bigDecimal) {
		if (bigDecimal == null) {
			return 0;
		}
		if (bigDecimal instanceof Long) {
			return (Long) bigDecimal;
		}
		if (bigDecimal instanceof BigDecimal) {
			return long2((BigDecimal) bigDecimal);
		}
		return long2(bigDecimal.toString());
	}

	/**
	 * ת��Ϊlong����
	 * 
	 * @param bigDecimal
	 * @return
	 */
	public static long long2(BigDecimal bigDecimal) {
		if (bigDecimal == null) {
			return 0;
		}
		return bigDecimal.longValue();
	}

	/**
	 * ת��Ϊlong����
	 * 
	 * @param bigDecimal
	 * @return
	 */
	public static long long2(String bigDecimal) {
		if (bigDecimal == null || bigDecimal.trim().length() == 0) {
			return 0;
		}
		return new BigDecimal(bigDecimal).longValue();
	}

	/**
	 * ת��Ϊdouble����
	 * 
	 * @param bigDecimal
	 * @return
	 */
	public static double double2(Object bigDecimal) {
		if (bigDecimal == null) {
			return 0;
		}
		if (bigDecimal instanceof Double) {
			return (Double) bigDecimal;
		}
		if (bigDecimal instanceof BigDecimal) {
			return double2((BigDecimal) bigDecimal);
		}
		return double2(bigDecimal.toString());
	}

	/**
	 * ת��Ϊdouble����
	 * 
	 * @param bigDecimal
	 * @return
	 */
	public static double double2(BigDecimal bigDecimal) {
		if (bigDecimal == null) {
			return 0;
		}
		return bigDecimal.doubleValue();
	}

	/**
	 * ת��Ϊdouble����
	 * 
	 * @param bigDecimal
	 * @return
	 */
	public static double double2(String bigDecimal) {
		if (bigDecimal == null || bigDecimal.trim().length() == 0) {
			return 0;
		}
		return new BigDecimal(bigDecimal).doubleValue();
	}

	/**
	 * ת��Ϊint����
	 * 
	 * @param bigDecimal
	 * @return
	 */
	public static int int2(Object bigDecimal) {
		if (bigDecimal instanceof Integer) {
			return (Integer) bigDecimal;
		}
		if (bigDecimal instanceof BigDecimal) {
			return int2((BigDecimal) bigDecimal);
		}
		if (bigDecimal instanceof Double) {
			return int2(new BigDecimal((Double) bigDecimal));
		}
		if (bigDecimal != null) {
			return int2(bigDecimal.toString());
		}
		return 0;
	}

	/**
	 * ת��Ϊint����
	 * 
	 * @param bigDecimal
	 * @return
	 */
	public static int int2(BigDecimal bigDecimal) {
		if (bigDecimal == null) {
			return 0;
		}
		return bigDecimal.intValue();
	}

	/**
	 * ת��Ϊint����
	 * 
	 * @param bigDecimal
	 * @return
	 */
	public static int int2(String bigDecimal) {
		if (bigDecimal == null || bigDecimal.trim().length() == 0) {
			return 0;
		}
		try {
			BigDecimal bigDecimal2 = new BigDecimal(bigDecimal);
			return bigDecimal2.intValue();
		} catch (Exception ex) {
			return 0;
		}
	}

	/**
	 * ��ȡ�ֶ�.
	 * 
	 * @param source
	 * @param start
	 *            ��ʼλ��
	 * @param len
	 *            ����
	 * @return
	 */
	public static String subst(String source, int start) {
		if (source != null) {
			if (source.length() > (start - 2)) {
				return source.substring(start - 1);
			} else {
				return "";
			}
		}
		return "";
	}

	/**
	 * �����ֵ.
	 * 
	 * @param input
	 * @return
	 */
	public static int abs(int input) {
		return Math.abs(input);
	}

	/**
	 * �����ֵ.
	 * 
	 * @param input
	 * @return
	 */
	public static double abs(double input) {
		return Math.abs(input);
	}

	/**
	 * �ַ����滻.
	 * 
	 * @param replacement
	 *            �滻�ַ���
	 * @param source
	 * @param position
	 *            �滻��positionλ��ʼ��length�����ַ�
	 * @param length
	 *            ��ȡsource�ĳ���
	 * @return
	 */
	public static String replace(String replacement, String source,
			int position, int length) {
		return replaceLocValue(replacement, position, length, source);
	}

	/**
	 * �ַ����滻.
	 * 
	 * @param replacement
	 *            �滻�ַ���
	 * @param source
	 * @param position
	 *            �滻��positionλ��ʼ��length�����ַ�
	 * @param length
	 *            ��ȡsource�ĳ���
	 * @return
	 */
	public static String replace(String replacement, String source,
			double position, int length) {
		return replaceLocValue(replacement, (int) position, length, source);
	}

	/**
	 * �ַ����滻.
	 * 
	 * @param replacement
	 *            �滻�ַ���
	 * @param source
	 * @param position
	 *            �滻��positionλ��ʼ���ַ�����sourceֱ���滻��replacement��,
	 * 
	 * @return
	 */
	public static String replace(String replacement, String source, int position) {
		return replaceLocValue(replacement, position, source.length(), source);
	}

	/**
	 * �ַ����滻.
	 * 
	 * @param replacement
	 *            �滻�ַ���
	 * @param source
	 *            ֱ�Ӵ�replacement��һλ��ʼ�滻
	 * @return
	 */
	public static String replace(String replacement, String source) {
		return replace(replacement, source, 0);
	}

	/**
	 * �ж��ַ����Ƿ����, �����Ϊnull,����true.
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean isEquals(String str1, String str2) {
		if (str1 == null) {
			if (str2 == null) {
				return true;
			} else {
				return isBlank(str2);
			}
		} else {
			if (str1.length() == 0) {
				return isBlank(str2);
			}
			return str1.equals(str2);
		}
	}

	/**
	 * �ж��ַ�����int�Ƿ����, �����Ϊnull,����true. �������Ϊnull�����ж�str1�Ƿ����str2
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean isEquals(String str1, int str2) {
		if (str1 == null || str1.trim().length() == 0) {
			return str2 == 0;
		}
		return isEquals(new BigDecimal(str1), new BigDecimal(str2));
	}

	/**
	 * �ж�double��int�Ƿ����, �����Ϊnull,����true. �������Ϊnull�����ж�str1�Ƿ����str2
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean isEquals(double str1, int str2) {
		return isEquals(new BigDecimal(str1), new BigDecimal(str2));
	}

	/**
	 * �ж�str1�Ƿ����str2
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean isEquals(int str1, int str2) {
		return str1 == str2;
	}

	/**
	 * �ж�str1�Ƿ����str2
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean isEquals(double str1, double str2) {
		return str1 == str2;
	}

	/**
	 * �ж��ַ�����BigDecimal�Ƿ����, �����Ϊnull,����true. �������Ϊnull�����ж�str1�Ƿ����str2
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean isEquals(String str1, BigDecimal str2) {
		if (str1 == null) {
			return str2 == null;
		} else {
			if (str1.length() == 0) {
				return str2 == null;
			}
			if (str2 != null) {
				return new BigDecimal(str1).equals(str2);
			} else {
				return false;
			}

		}

	}

	/**
	 * �ж��ַ����������Ƿ����, �����Ϊnull,����true. �������Ϊnull�����ж�str1�Ƿ����str2
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean isEquals(String str1, Date str2) {
		if (str1 == null) {
			return str2 == null;
		} else {
			if (str1.length() == 0) {
				return str2 == null;
			}
			if (str2 != null) {
				return str1.equals(iso0Format.format(str2));
			} else {
				return false;
			}

		}
	}

	/**
	 * �ж������Ƿ����, �����Ϊnull,����true. �������Ϊnull�����ж�str1�Ƿ����str2
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean isEquals(Date date1, Date date2) {
		if (date1 == null) {
			return date2 == null;
		} else {
			if (date2 == null) {
				return false;
			}
			return date1.compareTo(date2) == 0;
		}
	}

	/**
	 * �����Ϊnull�򷵻�true���������Ϊnull���ж��Ƿ����
	 * 
	 * @param dec1
	 * @param dec2
	 * @return
	 */
	public static boolean isEquals(BigDecimal dec1, BigDecimal dec2) {
		if (dec1 == null) {
			return dec2 == null;
		} else {
			if (dec2 == null) {
				return false;
			}
			return dec1.compareTo(dec2) == 0;
		}
	}

	/**
	 * ���str1Ϊnull��str2=0�򷵻�true�����str1��Ϊnull���ж�str1==str2
	 * 
	 * @param dec1
	 * @param dec2
	 * @return
	 */
	public static boolean isEquals(BigDecimal str1, int str2) {
		if (str1 == null) {
			return str2 == 0;
		}
		if (str1.intValue() == str2) {
			return isEquals(str1, new BigDecimal(str2));
		} else {
			return false;
		}
	}

	/**
	 * ���str1Ϊnull��str2=0.0���򷵻�true�����str1��Ϊnull���ж�str1==str2
	 * 
	 * @param dec1
	 * @param dec2
	 * @return
	 */
	public static boolean isEquals(BigDecimal str1, double str2) {
		if (str1 == null) {
			return str2 == 0.0;
		} else {
			return str1.doubleValue() == str2;
		}

	}

	/**
	 * �ж��ַ����Ƿ����, �����Ϊnull,����true. �������Ϊnull�����ж�str1�Ƿ����str2
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean isThan(String str1, String str2) {
		if (str1 == null) {
			if (str2 == null) {
				return true;
			} else {
				return isBlank(str2);
			}
		} else {
			if (str1.length() == 0) {
				return isBlank(str2);
			}
			return str1.compareTo(str2) > 0;
		}
	}

	/**
	 * �ж������Ƿ����, �����Ϊnull,����true. �������Ϊnull�����ж�str1�Ƿ����str2
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean isThan(Date date1, Date date2) {
		if (date1 == null) {
			return date2 == null;
		} else {
			if (date2 == null) {
				return true;
			}
			return date1.after(date2);
		}
	}

	/**
	 * �ж������Ƿ����, �����Ϊnull,����true. �������Ϊnull�����ж�dec1�Ƿ����dec2
	 * 
	 * @param dec1
	 * @param dec2
	 * @return
	 */
	public static boolean isThan(BigDecimal dec1, BigDecimal dec2) {
		if (dec1 == null) {
			return dec2 == null;
		} else {
			if (dec2 == null) {
				return true;
			}
			return dec1.compareTo(dec2) > 0;
		}
	}

	/**
	 * �����˻�У��λ �൱��BPPSYACCV.
	 * 
	 * @param sourceAcct
	 * @param targetLen
	 * @return
	 */
	public static String generateAcct(String sourceAcct, int targetLen) {
		int[] weigth_factors = { 11, 13, 17, 19, 23, 31, 37, 41, 47, 51 };
		int i, j = 0;
		long sum = 0, sum1 = 0;
		char[] self_check_digits = new char[2];
		char[] acct_no;
		char[] char_source;
		String TargetAcct = "";
		char_source = sourceAcct.toCharArray();

		if (targetLen > 32) {
			return null;
		}
		acct_no = new char[sourceAcct.trim().length() + 2];

		for (int y = 0; y < sourceAcct.trim().length(); y++) {
			acct_no[y] = char_source[y];
		}

		for (i = 0; i < targetLen - 3; i += 2) {
			sum = sum + ((acct_no[i + 1] - '0') * 10 + (acct_no[i] - '0'))
					* weigth_factors[j];
			j++;
		}

		sum1 = sum % 97;
		if (sum1 == 0L)
			sum1++;

		self_check_digits = String.valueOf(sum1).toCharArray();

		if (self_check_digits.length == 1) {
			char[] cs = new char[2];
			cs[1] = self_check_digits[0];
			cs[0] = '0';
			self_check_digits = cs;
		}

		if (self_check_digits[0] == self_check_digits[1]) {
			sum1 = (self_check_digits[0] - '0') + (self_check_digits[1] - '0');
			self_check_digits = String.valueOf(sum1).toCharArray();
		}

		if (self_check_digits.length == 1) {
			char[] cs = new char[2];
			cs[1] = self_check_digits[0];
			cs[0] = '0';
			self_check_digits = cs;
		}

		acct_no[targetLen - 2] = self_check_digits[0];
		acct_no[targetLen - 1] = self_check_digits[1];

		TargetAcct = String.valueOf(acct_no);

		return TargetAcct;
	}

	/**
	 * �ַ���ǰ�油0.
	 * 
	 * @param source
	 *            ��Ҫ��0���ַ���
	 * @param sourceLen
	 *            ������λ0(�������ַ����ĳ��ȣ��磺sourceLen=5��source.length=3����sourceǰ�Ჹ��2��0)
	 * @return
	 */
	public static String setPrvEvZero(String source, int sourceLen) {
		if (source == null || sourceLen <= source.trim().length()) {
			return " ";
		}
		String f_str = "";
		int f_i = sourceLen - source.trim().length();
		for (int i = 0; i < f_i; i++) {
			f_str = f_str.trim() + '0';
		}
		f_str = f_str.trim() + source.trim();
		return f_str;
	}

	/**
	 * ȡ��.
	 * 
	 * @param i
	 * @param mod
	 * @return
	 */
	public static int rem(int i, int mod) {
		return i % mod;
	}

	/**
	 * ȡ��.
	 * 
	 * i = 4 ,2 = 0
	 * 
	 * 
	 * @param i
	 * @param mod
	 *            ģ��
	 * @return
	 */
	public static int rem(double i, double mod) {
		return (int) ((long) i % (long) mod);
	}

	/**
	 * ���������.
	 * 
	 * @param d1
	 * @param d2
	 * @param scale
	 * @return
	 */
	public static double divide(double d1, double d2, int scale) {
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);
		return divide(b1, b2, scale);
	}

	/**
	 * ���������.ֱ�ӽ�ȡ.
	 * 
	 * @param d1
	 * @param d2
	 * @param scale
	 * @return
	 */
	public static double divideDown(BigDecimal b1, BigDecimal b2, int scale) {
		if (b1 == null || b2 == null) {
			return 0;
		}
		BigDecimal divide = b1.divide(b2, scale, RoundingMode.DOWN);
		return divide.doubleValue();
	}

	/**
	 * ���������.��������
	 * 
	 * @param d1
	 * @param d2
	 * @param scale
	 * @return
	 */
	public static double divide(BigDecimal b1, BigDecimal b2, int scale) {
		if (b1 == null || b2 == null) {
			return 0;
		}
		BigDecimal divide = b1.divide(b2, scale, RoundingMode.HALF_UP);
		return divide.doubleValue();
	}

	/**
	 * ���������.
	 * 
	 * @param d1
	 * @param d2
	 * @param scale
	 * @return
	 */
	public static double multiply(double d1, double d2) {
		return d1 * d2;
	}

	/**
	 * ���������. ��������
	 * 
	 * @param d1
	 * @param d2
	 * @param scale
	 * @return
	 */
	public static double multiply(BigDecimal b1, BigDecimal b2, int scale) {
		if (b1 == null || b2 == null) {
			return 0;
		}
		return b1.multiply(b2).setScale(scale, RoundingMode.HALF_UP)
				.doubleValue();
	}

	/**
	 * ���������. ֱ�ӽ�ȡ.
	 * 
	 * @param b1
	 * @param b2
	 * @param scale
	 * @return
	 */
	public static double multiplyDown(BigDecimal b1, BigDecimal b2, int scale) {
		if (b1 == null || b2 == null) {
			return 0;
		}
		return b1.multiply(b2).setScale(scale, RoundingMode.DOWN).doubleValue();
	}

	/**
	 * ��������� ��������.
	 * 
	 * @param d1
	 * @param d2
	 * @param scale
	 * @return
	 */
	public static double add(BigDecimal b1, BigDecimal b2, int scale) {
		if (b1 == null || b2 == null) {
			return 0;
		}
		return b1.add(b2).setScale(scale, RoundingMode.HALF_UP).doubleValue();
	}

	/**
	 * ���������.ֱ�ӽ�ȡ.
	 * 
	 * @param d1
	 * @param d2
	 * @param scale
	 * @return
	 */
	public static double addDown(BigDecimal b1, BigDecimal b2, int scale) {
		if (b1 == null || b2 == null) {
			return 0;
		}
		return b1.add(b2).setScale(scale, RoundingMode.DOWN).doubleValue();
	}

	/**
	 * ���������.��������
	 * 
	 * @param d1
	 * @param d2
	 * @param scale
	 * @return
	 */
	public static double subtract(BigDecimal b1, BigDecimal b2, int scale) {
		if (b1 == null || b2 == null) {
			return 0;
		}
		return b1.subtract(b2).setScale(scale, RoundingMode.HALF_UP)
				.doubleValue();
	}

	/**
	 * ���������.ֱ�ӽ�ȡ.
	 * 
	 * @param d1
	 * @param d2
	 * @param scale
	 * @return
	 */
	public static double subtractDown(BigDecimal b1, BigDecimal b2, int scale) {
		if (b1 == null || b2 == null) {
			return 0;
		}
		return b1.subtract(b2).setScale(scale, RoundingMode.DOWN).doubleValue();
	}

	/**
	 * ��λ�������滻��ֵ.
	 * 
	 * abc,1,10,1232 = 1232
	 * 
	 * @param source
	 *            ԭ�ַ���
	 * @param offset
	 *            λ��
	 * @param len
	 *            ����
	 * @param target
	 * @return
	 */
	public static String replaceLocValue(String source, int offset, int len,
			String target) {
		if (source == null) {
			source = "";
		}
		if (target == null) {
			target = "";
		}
		StringBuffer buffer = new StringBuffer();
		if (source.length() < offset - 1) {
			buffer.append(source);
			for (int i = 0; i < offset - 1 - source.length(); i++) {
				buffer.append(' ');
			}
			for (int i = 0; i < len; i++) {
				if (target.length() > i) {
					buffer.append(target.charAt(i));
				} else {
					buffer.append(' ');
				}
			}
		} else {
			for (int i = 0; i < offset - 1; i++) {
				buffer.append(source.charAt(i));
			}
			for (int i = 0; i < len; i++) {
				if (target.length() > i) {
					buffer.append(target.charAt(i));
				} else {
					buffer.append(' ');
				}
			}
			for (int i = offset - 1 + len; i < source.length(); i++) {
				buffer.append(source.charAt(i));
			}
		}
		return buffer.toString();
	}

	/**
	 * ����str�����ַ���.
	 * 
	 * @param str
	 * @param len
	 * @return
	 */
	public static String fullAllString(String str, int len) {
		if (str == null) {
			str = "";
		}
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < len;) {
			buffer.append(str);
			i += str.length();
		}
		if (buffer.length() > len) {
			buffer.delete(len, buffer.length());
		}
		return buffer.toString();
	}

	/**
	 * ��ȡ�ַ������ڵ�һλ��.
	 * 
	 * @param str
	 * @param scan
	 * @return Ϊ�ҵ�����-1, �ҵ�����λ��
	 */
	public static int scan(String str, String scan) {
		if (str == null || scan == null) {
			return -1;
		}
		int indexOf = str.indexOf(scan);
		if (indexOf < 0) {
			return indexOf;
		} else {
			return indexOf + 1;
		}
	}

	/**
	 * ��ȡ��ǰ 26λʱ���.
	 * 
	 * ��ʽ yyyy-MM-dd HH:mm:ss.SSSSSS
	 * 
	 * @return
	 */
	public static String timestamp2() {
		return iso2Format.format(new Date());
	}

	/**
	 * ��ȡ��ǰʱ��. 12λ�ַ���
	 * 
	 * ��ʽ��HHmmssSSS����ȷ�����룻 ��: 213301876
	 * 
	 * @return
	 */
	public static String time1() {
		return time3Format.format(new Date());
	}

	/**
	 * ��ʽ��yyyy-MM-dd HH:mm:ss.SSS.
	 * 
	 * 23λʱ���. ��: 2014-01-01 13:21:19
	 * 
	 * @return
	 */
	public static String timestamp1() {
		return iso1Format.format(new Date());
	}

	/**
	 * ��ʽ��yyyyMMddHHmmssSSS.
	 * 
	 * 17λʱ���. ��: 20140101132119
	 * 
	 * @return
	 */
	public static String timestamp3() {
		return iso4Format.format(new Date());
	}

	/**
	 * ת�����ڸ�ʽ ;�õ��ĸ�ʽΪ(Fri Jan 01 00:00:00 CST 20150202)
	 * 
	 * @param stamp
	 * @return
	 */
	public static Date timestamp(String stamp) {
		DateTime dateTime = DateTime.parse(stamp);
		return dateTime.toDate();
	}

	/**
	 * �������������ݵ��±�.
	 * 
	 * @param sourc
	 *            Ҫ���ҵ�����
	 * @param array
	 *            �����ҵ�����
	 * @return
	 */
	public static int lookup(String sourc, String[] array) {
		int tot = array.length;

		for (int i = 0; i < tot; i++) {
			if (isEquals(sourc, array[i])) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * �������������ݵ��±�.
	 * 
	 * @param sourc
	 *            Ҫ���ҵ�����
	 * @param array
	 *            �����ҵ�����
	 * @return
	 */
	public static int lookup(int sourc, int[] array) {
		int tot = array.length;
		for (int i = 0; i < tot; i++) {
			if (sourc == array[i]) {
				return i;
			}
		}

		return -1;
	}

	public static void main(String[] args) {
		// Date plus = plus(new Date(), 2, "*DAYS");
		// String plus1 = plus("20140501", 2, "*DAYS");
		// String replaceLocValue = replaceLocValue("abcefg", 10, 4, "1234");
		// System.out.println(replaceLocValue);
		// String[] array = toArray("abcefg1", 2);
		// System.out.println(array);
		// String fullAllString = fullAllString("12", 3);
		// System.out.println(fullAllString);
		// System.out.println(isEquals(1.0, 1));
		//
		// System.out.println(Integer.MAX_VALUE);
		// System.out.println(Double.MAX_VALUE);

		// System.out.println("------------------------------------------------");
		// String[] array2 = toArray("123", new int[] { 1, 2, 3 });
		// System.out.println(array2.toString());
		// System.out.println("------------------------------------------------");
		// System.out.println("----[" + isEquals(0.000000083333, 0) +
		// "]--------");
		// System.out.println(subtract(new BigDecimal("122222"), new
		// BigDecimal("15155.531"), 2));
		String xlate = xlate("DC", "CD", "D 2 C");
		System.out.println(xlate);
		double ss = 20140101d;
		ss = ss + 0.00;
		System.out.println(trimStringDecimal(ss));
		System.out.println(Math.pow(20, 3));
	}
}
