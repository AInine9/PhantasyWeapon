package hugu1026.com.github.phantasyweapon.listener;

import hugu1026.com.github.phantasyweapon.event.ExecuteWeaponAbilityEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class ExecuteWeaponAbility implements Listener {

    @EventHandler
    public void ExecuteWeaponAbility(ExecuteWeaponAbilityEvent event) {
        Player player = event.getPlayer();
        String type = event.getType();
        ItemStack weapon = event.getWeapon();

        Bukkit.getServer().broadcastMessage(player + type + weapon);
    }
}
