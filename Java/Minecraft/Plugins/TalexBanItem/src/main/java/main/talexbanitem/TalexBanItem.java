package main.talexbanitem;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class TalexBanItem<instance> extends JavaPlugin {

    private static Plugin instance;
    public static Plugin getInstance() { return instance; }

    @Override
    public void onEnable() {
        // Plugin startup logic

        instance = this;

        getServer().getConsoleSender().sendMessage("§e§m=============================================");
        getServer().getConsoleSender().sendMessage("§cTalexBanITem §7- §f感谢使用QAQ");
        getServer().getConsoleSender().sendMessage("");
        getServer().getConsoleSender().sendMessage("§bTds 系列 §8| §dTalexDreamSoul");
        getServer().getConsoleSender().sendMessage("§c作者QQ §32418876761");
        getServer().getConsoleSender().sendMessage("");
        getServer().getConsoleSender().sendMessage("§cTalexBanITem §7- §a初次运行，稍等片刻");
        getServer().getConsoleSender().sendMessage("§e§m=============================================");

        install();


    }

    public void install(){

        saveDefaultConfig();
        getServer().getPluginCommand("tbl").setExecutor(new Commands());
        getServer().getPluginManager().registerEvents(new Listeners(),this);

        getServer().getConsoleSender().sendMessage("§2§m=============================================");
        getServer().getConsoleSender().sendMessage("§cTalexBanITem §7- §f感谢使用QAQ");
        getServer().getConsoleSender().sendMessage("");
        getServer().getConsoleSender().sendMessage("§bTds 系列 §8| §dTalexDreamSoul");
        getServer().getConsoleSender().sendMessage("§c作者QQ §32418876761");
        getServer().getConsoleSender().sendMessage("");
        getServer().getConsoleSender().sendMessage("§cTalexBanITem §7- §a配置完毕 √");
        getServer().getConsoleSender().sendMessage("§2§m=============================================");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
