package hugu1026.com.github.phantasyweapon.weapon;

import hugu1026.com.github.phantasystatus.util.PlayerDataUtil;
import hugu1026.com.github.phantasyweapon.PhantasyWeapon;
import hugu1026.com.github.phantasyweapon.enchant.Glow;
import hugu1026.com.github.phantasyweapon.event.PhantasyWeaponAttackEvent;
import org.bukkit.Bukkit;
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

    public void AttackWithAbility(PhantasyWeaponAttackEvent event) {
        double power = event.getPower();
        int playerPower = PlayerDataUtil.getPlayerATTACK(event.getAttacker());
        int original_sharpness = event.getOriginal_sharpness();
        int damaged_sharpness = event.getDamaged_sharpness();
        double proportion = (double) damaged_sharpness / original_sharpness;
        Bukkit.getScheduler().scheduleSyncDelayedTask(PhantasyWeapon.getPlugin(PhantasyWeapon.class), () -> {
            event.getVictim().damage((power + (playerPower * 0.5)) * proportion);
            Bukkit.getScheduler().scheduleSyncDelayedTask(PhantasyWeapon.getPlugin(PhantasyWeapon.class), () -> {
                event.getVictim().damage((power + (playerPower * 0.5)) * proportion);
                ItemMeta meta = event.getAttacker().getInventory().getItemInMainHand().getItemMeta();
                meta.removeEnchant(new Glow(100));
                event.getAttacker().getInventory().getItemInMainHand().setItemMeta(meta);
            }, 10L);
        }, 10L);
    }
}
