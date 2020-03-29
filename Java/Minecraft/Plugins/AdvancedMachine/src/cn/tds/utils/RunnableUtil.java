package cn.tds.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import cn.tds.zcraft.Main;

public class RunnableUtil {
	
	//0002
	public static void Runnable_BlockMaker(String HashMapStr,Location BlockLocation) {
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				
				//判断Runnable是否需要取消
				if(Main.CobbleStoneMaker.get(HashMapStr)) {
					cancel();
					return;
				}
				
				
				Material BlockType = BlockLocation.getBlock().getType();
				//判断目标位置是否为熔炉 避免玩家打掉方块后Runnable持续运行
				if(!(BlockType.equals(Material.getMaterial(TalexFiles.langYaml.getString("BlockMaker.Material"))))) {
					Main.CobbleStoneMaker.put(HashMapStr, true);
					setList(BlockLocation, "BlockBreaker");
					cancel();
					return;
				}
				
				//创建新的位置信息
				Location newLoc = BlockLocation.clone();
				//将位置移动到熔炉上面
				newLoc.setY(newLoc.getY() + 1);
				
				ItemStack is = new ItemStack(Material.COBBLESTONE);
				BlockLocation.getWorld().dropItem(newLoc, is);
				//newLoc.getWorld().spawnParticle(Particle.CLOUD, newLoc.getX(), newLoc.getY(), newLoc.getZ(),8, 0.01, 0.01, 0.01, 0.01, 0.001);
					
			}
			
		}.runTaskTimer(Main.getPlugin(), 0L, 20L);
		
	}
	
	//0001
	public static void Runnable_BlockBreaker(String HashMapStr,Location BlockLocation) {
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				
				//判断Runnable是否需要取消
				if(Main.BlockBreaker.get(HashMapStr)) {
					cancel();
					return;
				}
				
				Material BlockType = BlockLocation.getBlock().getType();
				//判断目标位置是否为熔炉 避免玩家打掉方块后Runnable持续运行
				if(!(BlockType.equals(Material.getMaterial(TalexFiles.langYaml.getString("BlockBreaker.Material"))))) {
					Main.BlockBreaker.put(HashMapStr, true);
					setList(BlockLocation, "CobbleStoneMaker");
					cancel();
					return;
				}
				
				//创建新的位置信息
				Location newLoc = BlockLocation.clone();
				//将位置移动到熔炉下面
				newLoc.setY(newLoc.getY() - 1);
				
				//如果下面有水流则运行
				if(!(newLoc.getBlock().getType().equals(Material.STATIONARY_WATER))) { return; }
				
				//将水流清除
				newLoc.getBlock().breakNaturally(null);
				
				//创建新的位置信息
				Location dropLoc = BlockLocation.clone();
				
				//将位置移动到熔炉上面 来进行设置掉落物品掉落位置
				dropLoc.setY(dropLoc.getY() + 1);
				
				//将水流位置移动回来 回到熔炉位置
				newLoc.setY(newLoc.getY() + 1);
				
				//X坐标减去1
				newLoc.setX(newLoc.getX() - 1);
				
				//获得该位置的方块
				Block b1 = newLoc.getBlock();
				
				//处理该方块
				BlockUtil.BlockBreaker(b1, dropLoc);
					
				//将X回到原方块并且+1
				newLoc.setX(newLoc.getX() + 2);
				Block b2 = newLoc.getBlock();
				BlockUtil.BlockBreaker(b2, dropLoc);
				
				newLoc.setX(newLoc.getX() - 1);
				newLoc.setZ(newLoc.getZ() - 1);
				Block b3 = newLoc.getBlock();	
				BlockUtil.BlockBreaker(b3, dropLoc);
					
				newLoc.setZ(newLoc.getZ() + 2);
				Block b4 = newLoc.getBlock();
				BlockUtil.BlockBreaker(b4, dropLoc);
					
			}
			
		}.runTaskTimer(Main.getPlugin(), 0L, 30L);
		
	}
	public static boolean setList(Location loc,String type) {
		
		String locstr = loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ();
		locstr = locstr.replaceAll(".0","");
		
		YamlConfiguration yaml;
		yaml = YamlConfiguration.loadConfiguration(new File(Main.getPlugin().getDataFolder() + "/machines/" + type + ".yml"));
		List<String> list = yaml.getStringList(type+"."+loc.getWorld().getName());
		MessagesUtil.sendExternalMessage(5
				,"(" + type + ")List数据" + list, true);
		if(list.contains(locstr)) {

			for(int i = 0;i < list.size();i++) {
				if(list.get(i).equalsIgnoreCase(locstr)) {
					ItemStack itemStack = null;
					if(type.equalsIgnoreCase("BlockBreaker")) {
						Main.BlockBreaker.put(LocationUtil.loc2Str(loc.getBlockX(),loc.getBlockY(),loc.getBlockZ(),loc.getWorld().getName()),true);
						itemStack = ItemUtil.key2Config("BlockBreaker");
					} else if(type.equalsIgnoreCase("CobbleStoneMaker")) {
						Main.CobbleStoneMaker.put(LocationUtil.loc2Str(loc.getBlockX(),loc.getBlockY(),loc.getBlockZ(),loc.getWorld().getName()),true);
						itemStack = ItemUtil.key2Config("BlockMaker");
					} else if(type.equalsIgnoreCase("BlockCompress")) {
						Main.CobbleStoneMaker.put(LocationUtil.loc2Str(loc.getBlockX(),loc.getBlockY(),loc.getBlockZ(),loc.getWorld().getName()),true);
						itemStack = ItemUtil.key2Config("BlockCompress");
					}
					list.remove(i);
					
					yaml.set(type+"."+loc.getWorld().getName(),list);
					try {
						yaml.save(Main.getPlugin().getDataFolder() + "/machines/" + type + ".yml");
					} catch (IOException e) {
						MessagesUtil.sendExternalMessage(0, "§4错误: §c位于 §bSetList-Save §c的错误！ §2保存配置出错", true);
						MessagesUtil.sendExternalMessage(5, e.getMessage(), true);
						
					}
					
					loc.getWorld().dropItem(loc, itemStack);
					return true;
				}
			}
		}
		return false;
	}
	
	//AnvilCraft
	public static void Runnable_AnvilCraft(String HashMapStr,Inventory inv) {
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				
				MessagesUtil.sendExternalMessage(4, "§bAnvilCraft: §eRun", true);
				
				//判断Runnable是否需要取消
				if(Main.AnvilCraft.get(HashMapStr)) {
					cancel();
					return;
				}
				
				if(inv == null) {
					Main.AnvilCraft.put(HashMapStr, true);
					cancel();
					return;
				}
				
				ItemStack item0 = inv.getItem(0);
				if(item0 == null) {
					return;
				}
				
				ItemStack item1 = inv.getItem(1);
				if(item1 == null) {
					return;
				}
				
				if(item1.getType() != Material.ENCHANTED_BOOK) {
					return;
				}
				
				String lores0 = MessagesUtil.List2String(item0.getItemMeta().getLore());
				if(lores0 == null) {
					return;
				}
				
				if(!(item1.hasItemMeta()) || !(item1.getItemMeta().hasLore()))
					return;
				
				String lores1 = MessagesUtil.List2String(item1.getItemMeta().getLore());
				if(lores1 == null) {
					return;
				}
				
				if(!(lores0.contains("§0§0§0§4") && !(lores0.contains("§0§0§0§4§1")))) {
					return;
				}
				
				if(!(lores1.contains("§0§0§0§4§1"))) {
					return;
				}
				
				ItemStack item = item0.clone();
				List<String> lore = item.getLore();
				lore.add("§5工具切换 I §0§0§0§4§1");
				item.setLore(lore);
				inv.setItem(2, item);
				
			}
			
		}.runTaskTimer(Main.getPlugin(), 0, 0);
		
	}
	
	
}
