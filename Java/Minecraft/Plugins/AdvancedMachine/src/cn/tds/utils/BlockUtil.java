package cn.tds.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

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

}
