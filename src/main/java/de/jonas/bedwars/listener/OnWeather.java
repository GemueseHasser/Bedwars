package de.jonas.bedwars.listener;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class OnWeather implements Listener {

    @EventHandler
    public void onWeather(WeatherChangeEvent e) {
        if (!e.toWeatherState()) {
            return;
        }
        File file = new File("plugins/Bedwars", "Spawns.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        if (!e.getWorld().getName().equalsIgnoreCase(cfg.getString("Spawn.Blau.World"))) {
            return;
        }
        e.setCancelled(true);
    }

    @EventHandler
    public void onThunder(ThunderChangeEvent e) {
        if (!e.toThunderState()) {
            return;
        }
        File file = new File("plugins/Bedwars", "Spawns.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        if (!e.getWorld().getName().equalsIgnoreCase(cfg.getString("Spawn.Blau.World"))) {
            return;
        }
        e.setCancelled(true);
    }

}
