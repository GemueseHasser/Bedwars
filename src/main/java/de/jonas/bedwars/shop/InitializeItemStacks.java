package de.jonas.bedwars.shop;

import de.jonas.bedwars.util.ItemCreator;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class InitializeItemStacks {

    static ItemStack bloecke, rustung, spitzhacken, schwerter, boegen, essen, kisten, traenke, extra;

    static ItemStack bloeckeSandstone, bloeckeEndstone, bloeckeIronBlock;

    static ItemStack rustungHelm, rustungPlatte, rustungHose, rustungSchuhe, rustungPlatteI, rustungPlatteII, rustungPlatteIII;

    static ItemStack spitzhackenWood, spitzhackenStone, spitzhackenGold;

    static ItemStack schwerterKnockback, schwerterI, schwerterII, schwerterIII, schwerterGold;

    static ItemStack bogenI, bogenII, bogenIII, pfeil;

    static ItemStack essenBeef, essenPotato, essenGoldApple;

    static ItemStack kiste;

    static ItemStack traenkeInvisible, traenkeStaerke, traenkeJump, traenkeHeilung;

    static ItemStack extraLadder, extraCobwebs, extraBaseTeleporter, extraRod;

    public InitializeItemStacks() {
        ItemCreator item = new ItemCreator();
        bloecke = item.setDisplayName(bloecke, Material.SANDSTONE, "§6§lBlöcke");
        rustung = item.setDisplayName(rustung, Material.CHAINMAIL_CHESTPLATE, "§6§lRüstung");
        spitzhacken = item.setDisplayName(spitzhacken, Material.STONE_PICKAXE, "§6§lSpitzhacken");
        schwerter = item.setDisplayName(schwerter, Material.GOLD_SWORD, "§6§lSchwerter");
        boegen = item.setDisplayName(boegen, Material.BOW, "§6§lBögen");
        essen = item.setDisplayName(essen, Material.COOKED_BEEF, "§6§lEssen");
        kisten = item.setDisplayName(kisten, Material.CHEST, "§6§lKisten");
        traenke = item.setDisplayName(traenke, Material.GLASS_BOTTLE, "§6§lTränke");
        extra = item.setDisplayName(extra, Material.TNT, "§6§lExtra");
        bloeckeSandstone = item.setLore(bloeckeSandstone, Material.SANDSTONE, "§6Sandstein", "§c2 Bronze");
        bloeckeEndstone = item.setLore(bloeckeEndstone, Material.ENDER_STONE, "§6Endstone", "§c5 Bronze");
        bloeckeIronBlock = item.setLore(bloeckeIronBlock, Material.IRON_BLOCK, "§6Eisen-Block", "§82 Eisen");
        rustungHelm = item.setLore(rustungHelm, Material.LEATHER_HELMET, "§6Helm", "§c2 Bronze");
        rustungPlatte = item.setLore(rustungPlatte, Material.LEATHER_CHESTPLATE, "§6Brustplatte", "§c2 Bronze");
        rustungHose = item.setLore(rustungHose, Material.LEATHER_LEGGINGS, "§6Hose", "§c2 Bronze");
        rustungSchuhe = item.setLore(rustungSchuhe, Material.LEATHER_BOOTS, "§6Schuhe", "§c2 Bronze");
        rustungPlatteI = item.setEnchantment(rustungPlatteI, Material.IRON_CHESTPLATE, "§aBrustplatte I",
            Enchantment.PROTECTION_PROJECTILE, 1, "§81 Eisen");
        rustungPlatteII = item.setEnchantment(rustungPlatteII, Material.IRON_CHESTPLATE, "§aBrustplatte II",
            Enchantment.ARROW_KNOCKBACK, 20, "§85 Eisen");
        rustungPlatteIII = item.setEnchantment(rustungPlatteIII, Material.IRON_CHESTPLATE, "§aBristplatte III",
            Enchantment.PROTECTION_FALL, 20, "§87 Eisen");
        spitzhackenWood = item.setLore(spitzhackenWood, Material.WOOD_PICKAXE, "§6Holz-Spitzhacke", "§c10 Bronze");
        spitzhackenStone = item.setLore(spitzhackenStone, Material.STONE_PICKAXE, "§6Stein-Sptzhacke", "§83 Eisen");
        spitzhackenGold = item.setEnchantment(spitzhackenGold, Material.IRON_PICKAXE, "§aSpitzhacke",
            Enchantment.DIG_SPEED, 2, "§65 Gold");
        schwerterKnockback = item.setEnchantment(schwerterKnockback, Material.STICK, "§6Knüppel",
            Enchantment.KNOCKBACK, 9, "§c10 Bronze");
        schwerterI = item.setLore(schwerterI, Material.IRON_SWORD, "§6Schwert I", "§82 Eisen");
        schwerterII = item.setEnchantment(schwerterII, Material.IRON_SWORD, "§6Schwert II",
            Enchantment.DAMAGE_ALL, 0, "§85 Eisen");
        schwerterIII = item.setEnchantment(schwerterIII, Material.IRON_SWORD, "§6Schwert III",
            Enchantment.DAMAGE_ALL, 2, "§87 Eisen");
        schwerterGold = item.setEnchantment(schwerterGold, Material.IRON_SWORD, "§6Mega-Schwert",
            Enchantment.DAMAGE_ALL, 5, "§67 Gold");
        bogenI = item.setLore(bogenI, Material.BOW, "§6Bogen I", "§63 Gold");
        bogenII = item.setEnchantment(bogenII, Material.BOW, "§6Bogen II",
            Enchantment.ARROW_DAMAGE, 1, "§67 Gold");
        bogenIII = item.setEnchantment(bogenIII, Material.BOW, "§6Bogen III",
            Enchantment.ARROW_DAMAGE, 3, "§612 Gold");
        pfeil = item.setLore(pfeil, Material.ARROW, "§6Pfeil", "§81 Eisen");
        essenBeef = item.setLore(essenBeef, Material.COOKED_BEEF, "§6Steak", "§c5 Bronze");
        essenPotato = item.setLore(essenPotato, Material.BAKED_POTATO, "§6Kartoffel", "§c5 Bronze");
        essenGoldApple = item.setLore(essenGoldApple, Material.GOLDEN_APPLE, "§6Gold-Apfel", "§63 Gold");
        kiste = item.setLore(kiste, Material.CHEST, "§6Kiste", "§85 Eisen");
        traenkeInvisible = item.setPotion(traenkeInvisible, PotionEffectType.INVISIBILITY, "§6Unsichtbarkeit", "§85 Eisen");
        traenkeStaerke = item.setPotion(traenkeStaerke, PotionEffectType.INCREASE_DAMAGE, "§6Stärke", "§85 Eisen");
        traenkeJump = item.setPotion(traenkeJump, PotionEffectType.JUMP, "§6Sprunkkraft", "§85 Eisen");
        traenkeHeilung = item.setPotion(traenkeHeilung, PotionEffectType.HEALTH_BOOST, "§6Heilung", "§87 Eisen");
        extraLadder = item.setLore(extraLadder, Material.LADDER, "§6Leiter", "§c10 Bronze");
        extraCobwebs = item.setLore(extraCobwebs, Material.WEB, "§6Spinnweben", "§c15 Bronze");
        extraBaseTeleporter = item.setEnchantment(extraBaseTeleporter, Material.SULPHUR, "§6Base-Teleporter",
            Enchantment.LUCK, -1, "§87 Eisen");
        extraRod = item.setLore(extraRod, Material.FISHING_ROD, "§6Angel", "§85 Eisen");
    }

}
