/**
 * 
 */
package com.login.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @author Administrator
 *
 */
public class VTools {

	public static String getNewUUID(){
		
		String str = UUID.randomUUID().toString().replace("-", "");
	    return str;
	}
	public static boolean StringIsNullOrSpace(String paramString)
	  {
	    return (paramString == null) || ("".equals(paramString.trim())) || ("NULL".equals(paramString.trim().toUpperCase())) || ("<无>".equals(paramString.trim()));
	  }
	public static String getCurrencyTime(String type){
		SimpleDateFormat df = new SimpleDateFormat(type);
		return df.format(new Date());
	}

	/**
	 * 生成6位随机数
	 * @return
	 */
	public static String getRandomStr() {
		Random r = new Random();
		long i = r.nextInt(100000);
		long number = i + 900000L;
		return Long.toString(number);
	}
	/**
	 * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
	 * 此方法中前三位格式有：
	 * 13+任意数
	 * 15+除4的任意数
	 * 18+除1和4的任意数
	 * 17+除9的任意数
	 * 147
	 */
	/**
	 * 判断手机号码有效性(中国大陆)
	 *
	 * @param au_name 手机号码
	 * @return 13+
	 * 15+除4的任意数
	 * 18+除1和4的任意数
	 * 17+除9的任意数
	 * 145,147,149
	 */
	public static boolean isChinaPhoneLegal(String au_name) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9])|(17[0-8])|(14[5,7,9]))\\d{8}$");
		Matcher m = p.matcher(au_name);
		return m.matches();
	}

	/**
	 * 香港手机号码8位数，5|6|8|9开头+7位任意数
	 */
	public static boolean isHKPhoneLegal(String str) throws PatternSyntaxException {
		String regExp = "^(5|6|8|9)\\d{7}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(str);
		return m.matches();
	}

	/**
	 * 字符串为空
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str){
		if(str==""||str==null){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 获取当前系统时间
	 * @param format 时间格式
	 * @return
	 */
	public static String getSysDate(String format){

		String str = "";
		SimpleDateFormat dateF = null;
		if(isNull(format)){
			dateF = new SimpleDateFormat("yyyy-MM-dd");
		}else{
			dateF = new SimpleDateFormat(format);
		}
		Calendar calendar = Calendar.getInstance();
		return dateF.format(calendar.getTime());
	}
	public static String getNewUUid(){

		StringBuffer stringBuffer = new StringBuffer();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < 8; i++){

			String str = uuid.substring(i * 4, i * 4 + 4);
			int strInteger= Integer.parseInt(str, 16);
			System.out.println(str+"fdfd"+strInteger);
			stringBuffer.append(chars[strInteger % 0x3E]);
		}
		return stringBuffer.toString();
	}

	public static String[] chars = new String[]{

			"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",

			"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",

			"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"

	};
}
