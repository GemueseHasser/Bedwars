package de.jonas.bedwars.listener;

import java.io.File;

import de.jonas.bedwars.Bedwars;
import de.jonas.bedwars.system.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class OnChat implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        if (!(Main.blau.contains(player.getName()) || Main.rot.contains(player.getName()))) {
            return;
        }
        e.setCancelled(true);
        File file = new File("plugins/Bedwars", "Spawns.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        for (Player all : Bukkit.getOnlinePlayers()) {
            if (!all.getWorld().getName().equalsIgnoreCase(cfg.getString("Spawn.Blau.World"))) {
                continue;
            }
            all.sendMessage(Bedwars.prefix + "§a" + e.getMessage());
        }
    }

}
