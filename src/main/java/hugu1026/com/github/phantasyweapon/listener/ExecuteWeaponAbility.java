package hugu1026.com.github.phantasyweapon.listener;

import hugu1026.com.github.phantasyweapon.event.ExecuteWeaponAbilityEvent;
import hugu1026.com.github.phantasyweapon.weapon.Halberd;
import hugu1026.com.github.phantasyweapon.weapon.Rapier;
import hugu1026.com.github.phantasyweapon.weapon.Spear;
import hugu1026.com.github.phantasyweapon.weapon.Weapon;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
        Weapon weapon_class = null;

        Bukkit.getServer().broadcastMessage(weapon.getDurability() + "/" + weapon.getType().getMaxDurability());
        if ((weapon.getDurability() == 0)) {

            switch (type) {
                case "スピア":
                    weapon_class = new Spear();
                    break;
                case "レイピア":
                    weapon_class = new Rapier();
                    break;
                case "ハルバード":
                    weapon_class = new Halberd();
                    break;
            }

            weapon_class.WeaponAbility(player, weapon);
            weapon.setDurability((short) (weapon.getType().getMaxDurability() - 1));

        } else {
            player.sendMessage(ChatColor.RED + "APが足りない！");
        }
    }
}
