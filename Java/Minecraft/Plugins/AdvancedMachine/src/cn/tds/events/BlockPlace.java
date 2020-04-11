package cn.tds.events;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import cn.tds.utils.AdvancedUtil;
import cn.tds.utils.TalexFiles;
import cn.tds.utils.TalexLocationUtil;
import cn.tds.utils.MessagesUtil;
import cn.tds.utils.RunnableUtil;
import cn.tds.zcraft.Main;

public class BlockPlace implements Listener{
	
//	private Plugin thisInstance = Main.getPlugin();

	private String thisPrefix = AdvancedUtil.getPrefix();
	
	@EventHandler
	public void onPlayer(BlockPlaceEvent event) {
		
		
		ItemStack item = event.getItemInHand();
		
//		Material type = item.getType();

		List<String> lore = item.getItemMeta().getLore();
		if(lore == null) {
			return;
		}


		if(lore.contains("§0§0§0§1")) {

			boolean back = placeMachine("BlockBreaker",event.getBlockPlaced().getLocation());
			if(!back) {
				event.getPlayer().sendMessage(thisPrefix + "§a未知的错误！");
				return;
			}

			//给玩家进行提示
			tip(event.getPlayer(),"破碎机");
			String str = TalexLocationUtil.loc2Str(event.getBlockPlaced().getLocation());
			Main.BlockBreaker.put(str, false);


			RunnableUtil.Runnable_BlockBreaker(str, event.getBlockPlaced().getLocation());
		} else if(lore.contains("§0§0§0§2")) {

			boolean back = placeMachine("CobbleStoneMaker",event.getBlockPlaced().getLocation());
			if(!back) {
				event.getPlayer().sendMessage(thisPrefix + "§a未知的错误！");
				return;
			}

			//给玩家进行提示
			tip(event.getPlayer(),"刷石机");
			String str = TalexLocationUtil.loc2Str(event.getBlockPlaced().getLocation());
			Main.CobbleStoneMaker.put(str, false);


			RunnableUtil.Runnable_BlockMaker(str, event.getBlockPlaced().getLocation());
		} else if(lore.contains("§0§0§0§3")) {

			boolean back = placeMachine("BlockCompress",event.getBlockPlaced().getLocation());
			if(!back) {
				event.getPlayer().sendMessage(thisPrefix + "§a未知的错误！");
				return;
			}
			//给玩家进行提示
			tip(event.getPlayer(),"压缩机");

		} else if(lore.contains("§0§0§0§2§1")) {

			boolean back = placeMachine("CobbleStoneMaker",event.getBlockPlaced().getLocation());
			if(!back) {
				event.getPlayer().sendMessage(thisPrefix + "§a未知的错误！");
				return;
			}

			//给玩家进行提示
			tip(event.getPlayer(),"高级刷石机");
			String str = TalexLocationUtil.loc2Str(event.getBlockPlaced().getLocation());
			Main.AdvancedCobbleStoneMaker.put(str, false);


			RunnableUtil.Runnable_BlockMakerAddon(str, event.getBlockPlaced().getLocation());
		} else if(lore.contains("§0§0§0§2§1§2")) {

			boolean back = placeMachine("SuperCobbleStoneMaker",event.getBlockPlaced().getLocation());
			if(!back) {
				event.getPlayer().sendMessage(thisPrefix + "§a未知的错误！");
				return;
			}

			//给玩家进行提示
			tip(event.getPlayer(),"超级刷石机");
			String str = TalexLocationUtil.loc2Str(event.getBlockPlaced().getLocation());
			Main.SuperCobbleStoneMaker.put(str, false);


			RunnableUtil.Runnable_SuperBlockMaker(str, event.getBlockPlaced().getLocation());
		} else if(lore.contains("§0§0§0§2§1§2§3")) {

			boolean back = placeMachine("IronMaker",event.getBlockPlaced().getLocation());
			if(!back) {
				event.getPlayer().sendMessage(thisPrefix + "§a未知的错误！");
				return;
			}

			//给玩家进行提示
			tip(event.getPlayer(),"铁矿制造机");
			String str = TalexLocationUtil.loc2Str(event.getBlockPlaced().getLocation());
			Main.IronMaker.put(str, false);


			RunnableUtil.Runnable_IronMaker(str, event.getBlockPlaced().getLocation());
		} else if(lore.contains("§0§0§0§2§1§2§3§4")) {

			boolean back = placeMachine("DivineCobbleStoneMaker",event.getBlockPlaced().getLocation());
			if(!back) {
				event.getPlayer().sendMessage(thisPrefix + "§a未知的错误！");
				return;
			}

			//给玩家进行提示
			tip(event.getPlayer(),"神级刷石机");
			String str = TalexLocationUtil.loc2Str(event.getBlockPlaced().getLocation());
			Main.DivineCobbleStoneMaker.put(str, false);

			RunnableUtil.Runnable_DivineCobbleStoneMaker(str, event.getBlockPlaced().getLocation());
		}
//		if(type == Material.FURNACE) {
			

//		}
	}
	
	private void tip(Player player,String type) {
		
		String text = thisPrefix + TalexFiles.langYaml.getString("Block.Place","§a你放下了 §b{machine} §a.");
		text = MessagesUtil.varReplace(text);
		text = text.replace("{machine}", type);
		player.sendMessage(text);
		
	}
	
	private boolean placeMachine(String type,Location loc) {

		new YamlConfiguration();
		YamlConfiguration yaml = YamlConfiguration.loadConfiguration(new File(Main.getPlugin().getDataFolder() + "/machines/" + type + ".yml"));
		//获得世界名
		String worldName = loc.getWorld().getName();
		//将世界坐标整合为一个String
		String writtenLoc = loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ();
		//获得配置列表
		List<String> listB = yaml.getStringList(type + "." + worldName);
		//添加坐标str
		listB.add(writtenLoc);
		//写入配置文件
		yaml.set(type + "." + worldName, listB);
		//保存配置文件
		try {
			yaml.save(Main.getPlugin().getDataFolder() + "/machines/" + type + ".yml");
		} catch (IOException e) {
//			MessagesUtil.sendExternalMessage(5, e.getMessage(), true);
			MessagesUtil.sendExternalMessage(0, "§4错误 §a位于 §bBlockPlace §a中" + type + "错误",true);
		}
		return true;
		
	}
	
}
