package de.jonas.bedwars.shop;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class GUI {

    private void headMenu(Inventory inv) {
        inv.setItem(0, InitializeItemStacks.bloecke);
        inv.setItem(1, InitializeItemStacks.rustung);
        inv.setItem(2, InitializeItemStacks.spitzhacken);
        inv.setItem(3, InitializeItemStacks.schwerter);
        inv.setItem(4, InitializeItemStacks.boegen);
        inv.setItem(5, InitializeItemStacks.essen);
        inv.setItem(6, InitializeItemStacks.kisten);
        inv.setItem(7, InitializeItemStacks.traenke);
        inv.setItem(8, InitializeItemStacks.extra);
    }

    public Inventory startScreen() {
        Inventory inv = Bukkit.createInventory(null, 9, "§6§lSHOP");
        headMenu(inv);
        return inv;
    }

    public Inventory bloecke() {
        Inventory inv = Bukkit.createInventory(null, 36, "§6§lSHOP");
        headMenu(inv);
        inv.setItem(20, InitializeItemStacks.bloeckeSandstone);
        inv.setItem(22, InitializeItemStacks.bloeckeEndstone);
        inv.setItem(24, InitializeItemStacks.bloeckeIronBlock);
        return inv;
    }

    public Inventory rustung() {
        Inventory inv = Bukkit.createInventory(null, 36, "§6§lSHOP");
        headMenu(inv);
        inv.setItem(18, InitializeItemStacks.rustungSchuhe);
        inv.setItem(19, InitializeItemStacks.rustungHose);
        inv.setItem(20, InitializeItemStacks.rustungPlatte);
        inv.setItem(21, InitializeItemStacks.rustungHelm);
        inv.setItem(23, InitializeItemStacks.rustungPlatteI);
        inv.setItem(24, InitializeItemStacks.rustungPlatteII);
        inv.setItem(25, InitializeItemStacks.rustungPlatteIII);
        return inv;
    }

    public Inventory sptzhacken() {
        Inventory inv = Bukkit.createInventory(null, 36, "§6§lSHOP");
        headMenu(inv);
        inv.setItem(20, InitializeItemStacks.spitzhackenWood);
        inv.setItem(22, InitializeItemStacks.spitzhackenStone);
        inv.setItem(24, InitializeItemStacks.spitzhackenGold);
        return inv;
    }

    public Inventory schwerter() {
        Inventory inv = Bukkit.createInventory(null, 36, "§6§lSHOP");
        headMenu(inv);
        inv.setItem(19, InitializeItemStacks.schwerterKnockback);
        inv.setItem(21, InitializeItemStacks.schwerterI);
        inv.setItem(22, InitializeItemStacks.schwerterII);
        inv.setItem(23, InitializeItemStacks.schwerterIII);
        inv.setItem(25, InitializeItemStacks.schwerterGold);
        return inv;
    }

    public Inventory boegen() {
        Inventory inv = Bukkit.createInventory(null, 36, "§6§lSHOP");
        headMenu(inv);
        inv.setItem(20, InitializeItemStacks.bogenI);
        inv.setItem(21, InitializeItemStacks.bogenII);
        inv.setItem(22, InitializeItemStacks.bogenIII);
        inv.setItem(24, InitializeItemStacks.pfeil);
        return inv;
    }

    public Inventory essen() {
        Inventory inv = Bukkit.createInventory(null, 36, "§6§lSHOP");
        headMenu(inv);
        inv.setItem(20, InitializeItemStacks.essenBeef);
        inv.setItem(22, InitializeItemStacks.essenPotato);
        inv.setItem(24, InitializeItemStacks.essenGoldApple);
        return inv;
    }

    public Inventory chests() {
        Inventory inv = Bukkit.createInventory(null, 36, "§6§lSHOP");
        headMenu(inv);
        inv.setItem(22, InitializeItemStacks.kiste);
        return inv;
    }

    public Inventory traenke() {
        Inventory inv = Bukkit.createInventory(null, 36, "§6§lSHOP");
        headMenu(inv);
        inv.setItem(19, InitializeItemStacks.traenkeInvisible);
        inv.setItem(21, InitializeItemStacks.traenkeStaerke);
        inv.setItem(23, InitializeItemStacks.traenkeJump);
        inv.setItem(25, InitializeItemStacks.traenkeHeilung);
        return inv;
    }

    public Inventory extra() {
        Inventory inv = Bukkit.createInventory(null, 36, "§6§lSHOP");
        headMenu(inv);
        inv.setItem(19, InitializeItemStacks.extraLadder);
        inv.setItem(21, InitializeItemStacks.extraCobwebs);
        inv.setItem(23, InitializeItemStacks.extraBaseTeleporter);
        inv.setItem(25, InitializeItemStacks.extraRod);
        return inv;
    }

}
