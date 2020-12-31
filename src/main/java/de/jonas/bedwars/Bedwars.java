package de.jonas.bedwars;

import de.jonas.bedwars.commands.SetSpawner;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Bedwars extends JavaPlugin {

    private static Bedwars plugin;

    private final ConsoleCommandSender console = Bukkit.getConsoleSender();

    public static String prefix = "§f§l[§6Bedwars§f§l] §c";

    @Override
    public void onEnable() {
        plugin = this;
        console.sendMessage(prefix+"Das Plugin wurde erfolgreich aktiviert!");

        loadConfig();

        registerCommands();
        registerListener();
    }

    @Override
    public void onDisable() {
        console.sendMessage(prefix+"Das Plugin wurde deaktiviert!");
    }

    public static Bedwars getPlugin() {
        return plugin;
    }

    private void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    private void registerCommands() {
        getCommand("setspawner").setExecutor(new SetSpawner());
    }

    private void registerListener() {

    }

}
