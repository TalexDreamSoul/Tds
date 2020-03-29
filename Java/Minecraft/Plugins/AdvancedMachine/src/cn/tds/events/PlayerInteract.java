package cn.tds.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import cn.tds.utils.MessagesUtil;

public class PlayerInteract implements Listener{
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		
		if(!event.getAction().equals(Action.RIGHT_CLICK_AIR) && !event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
			return;
		ItemStack itemStack = event.getItem();
		if(itemStack == null)
			return;
		
		if(!event.getPlayer().isSneaking())
			return;
		
		if(!itemStack.hasItemMeta() || itemStack.getItemMeta().getLore() == null) {
			return;
		}
		
		if(MessagesUtil.List2String(itemStack.getItemMeta().getLore()).contains("§0§0§0§4") && MessagesUtil.List2String(itemStack.getItemMeta().getLore()).contains("§0§0§0§4§1")) {
			
			Material type = itemStack.getType();
			if(type.equals(Material.STONE_SPADE)) {
				
				itemStack.setType(Material.STONE_PICKAXE);
				
			}else {
				
				itemStack.setType(Material.STONE_SPADE);
				
			}
			
			event.getPlayer().getInventory().setItemInHand(itemStack);
			event.getPlayer().sendActionBar("§c破碎锤 §l切换 §b⛏");
			
		}
		
		
		
		
	}
	

}
