package hugu1026.com.github.phantasyweapon.listener;

import hugu1026.com.github.phantasyweapon.event.PhantasyWeaponAttackEvent;
import org.bukkit.Bukkit;
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
        int power = event.getPower();
        Player attacker = event.getAttacker();
        Creature victim = event.getVictim();
        String type = event.getType();
        ItemStack weapon = event.getWeapon();

        Random random = new Random();
        int num = random.nextInt(6); //20%
        if (num == 0) {
            ItemMeta meta = event.getWeapon().getItemMeta();

            int original_sharpness = event.getOriginal_sharpness();
            int damaged_sharpness = event.getDamaged_sharpness();
            int attacked_sharpness = damaged_sharpness - 1;

            Bukkit.getServer().broadcastMessage(original_sharpness + " " + damaged_sharpness);

            List<String> lore = event.getWeapon().getItemMeta().getLore();
            lore.set(2, ChatColor.YELLOW + "切れ味:" + attacked_sharpness + "/" + original_sharpness);
            meta.setLore(lore);

            attacker.getInventory().getItemInMainHand().setItemMeta(meta);
        }
        victim.damage(power);

        if(weapon.getDurability() != 0) {
            weapon.setDurability((short) (weapon.getDurability() - weapon.getType().getMaxDurability() * 0.05));
        }
    }
}
