package cn.zcraft.tds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor{

	private Main thisIns = Main.getIns();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub
		
		if(args.length == 1 && args[0].equalsIgnoreCase("reload")) {
			if(!(sender.isOp())) {
				sender.sendMessage("§4nCov-Bat §f>>> §cYou don't have permission!");
				return false;
			}
			thisIns.reloadConfig();
			sender.sendMessage("§4nCov-Bat §f>>> §bPlugin is reloaded !");
			return false;
		}
			
		
		
		return false;
	}
	
	

}
