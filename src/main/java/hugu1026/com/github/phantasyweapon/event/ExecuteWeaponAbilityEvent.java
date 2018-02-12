package hugu1026.com.github.phantasyweapon.event;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class ExecuteWeaponAbilityEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private ItemStack weapon;
    private String type;

    public ExecuteWeaponAbilityEvent(Player player) {
        this.player = player;
        this.weapon = player.getInventory().getItemInMainHand();
        try {
            type = weapon.getItemMeta().getLore().get(0).replace("ジャンル:", "");
        } catch (Exception ex) {
            return;
        }
        this.type = ChatColor.stripColor(type);
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public Player getPlayer() {
        return player;
    }

    public ItemStack getWeapon() {
        return weapon;
    }

    public String getType() {
        return type;
    }
}
