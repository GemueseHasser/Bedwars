package de.jonas.bedwars.spawner;

import java.io.File;

import de.jonas.bedwars.Bedwars;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class Spawner {

    private BukkitTask spawnerTask;

    private ItemStack bronze, eisen, gold;

    private int currentBrickProcedure = 0;
    private int currentIronProcedure = 0;
    private int currentGoldProcedure = 0;

    public void startSpawner(
        final int bricksPeriodInSecs,
        final int ironPeriodInSecs,
        final int goldPeriodInSecs
    ) {
        initializeItemStacks();
        spawnerTask = new BukkitRunnable() {
            @Override
            public void run() {
                if (currentBrickProcedure == bricksPeriodInSecs) {
                    spawnBricks();
                    currentBrickProcedure = 0;
                }
                if (currentIronProcedure == ironPeriodInSecs) {
                    spawnIron();
                    currentIronProcedure = 0;
                }
                if (currentGoldProcedure == goldPeriodInSecs) {
                    spawnGold();
                    currentGoldProcedure = 0;
                }
                currentBrickProcedure++;
                currentIronProcedure++;
                currentGoldProcedure++;
            }
        }.runTaskTimer(Bedwars.getPlugin(), 0, 20);
    }

    public void cancelTask() {
        spawnerTask.cancel();
    }

    private void spawnBricks() {
        File file = new File("plugins/Bedwars", "Spawners.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        int amount = cfg.getInt("Bronze.amount");
        for (int i = 1; i <= amount; i++) {
            String world = cfg.getString("Bronze." + i + ".World");
            double x = cfg.getDouble("Bronze." + i + ".X");
            double y = cfg.getDouble("Bronze." + i + ".Y");
            double z = cfg.getDouble("Bronze." + i + ".Z");
            Location location = new Location(Bukkit.getWorld(world), x, y, z);
            location.getWorld().dropItemNaturally(location, bronze);
        }
    }

    private void spawnIron() {
        File file = new File("plugins/Bedwars", "Spawners.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        int amount = cfg.getInt("Iron.amount");
        for (int i = 1; i <= amount; i++) {
            String world = cfg.getString("Iron." + i + ".World");
            double x = cfg.getDouble("Iron." + i + ".X");
            double y = cfg.getDouble("Iron." + i + ".Y");
            double z = cfg.getDouble("Iron." + i + ".Z");
            Location location = new Location(Bukkit.getWorld(world), x, y, z);
            location.getWorld().dropItemNaturally(location, eisen);
        }
    }

    private void spawnGold() {
        File file = new File("plugins/Bedwars", "Spawners.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        int amount = cfg.getInt("Gold.amount");
        for (int i = 1; i <= amount; i++) {
            String world = cfg.getString("Gold." + i + ".World");
            double x = cfg.getDouble("Gold." + i + ".X");
            double y = cfg.getDouble("Gold." + i + ".Y");
            double z = cfg.getDouble("Gold." + i + ".Z");
            Location location = new Location(Bukkit.getWorld(world), x, y, z);
            location.getWorld().dropItemNaturally(location, gold);
        }
    }

    private void initializeItemStacks() {
        bronze = new ItemStack(Material.CLAY_BRICK);
        ItemMeta bronzeMeta = bronze.getItemMeta();
        bronzeMeta.setDisplayName("§cBronze");
        bronze.setItemMeta(bronzeMeta);

        eisen = new ItemStack(Material.IRON_INGOT);
        ItemMeta silberMeta = eisen.getItemMeta();
        silberMeta.setDisplayName("§8Silber");
        eisen.setItemMeta(silberMeta);

        gold = new ItemStack(Material.GOLD_INGOT);
        ItemMeta goldMeta = gold.getItemMeta();
        goldMeta.setDisplayName("§6Gold");
        gold.setItemMeta(goldMeta);
    }

}
