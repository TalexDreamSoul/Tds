package talexlores.main;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static Main instance;

    @Override
    public void onEnable() {
        // Plugin startup logic

        instance = this;

        saveDefaultConfig();
        getServer().getPluginCommand("tl").setExecutor(new Commands());
        getServer().getPluginManager().registerEvents(new Events(),this);

        getServer().getConsoleSender().sendMessage("§7[§bTalexLores§7] §aPlugin has been enabled!");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
