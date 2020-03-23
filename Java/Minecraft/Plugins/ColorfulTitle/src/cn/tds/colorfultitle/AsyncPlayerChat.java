package cn.tds.colorfultitle;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import cn.tds.utils.ColorfulTitle;
import cn.tds.utils.FileUtil;

public class AsyncPlayerChat implements Listener{
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		
		//时间
		String format = event.getFormat();
		
		String time = Main.getPlugin().getConfig().getString("Time").replace("&", "§");
		time = time.replace("{time}", ColorfulTitle.getTime());
		format = time + format;
		
		//脏话
		if(Main.Ignore) {
			boolean replaceCompare = FileUtil.ignoreYaml.getBoolean("LongCompare",true);
			String msg = event.getMessage();
			List<String> list = FileUtil.ignoreYaml.getStringList("Lists");
			for(String str : list) {
				
				Pattern p = Pattern.compile(str);
				Matcher m = p.matcher(msg);
			    boolean b = m.matches();
			    if(b) {
			    	
			    	if(replaceCompare) {
			    		msg = msg.replace(str, getX(str));
			    	}else {
			    		msg = msg.replace(str, "*");
			    	}
			    	
			    	
			    }
				
			}
			event.setMessage(msg);
			
			
		}
		
		event.setFormat(format);
	}
	private static String getX(String str){
		char[] replaces = FileUtil.ignoreYaml.getString("Replace","*").toCharArray();
		char replace = replaces[0];
		char[] ch = new char[str.length()];
		Arrays.fill(ch, replace);
		return new String(ch);
	}
	
	

}
