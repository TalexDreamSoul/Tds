package talexlores.main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!sender.isOp()){
            return false;
        }

        if(args.length == 1 && args[0].equalsIgnoreCase("reload")){

            Main.instance.reloadConfig();
            sender.sendMessage("§7[§bTalexLores§7] §aReload successfully!");
            return false;

        }

        sender.sendMessage("§7[§bTalexLores§7] §cUnknown!");
        return false;
    }
}
