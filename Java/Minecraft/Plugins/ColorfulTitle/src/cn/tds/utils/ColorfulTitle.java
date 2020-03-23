package cn.tds.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ColorfulTitle{
	
	private static String prefix = "§7[§eCorlorfulTitle§7] ";
	public static String getPrefix() { return prefix; }
	
	public static boolean setPrefix(String str) {
		if(str.isEmpty()) {
			return false;
		}
		str = str.replaceAll("&", "§");
		prefix = str;
		return true;
	}
	public static String getTime() {	
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
		
		return simpleDateFormat.format(new Date());
	}

}
