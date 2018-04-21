package hugu1026.com.github.phantasyweapon.weapon;

import hugu1026.com.github.phantasyweapon.enchant.Glow;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Claw extends Weapon {

    @Override
    public void WeaponAbility(Player player, ItemStack weapon) {
        Glow glowEnchant = new Glow(100);
        ItemMeta meta = weapon.getItemMeta();
        meta.addEnchant(glowEnchant, 1, true);
        weapon.setItemMeta(meta);

        player.sendMessage(ChatColor.RED + "チャージ完了、次の攻撃で3連続攻撃をします");
    }
}
