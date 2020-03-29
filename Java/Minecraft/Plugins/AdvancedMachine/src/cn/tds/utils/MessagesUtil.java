package cn.tds.utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cn.tds.zcraft.Main;

public class MessagesUtil {
	
	private static String prefix = AdvancedUtil.getPrefix();
	
	
	
	public static String Encode_UTF8(String str) throws UnsupportedEncodingException {
		return new String(str.getBytes("gbk"), StandardCharsets.UTF_8);
	}
	
	public static List<String> String2List(String str,String spilitStr){
		String[] strSpilit = str.split(spilitStr);
		return Arrays.asList(strSpilit);
	}
	public static String List2String(List<String> list){
		if(list == null) {
			return null;
		}
		StringBuilder str = new StringBuilder();
		for(String s : list) {
			str.append(s);
		}
		
		return str.toString();
	}
	
	//发送消息
	@SuppressWarnings("deprecation")
	public static void sendExternalMessage(int type ,String msg ,boolean hasPrefix) {
		//type: 0 > conosleMsg 1 > broadcast 2 > title 3 > op bc 4 > debug
		// msg spl: \n   10\n20\n30\ntitle\nsubtitle
		msg = msg.replace("&", "§");
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
				int in;
				int fade;
				int stay;
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

		} else if(type == 4) {
			if(!Main.getDebugMode()) {
				return;
			}
			if (hasPrefix) {
				msg = prefix + msg;
			}
			
			sendExternalMessage(0,msg,false);

		} else if(type == 5) {
			if(!Main.getDebugMode()) {
				return;
			}
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
	
	public static String formatStr(String text) {
		
//		MessagesUtil.sendExternalMessage(5, "Format : " + text + "|" + text.replace("§", "&"), true);
//		text = text.replace("§", "&");
		List<String> list = String2List("1#2#3#4#5#6#7#8#9#0#a#b#c#d#e#f#r#l#o#m","#");
		for(String s : list) {
			
			String repStr = "§" + s;
			MessagesUtil.sendExternalMessage(5, "ThisFormat : " + repStr , true);
			text = text.replace(repStr, "");
			
		}
		
		return text;
	}
	
	public static String varReplace(String msg,Player player) {
		msg = msg.replaceAll("{player}", player.getName());
		msg = msg.replaceAll("{tag}", player.getDisplayName());
		return varReplace(msg);
	}
	
	public static String varReplace(String msg) {
		msg = msg.replaceAll("&", "§");
		if(prefix != null)
			msg = msg.replace("{prefix}", prefix);
		return msg;
	}
	
	//发送帮助信息
	public static void sendHelp(CommandSender sender) {
		
		List<String> list = TalexFiles.langYaml.getStringList("Help");
		sendExternalMessage(4,"帮助信息大小: " + list.size(),true);
		for(String str : list) {
			str = varReplace(str);
			sender.sendMessage(str);
		}
		//sender.sendMessage("233");

	}
}
