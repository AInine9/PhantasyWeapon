package hugu1026.com.github.phantasyweapon.listener;

import hugu1026.com.github.phantasyweapon.event.PhantasyWeaponAttackEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamage implements Listener{

    @EventHandler
    public void EntityDamage(EntityDamageByEntityEvent event){
        if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK
                && event.getDamager() instanceof Player
                && event.getEntity() instanceof Creature) {
            Player player = (Player) event.getDamager();
            Creature creature = (Creature) event.getEntity();
            String type = player.getInventory().getItemInMainHand().getItemMeta().getLore().get(0).replace("ジャンル:", "");
            type = ChatColor.stripColor(type);

            if(type == null) return;

            event.setCancelled(true);

            PhantasyWeaponAttackEvent phantasyWeaponAttackEvent = new PhantasyWeaponAttackEvent(type, player, creature);
            Bukkit.getServer().getPluginManager().callEvent(phantasyWeaponAttackEvent);
        }
    }
}
