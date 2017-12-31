package hugu1026.com.github.phantasyweapon.listener;

import hugu1026.com.github.phantasyweapon.event.ExecuteWeaponAbilityEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerShift implements Listener {

    @EventHandler
    public void PlayerShift(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if(item.getType() == null
                || !item.hasItemMeta()
                || !item.getItemMeta().hasLore()) return;

        if(!player.isSneaking()
                && item.getItemMeta().getLore().get(0).startsWith(ChatColor.YELLOW + "ジャンル:")) {

            ExecuteWeaponAbilityEvent executeWeaponAbilityEvent = new ExecuteWeaponAbilityEvent(player);
            Bukkit.getServer().getPluginManager().callEvent(executeWeaponAbilityEvent);
        }
    }
}
