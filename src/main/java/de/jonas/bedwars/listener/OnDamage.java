package de.jonas.bedwars.listener;

import java.io.File;

import de.jonas.bedwars.system.Variablen;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class OnDamage implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (Variablen.damage) {
            e.setCancelled(false);
            return;
        }

        File file = new File("plugins/Bedwars", "Spawns.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        for (Player all : Bukkit.getOnlinePlayers()) {
            if (!all.getWorld().getName().equalsIgnoreCase(cfg.getString("Spawn.Blau.World"))) {
                continue;
            }
            e.setCancelled(true);
        }
    }

}
