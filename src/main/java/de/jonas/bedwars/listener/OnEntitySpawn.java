package de.jonas.bedwars.listener;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class OnEntitySpawn implements Listener {

    @EventHandler
    public void onSpawnEntity(EntitySpawnEvent e) {
        File file = new File("plugins/Bedwars", "Spawns.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        if (!e.getEntity().getWorld().getName().equalsIgnoreCase(cfg.getString("Spawn.Blau.World"))) {
            return;
        }
        if (e.getEntity() instanceof Player || e.getEntity() instanceof Item || e.getEntity() instanceof Villager) {
            return;
        }
        e.setCancelled(true);
    }

}
