package cn.tds.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class BlockUtil {
	
	
	public static void BlockBreaker(Block block, Location dropLoc){
		
		//判断该方块是否为禁止破坏的方块(空气)
		if(ItemUtil.getBanBlock(block)){
			//New一个itemstack类型设置为该方块
			ItemStack itemStack = new ItemStack(block.getType());
			//设置方块为空
			block.getLocation().getBlock().setType(Material.AIR);
			
			//掉落该物品
			dropLoc.getWorld().dropItem(dropLoc, itemStack);

			//粒子效果
			dropLoc.getWorld().spawnParticle(Particle.FLAME, dropLoc.getX(), dropLoc.getY(), dropLoc.getZ(), 5, 0.01, 0.01, 0.01, 0.0001, null);
		}
		
	}

	public static boolean PlaceCobbleStone(Location loc,int x,int y,int z){

		Location newLoc = loc.clone();
		newLoc = newLoc.add(x , y , z);
		if(!newLoc.getBlock().getType().equals(Material.AIR))
			return false;
		newLoc.getBlock().setType(Material.COBBLESTONE);
		return true;

	}

	public static List<String> FlyingGlassPut(Location old, int addX, int addY, int addZ, List<String> list){

		Location newLoc = old.clone();
		newLoc = newLoc.add(addX , addY ,addZ);
		Block block = newLoc.getBlock();
		if(block.getType() == Material.AIR){

			block.setType(Material.GLASS);
			List<String> newList = list;
			newList.add(TalexLocationUtil.loc2Str2(block.getLocation()));

		}

		return list;

	}

}
