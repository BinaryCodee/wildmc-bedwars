package it.stortoh.bedwars.settings.game;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum SpawnerItems {

    IRON(new ItemStack(Material.IRON_INGOT)),
    GOLD(new ItemStack(Material.GOLD_INGOT)),
    DIAMOND(new ItemStack(Material.DIAMOND)),
    EMERALD(new ItemStack(Material.EMERALD));

    private ItemStack itemStack;

    SpawnerItems(ItemStack itemStack){
        this.itemStack = itemStack;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    static {
        ItemMeta ironMeta = IRON.itemStack.getItemMeta();
        ironMeta.setDisplayName("§7Ferro");
        IRON.itemStack.setItemMeta(ironMeta);

        ItemMeta goldMeta = GOLD.itemStack.getItemMeta();
        goldMeta.setDisplayName("§6Oro");
        GOLD.itemStack.setItemMeta(goldMeta);

        ItemMeta diamondMeta = DIAMOND.itemStack.getItemMeta();
        diamondMeta.setDisplayName("§bDiamante");
        DIAMOND.itemStack.setItemMeta(diamondMeta);

        ItemMeta emeraldMeta = EMERALD.itemStack.getItemMeta();
        emeraldMeta.setDisplayName("§aSmeraldo");
        EMERALD.itemStack.setItemMeta(emeraldMeta);
    }

}
