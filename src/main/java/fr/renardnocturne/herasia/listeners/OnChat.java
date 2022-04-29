package fr.renardnocturne.herasia.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class OnChat implements Listener {
    @EventHandler
    public void onChat (AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        event.setFormat(player.getDisplayName() + ChatColor.GRAY + " ▻ " + ChatColor.WHITE + event.getMessage());
    }
}
