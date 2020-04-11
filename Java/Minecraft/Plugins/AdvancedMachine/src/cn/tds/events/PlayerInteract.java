package cn.tds.events;

import cn.tds.utils.*;
import cn.tds.zcraft.Main;
import jdk.nashorn.internal.ir.Block;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerInteract implements Listener{
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		
		if(!event.getAction().equals(Action.RIGHT_CLICK_AIR) && !event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
			return;
		ItemStack itemStack = event.getItem();
		if(itemStack == null)
			return;

		if(!itemStack.hasItemMeta() || itemStack.getItemMeta().getLore() == null) {
			return;
		}


		if(MessagesUtil.List2String(itemStack.getItemMeta().getLore()).contains("§0§0§0§5")){

			long back = CooldownUtil.getCDComplete(event.getPlayer().getName(),15000);

			if(back != -1){
				event.getPlayer().sendMessage("§c你需要 " + (15000 - back) / 1000 + "秒后才可继续使用飞毯！");
				return;
			}

			Player player = event.getPlayer();
			if(itemStack.getAmount() == 1){
				player.getInventory().setItemInMainHand(null);
			}else{
				itemStack.setAmount(itemStack.getAmount() - 1);

				player.getInventory().setItemInMainHand(itemStack);
			}

//			event.getPlayer().getInventory().setItemInMainHand(itemStack);
			event.getPlayer().sendMessage("§c你使用了 §a飞毯");
			Bukkit.broadcastMessage("玩家 " + event.getPlayer().getDisplayName() + "§a在世界 §b" + event.getPlayer().getWorld().getName() + " §a使用了飞毯！ §7(位置: " + TalexLocationUtil.loc2Str2(event.getPlayer().getLocation()) + "§7)");
			CooldownUtil.setCooldown(event.getPlayer().getName());
			Main.FlyingGlass.put(event.getPlayer().getName(),true);
			RunnableUtil.Runnable_FlyingGlass(event.getPlayer());


		}else if(MessagesUtil.List2String(itemStack.getItemMeta().getLore()).contains("§0§0§0§6")){

			Player player = event.getPlayer();
			Inventory inv = player.getInventory();

			ItemStack removeIS = new ItemStack(Material.COBBLESTONE);
			removeIS.setAmount(1);

			if(!inv.contains(Material.COBBLESTONE)){

				player.sendMessage("§c你的背包没有圆石或数量不够！");
				return;

			}



			Location main = player.getLocation();
			main = main.add(0,-1,0);

//			boolean back = false;
//			int amount = 0;

			if(inv.contains(Material.COBBLESTONE) && BlockUtil.PlaceCobbleStone(main,1,0,0)){

				inv.remove(removeIS);

			}
			if(inv.contains(Material.COBBLESTONE) &&  BlockUtil.PlaceCobbleStone(main,-1,0,0)){

				inv.remove(removeIS);

			}

			if(inv.contains(Material.COBBLESTONE) &&  BlockUtil.PlaceCobbleStone(main,0,0,1)){

				inv.remove(removeIS);

			}
			if(inv.contains(Material.COBBLESTONE) &&  BlockUtil.PlaceCobbleStone(main,0,0,-1)){

				inv.remove(removeIS);

			}

			if(inv.contains(Material.COBBLESTONE) &&  BlockUtil.PlaceCobbleStone(main,1,0,1)){

				inv.remove(removeIS);

			}
			if(inv.contains(Material.COBBLESTONE) &&  BlockUtil.PlaceCobbleStone(main,-1,0,-1)){

				inv.remove(removeIS);

			}

			if(inv.contains(Material.COBBLESTONE) &&  BlockUtil.PlaceCobbleStone(main,-1,0,1)){

				inv.remove(removeIS);

			}
			if(inv.contains(Material.COBBLESTONE) &&  BlockUtil.PlaceCobbleStone(main,1,0,-1)){

				inv.remove(removeIS);

			}

			if(inv.contains(Material.COBBLESTONE) &&  BlockUtil.PlaceCobbleStone(main,0,0,0)){

				inv.remove(removeIS);

			}
//			if(amount != 0){
//				removeIS.setAmount(amount);
//				inv.addItem(removeIS);
//
//			}


		}

		if(!event.getPlayer().isSneaking())
			return;
		

		
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
