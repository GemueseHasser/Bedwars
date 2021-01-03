package de.jonas.bedwars.commands;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import de.jonas.bedwars.Bedwars;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class SetShop implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("bedwars.setshop")) {
            player.sendMessage(Bedwars.prefix+"Dazu hzast du keine Rechte!");
            return true;
        }

        if (args.length != 0) {
            player.sendMessage(Bedwars.prefix+"Bitte benutze §c§l/setshop");
            return true;
        }

        spawnShop(player.getLocation());
        player.sendMessage(Bedwars.prefix+"Du hast den §aShop §cgesetzt!");

        return true;
    }

    private void spawnShop(Location location) {
        File file = new File("plugins/Bedwars", "Shops.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        int amount;
        if (cfg.get("Shop.amount") == null) {
            cfg.set("Shop.amount", 0);
            try {
                cfg.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        amount = cfg.getInt("Shop.amount");
        amount++;
        cfg.set("Shop.amount", amount);
        cfg.set("Shop." + amount + ".World", location.getWorld().getName());
        cfg.set("Shop." + amount + ".X", location.getX());
        cfg.set("Shop." + amount + ".Y", location.getY());
        cfg.set("Shop." + amount + ".Z", location.getZ());
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
