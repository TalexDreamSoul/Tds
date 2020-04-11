package cn.tds.colorfultitle;

import java.io.IOException;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;

import cn.tds.utils.ColorfulTitle;
import cn.tds.utils.FileUtil;
import cn.tds.utils.MessageUtil;
import org.bukkit.scheduler.BukkitRunnable;

public class Commands implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub
		if(args.length == 1 ) {
			if(args[0].equalsIgnoreCase("clear")) {
				if(sender.isOp() || sender.hasPermission("ct.use") || sender.hasPermission("ct.clear")) {
					int i = 30;
					while(i > 0) {
						sender.sendMessage("§a");
						i--;
					}
					
					return false;
				}
				sender.sendMessage(ColorfulTitle.getPrefix() + "§c§lHey,§7你没有权限！");
				return false;
			}else if(args[0].equalsIgnoreCase("time")) {
				if(sender.isOp() || sender.hasPermission("ct.use") || sender.hasPermission("ct.time")) {
					
					sender.sendMessage("§a当前时间: §b" + ColorfulTitle.getTime());
					return false;
				}
				sender.sendMessage(ColorfulTitle.getPrefix() + "§c§lHey,§7你没有权限！");
				return false;
			}
			
		}
		if(!(sender.hasPermission("ct.use")) || !(sender.isOp())) {
			return false;
		}
		if(args.length < 1) {
			MessageUtil.sendHelp(sender);
			return false;
		}
		if(args[0].equalsIgnoreCase("reload")) {
			
			Main.getPlugin().reloadConfig();
			
			try {
				FileUtil.DefaultFiles();
			} catch (IOException | InvalidConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				sender.sendMessage(ColorfulTitle.getPrefix() + "§a重载失败 ！");
				return false;
			}
			
//			YamlConfiguration thisYaml = new YamlConfiguration();
//			YamlConfiguration.loadConfiguration(new File(FileUtil.FolderPath + "/lang/Chinese.yml"));
//			FileUtil.langYaml = thisYaml;
//				
//			YamlConfiguration thisYaml2 = new YamlConfiguration();
//			YamlConfiguration.loadConfiguration(new File(FileUtil.FolderPath + "/broadcast.yml"));
//			FileUtil.bcYaml = thisYaml2;

			sender.sendMessage(ColorfulTitle.getPrefix() + "§a重载完毕 ！");
			return false;
		}
		if(args.length < 2) {
			MessageUtil.sendHelp(sender);
			return false;
		}
		
		Player p = Bukkit.getPlayer(args[0]);
		if(p != null) {
			p.sendMessage(getMsg(args).replace("&","§"));
			return false;
		}
		
		String type = args[0];
		type = FileUtil.bcYaml.getString(type+".type");

		if(type == null){
			sender.sendMessage(ColorfulTitle.getPrefix() + "§c这个类型被吃掉了！");
			return false;
		}

		if(type.equalsIgnoreCase("broadcast")) {
			
			String msg = getMsg(args);
			String format = FileUtil.bcYaml.getString(args[0]+".format");
			if(sender instanceof Player) {
				MessageUtil.varReplace(format,(Player)sender);
			}
			Bukkit.broadcastMessage(MessageUtil.varReplace(format + msg));
			return false;
		}else if(type.equalsIgnoreCase("title")) {
			
			String msg = getMsg(args);
			String title = FileUtil.bcYaml.getString(args[0]+".format.title");
			String subtitle = FileUtil.bcYaml.getString(args[0]+".format.subtitle");
			String fade = FileUtil.bcYaml.getString(args[0]+".format.fade");
			String[] fades = fade.split(",");
			String[] msgs = msg.split("###");

			if(fades.length != 3) {
				sender.sendMessage(ColorfulTitle.getPrefix() + "§c位于 §o" + FileUtil.FolderPath + "/broadcast.yml §c中的错误: §o" + args[0] + " §c中 format §c项格式错误！");
				return false;
			}
			if( msgs.length < 2) {
				sender.sendMessage(ColorfulTitle.getPrefix() + "§cTitle项输入错误！ 请检查 ###");
				return false;
			}
			if(sender instanceof Player) {
				MessageUtil.varReplace(title,(Player)sender);
				MessageUtil.varReplace(subtitle,(Player)sender);
			}
			title = title.replace("{msg}", msgs[0]);
			msg = msg.replace(msgs[0]+"###", "");
			subtitle = subtitle.replace("{msg2}", msg);
			for(Player player : Bukkit.getOnlinePlayers()) {

				player.sendTitle(MessageUtil.varReplace(title),MessageUtil.varReplace(subtitle), Integer.parseInt(fades[0]), Integer.parseInt(fades[1]), Integer.parseInt(fades[2]));

			}
			return false;
		}else if(type.equalsIgnoreCase("Player")) {
			
			String msg = getMsg(args);
			String[] msgs = msg.split("###");
			String playerName = msgs[0];
			Player player = Bukkit.getPlayer(playerName);
			if(player == null) {
				sender.sendMessage(ColorfulTitle.getPrefix() + "§c" + playerName + "不是玩家！");
				return false;
			}
			
			msg = msg.replace(playerName + "###", "");
			player.chat(msg);
			
			return false;
		}else if(type.equalsIgnoreCase("CountDown")){

			String classType = FileUtil.bcYaml.getString(args[0] + ".super.class");
			String thisType = FileUtil.bcYaml.getString(classType + ".type");

			if(thisType.equalsIgnoreCase("Title")){
				//ct CountDown 10
				String msg = FileUtil.bcYaml.getString(args[0] + ".super.msg1") + "###" + FileUtil.bcYaml.getString(args[0] + ".super.msg2");
				String title = FileUtil.bcYaml.getString(classType +".format.title");
				String subtitle = FileUtil.bcYaml.getString(classType +".format.subtitle");
				String fade = FileUtil.bcYaml.getString(classType +".format.fade");
				String[] fades = fade.split(",");
				String[] msgs = msg.split("###");

				if(fades.length != 3) {
					sender.sendMessage(ColorfulTitle.getPrefix() + "§c位于 §o" + FileUtil.FolderPath + "/broadcast.yml §c中的错误: §o" + args[0] + " §c中 format §c项格式错误！");
					return false;
				}
				if( msgs.length < 2) {
					sender.sendMessage(ColorfulTitle.getPrefix() + "§cTitle项输入错误！ 请检查 ###");
					return false;
				}
				if(sender instanceof Player) {
					MessageUtil.varReplace(title,(Player)sender);
					MessageUtil.varReplace(subtitle,(Player)sender);
				}
				title = title.replace("{msg}", msgs[0]);
				msg = msg.replace(msgs[0]+"###", "");
				subtitle = subtitle.replace("{msg2}", msg);

				int delay = FileUtil.bcYaml.getInt(args[0] + ".delay");
				int period = FileUtil.bcYaml.getInt(args[0] + ".period");

				final int[] count = new int[1];

				try{

					 count[0] = Integer.parseInt(args[1]);

				}catch(NumberFormatException e){

					sender.sendMessage(ColorfulTitle.getPrefix() + "§c数字被吃掉了！");
					return false;

				}

				;

				String finalTitle = title;
				String finalSubtitle = MessageUtil.varReplace(subtitle);
				new BukkitRunnable() {
					@Override
					public void run() {
						if(count[0] == 1){
							cancel();
						}

						String countTitle = finalSubtitle.replace("{count}", Arrays.toString(count).replace("[","").replace("]",""));

						for(Player player : Bukkit.getOnlinePlayers()) {

							player.sendTitle(MessageUtil.varReplace(finalTitle),countTitle, Integer.parseInt(fades[0]), Integer.parseInt(fades[1]), Integer.parseInt(fades[2]));

						}

						count[0] = count[0] - 1;

					}
				}.runTaskTimer(Main.getPlugin(),delay,period);


				return false;
			}

			String msg = FileUtil.bcYaml.getString(args[0] + ".super.verplace");
			String format = FileUtil.bcYaml.getString(classType +".format");

			if(sender instanceof Player) {
				MessageUtil.varReplace(format,(Player)sender);
			}

			final int[] count;

			try{

				count = new int[]{Integer.parseInt(args[1])};

			}catch(NumberFormatException e){

				sender.sendMessage(ColorfulTitle.getPrefix() + "§c数字被吃掉了！");
				return false;

			}

			int delay = FileUtil.bcYaml.getInt(args[0] + ".delay");
			int period = FileUtil.bcYaml.getInt(args[0] + ".period");


			new BukkitRunnable() {
				@Override
				public void run() {
					if(count[0] == 1){
						cancel();
					}
					Bukkit.broadcastMessage(MessageUtil.varReplace(format.replace(msg,String.valueOf(count[0]))));

					count[0]--;

				}
			}.runTaskTimer(Main.getPlugin(),delay,period);
			return false;
		}
		MessageUtil.sendHelp(sender);
		return false;
	}
	private static String getMsg(String[] args) {
		StringBuilder str = new StringBuilder();
		for(int i = 0;i < args.length - 1;i++) {
			str.append(args[i + 1]).append(" ");
		}
		return str.toString();
		
	}
	
	
	

}
