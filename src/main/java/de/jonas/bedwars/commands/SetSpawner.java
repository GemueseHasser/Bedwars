package de.jonas.bedwars.commands;

import java.io.File;
import java.io.IOException;

import de.jonas.bedwars.Bedwars;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class SetSpawner implements CommandExecutor {
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
            player.sendMessage(Bedwars.prefix+"Bitte benutze §c§lsetspawner <bronze|eisen|gold>");
            return true;
        }

        if (args[0].equalsIgnoreCase("bronze")) {
            createSpawnerAtLocation(player, "Bronze");
            player.sendMessage(Bedwars.prefix+"Du hast erfolgreich einen §aBronze-Spawner §cgesetzt!");
        } else if (args[0].equalsIgnoreCase("eisen")) {
            createSpawnerAtLocation(player, "Iron");
            player.sendMessage(Bedwars.prefix+"Du hast erfolgreich einen §aEisen-Spawner §cgesetzt!");
        } else if (args[0].equalsIgnoreCase("gold")) {
            createSpawnerAtLocation(player, "Gold");
            player.sendMessage(Bedwars.prefix+"Du hast erfolgreich einen §aGold-Spawner §cgesetzt!");
        }
        return true;
    }

    private void createSpawnerAtLocation(Player player, String category) {
        int amount;
        File file = new File("plugins/Bedwars", "Spawners.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        if (cfg.get(category + ".amount") == null) {
            cfg.set(category + ".amount", 0);
            try {
                cfg.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        amount = cfg.getInt(category + ".amount");
        amount++;
        cfg.set(category + ".amount", amount);
        cfg.set(category + "." + amount + ".World", player.getWorld().getName());
        cfg.set(category + "." + amount + ".X", player.getLocation().getX());
        cfg.set(category + "." + amount + ".Y", player.getLocation().getY());
        cfg.set(category + "." + amount + ".Z", player.getLocation().getZ());
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
