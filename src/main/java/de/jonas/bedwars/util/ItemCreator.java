package de.jonas.bedwars.util;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ItemCreator {

    public ItemStack setDisplayName(
        ItemStack item,
        Material material,
        String displayname
    ) {
        item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack setEnchantment(
        ItemStack item,
        Material material,
        String displayname,
        Enchantment enchantment,
        int level,
        String lore
    ) {
        item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        meta.addEnchant(enchantment, level, true);
        ArrayList<String> loreList = new ArrayList<>();
        loreList.add(" ");
        loreList.add(lore);
        meta.setLore(loreList);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack setLore(
        ItemStack item,
        Material material,
        String displayname,
        String lore
    ) {
        item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        ArrayList<String> loreList = new ArrayList<>();
        loreList.add(" ");
        loreList.add(lore);
        meta.setLore(loreList);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack setPotion(
        ItemStack item,
        PotionEffectType type,
        String displayname,
        String lore
    ) {
        item = new ItemStack(Material.POTION);
        PotionMeta meta = (PotionMeta) item.getItemMeta();
        meta.setDisplayName(displayname);
        ArrayList<String> loreList = new ArrayList<>();
        loreList.add(" ");
        loreList.add(lore);
        meta.setLore(loreList);
        meta.addCustomEffect(new PotionEffect(type, 600, 2), true);
        item.setItemMeta(meta);
        return item;
    }

}
