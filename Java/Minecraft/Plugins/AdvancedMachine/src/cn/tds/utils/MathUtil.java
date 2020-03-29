package cn.tds.utils;

import java.util.regex.Pattern;

public class MathUtil {
	
	public static boolean isInteger(String str) {
		//是整数返还true
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}
	
}
