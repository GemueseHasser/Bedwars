package de.jonas.bedwars.listener;

import java.util.ArrayList;

import de.jonas.bedwars.system.Main;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class OnBlockPlace implements Listener {

    public static ArrayList<Location> locs = new ArrayList<>();

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        if (Main.waiters.contains(player.getUniqueId())) {
            e.setCancelled(true);
        }
        if (!(Main.blau.contains(player.getName()) || Main.rot.contains(player.getName()))) {
            return;
        }
        Location location = e.getBlock().getLocation();
        if (!locs.contains(location)) {
            locs.add(location);
        }
    }

}
