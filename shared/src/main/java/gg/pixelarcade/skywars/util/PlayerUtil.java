package gg.pixelarcade.skywars.util;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * @author alfie
 * @project SkyWars
 * @date 27/10/2023 / 03:05
 */


public class PlayerUtil {

    public static void resetPlayer(Player player) {
        player.getInventory().clear();
        player.getActivePotionEffects().clear();
        player.getInventory().setArmorContents(new ItemStack[4]);

        player.setGameMode(GameMode.SURVIVAL);
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setFireTicks(0);
        player.setExp(0);
        player.setLevel(0);

        player.setFlying(false);
        player.setAllowFlight(false);
    }

}
