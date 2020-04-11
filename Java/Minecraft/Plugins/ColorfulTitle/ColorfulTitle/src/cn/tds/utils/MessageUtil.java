package cn.tds.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cn.tds.colorfultitle.Main;

public class MessageUtil {
	
	private static String prefix = ColorfulTitle.getPrefix();
	
	public static String Encode_UTF8(String str) throws UnsupportedEncodingException {
		return new String(str.getBytes("gbk"), StandardCharsets.UTF_8);
	}
	
	public static List<String> String2List(String str,String spilitStr){
		String[] strSpilit = str.split(spilitStr);	
		List<String> list = Arrays.asList(strSpilit);
		return list;
	}
	
	//发送消息
	@SuppressWarnings("deprecation")
	public static void sendExternalMessage(int type,String msg,boolean hasPrefix) {
		//type: 0 > conosleMsg 1 > broadcast 2 > title 3 > op bc 4 > debug
		// msg spl: \n   10\n20\n30\ntitle\nsubtitle
		msg = msg.replaceAll("&", "§");
		if(type == 0) {
			if (hasPrefix) {
				msg = prefix + msg;
			}
			Main.getPlugin().getServer().getConsoleSender().sendMessage(msg);
		} else if(type == 1) {
			if (hasPrefix) {
				msg = prefix + msg;
			}
			Main.getPlugin().getServer().broadcastMessage(msg);
		} else if(type == 2) {
			if (hasPrefix) {
				msg = prefix + msg;
			}
			
			String[] msgArgs = msg.split("\n");
			if(msgArgs.length != 5) {
				sendExternalMessage(3,"§c§oThe plugin has a fatel error! msg:\n"+msg,true);
				return;
			}
			
			for(Player player: Bukkit.getOnlinePlayers()) {
				int in = 20;
				int fade = 20;
				int stay = 20;
				in = Integer.parseInt(msgArgs[0]);
				stay = Integer.parseInt(msgArgs[1]);
				fade = Integer.parseInt(msgArgs[2]);
				player.setTitleTimes(in, stay, fade);
				player.sendTitle(msgArgs[3], msgArgs[4]);
				
			}

		} else if(type == 3) {
			if (hasPrefix) {
				msg = prefix + msg;
			}
			
			for(Player player: Bukkit.getOnlinePlayers()) {
				
				if(player.isOp()) {
					player.sendMessage(msg);
				}
				
			}

		} 
		
		
	}
	
	public static String getTypeMsg(String type) {
		return FileUtil.langYaml.getString(type);
	}
	
	public static String varReplace(String msg, Player player) {
		msg = msg.replace("{player}", player.getName());
		msg = msg.replace("{tag}", player.getDisplayName());
		return varReplace(msg);
	}
	
	public static String varReplace(String msg) {
		msg = msg.replace("&", "§");
//		msg= msg.replace("&amp;", "&sect;");
		msg = msg.replace("{prefix}", prefix);//.replace("&", "§");
		return msg;
	}
	
	//发送帮助信息
	public static void sendHelp(CommandSender sender) {
		
		List<String> list = FileUtil.langYaml.getStringList("Help");
//		sendExternalMessage(0,"帮助信息大小: " + list.size(),true);
		for(String str : list) {
			str = varReplace(str);
			sender.sendMessage(str);
		}
		return;
	}
}
