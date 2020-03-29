package cn.tds.events;

import java.util.List;

import cn.tds.utils.*;
import org.bukkit.GameMode;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import cn.tds.zcraft.Main;

public class InventoryOpen implements Listener{
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		
		Inventory inv = event.getClickedInventory();
		if(inv == null || inv.getType() == null)
			return;
			
		MessagesUtil.sendExternalMessage(5, "§bSlot: " + event.getSlot() + "- " + inv.getType(), true);
		
		if(inv.getType() == InventoryType.ANVIL) {
			
			HumanEntity human = event.getWhoClicked();
			Player player = (Player)human;
			if(player.getLevel() < 11 && player.getGameMode() != GameMode.CREATIVE) {
				return;
			}
			
			if(event.getSlot() != 2)
				return;
			
			ItemStack itemStack = inv.getItem(2);
			if(itemStack == null)
				return;
			
			String str = MessagesUtil.List2String(itemStack.getLore());
			if(str == null || !(str.contains("§0§0§0§4§1"))){
				return;
			}
			
			inv.setItem(0, null);
			inv.setItem(1, null);
			
			human.closeInventory();	
			player.giveExpLevels(-10);
			human.getInventory().addItem(itemStack);
			
			return;
			
		}
		
		if(inv.getType() != InventoryType.CHEST)
			return;
		
		String str = inv.getName();
		MessagesUtil.sendExternalMessage(5,"InventoryName: " + str,true);
		if(str.equals(TalexFiles.langYaml.getString("BlockCompress.Name"))) {
			
			if(event.getSlot() != 11 && event.getSlot() != 13 && event.getSlot() != 15) {
				event.setCancelled(true);
				return;
			}
			if(event.getSlot() == 13) {
				
				//配置
				event.setCancelled(true);
				
				ItemStack item0 = inv.getItem(11);
				ItemStack item1 = inv.getItem(15);
				if(item0 == null || item1 == null) {
					return;
				}
				
				String str1 = item0.getType().name() + ":" + String.valueOf(item0.getDurability());
				String str2 = item1.getType().name() + ":" + String.valueOf(item1.getDurability());
				String strs = str1 + "-" + str2;
				
				List<String> list = Main.getPlugin().getConfig().getStringList("BreakAXE");
				Player player = (Player)event.getWhoClicked();
				if(list.contains(strs)) {
					event.getWhoClicked().sendMessage(AdvancedUtil.getPrefix() + "§c已存在！");
					return;
				}
				list.add(strs);
				Main.getPlugin().getConfig().set("BreakAXE", list);
				Main.getPlugin().saveConfig();
				inv.setItem(11, null);
				inv.setItem(15, null);
				event.getWhoClicked().sendMessage(AdvancedUtil.getPrefix() + "§a添加完毕！");
				
				
				player.updateInventory();
				
				return;
			}
			
			
			return;
		}

//		if(!(str.equals("§7[§c压缩机§7]")))
//			return;

		if(!str.contains("§6§6§6"))
			return;

		if(event.getSlot() >= 45) {
			return;
		}
		
		String slots = "10#11#12#19#20#21#28#29#30";
		
		
		if(event.getSlot() == 24) {
			ItemStack normalStack = inv.getItem(10);
			
			if(normalStack == null) {
				event.setCancelled(true);
				return;
			}
			
			//合成物品
			for(String s : slots.split("#")) {
				
//				MessageUtil.sendExternalMessage(4, "ForSlot: " + s, true);
				ItemStack thisStack = inv.getItem(Integer.parseInt(s));
				if(thisStack == null)
					return;
				
				if(!(thisStack.equals(normalStack))) {
//					int amount = thisStack.getAmount();
//					thisStack.setAmount(normalStack.getAmount());
//					if(!thisStack.equals(normalStack)) {
//						thisStack.setAmount(amount);
						event.setCancelled(true);
						return;
//					}
				}
				
			}
			for(String s : slots.split("#")) {
				
				ItemStack thisStack = inv.getItem(Integer.parseInt(s));
				if(thisStack.getAmount() == 1) {
					inv.setItem(Integer.parseInt(s), null);
				}else {
					thisStack.setAmount(thisStack.getAmount() - 1);
					inv.setItem(Integer.parseInt(s), thisStack);
				}

			}
			ItemMeta itemMeta = normalStack.getItemMeta();
			if(itemMeta == null) {
				normalStack = ItemUtil.getMachine(normalStack.getType(), normalStack.getType().name() + "§1§2§3§cx1", null, 1);
			}else {
				String name = itemMeta.getDisplayName();
				if(name == null) {
					itemMeta.setDisplayName(normalStack.getType().name() + "§1§2§3§cx1");
				}else {
					int a = name.indexOf("§1§2§3§cx");
					if(a == -1) {
						itemMeta.setDisplayName(name + "§1§2§3§cx1");
					}else {
						
//						int b = name.indexOf("x");
//						if(b == -1) {
//							itemMeta.setDisplayName(name + "§1§2§3§cx1");
//						}else {
							
							String numberX = name.substring(a + 9);
							MessagesUtil.sendExternalMessage(5, "numberX : " + numberX,true);
							numberX = MessagesUtil.formatStr(numberX);
							MessagesUtil.sendExternalMessage(5, "FormatnumberX : " + numberX,true);
							
							boolean isInt = MathUtil.isInteger(numberX);
							if(!isInt) {
								
								itemMeta.setDisplayName(name + "§1§2§3§cx1");
								
							}else {
								
								int number = Integer.parseInt(numberX);
								number++;
								itemMeta.setDisplayName(name.replace("§1§2§3§cx" + (number - 1), "§1§2§3§cx" + number));
							}
								
//						}
						
					}
					
				}
				normalStack.setItemMeta(itemMeta);
				normalStack.setAmount(1);
			}
			
			event.getWhoClicked().getInventory().addItem(normalStack);
			event.setCancelled(true);
//			event.getWhoClicked().closeInventory();
			Player player = (Player)event.getWhoClicked();
			player.updateInventory();
			return;
		}
		
		
		for(String s : slots.split("#")) {
			
			MessagesUtil.sendExternalMessage(4, "ForSlot: " + s, true);
			
			if(String.valueOf(event.getSlot()).equals(s)) {
				return;
			}
			
		}
		
		event.setCancelled(true);

	}
	
	@EventHandler
	public void onOpen(InventoryOpenEvent event) {
		
		Inventory inv = event.getInventory();
		if(inv == null)
			return;
		
		if(inv.getType().equals(InventoryType.ANVIL)) {
			
			Main.AnvilCraft.put(event.getPlayer().getName(), false);
			RunnableUtil.Runnable_AnvilCraft(event.getPlayer().getName(), inv);
			
			return;
		}
		
		String str = inv.getName();
		if(!(str.contains("§6§6§6")))
			return;
		
		event.setCancelled(true);
		HumanEntity human = event.getPlayer();
		human.closeInventory();
		Inventory readyInventory = InventoryUtil.createInventoryFrame("§7[§c压缩机§7]");
		
		human.openInventory(readyInventory);
		
	}
	
	@EventHandler
	public void onClose(InventoryCloseEvent event) {
		
		Inventory inv = event.getInventory();
		if(inv.getType() != InventoryType.CHEST) {
			
			if(inv.getType() == InventoryType.ANVIL) {
				
				Main.AnvilCraft.put(event.getPlayer().getName(), true);
				return;
			}
			
			return;
		}
		String str = inv.getName();
		if(str.contains("§a§a§a§0§0")) {
			
			ItemStack itemStack = inv.getItem(11);
			ItemStack itemStack1 = inv.getItem(15);
			if(itemStack != null) {
				event.getPlayer().getInventory().addItem(itemStack);
			}
			
			if(itemStack1 != null) {
				event.getPlayer().getInventory().addItem(itemStack1);
			}
			return;
		}
		if(!(str.equals("§7[§c压缩机§7]"))) {
			return;
		}

		String slots = "10#11#12#19#20#21#28#29#30";
		for(String s : slots.split("#")) {
		
//			MessageUtil.sendExternalMessage(4, "§6ForSlot: " + s, true);
			ItemStack itemStack = inv.getItem(Integer.parseInt(s));
			if(itemStack != null) {
				event.getPlayer().getInventory().addItem(itemStack);
			}
			
			
		
		}

	}
	
	
	

}
