package icraftaddon.main;

import cn.mcres.iCraft.model.Panel;
import cn.mcres.iCraft.model.Recipe;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class Main extends JavaPlugin {

    public static HashMap<String, Inventory> PanelCraft = new HashMap<>();
    public static HashMap<String, Panel> Panels = new HashMap<>();
    public static HashMap<String, Recipe> CraftRecipe = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getConsoleSender().sendMessage("§aiCraftAddon > §b已加载！");

        getServer().getPluginManager().registerEvents(new Inventories(),this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getConsoleSender().sendMessage("§aiCraftAddon > §c已卸载！");

    }
}
