package cn.tds.utils;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import cn.tds.zcraft.Main;
import sun.misc.MessageUtils;

import static cn.tds.utils.MessagesUtil.String2List;

public class ItemUtil extends AdvancedUtil{

	private static String prefix = AdvancedUtil.getPrefix();
	
	
	
	//获得被禁止破坏的方块
	public static boolean getBanBlock(Block b) {
		return !b.getType().equals(Material.AIR) && !b.getType().equals(Material.BEDROCK) && !b.getType().equals(Material.BANNER);
	}

	/*private ItemStack getChest(int amount) {
		return getMachine(Material.CHEST,"§c高级箱子",MessageUtil.String2List("§e能储存更多物品...##§0§0§0§0","##"),amount);
	}*/
	/*public static ItemStack getAdvancedFurnace(int amount) {
		return getMachine(Material.FURNACE,"§c高级熔炉",MessageUtil.String2List("§e提升燃料利用率 100% ...##§0§0§0§1","##"),amount);
	}*/

	public static ItemStack key2Config(String key){

		FileConfiguration yaml = TalexFiles.langYaml;

//		if(!yaml.contains(key+".Name")){
//			return null;
//		}

		String name = yaml.getString(key + ".Name","未知");
		String type = yaml.getString(key + ".Material","未知");
		List<String> lore = yaml.getStringList(key + ".Lore");
		return config2Machine(name,type,lore);

	}

	public static ItemStack config2Machine(String name, String material, List<String> lore){


		Material type = Material.getMaterial(material);
		if(type == null)
			return null;
		return getMachine(type,MessagesUtil.varReplace(name),lore,1);

	}
	
	public static ItemStack String2ItemStack(String str) {
		
		String[] strs = str.split(":");
		if(strs.length != 2) {
			return null;
		}
		
		MessagesUtil.sendExternalMessage(4, "§bMaterial: " + strs[0], true);

		Material material = Material.getMaterial(strs[0]);
		
		if(material == null) {
			MessagesUtil.sendExternalMessage(4, "§bMaterial: §cnull", true);
			return null;
		}
		
		String name = Main.getPlugin().getConfig().getString("BreakName","§a破碎的 §n{name}");
		
		name = name.replace("{name}", material.name());
		
		ItemStack item = getMachine(material,name,null,1);
		
		if(!MathUtil.isInteger(strs[1])) {
			MessagesUtil.sendExternalMessage(4, "§bMaterial: §cIntegerNull", true);
			return null;
		}
		item.setDurability(Short.parseShort(strs[1]));
		
		return item;
		
	}
	
	/**
	 *  以下为自动化部分
	 */
	
	public static void AdvancedMachineItemGive(String type, Player player) {
		ItemStack item = key2Config(type);
		 if(item == null) {
			player.sendMessage(prefix + "§bThe type §c§o" + type + " §bis not a machine.");
			return;
		}
		player.getInventory().addItem(item);
		player.sendMessage(prefix + "§b你获得了 §c§o" + type + "x" + item.getAmount() + " §b.");
	}
	
	public static void AdvancedMachineItemGive(String type, Player player, Player givePlayer) {
		ItemStack item =key2Config(type);
		 if(item == null) {
			player.sendMessage(prefix + "§bThe type §c§o" + type + " §bis not a machine.");
			return;
		}
		 if(givePlayer == null){
			 player.sendMessage(prefix + "§bPlayer is not online!");
			 return;
		 }
			givePlayer.getInventory().addItem(item);
			player.sendMessage(prefix + "§b你给 §c" + givePlayer.getDisplayName() + "§e" + type + " x" + item.getAmount() + " §b!");
			givePlayer.sendMessage(prefix + "§b你获得了  §c§o" + type + "x" + item.getAmount() + " §b 通过 "+player.getName()+" !");
	}
	
	public static ItemStack getMachine(Material material,String name,List<String> lore,int amount) {

		ItemStack itemStack = new ItemStack(material);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemStack.setAmount(amount);
		itemMeta.setDisplayName(name);
		itemMeta.setLore(lore);
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}
	
}
