package hugu1026.com.github.phantasyweapon.weapon;

import hugu1026.com.github.phantasyweapon.enchant.Glow;
import hugu1026.com.github.phantasyweapon.event.PhantasyWeaponAttackEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SwordBreaker extends Weapon {

    @Override
    public void WeaponAbility(Player player, ItemStack weapon) {
        Glow glowEnchant = new Glow(100);
        ItemMeta meta = weapon.getItemMeta();
        meta.addEnchant(glowEnchant, 1, true);
        weapon.setItemMeta(meta);

        player.sendMessage(ChatColor.RED + "チャージ完了、次の攻撃で相手を移動不可能にします");
    }

    public void AttackWithAbility(PhantasyWeaponAttackEvent event) {
        event.getVictim().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * 3, 1000));
        ItemMeta meta = event.getAttacker().getInventory().getItemInMainHand().getItemMeta();
        meta.removeEnchant(new Glow(100));
        event.getAttacker().getInventory().getItemInMainHand().setItemMeta(meta);
    }
}
