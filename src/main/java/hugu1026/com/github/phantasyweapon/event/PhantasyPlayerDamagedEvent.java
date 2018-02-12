package hugu1026.com.github.phantasyweapon.event;

import hugu1026.com.github.phantasystatus.util.PlayerDataUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class PhantasyPlayerDamagedEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private Player player;
    private int defenseHelmet, defenseChestPlate, defenseLeggings, defenseBoots;
    private double originalDamage, playerHP, playerMAX_HP;

    public PhantasyPlayerDamagedEvent(Player player, double originalDamage) {
        this.player = player;
        this.originalDamage = originalDamage;
        this.playerHP = PlayerDataUtil.getPlayerHP(player);
        this.playerMAX_HP = PlayerDataUtil.getPlayerMAX_HP(player);

        ItemStack helmet = player.getInventory().getHelmet();
        ItemStack chestPlate = player.getInventory().getChestplate();
        ItemStack leggings = player.getInventory().getLeggings();
        ItemStack boots = player.getInventory().getBoots();

        if(helmet == null) {
            this.defenseHelmet = 0;
        } else {
            this.defenseHelmet = Integer.parseInt(ChatColor.stripColor(helmet.getItemMeta().getLore().get(0).replace(ChatColor.YELLOW + "防御力:", "")));
        }

        if(chestPlate == null) {
            this.defenseHelmet = 0;
        } else {
            this.defenseChestPlate = Integer.parseInt(ChatColor.stripColor(chestPlate.getItemMeta().getLore().get(0).replace(ChatColor.YELLOW + "防御力:", "")));
        }

        if(leggings == null) {
            this.defenseHelmet = 0;
        } else {
            this.defenseLeggings = Integer.parseInt(ChatColor.stripColor(leggings.getItemMeta().getLore().get(0).replace(ChatColor.YELLOW + "防御力:", "")));
        }

        if(boots == null) {
            this.defenseHelmet = 0;
        } else {
            this.defenseBoots = Integer.parseInt(ChatColor.stripColor(boots.getItemMeta().getLore().get(0).replace(ChatColor.YELLOW + "防御力:", "")));
        }
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static  HandlerList getHandlerList() {
        return handlers;
    }

    public Player getPlayer() {
        return this.player;
    }

    public double getOriginalDamage() {
        return this.originalDamage;
    }

    public double getPlayerHP() {
        return this.playerHP;
    }

    public double getPlayerMAX_HP() {
        return this.playerMAX_HP;
    }

    public int getDefenseBoots() {
        return this.defenseBoots;
    }

    public int getDefenseChestPlate() {
        return this.defenseChestPlate;
    }

    public int getDefenseHelmet() {
        return this.defenseHelmet;
    }

    public int getDefenseLeggings() {
        return this.defenseLeggings;
    }
}
