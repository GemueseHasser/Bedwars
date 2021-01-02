package de.jonas.bedwars.listener;

import java.util.ArrayList;

import de.jonas.bedwars.Bedwars;
import de.jonas.bedwars.system.Main;
import de.jonas.bedwars.system.Variablen;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class OnInteract implements Listener {

    public static ItemStack blau = new ItemStack(Material.WOOL, 1, (short) 11);

    public static ItemStack rot = new ItemStack(Material.WOOL, 1, (short) 14);

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (e.getClickedBlock().getState() instanceof Sign) {
                    Sign sign = (Sign) e.getClickedBlock().getState();
                    if (sign.getLine(0)
                        .equalsIgnoreCase(ChatColor.GREEN + "[" + ChatColor.GOLD + "Bedwars" + ChatColor.GREEN + "]")) {
                        Main system = new Main();
                        system.addPlayerToWaiters(e.getPlayer().getUniqueId());
                        sign.setLine(2, Variablen.player + "/2");
                        e.getPlayer().teleport(system.getLobby());
                        return;
                    }
                }
            }
            if (e.getItem() == null) {
                return;
            }
            if (Main.waiters.contains(e.getPlayer().getUniqueId()) && e.getItem().getType() == Material.BED) {
                Inventory inv = Bukkit.createInventory(null, 9, "§aTeams");

                if (blau.getItemMeta() == null || rot.getItemMeta() == null) {
                    return;
                }
                ItemMeta blauMeta = blau.getItemMeta();
                blauMeta.setDisplayName("§f§lBlau");
                blau.setItemMeta(blauMeta);

                ItemMeta rotMeta = rot.getItemMeta();
                rotMeta.setDisplayName("§f§lRot");
                rot.setItemMeta(rotMeta);

                inv.setItem(3, blau);
                inv.setItem(5, rot);
                e.getPlayer().openInventory(inv);
            }
        }
    }

    @EventHandler
    public void onChooseTeam(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) {
            return;
        }
        if (!e.getInventory().getTitle().equals("§aTeams")) {
            return;
        }
        e.setCancelled(true);
        if (rot.equals(e.getCurrentItem())) {
            new Main().clearFromAllArrays((Player) e.getWhoClicked());
            if (Main.rot.size() > 0) {
                e.getWhoClicked().sendMessage(Bedwars.prefix+"Das Team ist bereits voll!");
                return;
            }
            Main.rot.add(e.getWhoClicked().getName());
            new Main().updateTeamItems();
            new Main().checkIfGameCanStart();
            e.getWhoClicked().closeInventory();
        }
        if (blau.equals(e.getCurrentItem())) {
            new Main().clearFromAllArrays((Player) e.getWhoClicked());
            if (Main.blau.size() > 0) {
                e.getWhoClicked().sendMessage(Bedwars.prefix+"Das Team ist bereits voll!");
                return;
            }
            Main.blau.add(e.getWhoClicked().getName());
            new Main().updateTeamItems();
            e.getWhoClicked().closeInventory();
            new Main().checkIfGameCanStart();
        }
    }

    @EventHandler
    public void onInteractWithSign(SignChangeEvent e) {
        if (!e.getPlayer().hasPermission("bw.setsign")) {
            return;
        }

        if (e.getLine(0).equalsIgnoreCase("[bedwars]")) {
            e.setLine(0, ChatColor.GREEN + "[" + ChatColor.GOLD + "Bedwars" + ChatColor.GREEN + "]");
            e.setLine(1, "");
            e.setLine(2, Variablen.player + "/2");
        }
    }

}
