package de.jonas.bedwars;

import de.jonas.bedwars.commands.SetShop;
import de.jonas.bedwars.commands.SetSpawner;
import de.jonas.bedwars.shop.InitializeItemStacks;
import de.jonas.bedwars.shop.OnInteractAtEntity;
import de.jonas.bedwars.shop.OnInventoryClick;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginManager;
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
        new InitializeItemStacks();
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
        getCommand("setshop").setExecutor(new SetShop());
    }

    private void registerListener() {
        PluginManager pluginManager = Bukkit.getServer().getPluginManager();
        pluginManager.registerEvents(new OnInteractAtEntity(), this);
        pluginManager.registerEvents(new OnInventoryClick(), this);
    }

}
