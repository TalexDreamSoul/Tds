package main.talexbanitem;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Commands implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lavel, String[] args) {

        if(!sender.hasPermission("tbl.use") && !sender.isOp()){
            return false;
        }

        if(args.length < 1){
            sender.sendMessage(sendHelp());
            return false;
        }

        if(args[0].equalsIgnoreCase("reload")){

            TalexBanItem.getInstance().reloadConfig();
            sender.sendMessage("§cTBL §a重载完毕 §7(§a数据删除§7)");
            return false;
        }

        if(args[0].equalsIgnoreCase("getLore")){

            if(args.length > 2){
                sender.sendMessage("§cTBL §d喵喵喵？获取那么多干嘛？");
                return false;
            }

            if(!(sender instanceof Player)){
                sender.sendMessage("§cTBL §d了解，后台也是人！？");
                return false;
            }

            Player player = (Player)sender;

            ItemStack itemStack = player.getInventory().getItemInMainHand();

            if(itemStack == null){
                sender.sendMessage("§cTBL §e我觉得你在无中生有，暗度陈仓，凭空想象，凭空捏造，无言无语，无可救药，逝者安息 ...");
                return false;
            }

            if(!itemStack.hasItemMeta()){
                sender.sendMessage("§cTBL §6我觉得你在无中生有，暗度陈仓，凭空想象，凭空捏造，无言无语，无可救药，逝者安息 ...");
                return false;
            }

            ItemMeta itemMeta = itemStack.getItemMeta();

            if(!itemMeta.hasLore()){
                sender.sendMessage("§cTBL §b我觉得你在无中生有，暗度陈仓，凭空想象，凭空捏造，无言无语，无可救药，逝者安息 ...");
                return false;
            }

            if(args.length != 1){
                sender.sendMessage("§cTBL §f我觉得你在无中生有，暗度陈仓，凭空想象，凭空捏造，无言无语，无可救药，逝者安息 ...");
                return false;
            }

            //获取全部Lore

            List<String> lore = itemMeta.getLore();
            for(int i = 0;i < lore.size();i++) {

                String line = "000" + i;
                line = line.substring(line.length() - 2);

                sender.sendMessage("§c" + line + "§e行数 §7|§r " + lore.get(i));

            }

            return false;
        }

        if(args[0].equalsIgnoreCase("addLore")){

            if(args.length < 2){
                sender.sendMessage("§cTBL §c我觉得你在无中生有，暗度陈仓，凭空想象，凭空捏造，无言无语，无可救药，逝者安息 ...");
                return false;
            }

            if(!(sender instanceof Player)){
                sender.sendMessage("§cTBL §d了解，后台也是人！？");
                return false;
            }

            Player player = (Player)sender;

            ItemStack itemStack = player.getInventory().getItemInMainHand();

            if(itemStack == null || itemStack.getType().equals(Material.AIR)){
                sender.sendMessage("§cTBL §e我觉得你在无中生有，暗度陈仓，凭空想象，凭空捏造，无言无语，无可救药，逝者安息 ...");
                return false;
            }

            ItemMeta itemMeta = itemStack.getItemMeta();
            List<String> lore = new ArrayList<>();

            if(itemMeta != null && itemMeta.hasLore()){
                lore = itemMeta.getLore();
            }

            StringBuilder name = new StringBuilder();
            for(int i = 0;i < args.length - 1;i++){
                if(i == args.length -1){
                    name.append(args[i + 1]);
                    break;
                }
                name.append(args[i + 1]).append(" ");
            }

            lore.add(name.toString().replace("&","§"));
            if (itemMeta == null) {
                return false;
            }
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
            player.getInventory().setItemInMainHand(itemStack);
            sender.sendMessage("§cTBL §a添加成功");
            return false;
        }

        if(args[0].equalsIgnoreCase("setLore")){

            if(args.length < 3){
                sender.sendMessage("§cTBL §c我觉得你在无中生有，暗度陈仓，凭空想象，凭空捏造，无言无语，无可救药，逝者安息 ...");
                return false;
            }

            if(!(sender instanceof Player)){
                sender.sendMessage("§cTBL §d了解，后台也是人！？");
                return false;
            }

            Player player = (Player)sender;

            ItemStack itemStack = player.getInventory().getItemInMainHand();

            if(itemStack == null){
                sender.sendMessage("§cTBL §e我觉得你在无中生有，暗度陈仓，凭空想象，凭空捏造，无言无语，无可救药，逝者安息 ...");
                return false;
            }

            if(!itemStack.hasItemMeta()){
                sender.sendMessage("§cTBL §6我觉得你在无中生有，暗度陈仓，凭空想象，凭空捏造，无言无语，无可救药，逝者安息 ...");
                return false;
            }

            ItemMeta itemMeta = itemStack.getItemMeta();

            if(!itemMeta.hasLore()){
                sender.sendMessage("§cTBL §b我觉得你在无中生有，暗度陈仓，凭空想象，凭空捏造，无言无语，无可救药，逝者安息 ...");
                return false;
            }

            int num = Integer.parseInt(args[1]);
            List<String> lore = itemMeta.getLore();
            StringBuilder name = new StringBuilder();
            for(int i = 0;i < args.length - 2;i++){
                if(i == args.length - 2){
                    name.append(args[i + 2]);
                    break;
                }
                name.append(args[i + 2]).append(" ");
            }
            lore.set(num, name.toString().replace("&","§"));
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
            player.getInventory().setItemInMainHand(itemStack);
            sender.sendMessage("§cTBL §a添加成功");
            return false;
        }

        if(args[0].equalsIgnoreCase("setName")){

            if(args.length < 2){
                sender.sendMessage("§cTBL §c我觉得你在无中生有，暗度陈仓，凭空想象，凭空捏造，无言无语，无可救药，逝者安息 ...");
                return false;
            }

            if(!(sender instanceof Player)){
                sender.sendMessage("§cTBL §d了解，后台也是人！？");
                return false;
            }

            Player player = (Player)sender;

            ItemStack itemStack = player.getInventory().getItemInMainHand();

            if(itemStack == null || itemStack.getType().equals(Material.AIR)){
                sender.sendMessage("§cTBL §e我觉得你在无中生有，暗度陈仓，凭空想象，凭空捏造，无言无语，无可救药，逝者安息 ...");
                return false;
            }

            ItemMeta itemMeta = itemStack.getItemMeta();

            StringBuilder name = new StringBuilder();
            for(int i = 0;i < args.length - 1;i++){
                if(i == args.length -1){
                    name.append(args[i + 1]);
                    break;
                }
                name.append(args[i + 1]).append(" ");
            }

            itemMeta.setDisplayName(name.toString().replace("&","§"));
            itemStack.setItemMeta(itemMeta);
            player.getInventory().setItemInMainHand(itemStack);
            sender.sendMessage("§cTBL §a添加成功");
            return false;
        }

        if(args[0].equalsIgnoreCase("getKeys")){

            Set<String> sets = TalexBanItem.getInstance().getConfig().getKeys(false);
            List<String> list = new ArrayList<>(sets);

            for(int i = 0;i < list.size();i++){

                String line = "000" + i;
                line = line.substring(line.length() - 2);

                sender.sendMessage("§c" + line + "§e行数 §7|§r " + list.get(i));

            }

            return false;
        }

        sender.sendMessage(sendHelp());
        return false;
    }

    public String sendHelp(){

        String str = "";
        str = str + "§a§m========§b§m========§c§m========§b§m========§a§m========\n";
        str = str + "§2/tbl setName <name> §3#设置手上物品昵称\n";
        str = str + "§2/tbl getLore §3#获取手上物品的lore\n";
        str = str + "§2/tbl setLore <number> <name> §3#设置手上物品指定行Lore的内容\n";
        str = str + "§2/tbl addLore <name> §3#添加一行lore到手上物品\n";
        str = str + "§2/tbl getKeys §3#获取当前有的节点表\n";
        str = str + "§2/tbl reload §3#重载插件\n";
        str = str + "§a§m========§b§m========§c§m========§b§m========§a§m========";

        return str;

    }

}
