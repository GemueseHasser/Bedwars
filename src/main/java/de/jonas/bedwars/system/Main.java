package de.jonas.bedwars.system;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import de.jonas.bedwars.Bedwars;
import de.jonas.bedwars.listener.OnBlockPlace;
import de.jonas.bedwars.listener.OnInteract;
import de.jonas.bedwars.spawner.Spawner;
import de.jonas.bedwars.util.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Bed;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

public class Main {

    public static ArrayList<UUID> waiters = new ArrayList<>();

    public static ArrayList<String> rot = new ArrayList<>();

    public static ArrayList<String> blau = new ArrayList<>();

    private BukkitTask task, taskI;

    public void addPlayerToWaiters(UUID uuid) {
        Player player = Bukkit.getPlayer(uuid);
        setWaitingHotbar(player);
        if (!waiters.contains(player.getUniqueId())) {
            waiters.add(player.getUniqueId());
            Variablen.player++;
        }
    }

    private void setWaitingHotbar(Player player) {
        Inventory inv = player.getInventory();

        ItemStack bed = null;

        for (int i = 0; i < 9; i++) {
            if (i == 4) continue;
            inv.setItem(i, new ItemStack(Material.AIR));
        }
        inv.setItem(4, new ItemCreator().setDisplayName(bed, Material.BED, "§6Teams"));
    }

    public void checkIfGameCanStart() {
        if (rot.size() == 1 && blau.size() == 1) {
            startGame();
        } else {
            for (Player all : Bukkit.getOnlinePlayers()) {
                all.sendMessage(Bedwars.prefix+"Es fehlt noch ein Spieler, bis die Bedwars Runde startet!");
            }
        }
    }

    public void startGame() {
        task = new BukkitRunnable() {

            int time = 10;

            @Override
            public void run() {
                switch (time) {
                    case 5:
                        for (UUID uuid : waiters) {
                            Player player = Bukkit.getPlayer(uuid);
                            player.sendMessage(Bedwars.prefix+"---------------------------");
                            if (Variablen.gold) {
                                player.sendMessage(Bedwars.prefix+"§6Das Gold ist §aaktiviert§6!");
                            } else {
                                player.sendMessage(Bedwars.prefix+"§6Das Gold ist §cdeaktiviert§6!");
                            }
                            player.sendMessage(Bedwars.prefix+"---------------------------");
                        }
                        break;
                    case 10:
                    case 3:
                    case 2:
                        for (UUID uuid : waiters) {
                            Player player = Bukkit.getPlayer(uuid);
                            player.sendMessage(Bedwars.prefix+"Das Spiel startet in §a" + time + " Sekunden!");
                        }
                        break;

                    case 1:
                        for (UUID uuid : waiters) {
                            Player player = Bukkit.getPlayer(uuid);
                            player.sendMessage(Bedwars.prefix+"Das Spiel startet in §a" + time + " Sekunde!");
                        }
                        break;

                    case 0:
                        Variablen.ende = false;
                        Variablen.damage = true;
                        removeItems();
                        Player playerRot = Bukkit.getPlayer(rot.get(0));
                        Player playerBlau = Bukkit.getPlayer(blau.get(0));

                        waiters.remove(playerRot.getUniqueId());
                        waiters.remove(playerBlau.getUniqueId());

                        Spawner spawner = new Spawner();
                        spawner.startSpawner(
                            2,
                            10,
                            20
                        );

                        for (int i = 0; i < 36; i++) {
                            playerRot.getInventory().setItem(i, new ItemStack(Material.AIR));
                            playerBlau.getInventory().setItem(i, new ItemStack(Material.AIR));
                        }

                        setBed(0);
                        setBed(1);

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

                        playerRot.teleport(spawnRot);
                        playerBlau.teleport(spawnBlau);
                        task.cancel();
                        break;

                    default:
                        break;
                }
                time--;
            }
        }.runTaskTimer(Bedwars.getPlugin(), 0, 20);
    }

    public void stopGame() {
        updateTeamItems();
        Player player = Bukkit.getPlayer(rot.get(0));
        Player playerI = Bukkit.getPlayer(blau.get(0));
        for (int i = 0; i < player.getInventory().getSize(); i++) {
            player.getInventory().setItem(i, new ItemStack(Material.AIR));
            playerI.getInventory().setItem(i, new ItemStack(Material.AIR));
        }
        Variablen.damage = false;
        taskI = new BukkitRunnable() {

            int time = 10;

            @Override
            public void run() {
                switch (time) {
                    case 10:
                    case 5:
                    case 3:
                    case 2:
                        player.sendMessage(Bedwars.prefix+"Das Spiel stoppt in §a" + time + " Sekunden!");
                        playerI.sendMessage(Bedwars.prefix+"Das Spiel stoppt in §a" + time + " Sekunden!");
                        break;

                    case 1:
                        player.sendMessage(Bedwars.prefix+"Das Spiel stoppt in §a" + time + " Sekunde!");
                        playerI.sendMessage(Bedwars.prefix+"Das Spiel stoppt in §a" + time + " Sekunde!");
                        break;

                    case 0:
                        Spawner spawner = new Spawner();
                        spawner.cancelTask();
                        resetMap();
                        clearFromAllArrays(player);
                        clearFromAllArrays(playerI);
                        player.teleport(getLobby());
                        playerI.teleport(getLobby());
                        taskI.cancel();
                        break;

                    default:
                        break;
                }
                time--;
            }
        }.runTaskTimer(Bedwars.getPlugin(), 0, 20);
    }

    public void clearFromAllArrays(@NotNull Player player) {
        rot.remove(player.getName());
        blau.remove(player.getName());
    }

    public void resetMap() {
        for (Location loc : OnBlockPlace.locs) {
            loc.getBlock().setType(Material.AIR);
        }
    }

    private void setBed(int type) {
        // type = 0 -> Team-Rot; type = 1 -> Team-Blau;
        File file = new File("plugins/Bedwars", "Beds.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        String[] team = {"Rot", "Blau"};

        String world = cfg.getString("Bed." + team[type] + ".World");
        int x = cfg.getInt("Bed." + team[type] + ".X");
        int y = cfg.getInt("Bed." + team[type] + ".Y");
        int z = cfg.getInt("Bed." + team[type] + ".Z");

        Location loc = new Location(Bukkit.getWorld(world), x, y, z);
        Block head = loc.getBlock();
        Block foot = head.getRelative(BlockFace.EAST.getOppositeFace());

        BlockState footState = foot.getState();
        footState.setType(Material.BED_BLOCK);
        Bed footData = new Bed(Material.BED_BLOCK);
        footData.setHeadOfBed(false);
        footData.setFacingDirection(BlockFace.EAST);
        footState.setData(footData);
        footState.update(true);

        BlockState headState = head.getState();
        headState.setType(Material.BED_BLOCK);
        Bed headData = new Bed(Material.BED_BLOCK);
        headData.setHeadOfBed(true);
        headData.setFacingDirection(BlockFace.EAST);
        headState.setData(headData);
        headState.update(true);
    }

    public void removeItems() {
        File file = new File("plugins/Bedwars", "Spawns.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        String world = cfg.getString("Spawn.Rot.World");
        World w = Bukkit.getWorld(world);
        for (Entity e : w.getEntities()) {
            if (e instanceof Player || e instanceof Villager) {
                continue;
            }
            e.remove();
        }
    }

    public Location getLobby() {
        File file = new File("plugins/Bedwars", "Lobby.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        String world = cfg.getString("Lobby.World");
        int x = cfg.getInt("Lobby.X");
        int y = cfg.getInt("Lobby.Y");
        int z = cfg.getInt("Lobby.Z");
        Location loc = new Location(Bukkit.getWorld(world), x, y, z);
        return loc;
    }

    public void updateTeamItems() {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(" ");
        for (String s : Main.rot) {
            lore.add(ChatColor.RED + s);
        }
        ItemMeta meta = OnInteract.rot.getItemMeta();
        meta.setLore(lore);
        OnInteract.rot.setItemMeta(meta);
        ItemMeta vsMeta = OnInteract.blau.getItemMeta();
        ArrayList<String> loreBlau = new ArrayList<>();
        loreBlau.add(" ");
        for (String s : blau) {
            loreBlau.add(ChatColor.BLUE + s);
        }
        vsMeta.setLore(loreBlau);
        OnInteract.blau.setItemMeta(vsMeta);
    }

}
