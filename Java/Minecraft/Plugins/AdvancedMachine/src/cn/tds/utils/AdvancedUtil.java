package cn.tds.utils;

import java.io.File;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;

import cn.tds.zcraft.Main;

public class AdvancedUtil{
	
	//private static Plugin instance = Main.getPlugin();

	private static String prefix = "§7[§e高级§8机械§7] ";
	public static String getPrefix() { return prefix; }
	
	public static void setPrefix(String str) {
		if(str.isEmpty()) {
			return;
		}
		str = str.replaceAll("&", "§");
		prefix = str;
	}



	public static boolean getBlockinConfig(String type,String worldName,int x,int y,int z) {
		
		String pointName = type+"."+worldName;

		new YamlConfiguration();
		YamlConfiguration yaml = YamlConfiguration.loadConfiguration(new File(Main.getPlugin().getDataFolder() + "/machines/" + type));
		
		List<String> list = yaml.getStringList(pointName);

		for (String s : list) {
			String loc = x + "," + y + "," + z;

			if (s.equalsIgnoreCase(loc)) {
				MessagesUtil.sendExternalMessage(1, "true", false);
				return true;
			}

		}
		return false;
	}
	

}
