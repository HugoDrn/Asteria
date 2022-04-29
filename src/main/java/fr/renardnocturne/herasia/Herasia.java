package fr.renardnocturne.herasia;

import fr.renardnocturne.herasia.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Herasia extends JavaPlugin {

    @Override
    public void onEnable() {
        //Getting config
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        saveConfig();

        //createFile("tasks");

        getLogger().info(getConfig().getString("messages.pluginOn"));

        //Registering events
        getServer().getPluginManager().registerEvents(new OnJoin(this), this);
        getServer().getPluginManager().registerEvents(new OnPLayerLeave(this), this);
        getServer().getPluginManager().registerEvents(new OnPlayerInteract(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerInteractEntity(), this);
        getServer().getPluginManager().registerEvents(new OnChat(), this);
        getServer().getPluginManager().registerEvents(new OnSignChange(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info(getConfig().getString("messages.pluginOff"));
    }

    private void createFile(String filename) {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }

        File file = new File(getDataFolder(), filename + ".yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                Bukkit.getLogger().info("Error: " + e);
            }
        }
    }

    public File getFile (String fileName) {
        return new File(getDataFolder(), fileName + ".yml");
    }
}

