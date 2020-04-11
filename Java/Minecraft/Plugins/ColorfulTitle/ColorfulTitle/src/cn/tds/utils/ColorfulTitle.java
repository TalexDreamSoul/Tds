package cn.tds.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ColorfulTitle{
	
	private static String prefix = "§7[§eColorfulTitle§7] ";
	public static String getPrefix() { return prefix; }
	
	public static void setPrefix(String str) {
		if(str.isEmpty()) {
			return;
		}
		str = str.replaceAll("&", "§");
		prefix = str;
	}
	public static String getTime() {	
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
		
		return simpleDateFormat.format(new Date());
	}

}
