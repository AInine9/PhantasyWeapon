package hugu1026.com.github.phantasyweapon.listener;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class PlayerInteract implements Listener {

    @EventHandler
    public void PlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        ItemStack whetstone = new ItemStack(Material.FLINT, 1);
        ItemMeta meta = whetstone.getItemMeta();
        meta.setDisplayName("砥石");
        whetstone.setItemMeta(meta);

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK
                || event.getHand() != EquipmentSlot.HAND) {
            return;
        }

        if (block.getType() == Material.ANVIL
                && player.getInventory().getItemInMainHand().getType() == Material.IRON_SWORD
                && player.getInventory().contains(whetstone)) {

            ItemStack weapon = player.getInventory().getItemInMainHand();
            String sharpnessLore[] = weapon.getItemMeta().getLore().get(2).split("/");
            int sharpness = Integer.parseInt(sharpnessLore[0].replace(ChatColor.YELLOW + "切れ味:", ""));
            int maxSharpness = Integer.parseInt(sharpnessLore[1]);

            if (sharpness == maxSharpness) {
                player.sendMessage(ChatColor.GOLD + "切れ味はもう最大だ！");
                return;
            }

            ItemMeta weaponMeta = weapon.getItemMeta();
            List<String> weaponLore = weaponMeta.getLore();
            weaponLore.set(2, ChatColor.YELLOW + "切れ味:" + maxSharpness + "/" + maxSharpness);
            weaponMeta.setLore(weaponLore);

            player.getInventory().getItemInMainHand().setItemMeta(weaponMeta);
            player.getInventory().removeItem(whetstone);

            player.sendMessage(ChatColor.GOLD + "切れ味が回復した！");
        }
    }
}
