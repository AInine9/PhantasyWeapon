package hugu1026.com.github.phantasyweapon.listener;

import hugu1026.com.github.phantasyweapon.event.ExecuteWeaponAbilityEvent;
import hugu1026.com.github.phantasyweapon.weapon.Spear;
import hugu1026.com.github.phantasyweapon.weapon.Weapon;
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
        switch (type) {
            case "スピア":
                weapon_class = new Spear();
                break;
        }

        weapon_class.WeaponAbility(player, weapon);
    }
}
