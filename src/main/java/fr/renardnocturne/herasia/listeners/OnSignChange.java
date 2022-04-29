package fr.renardnocturne.herasia.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class OnSignChange implements Listener {
    Chest chest = null;
    Boolean isChestSet = false;

    @EventHandler
    public void onSignChange (SignChangeEvent event) {
        String firstLine = event.getLine(0);
        assert firstLine != null;

        if (firstLine.equals("&task")) {
            Block sign = event.getBlock();
            Player player = event.getPlayer();
            BlockData data = sign.getBlockData();

            if (data instanceof Directional) {
                Directional directional = (Directional)data;
                Block blockBehind = sign.getRelative(directional.getFacing().getOppositeFace());
                if (blockBehind.getType().equals(Material.CHEST)) {
                    chest = (Chest) blockBehind.getState();
                    isChestSet = true;
                    sign.setType(Material.AIR);
                    player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 2, 1);

                    Inventory inv = Bukkit.createInventory(null, 9, "§0Définir une tâche");
                    inv.setItem(0, getItem(Material.BARRIER, "Annuler."));
                    inv.setItem(2, getItem(Material.SPYGLASS, "Espionner."));
                    inv.setItem(4, getItem(Material.NETHERITE_PICKAXE, "Apporter une ressource."));
                    inv.setItem(6, getItem(Material.CRAFTING_TABLE, "Construire."));
                    inv.setItem(8, getItem(Material.MINECART, "Acheminer des marchandises."));

                    player.openInventory(inv);
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClick (InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack currentItem = event.getCurrentItem();
        assert currentItem != null;
        if (event.getView().getTitle().equalsIgnoreCase("§0Définir une tâche")) {
            event.setCancelled(true); //Pas prendre l'objet

            if (currentItem.getType().equals(Material.BARRIER)) {
                player.closeInventory();
            } else if (currentItem.getType().equals(Material.SPYGLASS)) {
                player.closeInventory();
                // TODO: 16/04/2022
            } else if (currentItem.getType().equals(Material.NETHERITE_PICKAXE)) {
                player.closeInventory();
                player.sendMessage("Mettez dans le coffre l'item à aller récupérer/crafter !");
                // TODO: 16/04/2022
            } else if (currentItem.getType().equals(Material.CRAFTING_TABLE)) {
                player.closeInventory();
                // TODO: 16/04/2022
            } else if (currentItem.getType().equals(Material.MINECART)) {
                player.closeInventory();
                // TODO: 16/04/2022
            }
        }

        if (!isChestSet) return;
        if (Objects.requireNonNull(event.getClickedInventory()).getLocation() == chest.getLocation()) {
            player.sendMessage("YESSSSS");
        }
    }

    public ItemStack getItem(Material material, String customName) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta itemMeta = item.getItemMeta();
        assert itemMeta != null;

        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        if (customName != null) itemMeta.setDisplayName(customName);

        item.setItemMeta(itemMeta);
        return item;
    }
}
