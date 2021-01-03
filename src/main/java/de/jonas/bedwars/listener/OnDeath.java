package de.jonas.bedwars.listener;

import java.io.File;

import de.jonas.bedwars.Bedwars;
import de.jonas.bedwars.system.Main;
import de.jonas.bedwars.system.Variablen;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class OnDeath implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        if (e.getEntity() == null) {
            return;
        }

        Player player = e.getEntity();

        if (!(Main.blau.contains(player.getName()) || Main.rot.contains(player.getName()))) {
            return;
        }

        File file = new File("plugins/Bedwars", "Spawns.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        String worldRot = cfg.getString("Spawn.Rot.World");
        int xRot = cfg.getInt("Spawn.Rot.X");
        int yRot = cfg.getInt("Spawn.Rot.Y");
        int zRot = cfg.getInt("Spawn.Rot.Z");

        String worldBlau = cfg.getString("Spawn.Blau.World");
        int xBlau = cfg.getInt("Spawn.Blau.X");
        int yBlau = cfg.getInt("Spawn.Blau.Y");
        int zBlau = cfg.getInt("Spawn.Blau.Z");

        Location spawnRot = new Location(Bukkit.getWorld(worldRot), xRot, yRot, zRot);
        Location spawnBlau = new Location(Bukkit.getWorld(worldBlau), xBlau, yBlau, zBlau);

        player.getInventory().clear();

        for (Player all : Bukkit.getOnlinePlayers()) {
            if (!all.getWorld().getName().equalsIgnoreCase(cfg.getString("Spawn.Blau.World"))) {
                continue;
            }
            all.sendMessage(Bedwars.prefix+ ChatColor.GREEN + player.getName() + "§c ist gestorben!");
        }

        if (Main.blau.contains(player.getName())) {
            e.setDeathMessage("");
            player.spigot().respawn();
            player.teleport(spawnBlau);
            if (!Variablen.bedBlau) {
                new Main().win(0);
            }
        } else {
            e.setDeathMessage("");
            player.spigot().respawn();
            player.teleport(spawnRot);
            if (!Variablen.bedRot) {
                new Main().win(1);
            }
        }
    }

}
