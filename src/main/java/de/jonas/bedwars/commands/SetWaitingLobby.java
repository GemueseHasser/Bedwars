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

public class SetWaitingLobby implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("bedwars.setwaitinglobby")) {
            player.sendMessage(Bedwars.prefix+"Dazu hast du keine Rechte!");
            return true;
        }

        if (args.length != 0) {
            player.sendMessage(Bedwars.prefix+"Bitte benutze §c§l/setwaitinglobby");
            return true;
        }

        File file = new File("plugins/Bedwars", "WarteLobby.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        cfg.set("WarteLobby.World", player.getWorld().getName());
        cfg.set("WarteLobby.X", player.getLocation().getX());
        cfg.set("WarteLobby.Y", player.getLocation().getY());
        cfg.set("WarteLobby.Z", player.getLocation().getZ());
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.sendMessage(Bedwars.prefix+"Du hast die Warte-Lobby gesetzt!");
        return true;
    }
}
