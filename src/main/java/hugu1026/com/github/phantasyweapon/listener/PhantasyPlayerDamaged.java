package hugu1026.com.github.phantasyweapon.listener;

import hugu1026.com.github.phantasystatus.util.PlayerDataUtil;
import hugu1026.com.github.phantasyweapon.event.PhantasyPlayerDamagedEvent;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.io.File;

public class PhantasyPlayerDamaged implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void PhantasyPlayerDamaged(PhantasyPlayerDamagedEvent event) {
        Player player = event.getPlayer();

        File playerFile = PlayerDataUtil.getPlayerFile(player);
        FileConfiguration playerData = PlayerDataUtil.getPlayerData(player);

        double originalDamage = event.getOriginalDamage();
        double playerHP = event.getPlayerHP();
        double playerMaxHP = event.getPlayerMAX_HP();
        int playerDefense = PlayerDataUtil.getPlayerDEFEND(player);
        int defensePower = event.getDefenseHelmet() + event.getDefenseChestPlate() + event.getDefenseLeggings() + event.getDefenseBoots();

        double damage = (originalDamage * 1.05) - (defensePower + (playerDefense / 2)) / 4;

        if (damage < 0) {
            damage = 1;
        }

        playerData.set("status.HP", playerHP - damage);
        PlayerDataUtil.savePlayerData(playerFile, playerData, player);

        playerHP = playerHP - damage;
        double proportion = playerHP / playerMaxHP;

        if (1 >= proportion && proportion > 0.9) {
            player.damage(0);
        } else if (proportion > 0.8) {
            player.setHealth(18);
            player.damage(0);
        } else if (proportion > 0.7) {
            player.setHealth(16);
            player.damage(0);
        } else if (proportion > 0.6) {
            player.setHealth(14);
            player.damage(0);
        } else if (proportion > 0.5) {
            player.setHealth(12);
            player.damage(0);
        } else if (proportion > 0.4) {
            player.setHealth(10);
            player.damage(0);
        } else if (proportion > 0.3) {
            player.setHealth(8);
            player.damage(0);
        } else if (proportion > 0.2) {
            player.setHealth(6);
            player.damage(0);
        } else if (proportion > 0.1) {
            player.setHealth(4);
            player.damage(0);
        } else if (proportion > 0) {
            player.setHealth(2);
            player.damage(0);
        } else if (proportion <= 0) {
            player.damage(20);

            playerData.set("status.HP", playerData.getInt("status.addition.HP") + 20);
            PlayerDataUtil.savePlayerData(playerFile, playerData, player);
        }
    }
}
