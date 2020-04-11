package cn.tds.zcraft;

import cn.tds.events.PlayerMove;
import cn.tds.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class GlobalCommand implements CommandExecutor{
	
	private String thisPrefix = AdvancedUtil.getPrefix();
	
	@Override
	public boolean onCommand(CommandSender sender,Command cmd, String label, String[] args) {
		if(args.length < 1) {
			MessagesUtil.sendHelp(sender);
			return false;
		}
		/*if(args[0].equalsIgnoreCase("check")) {
			if(!(sender instanceof  Player)){
				return false;
			}

			Player player = (Player)sender;

			ItemStack itemStack = player.getInventory().getItemInMainHand();
			if(itemStack == null || itemStack.getType().equals(Material.AIR)){
				sender.sendMessage(thisPrefix+ "§c你手上没有物品！");
				return false;
			}

			ItemMeta itemMeta = itemStack.getItemMeta();

			if(itemMeta.getLore() != null){


				List<String> list = itemMeta.getLore();
				if(list != null){
					for(String str : list) {
						if (str.contains("§k§o§u§z§h§a§o")) {

							sender.sendMessage(thisPrefix + "§a你手上的物品是口罩！");
							return false;

						}
					}
				}


			}
			sender.sendMessage(thisPrefix+ "§c你手上的是原版物品！§f(" + itemMeta.getDisplayName() + "§f)");
			return false;

		}*/

		if(args[0].equalsIgnoreCase("reload")) {
			if(!(sender.isOp()) && !(sender.hasPermission("am.reload"))) {
				sender.sendMessage(thisPrefix+ TalexFiles.langYaml.getString("WithoutPermission","§c您没有使用此命令的权限！").replace("&","§"));
				return false;
			}

			Main.getPlugin().reloadConfig();
			AdvancedUtil.setPrefix(Main.getPlugin().getConfig().getString("Prefix"));
			sender.sendMessage(thisPrefix+"§bPlugin has been reloaded.");
			return false;

		}

		if(args[0].equalsIgnoreCase("debug")) {
			if(!(sender.isOp()) && !(sender.hasPermission("am.debug"))) {
				sender.sendMessage(thisPrefix+ TalexFiles.langYaml.getString("WithoutPermission","§c您没有使用此命令的权限！").replace("&","§"));
				return false;
			}
			if(args.length < 2) {
				sender.sendMessage(thisPrefix+"§cWrong prompt! Usage: §b/ad debug true/false");
				return false;
			}
			if(args[1].equalsIgnoreCase("true")) {
				if(Main.getDebugMode()) {
					sender.sendMessage(thisPrefix+"§cThe Debug mode has been enabled!");
					return false;
				}
				Main.getPlugin().getConfig().set("Debug", true);
				Main.setDebugMode(true);
				sender.sendMessage(thisPrefix+"§aThe Debug mode has been enabled!");
				return false;
			}else if(args[1].equalsIgnoreCase("false")) {
				if(Main.getDebugMode()) {
					Main.getPlugin().getConfig().set("Debug", false);
					Main.setDebugMode(false);
					sender.sendMessage(thisPrefix+"§aThe Debug mode has been closed!");
					return false;
				}
				sender.sendMessage(thisPrefix+"§cThe Debug mode has been closed!");
				return false;
			}else {
				sender.sendMessage(thisPrefix+"§4Unknown mode!");
				return false;
			}

		}

		/*if(args[0].equalsIgnoreCase("cov")) {
			if(!(sender.isOp()) && !(sender.hasPermission("am.cov"))) {
				sender.sendMessage(thisPrefix+ TalexFiles.langYaml.getString("WithoutPermission","§c您没有使用此命令的权限！").replace("&","§"));
				return false;
			}
			if(args.length < 2) {
				sender.sendMessage(thisPrefix+"§cWrong prompt! Usage: §b/am cov <player>");
				return false;
			}
			if(args[1].equalsIgnoreCase("all")) {

				for(Player player : Bukkit.getOnlinePlayers()){

					player.sendTitle("","§7- §c你感染了病毒 §7-",0,60,10);
					PlayerMove.covPlayer(player);

				}

				//sender.sendMessage("所有玩家以获得");

				return false;

			}
			Player player = Bukkit.getPlayer(args[1]);
			if(player == null){
				sender.sendMessage(thisPrefix+"§cSorry , but that player §o" + args[1] + "§c is not online!");
				return false;
			}
			player.sendTitle("","§7- §c你感染了病毒 §7-",0,60,10);
			PlayerMove.covPlayer(player);
			return false;

		}*/

		if(args[0].equalsIgnoreCase("msg")) {
			if(!(sender.isOp()) && !(sender.hasPermission("am.msg"))) {
				sender.sendMessage(thisPrefix+ TalexFiles.langYaml.getString("WithoutPermission","§c您没有使用此命令的权限！").replace("&","§"));
				return false;
			}
			if(args.length != 4) {
				sender.sendMessage(thisPrefix+"§cWrong prompt! Usage: §b/am msg type hasPrefix msg");
				return false;
			}
			boolean thisPrefix = false;
			if(args[2].equalsIgnoreCase("true")) {
				thisPrefix = true;
			}
			int thisType;
			thisType = Integer.parseInt(args[1]);

//			args[1].indexOf(thisType);
			MessagesUtil.sendExternalMessage(thisType, args[3], thisPrefix);
			return false;
		}

		if(!(sender instanceof Player)) {
			sender.sendMessage(thisPrefix+"§c§lYou must be a player!");
			return false;
		}
		Player player = (Player)sender;
		if(args[0].equalsIgnoreCase("give")) {
			if(!(player.isOp()) && !(player.hasPermission("am.give"))) {
				sender.sendMessage(thisPrefix+ TalexFiles.langYaml.getString("WithoutPermission","§c您没有使用此命令的权限！").replace("&","§"));
				return false;
			}
			if(args.length < 2) {
				sender.sendMessage(thisPrefix+"§cWrong prompt! Usage: §b/am give type player");
				return false;
			}

			if(args.length == 3) {
				Player givePlayer = Bukkit.getPlayerExact(args[2]);
				if(givePlayer == null || !givePlayer.isOnline()){

					sender.sendMessage(thisPrefix+"§cSorry , but that player §o" + args[2] + "§c is not online!");
					return false;

				}
				ItemUtil.AdvancedMachineItemGive(args[1],player,Bukkit.getPlayerExact(args[2]));
			}else {
				ItemUtil.AdvancedMachineItemGive(args[1],player);
			}
			return false;
		}

		if(args[0].equalsIgnoreCase("breakgui")) {
			if(!(player.isOp()) && !(player.hasPermission("am.breakgui"))) {
				sender.sendMessage(thisPrefix+ TalexFiles.langYaml.getString("WithoutPermission","§c您没有使用此命令的权限！").replace("&","§"));
				return false;
			}
			player.closeInventory();
			player.openInventory(InventoryUtil.createBreakGui());
			return false;
			
		}
		MessagesUtil.sendHelp(sender);
		return false;
	}
	
}
