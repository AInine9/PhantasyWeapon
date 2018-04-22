package hugu1026.com.github.phantasyweapon.weapon;

import hugu1026.com.github.phantasystatus.util.PlayerDataUtil;
import hugu1026.com.github.phantasyweapon.enchant.Glow;
import hugu1026.com.github.phantasyweapon.event.PhantasyWeaponAttackEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Katana extends Weapon {

    @Override
    public void WeaponAbility(Player player, ItemStack weapon) {
        Glow glowEnchant = new Glow(100);
        ItemMeta meta = weapon.getItemMeta();
        meta.addEnchant(glowEnchant, 1, true);
        weapon.setItemMeta(meta);

        player.sendMessage(ChatColor.RED + "チャージ完了、次の攻撃で半径3ブロックの範囲攻撃をします");
    }

    public void AttackWithAbility(PhantasyWeaponAttackEvent event) {
        double power = event.getPower();
        int playerPower = PlayerDataUtil.getPlayerATTACK(event.getAttacker());
        int original_sharpness = event.getOriginal_sharpness();
        int damaged_sharpness = event.getDamaged_sharpness();
        double proportion = (double) damaged_sharpness / original_sharpness;
        List<Entity> monsters = event.getAttacker().getNearbyEntities(3, 3, 3);

        monsters.stream()
                .filter(Creature.class::isInstance)
                .map(Creature.class::cast)
                .forEach(monster -> monster.damage((power + (playerPower * 0.5)) * proportion));

        ItemMeta meta = event.getAttacker().getInventory().getItemInMainHand().getItemMeta();
        meta.removeEnchant(new Glow(100));
        event.getAttacker().getInventory().getItemInMainHand().setItemMeta(meta);
    }
}
