package hugu1026.com.github.phantasyweapon;

import hugu1026.com.github.phantasyweapon.listener.EntityDamage;
import hugu1026.com.github.phantasyweapon.listener.PhantasyWeaponAttack;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class PhantasyWeapon extends JavaPlugin {

    @Override
    public void onEnable() {
        super.onEnable();

        this.registerEvents();
    }

    @Override
    public void onDisable() {
        super.onEnable();
    }

    public void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new EntityDamage(), this);
        pm.registerEvents(new PhantasyWeaponAttack(), this);
    }
}
