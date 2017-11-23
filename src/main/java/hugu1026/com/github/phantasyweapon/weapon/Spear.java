package hugu1026.com.github.phantasyweapon.weapon;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class Spear extends Weapon {

    @Override
    public void WeaponAbility(Player player, ItemStack weapon) {
        Vector vector = new Vector(0, 0.05, 0);
        player.setVelocity(player.getLocation().getDirection().multiply(-1).add(vector));
    }
}
