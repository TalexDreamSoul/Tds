package cn.tds.colorfultitle;

import java.io.IOException;
import java.net.MalformedURLException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;

import cn.tds.utils.ColorfulTitle;
import cn.tds.utils.FileUtil;
import cn.tds.utils.MessageUtil;

public class Main extends JavaPlugin{
	
	private static Main instance;
	public static Main getPlugin() { return instance; }
	public static boolean Ignore = false;
	
	public void onEnable() {
		instance = this;
		
		saveDefaultConfig();
		
		try {
			FileUtil.DefaultFiles();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String prefix = getConfig().getString("Prefix");
		ColorfulTitle.setPrefix(prefix);
		getServer().getPluginCommand("ct").setExecutor(new Commands());
		Bukkit.getPluginManager().registerEvents(new AsyncPlayerChat(), this);
		
		MessageUtil.sendExternalMessage(0, "插件加载完毕！", true);
		
	}
	

}
