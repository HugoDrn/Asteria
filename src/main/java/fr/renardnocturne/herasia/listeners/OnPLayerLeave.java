package fr.renardnocturne.herasia.listeners;

import fr.renardnocturne.herasia.Herasia;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnPLayerLeave implements Listener {
    private Herasia main;

    public OnPLayerLeave (Herasia herasia) {
        this.main = herasia;
    }

    @EventHandler
    public  void onPLayerLeave (PlayerQuitEvent event) {
        event.setQuitMessage(main.getConfig().getString("messages.playerLeave").replace("{player}", event.getPlayer().getDisplayName()));
    }
}
