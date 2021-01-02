package de.jonas.bedwars.commands;

import java.io.File;
import java.io.IOException;

import de.jonas.bedwars.Bedwars;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class SetSpawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("bedwars.setSpawner")) {
            player.sendMessage(Bedwars.prefix+"Dazu hast du keine Rechte!");
            return true;
        }

        if (args.length != 1) {
            player.sendMessage(Bedwars.prefix+"Bitte benutze §c§lsetspawn <blau|rot>");
            return true;
        }

        if (args[0].equalsIgnoreCase("blau")) {
            setSpawn("Blau", player.getLocation());
            player.sendMessage(Bedwars.prefix+"Du hast den Spawn für Blau gesetzt!");
        } else if (args[0].equalsIgnoreCase("rot")) {
            setSpawn("Rot", player.getLocation());
            player.sendMessage(Bedwars.prefix+"Du hast den Spawn für Rot gesetzt!");
        } else {
            player.sendMessage(Bedwars.prefix+"Bitte benutze §c§lsetspawn <blau|rot>");
        }
        return true;
    }

    private void setSpawn(String team, Location loc) {
        File file = new File("plugins/Bedwars", "Spawns.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        cfg.set("Spawn." + team + ".World", loc.getWorld().getName());
        cfg.set("Spawn." + team + ".X", loc.getX());
        cfg.set("Spawn." + team + ".Y", loc.getY());
        cfg.set("Spawn." + team + ".Z", loc.getZ());
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
