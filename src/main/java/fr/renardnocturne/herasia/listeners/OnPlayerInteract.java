package fr.renardnocturne.herasia.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class OnPlayerInteract implements Listener {
    @EventHandler()
    public void onPlayerInteracts(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();

        if (event.getClickedBlock() != null) {
            Location blockPos = event.getClickedBlock().getLocation();

            int x = blockPos.getBlockX();
            int y = blockPos.getBlockY();
            int z = blockPos.getBlockZ();

            if (action.equals(Action.PHYSICAL) && event.getClickedBlock().getType().equals(Material.LIGHT_WEIGHTED_PRESSURE_PLATE)) {
                if (x == -23 && y == 12 && z == 16) {
                    player.setVelocity(new Vector(-5, 1, 0));
                } else if (x == 12 && y == 12 && z == 51) {
                    player.setVelocity(new Vector(0, 1, 5));
                } else if (x == 47 && y == 12 && z == 16) {
                    player.setVelocity(new Vector(5, 1, 0));
                } else if (x == 12 && y == 12 && z == -19) {
                    player.setVelocity(new Vector(0, 1, -5));
                }
            }
        }
    };
}
