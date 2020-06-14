package talexlores.main;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Events implements Listener {

    @EventHandler
    public void onRight(PlayerInteractEvent event){

        Action action = event.getAction();
        if(action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK){

            ItemStack itemStack = event.getItem();
            if(!itemStack.hasItemMeta()){
                return;
            }

            ItemMeta itemMeta = itemStack.getItemMeta();
            if(!itemMeta.hasLore()){

                return;

            }

            List<String> lore = itemMeta.getLore();
            List<String> matches = this.yamlConfigSets2List("RightClickRemoveItem.Orders");

            for(String match : matches){

                for(String str : lore){

                    String path = "RightClickRemoveItem.Orders." + match;
                    String key = Main.instance.getConfig().getString(path + ".Key","");
                    String compare = Main.instance.getConfig().getString(path + ".Compare","Completely");
                    String message = Main.instance.getConfig().getString(path + ".message","none");
                    boolean yes = false;
                    if(compare.equalsIgnoreCase("Contains")){

                        if(str.contains(key)) {

                            yes = true;

                        }

                    }else{

                        if(str.equals(key)){

                            yes = true;

                        }

                    }

                    if(yes){

                        event.getPlayer().getInventory().getItem(event.getHand()).setType(Material.AIR);

                        if(!message.equalsIgnoreCase("none")){

                            event.getPlayer().sendMessage(message.replace("&","§").replace("%name%",itemMeta.getDisplayName()).replace("%lore%",key).replace("%thislore%",str));

                        }

                    }


                }


            }


            matches = this.yamlConfigSets2List("RightClickRemoveLore.Orders");

            for(String match : matches){

                for(int i = 0;i < lore.size();i++){

                    String str = lore.get(i);
                    String path = "RightClickRemoveLore.Orders." + match;
                    String key = Main.instance.getConfig().getString(path + ".Key","");
                    String compare = Main.instance.getConfig().getString(path + ".Compare","Completely");
                    String message = Main.instance.getConfig().getString(path + ".message","none");
                    boolean yes = false;
                    if(compare.equalsIgnoreCase("Contains")){

                        if(str.contains(key)) {

                            yes = true;

                        }

                    }else{

                        if(str.equals(key)){

                            yes = true;

                        }

                    }

                    if(yes){

                        List<String> loreRep = Main.instance.getConfig().getStringList(path + ".removeLores");

                        for(String text : loreRep){

                            text = text.replace("%name%",itemMeta.getDisplayName()).replace("%lore%",key);

                            if(text.equals("%all%")){

                                itemMeta.setLore(new ArrayList<>());

                            }

                            if(str.equals(text)){

                                lore.remove(i);

                            }

                            if(str.equals("%thislore%")){

                                lore.remove(i);

                            }

                        }

                        itemStack.setItemMeta(itemMeta);
                        event.getPlayer().getInventory().setItem(event.getHand(),itemStack);


                        if(!message.equalsIgnoreCase("none")){

                            event.getPlayer().sendMessage(message.replace("&","§").replace("%name%",itemMeta.getDisplayName()).replace("%lore%",key).replace("%thislore%",str));

                        }

                    }


                }


            }





        }

    }

    private List<String> yamlConfigSets2List(String path){

        Set<String> sets = Main.instance.getConfig().getConfigurationSection(path).getKeys(false);
        List<String> list = new ArrayList<>(sets);
        return list;

    }

}
