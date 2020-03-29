package cn.tds.zcraft;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import cn.tds.events.BlockBreak;
import cn.tds.events.BlockPlace;
import cn.tds.events.InventoryOpen;
import cn.tds.events.PlayerInteract;
import cn.tds.utils.AdvancedUtil;
import cn.tds.utils.TalexFiles;
import cn.tds.utils.ItemUtil;
import cn.tds.utils.LocationUtil;
import cn.tds.utils.MessagesUtil;
import cn.tds.utils.RunnableUtil;

public class Main extends JavaPlugin{
	
	private static Plugin instance;
	public static HashMap<String,Boolean> BlockBreaker = new HashMap<>();
	public static HashMap<String,Boolean> CobbleStoneMaker = new HashMap<>(); 
	public static HashMap<String,Boolean> AnvilCraft = new HashMap<>();
	
	private static boolean Debug = false;
	public static Language lang;

	public static Plugin getPlugin() { return instance; }
	public static boolean getDebugMode() { return Debug; }
	public static void setDebugMode(boolean mode) { Debug = mode; }
	
	@Override
	public void onEnable() {
		
		instance = this;
		lang = Language.Chinese;

		saveDefaultConfig();
		AdvancedUtil.setPrefix(getConfig().getString("Prefix"));

		Main.getPlugin().saveResource("lang/Chinese.yml",false);
		for(Machines m : Machines.values()) {

			String machine = m.name();
			if (!(TalexFiles.hasFile(getDataFolder() + "/machines/" + machine + ".yml"))) {
				TalexFiles.createFile(getDataFolder() + "/machines/" + machine + ".yml");
			}
		}

		if (!(TalexFiles.hasFile(getDataFolder() + "/Errors/"))) {
			TalexFiles.createFile(getDataFolder() + "/Errors/new.txt");
		}
		Main.getPlugin().saveResource("Variables.txt",true);
		TalexFiles.sendExternalMessage(0,"§3All files has benn exported!",true);
		TalexFiles.sendExternalMessage(4,"§b语言文件加载 ...",true);
		File langFile = new File(getDataFolder() + "/lang/" + Main.lang.name() + ".yml");



		TalexFiles.langYaml = YamlConfiguration.loadConfiguration(langFile);

		Debug = getConfig().getBoolean("Debug");
		MessagesUtil.sendExternalMessage(4,"§a调试模式  已启用 ...",true);

		MessagesUtil.sendExternalMessage(4,"§aSave config  ...",true);
		
		String langStr = getConfig().getString("Language");
		lang = Language.valueOf(langStr);
		MessagesUtil.sendExternalMessage(4,"§a选择语言  " + langStr + " !",true);
		if(!TalexFiles.hasFile(getDataFolder() + "/lang/" + langStr + ".yml")) {
			MessagesUtil.sendExternalMessage(0,"§c语言" + langStr + "不存在,,已自动切换为默认语言(Chinese)",true);
		} else {
			MessagesUtil.sendExternalMessage(4,"§a选择语言  " + langStr + " √",true);
		}

		MessagesUtil.sendExternalMessage(4,"§aRegister events ...",true);
		Bukkit.getPluginManager().registerEvents(new BlockPlace(),this);
		Bukkit.getPluginManager().registerEvents(new BlockBreak(),this);
//		Bukkit.getPluginManager().registerEvents(new PlayerMove(),this);
		Bukkit.getPluginManager().registerEvents(new InventoryOpen(),this);
		Bukkit.getPluginManager().registerEvents(new PlayerInteract(),this);
		MessagesUtil.sendExternalMessage(5,"§aStart runnable ...",true);
		
		if(TalexFiles.hasFile(getDataFolder() + "/location/BlockBreaker.yml")) {
			MessagesUtil.sendExternalMessage(4,"§b正在重新启动所有机械 ...",true);
			
			RestartRunnable();
			
			MessagesUtil.sendExternalMessage(0,"§b所有机械已重新运行 ...",true);
		}
		
		MessagesUtil.sendExternalMessage(4,"§a正在注册命令 ...",true);
		Bukkit.getPluginCommand("am").setExecutor(new GlobalCommand());
		
		MessagesUtil.sendExternalMessage(0,"§a高级机械已启动",true);
		MessagesUtil.sendExternalMessage(5,"§aPlugin has been enabled.",true);
		
		
	}	
	private static void RestartRunnable() {
		
		MessagesUtil.sendExternalMessage(5, "正在启动破碎机 ...e", true);
		getMachineList("BlockBreaker");
		MessagesUtil.sendExternalMessage(0,"§e§o破碎机 §ehas been restarted...",true);
		MessagesUtil.sendExternalMessage(5, "正在启动刷石机 ...", true);
		MessagesUtil.sendExternalMessage(0,"§e§刷石机 §ehas been restarted...",true);
		getMachineList("CobbleStoneMaker");
		MessagesUtil.sendExternalMessage(5, "正在启动压缩机 ...", true);
		getMachineList("BlockCompress");
		MessagesUtil.sendExternalMessage(0,"§e§o压缩机 §ehas been restarted...",true);
		
		
	}
	@SuppressWarnings({ "unused"})
	private static void getMachineList(String type){

		new YamlConfiguration();
		YamlConfiguration yaml = YamlConfiguration.loadConfiguration(new File(Main.getPlugin().getDataFolder() + "/" + type));

		if(!(yaml.contains(type))){
			
			MessagesUtil.sendExternalMessage(0, "Config " + type + " is not available.", true);
			return;
			
		}
		
		Set<String> set  = yaml.getConfigurationSection(type).getKeys(false);
		if(set == null) {
			MessagesUtil.sendExternalMessage(0, "Config SetList null - NPE", true);
			return;
		}
		List<String> list;
		MessagesUtil.sendExternalMessage(4,"§6" + type + "data (" + set.size() + ")",true);
		for(String worldName : set) {
			MessagesUtil.sendExternalMessage(4, "§6Search set (" + worldName + ")", true);
			list = yaml.getStringList(type+"." + worldName);
			for(int i = 0;i < list.size();i++) {
				String str = list.get(i);
				MessagesUtil.sendExternalMessage(4,"§fSearch config (" + str + ")",true);
				World world = Bukkit.getWorld(worldName);
				Location loc = new Location(world,LocationUtil.getStringLocationAddon(str, 1),LocationUtil.getStringLocationAddon(str, 2),LocationUtil.getStringLocationAddon(str, 3));
				MessagesUtil.sendExternalMessage(4,"§d" + LocationUtil.loc2Str2(loc),true);
				
				
				Block block = loc.getBlock();
				if(block == null) {
					
					MessagesUtil.sendExternalMessage(0, "Block NPE - " + str, true);
					return;
					
				}
				
				MessagesUtil.sendExternalMessage(4,"§f" + str + " type: " + block.getType().name(),true);
				if(ItemUtil.getBanBlock(block)) {
					MessagesUtil.sendExternalMessage(4,"§a" + type + " restart §d" + str,true);
					String hashMapStr = LocationUtil.loc2Str(loc);
					if(type.equalsIgnoreCase("BlockBreaker")) {
						BlockBreaker.put(hashMapStr,false);
						RunnableUtil.Runnable_BlockBreaker(hashMapStr, loc);
					}else if(type.equalsIgnoreCase("CobbleStoneMaker")) {
						CobbleStoneMaker.put(hashMapStr,false);
						RunnableUtil.Runnable_BlockMaker(hashMapStr, loc);
					}
					
					
				}else {
					String str2 = list.remove(i);
					if(list.size() == 0) {
						instance.getConfig().set(type+"."+worldName, "");
					}else {
						instance.getConfig().set(type+"."+worldName, str2);
					}
					
					try {
						yaml.save(Main.getPlugin().getDataFolder() + "/" + type);
					} catch (IOException e) {
						
						e.printStackTrace();
						
					}
					
					MessagesUtil.sendExternalMessage(4,"§c移除无效机械文件 位置 (" + str + ")",true);
					
				}
			}
			
		}
		
		
	}
}
