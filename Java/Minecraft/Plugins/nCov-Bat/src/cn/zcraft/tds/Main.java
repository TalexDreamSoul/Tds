package cn.zcraft.tds;


import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	private static Main instance;
	public static Main getIns() { return instance; }
	
	public void onEnable() {
		
		instance = this;
		saveDefaultConfig();
		getServer().getConsoleSender().sendMessage("§4Bat-Cov §f>>> §bPlugin is loaded.");
		getServer().getPluginCommand("ncb").setExecutor(new Commands());
		Bukkit.getPluginManager().registerEvents(new Listeners(), this);
		
		
	}
	
	

}


