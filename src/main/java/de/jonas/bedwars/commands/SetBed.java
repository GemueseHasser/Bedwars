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

public class SetBed implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("bedwars.setbed")) {
            player.sendMessage(Bedwars.prefix+"Dazu hast du keine Rechte!");
            return true;
        }

        if (args.length != 1) {
            player.sendMessage(Bedwars.prefix+"Bitte benutze §c§l/setbed <blau|rot>");
            return true;
        }

        if (args[0].equalsIgnoreCase("blau")) {
            setBed(player, 1);
            player.sendMessage(Bedwars.prefix+"Du hast das Bett für Team-Blau gesetzt!");
        } else if (args[0].equalsIgnoreCase("rot")) {
            setBed(player, 0);
            player.sendMessage(Bedwars.prefix+"Du hast das Bett für Team-Rot gesetzt!");
        } else {
            player.sendMessage(Bedwars.prefix+"Bitte benutze §c§l/setbed <blau|rot>");
        }
        return true;
    }

    private void setBed(Player player, int type) {
        // type = 0 -> Team-Rot; type = 1 -> Team-Blau;
        File file = new File("plugins/Bedwars", "Beds.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        String[] team = {"Rot", "Blau"};
        Location loc = player.getLocation();
        cfg.set("Bed." + team[type] + ".World", loc.getWorld().getName());
        cfg.set("Bed." + team[type] + ".X", loc.getX());
        cfg.set("Bed." + team[type] + ".Y", loc.getY());
        cfg.set("Bed." + team[type] + ".Z", loc.getZ());
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
