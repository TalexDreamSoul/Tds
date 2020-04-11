package cn.tds.utils;

import cn.tds.zcraft.Main;

public class CooldownUtil {

    public static void setCooldown(String key){

        Main.File_CD.set(key,System.currentTimeMillis());

    }

    public static long getCDComplete(String key,long compareNum){

        long lastTimeStamp = Main.File_CD.getLong(key,System.currentTimeMillis());
        long thisTimeStamp = System.currentTimeMillis();
        long compareTS = thisTimeStamp - lastTimeStamp;
        if(compareTS == 0 || compareTS > compareNum){
            return -1;
        }

        return compareTS;

    }

}
