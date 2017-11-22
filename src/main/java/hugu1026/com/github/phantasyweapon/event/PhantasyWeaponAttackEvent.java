package hugu1026.com.github.phantasyweapon.event;

import org.bukkit.ChatColor;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class PhantasyWeaponAttackEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private String type;
    private Player attacker;
    private Creature victim;
    private ItemStack weapon;
    private int power;
    private int original_sharpness;
    private int damaged_sharpness;

    public PhantasyWeaponAttackEvent(String type, Player attacker, Creature victim) {
        this.type = type;
        this.attacker = attacker;
        this.victim = victim;
        this.weapon= attacker.getInventory().getItemInMainHand();
        String power = weapon.getItemMeta().getLore().get(1).replace(ChatColor.YELLOW + "攻撃力:", "");
        this.power = Integer.parseInt(ChatColor.stripColor(power));
        String sharpness[] = weapon.getItemMeta().getLore().get(2).split("/");
        String damaged_sharpness = sharpness[0].replace(ChatColor.YELLOW + "切れ味:", "");
        String original_sharpness = sharpness[1];
        this.damaged_sharpness = Integer.parseInt(ChatColor.stripColor(damaged_sharpness));
        this.original_sharpness = Integer.parseInt(ChatColor.stripColor(original_sharpness));
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static  HandlerList getHandlerList() {
        return handlers;
    }

    public String getType() {
        return type;
    }

    public Player getAttacker() {
        return attacker;
    }

    public Creature getVictim() {
        return victim;
    }

    public ItemStack getWeapon() {
        return weapon;
    }

    public int getPower() {
        return power;
    }

    public int getDamaged_sharpness() {
        return damaged_sharpness;
    }

    public int getOriginal_sharpness() {
        return original_sharpness;
    }
}
