package talexweather.main;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static String appId;
    public static Plugin instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        saveDefaultConfig();
        appId = getConfig().getString("Key","none");
        if(appId == "none"){
            getServer().getConsoleSender().sendMessage("§a天气 §b> §6未找到AppId ， 卸载插件！");
            setEnabled(false);
            return;
        }
        getServer().getConsoleSender().sendMessage("§a天气 §b> §6已加载！");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getConsoleSender().sendMessage("§a天气 §b> §6已卸载！");

    }
}
