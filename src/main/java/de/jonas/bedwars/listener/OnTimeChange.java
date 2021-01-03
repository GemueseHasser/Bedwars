package de.jonas.bedwars.listener;

import java.io.File;

import de.jonas.bedwars.Bedwars;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class OnTimeChange {

    BukkitTask task;

    public OnTimeChange() {
        File file = new File("plugins/Bedwars", "Spawns.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        task = new BukkitRunnable() {
            @Override
            public void run() {
                if (cfg.getString("Spawn.Blau.World") == null) {
                    return;
                }
                Bukkit.getServer().getWorld(cfg.getString("Spawn.Blau.World")).setTime(6000);
            }
        }.runTaskTimer(Bedwars.getPlugin(), 0, 50);
    }

}
