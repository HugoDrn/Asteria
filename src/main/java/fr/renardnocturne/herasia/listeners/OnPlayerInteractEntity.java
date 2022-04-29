package fr.renardnocturne.herasia.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

public class OnPlayerInteractEntity implements Listener {
    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        if(event.getHand().equals(EquipmentSlot.OFF_HAND)) return;
        Player player  = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (event.getRightClicked().getType().equals(EntityType.PLAYER)) {
            Player rightCLicked = (Player) event.getRightClicked();
            if (item.getType().equals(Material.STICK) && Objects.requireNonNull(item.getItemMeta()).getDisplayName().equalsIgnoreCase("§fFreeze Player")) {
                rightCLicked.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 255, false, false));
                rightCLicked.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 137, false, false));
                rightCLicked.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 255, false, false));
                player.sendMessage("§2Joueur immobilisé !");
                rightCLicked.sendMessage("§4Vous avez été immobilisé par un membre du staff !");

            } else if (item.getType().equals(Material.BLAZE_ROD) && Objects.requireNonNull(item.getItemMeta()).getDisplayName().equalsIgnoreCase("§fBâton du pardon")) {
                rightCLicked.removePotionEffect(PotionEffectType.SLOW);
                rightCLicked.removePotionEffect(PotionEffectType.JUMP);
                rightCLicked.removePotionEffect(PotionEffectType.BLINDNESS);

                player.sendMessage("§2Joueur réhabilité !");
                rightCLicked.sendMessage("§2Vous venez d'être réhabilité par un membre du staff !");
            }
        }
    }
}
