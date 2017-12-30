package hugu1026.com.github.phantasyweapon.weapon;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Rapier extends Weapon {

    @Override
    public void WeaponAbility(Player player, ItemStack weapon) {
        player.setVelocity(player.getLocation().getDirection().multiply(1.5));
    }
}
