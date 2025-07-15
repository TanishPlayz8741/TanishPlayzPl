package com.tanishplayz;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.persistence.PersistentDataType;

public class TanishPlayzPl extends JavaPlugin implements Listener {

    private NamespacedKey customIdKey;

    @Override
    public void onEnable() {
        customIdKey = new NamespacedKey(this, "custom_id");
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("TanishPlayzPl enabled.");
    }

    @EventHandler
    public void onPlayerUseItem(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (item == null || !item.hasItemMeta()) return;

        ItemMeta meta = item.getItemMeta();
        if (meta == null || !meta.getPersistentDataContainer().has(customIdKey, PersistentDataType.STRING)) return;

        String customId = meta.getPersistentDataContainer().get(customIdKey, PersistentDataType.STRING);
        if (customId == null) return;

        if (customId.equals("eco_to_giant") && item.getType() == Material.ECHO_SHARD) {
            player.addTag("Giant");
            player.removeTag("Tiny");
            player.sendMessage("§aYou are now tagged as §lGiant§r§a.");
        } else if (customId.equals("amethyst_to_tiny") && item.getType() == Material.AMETHYST_SHARD) {
            player.addTag("Tiny");
            player.removeTag("Giant");
            player.sendMessage("§dYou are now tagged as §lTiny§r§d.");
        }
    }
}
