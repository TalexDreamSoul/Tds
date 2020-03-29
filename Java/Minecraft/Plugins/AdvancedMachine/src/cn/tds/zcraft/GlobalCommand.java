package cn.tds.zcraft;

import cn.tds.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GlobalCommand implements CommandExecutor{
	
	private String thisPrefix = AdvancedUtil.getPrefix();
	
	@Override
	public boolean onCommand(CommandSender sender,Command cmd, String label, String[] args) {
		if(args.length < 1) {
			MessagesUtil.sendHelp(sender);
			return false;
		}
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
				ItemUtil.AdvancedMachineItemGive(args[1],player,Bukkit.getPlayer(args[2]));
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
