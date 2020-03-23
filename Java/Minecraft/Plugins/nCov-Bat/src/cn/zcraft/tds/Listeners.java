package cn.zcraft.tds;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Listeners implements Listener{
	
	private static Main thisIns = Main.getIns();
	
	@EventHandler
	public void onPVP(EntityDamageByEntityEvent event) {
		
		if(event.getDamager() instanceof Bat) {
			
			
			Entity entity = event.getEntity();

			if(!(entity instanceof Player)) {
				return;
			}
			
			int BatnCov = thisIns.getConfig().getInt("Random.bat");
			int BatDeath = thisIns.getConfig().getInt("Random.batDeath");
			
			Random random = new Random();
			
			if(random.nextInt(100) <= BatnCov) {
				Player player = (Player)entity;
				String keyStr = thisIns.getConfig().getString("Settings.MaskKeyWord");
				ItemStack itemStack = player.getInventory().getHelmet();
				if(itemStack.getItemMeta().getDisplayName().contains(keyStr) || itemStack.getItemMeta().getLore().contains(keyStr)) {
					
					player.sendMessage("§c你的口罩为你挡住了病毒！");
					return;
					
				}
				
				if(random.nextInt(100) <= BatDeath) {
					
					player.setHealth(0);
					player.sendMessage(thisIns.getConfig().getString("Settings.msgDeath"));
					return;
				}

				
				player.addPotionEffect(new PotionEffect(PotionEffectType.POISON,thisIns.getConfig().getInt("Settings.time"), 1));
				
			}
			
			
		}
		
	}
	@EventHandler
	public void ondamage(EntityDamageByEntityEvent event) {
		
		Entity entity = event.getDamager();
		if(!(entity instanceof Zombie)) {
			return;
		}
		if(entity.getCustomName().equalsIgnoreCase(thisIns.getConfig().getString("Settings.zombieName"))) {
			
			event.setDamage(event.getDamage() * 2);
			
		}
		
		
	}
	@EventHandler
	public void onTurn(CreatureSpawnEvent event) {
		
		if(!(event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.CURED))) {
			return;
		}

			
			Random random = new Random();
			int zombie = thisIns.getConfig().getInt("Random.zombie");
			
			if(random.nextInt(100) <= zombie) {
				
				event.getEntity().remove();
				event.getEntity().getWorld().strikeLightning(event.getLocation());
				
				Entity entity = event.getLocation().getWorld().spawnEntity(event.getLocation(), EntityType.ZOMBIE);
				entity.setGlowing(true);
				Zombie newZombie = (Zombie)entity;
				newZombie.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE,99999,233));
				newZombie.addPotionEffect(new PotionEffect(PotionEffectType.HEAL,99999,2));
				newZombie.setHealth(100);
				
				entity.setCustomName(thisIns.getConfig().getString("Settings.zombieName"));
				
				Location entityLocation = entity.getLocation();
				
				
				for(Player player : Bukkit.getOnlinePlayers()) {
					
					Location loc = player.getLocation();
					
					if(loc.getWorld() == entityLocation.getWorld() && loc.distance(entityLocation) < 5) {
							
						player.sendMessage(thisIns.getConfig().getString("Settings.zombieMsg"));

					}
					
				}
				

			
			
			
			
		}
		
	}
	
	
}
