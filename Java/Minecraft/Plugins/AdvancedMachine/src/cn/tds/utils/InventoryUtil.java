package cn.tds.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryUtil extends ItemUtil{

	public static Inventory createInventoryFrame(String str) {
		
		Inventory inv = Bukkit.createInventory(null, 5 * 9,str);
		ItemStack itemStack = ItemUtil.getMachine(Material.STAINED_GLASS_PANE, "§e格挡板", null, 1);
		for(int i =  0;i < 9;i++) {
			
			inv.setItem(i,itemStack);
			
		}
		for(int i = 1;i <= 3;i++) {
			inv.setItem(9 * i, itemStack);
			inv.setItem(9 * i + 8, itemStack);
		}
		for(int i =  0;i < 9;i++) {
			
			inv.setItem(36 + i,itemStack);
			
		}
		inv.setItem(13,itemStack);
		inv.setItem(22,itemStack);
		inv.setItem(31,itemStack);
		inv.setItem(22,itemStack);
		inv.setItem(14,itemStack);
		inv.setItem(15,itemStack);
		inv.setItem(16,itemStack);
		inv.setItem(23,itemStack);
		inv.setItem(25,itemStack);
		inv.setItem(32,itemStack);
		inv.setItem(33,itemStack);
		inv.setItem(34,itemStack);
		inv.setItem(24, ItemUtil.getMachine(Material.SLIME_BALL, "§e压缩", null, 1));
		return inv;
		
	}
	
	public static Inventory createBreakGui() {
		
		Inventory inv = Bukkit.createInventory(null, 3 * 9,"§c破坏配置§a§a§a§0§0");
		ItemStack itemStack = ItemUtil.getMachine(Material.STAINED_GLASS_PANE, "§e格挡板", null, 1);
		for(int i =  0;i < 9;i++) {
			
			inv.setItem(i,itemStack);
			
		}
		for(int i =  0;i < 9;i++) {
			
			inv.setItem(18 + i,itemStack);
			
		}
		inv.setItem(9,itemStack);
		inv.setItem(10,itemStack);
		inv.setItem(12,itemStack);
		inv.setItem(14,itemStack);
		inv.setItem(16,itemStack);
		inv.setItem(17,itemStack);
		inv.setItem(13, ItemUtil.getMachine(Material.SLIME_BALL, "§e生成", null, 1));
		return inv;
		
	}
	
}
