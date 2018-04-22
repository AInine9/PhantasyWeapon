package hugu1026.com.github.phantasyweapon.listener;

import hugu1026.com.github.phantasyweapon.event.PhantasyPlayerDamagedEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerDamage implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        if (event.getCause().equals(EntityDamageEvent.DamageCause.CUSTOM)) return;

        Player player = (Player) event.getEntity();

        PhantasyPlayerDamagedEvent playerDamagedEvent = new PhantasyPlayerDamagedEvent(player, event.getDamage());
        Bukkit.getServer().getPluginManager().callEvent(playerDamagedEvent);

        ((Player) event.getEntity()).damage(0.1);
        event.setCancelled(true);
    }
}
