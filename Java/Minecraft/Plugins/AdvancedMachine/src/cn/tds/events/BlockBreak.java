package cn.tds.events;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import cn.tds.utils.AdvancedUtil;
import cn.tds.utils.TalexFiles;
import cn.tds.utils.ItemUtil;
import cn.tds.utils.MessagesUtil;
import cn.tds.utils.RunnableUtil;
import cn.tds.zcraft.Main;

public class BlockBreak implements Listener{

	private String thisPrefix = AdvancedUtil.getPrefix();
	
	@EventHandler	
	public void onPlayer(BlockBreakEvent event) {
		
		Block block = event.getBlock();
		Material type = block.getType();
		
		MessagesUtil.sendExternalMessage(5, "触发 : BREAK", true);

		if(RunnableUtil.setList(event.getBlock().getLocation(), "BlockBreaker")) {
			event.setDropItems(false);
			tip(event.getPlayer(),"破碎机");
		} else if(RunnableUtil.setList(event.getBlock().getLocation(), "CobbleStoneMaker")) {
			event.setDropItems(false);
			tip(event.getPlayer(),"刷石机");
		} else if(RunnableUtil.setList(event.getBlock().getLocation(), "BlockCompress")) {
			event.setDropItems(false);
			tip(event.getPlayer(),"压缩机");
		}

		Player player = event.getPlayer();
		@SuppressWarnings("deprecation")
		ItemStack itemStack = player.getInventory().getItemInHand();
		
		if(itemStack != null) {
			if(itemStack.getType() == Material.ENCHANTED_BOOK) {
				return;
			}
			
			if(!itemStack.hasItemMeta() || itemStack.getItemMeta().getLore() == null) {
				return;
			}
			
			String lores = MessagesUtil.List2String(itemStack.getItemMeta().getLore());
			if(lores == null) {
				return;
			}


			if(lores.contains("§0§0§0§4")) {
				
				List<String> lists = Main.getPlugin().getConfig().getStringList("BreakAXE");
				
				if(lists == null) {
					return;
				}


				Location loc = block.getLocation();
				
				for(String str : lists) {
					
					String[] Blocks = str.split("-");
					if(Blocks.length != 2) {
						MessagesUtil.sendExternalMessage(5, "§4您的Config Lists配置出现问题，请检查格式！", true);
						return;
					}
					ItemStack item1 = ItemUtil.String2ItemStack(Blocks[0]);
					ItemStack item2 = ItemUtil.String2ItemStack(Blocks[1]);
					if(item1 == null || item2 == null) {
						MessagesUtil.sendExternalMessage(5, "§4您的Config Lists配置出现问题，请检查格式！\n材质: " + Blocks[0] + " 或 " + Blocks[1] + "并不存在！", true);
						return;
					}
					
					if(type.equals(item1.getType())) {
						
						event.setDropItems(false);
						loc.getWorld().dropItem(loc, item2);
						return;
						
					}
					
					
				}
				
//				if(type.equals(Material.COBBLESTONE)) {
//
//					event.setDropItems(false);
//					loc.getWorld().dropItem(loc, ItemUtil.getMachine(Material.GRAVEL, "§a破碎的§8沙砾", null, 1));
//					return;
//
//				}
//				if(type.equals(Material.GRAVEL)) {
//
//					event.setDropItems(false);
//					loc.getWorld().dropItem(loc, ItemUtil.getMachine(Material.SAND, "§a破碎的§8沙子", null, 1));
//					return;
//
//				}
//				if(type.equals(Material.NETHERRACK)) {
//
//					event.setDropItems(false);
//					loc.getWorld().dropItem(loc, ItemUtil.getMachine(Material.ENDER_STONE, "§a破碎的§e末地石", null, 1));
//					return;
//
//				}

				//short dua = itemStack.getDurability();
				//itemStack.setDurability((short) (dua + 1));
				//player.getInventory().setItemInHand(itemStack);
				
			}
			
		}
		
//		if(type == Material.FURNACE) {
			
//			MessagesUtil.sendExternalMessage(5, "触发 : FURNACE - BREAK", true);
			

			
			
			/*List<String> lore = item.getItemMeta().getLore();
			for(String s : lore) {
				
				if(s.equalsIgnoreCase("§6§b§a§n")) {
					
					Player player = event.getPlayer();
					String worldName = player.getWorld().getName();
					Location loc = event.get
					String writtenLoc = loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ();
					thisInstance.getConfig().set(worldName + "-"+player.getName(),writtenLoc);
					break;
				}
				
			}*/
			
			
//		}
		
	}
	
	private void tip(Player player,String type) {
		
		String text = thisPrefix + TalexFiles.langYaml.getString("Block.Break","§c你拆除了 §b{machine} §c.");
		text = MessagesUtil.varReplace(text);
		text = text.replace("{machine}", type);
		player.sendMessage(text);
		
	}

}

