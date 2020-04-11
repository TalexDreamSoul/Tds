package cn.tds.utils;


import org.bukkit.Location;

public class TalexLocationUtil extends AdvancedUtil{
	
	
//	//获取玩家指向方块
//	@SuppressWarnings("null")
//	public static Block getPointAtBlock(Player player) {
//		Location loc = player.getLocation();
//		Location newLoc = loc.add(0,15,0);
//		double yaw = Math.toRadians(newLoc.getYaw());
//		double pitch = Math.toRadians(newLoc.getPitch());
//		double m = Math.cos(pitch);
//		Vector base = player.getEyeLocation().toVector();
//		base.setX(Math.cos(yaw) + m);
//		base.setY(Math.sin(pitch));
//		base.setZ(Math.sin(yaw) * m);
//		Block block = loc.getWorld().getBlockAt(newLoc.clone().add(base));
//		double distance = 0;
//		while(block.getType() != Material.AIR && distance <= 16) {
//			
//			distance += 0.2;
//			Vector vec = player.getEyeLocation().toVector();
//			vec.setX(base.getX());
//			vec.setY(base.getY());
//			vec.setZ(base.getZ());
//			vec.multiply(distance);
//			block = loc.getWorld().getBlockAt(newLoc.clone().add(vec));
//			return block;
//			
//		}
//		return null;
//	}
	//HashMapLocation
	public static String loc2Str2(Location loc) {
					return loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ();
	}
	//HashMapLocation
	public static String loc2Str2(double x,double y,double z) {
				return x + "," + y + "," + z;
	}
	//HashMapLocation
	public static String loc2Str(double x,double y,double z,String worldName) {
			return x + "," + y + "," + z + "##" + worldName;
	}
	//HashMapLocation
	public static String loc2Str(Location loc) {
		return loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockZ() + "##" + loc.getWorld().getName();
	}
	
	@SuppressWarnings("unused")
	private static String getStringWorldName(String str) {
		// 0,0,0##worldName
		String worldName;
		String[] str2 = str.split("##");
		if(str2.length != 2) {
			return "null";
		}
		worldName = str2[1];
		
		return worldName;
	}
	public static String getStringLocation(String str) {
		// 0,0,0##worldName
		String worldName = "";
		String[] str2 = str.split("##");
		if(str2.length != 2) {
			return "null";
		}
		worldName = str2[1];
		
		return worldName;
	}

	public static double getStringLocationAddon(String str,int type) {
		
		String[] str2 = str.split(",");
//		MessagesUtil.sendExternalMessage(5,"GSLA: " + str,true);
		if(str2.length != 3) {
			return -1;
		}
		if(type > 0 && type <= 3) {
			return Double.parseDouble(str2[type - 1]);
		}
		return -1; 
	}

}
