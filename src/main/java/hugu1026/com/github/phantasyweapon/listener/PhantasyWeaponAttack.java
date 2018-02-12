package hugu1026.com.github.phantasyweapon.listener;

import hugu1026.com.github.phantasystatus.util.PlayerDataUtil;
import hugu1026.com.github.phantasyweapon.event.PhantasyWeaponAttackEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Random;

public class PhantasyWeaponAttack implements Listener {

    @EventHandler
    public void PhantasyWeaponAttack(PhantasyWeaponAttackEvent event) {
        double power = event.getPower();
        Player attacker = event.getAttacker();
        Creature victim = event.getVictim();
        String type = event.getType();
        ItemStack weapon = event.getWeapon();
        int original_sharpness;
        int damaged_sharpness;
        int attacked_sharpness;
        int playerPower = PlayerDataUtil.getPlayerATTACK(attacker);

        original_sharpness = event.getOriginal_sharpness();
        damaged_sharpness = event.getDamaged_sharpness();
        attacked_sharpness = damaged_sharpness - 1;

        if(damaged_sharpness == 0) {
            attacker.sendMessage(ChatColor.RED + "切れ味が落ちてしまっている！");
            return;
        }

        weapon.setDurability((short) (weapon.getDurability() - 2));

        Random random = new Random();
        int num = random.nextInt(6); //20%
        if (num == 0) {
            ItemMeta meta = event.getWeapon().getItemMeta();

            List<String> lore = event.getWeapon().getItemMeta().getLore();
            lore.set(2, ChatColor.YELLOW + "切れ味:" + attacked_sharpness + "/" + original_sharpness);
            meta.setLore(lore);

            attacker.getInventory().getItemInMainHand().setItemMeta(meta);
        }

        double proportion = (double) damaged_sharpness / original_sharpness;
        EntityDamageEvent entityDamageEvent = (EntityDamageEvent) event.getEvent();
        entityDamageEvent.setDamage((power + (playerPower * 0.5)) * proportion);

        short healAbilityPoint = (short) (weapon.getDurability() - weapon.getType().getMaxDurability() * 0.03);

        if(weapon.getDurability() == 0) return;

        if(healAbilityPoint <= 0) {
            weapon.setDurability((short) 0);
        } else {
            weapon.setDurability(healAbilityPoint);
        }
    }
}
