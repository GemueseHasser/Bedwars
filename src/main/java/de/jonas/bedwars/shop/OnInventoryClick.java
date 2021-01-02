package de.jonas.bedwars.shop;

import de.jonas.bedwars.Bedwars;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class OnInventoryClick implements Listener {

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) {
            return;
        }

        Player player = (Player) e.getWhoClicked();

        if (e.getInventory().getTitle().equals("§6§lSHOP")) {
            e.setCancelled(true);
            checkShop(e, player);
        }

    }

    private void checkShop(InventoryClickEvent e, Player player) {
        GUI gui = new GUI();
        ItemStack currentItem = e.getCurrentItem();

        if (currentItem == null || currentItem.getType() == Material.AIR) {
            player.sendMessage(Bedwars.prefix + "Du hast einen leeren Slot angeklickt!");
        }

        if (InitializeItemStacks.bloecke.equals(currentItem)) {
            player.openInventory(gui.bloecke());
        } else if (InitializeItemStacks.rustung.equals(currentItem)) {
            player.openInventory(gui.rustung());
        } else if (InitializeItemStacks.spitzhacken.equals(currentItem)) {
            player.openInventory(gui.sptzhacken());
        } else if (InitializeItemStacks.schwerter.equals(currentItem)) {
            player.openInventory(gui.schwerter());
        } else if (InitializeItemStacks.boegen.equals(currentItem)) {
            player.openInventory(gui.boegen());
        } else if (InitializeItemStacks.essen.equals(currentItem)) {
            player.openInventory(gui.essen());
        } else if (InitializeItemStacks.kisten.equals(currentItem)) {
            player.openInventory(gui.chests());
        } else if (InitializeItemStacks.traenke.equals(currentItem)) {
            player.openInventory(gui.traenke());
        } else if (InitializeItemStacks.extra.equals(currentItem)) {
            player.openInventory(gui.extra());
        } else if (InitializeItemStacks.bloeckeSandstone.equals(currentItem)) {
            buy(player, currentItem, 0, 2);
        } else if (InitializeItemStacks.bloeckeEndstone.equals(currentItem)) {
            buy(player, currentItem, 0, 5);
        } else if (InitializeItemStacks.bloeckeIronBlock.equals(currentItem)) {
            buy(player, currentItem, 1, 2);
        } else if (InitializeItemStacks.rustungSchuhe.equals(currentItem)) {
            buy(player, currentItem, 0, 2);
        } else if (InitializeItemStacks.rustungHose.equals(currentItem)) {
            buy(player, currentItem, 0, 2);
        } else if (InitializeItemStacks.rustungPlatte.equals(currentItem)) {
            buy(player, currentItem, 0, 2);
        } else if (InitializeItemStacks.rustungHelm.equals(currentItem)) {
            buy(player, currentItem, 0, 2);
        } else if (InitializeItemStacks.rustungPlatteI.equals(currentItem)) {
            buy(player, currentItem, 1, 1);
        } else if (InitializeItemStacks.rustungPlatteII.equals(currentItem)) {
            buy(player, currentItem, 1, 5);
        } else if (InitializeItemStacks.rustungPlatteIII.equals(currentItem)) {
            buy(player, currentItem, 1, 7);
        } else if (InitializeItemStacks.spitzhackenWood.equals(currentItem)) {
            buy(player, currentItem, 0, 10);
        } else if (InitializeItemStacks.spitzhackenStone.equals(currentItem)) {
            buy(player, currentItem, 1, 3);
        } else if (InitializeItemStacks.spitzhackenGold.equals(currentItem)) {
            buy(player, currentItem, 2, 5);
        } else if (InitializeItemStacks.schwerterKnockback.equals(currentItem)) {
            buy(player, currentItem, 0, 10);
        } else if (InitializeItemStacks.schwerterI.equals(currentItem)) {
            buy(player, currentItem, 1, 2);
        } else if (InitializeItemStacks.schwerterII.equals(currentItem)) {
            buy(player, currentItem, 1, 5);
        } else if (InitializeItemStacks.schwerterIII.equals(currentItem)) {
            buy(player, currentItem, 1, 7);
        } else if (InitializeItemStacks.schwerterGold.equals(currentItem)) {
            buy(player, currentItem, 2, 7);
        } else if (InitializeItemStacks.bogenI.equals(currentItem)) {
            buy(player, currentItem, 2, 3);
        } else if (InitializeItemStacks.bogenII.equals(currentItem)) {
            buy(player, currentItem, 2, 7);
        } else if (InitializeItemStacks.bogenIII.equals(currentItem)) {
            buy(player, currentItem, 2, 12);
        } else if (InitializeItemStacks.pfeil.equals(currentItem)) {
            buy(player, currentItem, 1, 1);
        } else if (InitializeItemStacks.essenBeef.equals(currentItem)) {
            buy(player, currentItem, 0, 5);
        } else if (InitializeItemStacks.essenPotato.equals(currentItem)) {
            buy(player, currentItem, 0, 5);
        } else if (InitializeItemStacks.essenGoldApple.equals(currentItem)) {
            buy(player, currentItem, 2, 3);
        } else if (InitializeItemStacks.kiste.equals(currentItem)) {
            buy(player, currentItem, 1, 5);
        } else if (InitializeItemStacks.traenkeInvisible.equals(currentItem)) {
            buy(player, currentItem, 1, 5);
        } else if (InitializeItemStacks.traenkeStaerke.equals(currentItem)) {
            buy(player, currentItem, 1, 5);
        } else if (InitializeItemStacks.traenkeJump.equals(currentItem)) {
            buy(player, currentItem, 1, 5);
        } else if (InitializeItemStacks.traenkeHeilung.equals(currentItem)) {
            buy(player, currentItem, 1, 7);
        } else if (InitializeItemStacks.extraLadder.equals(currentItem)) {
            buy(player, currentItem, 0, 10);
        } else if (InitializeItemStacks.extraCobwebs.equals(currentItem)) {
            buy(player, currentItem, 0, 15);
        } else if (InitializeItemStacks.extraBaseTeleporter.equals(currentItem)) {
            buy(player, currentItem, 1, 7);
        } else if (InitializeItemStacks.extraRod.equals(currentItem)) {
            buy(player, currentItem, 1, 5);
        }
    }

    private void buy(Player player, ItemStack item, int type, int amount) {
        // type = 0 -> Bronze; type = 1 -> Eisen; type = 2 -> Gold
        Material[] material = {Material.CLAY_BRICK, Material.IRON_INGOT, Material.GOLD_INGOT};
        // inventar des Spielers durchgehen
        for (int i = 0; i < 27; i++) {
            ItemStack itemStack = player.getInventory().getItem(i);
            if (itemStack == null) {
                continue;
            }
            if (itemStack.getType() == material[type]) {
                if (itemStack.getAmount() == amount) {
                    player.getInventory().remove(itemStack);
                    player.getInventory().addItem(item);
                    return;
                } else if (itemStack.getAmount() > amount) {
                    itemStack.setAmount(itemStack.getAmount() - amount);
                    player.getInventory().addItem(item);
                    return;
                }
            }
        }
    }

}
