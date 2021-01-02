package de.jonas.bedwars.shop;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class OnInteractAtEntity implements Listener {

    @EventHandler
    public void onInteractAtEntity(PlayerInteractEntityEvent e) {
        Entity clicked = e.getRightClicked();
        if (clicked == null) {
            return;
        }
        if (clicked.getCustomName() == null) {
            return;
        }
        if (!clicked.getCustomName().equals("§6§lSHOP")) {
            return;
        }
        e.setCancelled(true);
        Player player = e.getPlayer();
        GUI inv = new GUI();
        player.openInventory(inv.startScreen());
    }

}
