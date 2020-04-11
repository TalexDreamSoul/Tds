package cn.tds.events;

import cn.tds.utils.TalexFiles;
import cn.tds.zcraft.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Random;

public class PlayerMove implements Listener{

	/*public static void covPlayer(Player player){

		Random random = new Random();

		new BukkitRunnable() {

			@Override
			public void run() {


				if(player.getHealth() < player.getMaxHealth() * 0.2){

					int j = random.nextInt(100);
					if(j >= 95){

						player.setHealth(0);
						Bukkit.broadcastMessage("§a玩家 §c" + player.getDisplayName() + " §a身体过度劳累感染病毒死亡了！");

					}
					cancel();

				}else{

					double health = player.getMaxHealth() * 0.8;
					if(player.getHealth() - health < 0.5){
						player.setHealth(1);
					}else{

						player.setHealth(health);
						player.sendTitle("","§7- §b病毒正在侵蚀你的身体 §7-",0,20,10);

					}

				}


			}

		}.runTaskTimer(Main.getPlugin(), 50L, 40L);

	}

	@EventHandler
	public void onMove(PlayerMoveEvent event) {

		Random random = new Random();

		int i = random.nextInt(1000000);
		if(i > 999800){

			Player player = event.getPlayer();

			ItemStack itemStack = player.getInventory().getHelmet();

			if(itemStack != null && itemStack.hasItemMeta()){

				ItemMeta itemMeta = itemStack.getItemMeta();
				if(itemMeta.getLore() != null){

					List<String> list = itemMeta.getLore();
					//if(list != null){

						for(String str : list){

							if(str.contains("§k§o§u§z§h§a§o")) {

								player.sendTitle("", "§e- §d你的口罩帮助您抵挡住了病毒 §e-");
								return;

							}
						}

					//}


				}

			}

			if(i >= 999950){

				player.setHealth(0);
				Bukkit.broadcastMessage("§b玩家 §c" + player.getDisplayName() + " §b因昨晚过度劳累导致感染病毒死亡了！");
				return;

			}

			player.sendMessage("§a嗷,你因为行走过度感染了病毒！");
			player.sendTitle("","§7- §c你感染了病毒 §7-",0,60,10);


			covPlayer(player);

		}

		
//		Set<Material> setM = null;
//		event.getPlayer().getLocation().getY
//		Block block = LocationUtil.getPointAtBlock(event.getPlayer());
//		Block block = event.getPlayer().getTargetBlock(null, 2020);
//		if(block.getType().equals(Material.FURNACE)) {
//			
//			
//			
//			
//		}
		
		
		
	}*/
	

}
