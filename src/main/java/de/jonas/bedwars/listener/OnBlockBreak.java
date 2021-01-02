package de.jonas.bedwars.listener;

import java.io.File;

import de.jonas.bedwars.Bedwars;
import de.jonas.bedwars.system.Main;
import de.jonas.bedwars.system.Variablen;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class OnBlockBreak implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        if (!(Main.blau.contains(player.getName()) || Main.rot.contains(player.getName()))) {
            e.setCancelled(false);
            return;
        }
        Location location = e.getBlock().getLocation();
        if ((e.getBlock().getLocation().getX() == getBed(0).getX()
        || e.getBlock().getLocation().getX() == getBed(0).getX() - 1)
        && e.getBlock().getLocation().getY() == getBed(0).getY()
        && e.getBlock().getLocation().getZ() == getBed(0).getZ()) {
            // bett von Team rot wurde zerstört
            if (player.equals(Bukkit.getPlayer(Main.rot.get(0)))) {
                e.setCancelled(true);
                player.sendMessage(Bedwars.prefix+"Du kannst dein eigenes Bett nicht abbauen!");
                return;
            }
            Variablen.bedRot = false;
            e.setCancelled(false);
            e.setDropItems(false);
            Player gegner = Bukkit.getPlayer(Main.blau.get(0));
            player.sendTitle("", ChatColor.RED + "Das Bett von Team " + ChatColor.DARK_RED + "Rot" + ChatColor.RED + " wurde zerstört",
                10, 20, 10);
            gegner.sendTitle("", ChatColor.RED + "Das Bett von Team " + ChatColor.DARK_RED + "Rot" + ChatColor.RED + " wurde zerstört",
                10, 20, 10);
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 10, 1);
            gegner.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 10, 1);
            return;
        }
        if ((e.getBlock().getLocation().getX() == getBed(1).getX()
            || e.getBlock().getLocation().getX() == getBed(1).getX() - 1)
            && e.getBlock().getLocation().getY() == getBed(1).getY()
            && e.getBlock().getLocation().getZ() == getBed(1).getZ()) {
            if (player.equals(Bukkit.getPlayer(Main.blau.get(0)))) {
                e.setCancelled(true);
                player.sendMessage(Bedwars.prefix+"Du kannst dein eigenes Bett nicht abbauen!");
                return;
            }
            Variablen.bedBlau = false;
            e.setCancelled(false);
            e.setDropItems(false);
            Player gegner = Bukkit.getPlayer(Main.rot.get(0));
            player.sendTitle("", ChatColor.RED + "Das Bett von Team " + ChatColor.BLUE + "Blau" + ChatColor.RED + " wurde zerstört",
                10, 20, 10);
            gegner.sendTitle("", ChatColor.RED + "Das Bett von Team " + ChatColor.BLUE + "Blau" + ChatColor.RED + " wurde zerstört",
                10, 20, 10);
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 10, 1);
            gegner.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 10, 1);
            return;
        }
        if ((!OnBlockPlace.locs.contains(location)) && (location.getBlock().getType() != Material.AIR
        || location.getBlock().getType() != null)) {
            e.setCancelled(true);
        }
    }

    private Location getBed(int type) {
        // type = 0 -> Team-Rot; type = 1 -> Team-Blau;
        File file = new File("plugins/Bedwars", "Beds.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        String[] team = {"Rot", "Blau"};

        String world = cfg.getString("Bed." + team[type] + ".World");
        int x = cfg.getInt("Bed." + team[type] + ".X");
        int y = cfg.getInt("Bed." + team[type] + ".Y");
        int z = cfg.getInt("Bed." + team[type] + ".Z");

        Location loc = new Location(Bukkit.getWorld(world), x, y, z);
        return loc;
    }

}
