package de.jonas.bedwars.listener;

import java.io.File;

import de.jonas.bedwars.system.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class OnWorldChange implements Listener {

    @EventHandler
    public void onChangeWorld(PlayerChangedWorldEvent e) {
        Player player = e.getPlayer();
        File file = new File("plugins/Bedwars", "Spawns.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        if (!e.getFrom().getName().equalsIgnoreCase(cfg.getString("Spawn.Blau.World"))) {
            return;
        }
        new Main().exitGame(player);
    }

}
