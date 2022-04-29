package fr.renardnocturne.herasia.listeners;

import fr.renardnocturne.herasia.Herasia;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;

public class OnJoin implements Listener {
    private Herasia main;

    public OnJoin(Herasia herasia) {
       this.main = herasia;
    }

    @EventHandler
    public void onPLayerJoin (PlayerJoinEvent event) {
        Bukkit.getLogger().info(main.getConfig().getString("messages.playerJoin"));
        // event.getPlayer().setDisplayName(event.getPlayer().getDisplayName());
        event.setJoinMessage(Objects.requireNonNull(main.getConfig().getString("messages.playerJoin")).replace("{player}", event.getPlayer().getDisplayName()));
    }
}
