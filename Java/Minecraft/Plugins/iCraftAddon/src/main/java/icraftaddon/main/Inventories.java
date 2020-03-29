package icraftaddon.main;

import cn.mcres.iCraft.api.Events.PanelCloseEvent;
import cn.mcres.iCraft.api.Events.PanelCraftEvent;
import cn.mcres.iCraft.api.Events.PanelOpenEvent;
import cn.mcres.iCraft.model.Panel;
import cn.mcres.iCraft.model.Recipe;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Inventories implements Listener {

    @EventHandler
    public void onOpen(PanelOpenEvent event){

        Player player = event.getPlayer();
        Inventory inv=  player.getOpenInventory().getTopInventory();
        if(inv == null)
            return;
        Main.PanelCraft.put(player.getName(),inv);
        Main.Panels.put(player.getName(),event.getPanel());

    }

    @EventHandler
    public void onClose(PanelCloseEvent event){

        Main.PanelCraft.remove(event.getPlayer().getName());
        Main.Panels.remove(event.getPlayer().getName());
        Main.CraftRecipe.remove(event.getPlayer().getName());

    }

    @EventHandler
    public void onCraft(PanelCraftEvent event){

        Main.CraftRecipe.put(event.getPlayer().getName(),event.getRecipe());

    }

    @EventHandler
    public void onCLick(InventoryClickEvent event){

        Inventory inv = event.getInventory();
        if(inv != Main.PanelCraft.get(event.getWhoClicked().getName()))
            return;

        Panel panel = Main.Panels.get(event.getWhoClicked().getName());
        Recipe craftRecipe = Main.CraftRecipe.get(event.getWhoClicked().getName());
        List<ItemStack> itemStacks = craftRecipe.getItemMatrix();
        List<Integer> slots = panel.getMatrixSlots();
        int j = 0;
        for(Integer i : slots){

            ItemStack itemStack = inv.getItem(i);
            if(itemStack == null)
                return;
            ItemStack itemStack1 = itemStacks.get(j);
            j++;
            if(!this.equals(itemStack,itemStack1)){
                return;
            }



        }

    }

    public boolean equals(ItemStack itemStack1 , ItemStack itemStack2){

        return itemStack1.getAmount() == itemStack2.getAmount() && itemStack1.isSimilar(itemStack2);

    }

}
