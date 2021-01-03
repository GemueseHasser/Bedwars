package de.jonas.bedwars.listener;

import de.jonas.bedwars.Bedwars;
import de.jonas.bedwars.system.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class OnRespawn implements Listener {

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Player player = e.getPlayer();
        if (!(Main.blau.contains(player.getName()) || Main.rot.contains(player.getName()))) {
            return;
        }
        BukkitTask task = new BukkitRunnable() {
            @Override
            public void run() {
                player.getInventory().clear();
            }
        }.runTaskLater(Bedwars.getPlugin(), 30L);
    }

}
