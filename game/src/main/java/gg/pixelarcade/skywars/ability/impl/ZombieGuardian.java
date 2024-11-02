package gg.pixelarcade.skywars.ability.impl;

import gg.pixelarcade.skywars.ability.Ability;
import gg.pixelarcade.skywars.ability.type.Interact;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import net.evilblock.cubed.util.Color;
import net.evilblock.cubed.util.bukkit.Tasks;
import net.evilblock.cubed.util.bukkit.ItemBuilder;

import java.util.List;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.ability.impl
 * @date 17/10/2023 / 17:06
 */

public class ZombieGuardian extends Ability implements Interact {

    private final static int ZOMBIE_DURATION = 30;
    private final static int ZOMBIE_RADIUS = 5;

    @Override
    public String getName() {
        return Color.translate("&b&lZombie Guardian");
    }

    @Override
    public ItemStack getItem() {
        return new ItemBuilder(Material.MONSTER_EGG)
                .setDurability(54)
                .name(getName())
                .addToLore(Color.translate(List.of(
                        "&7Click to spawn a Zombie Guardian which will fight for you.",
                        " ",
                        "&e&lNOTE: &fZombie Guardian will only last for " + ZOMBIE_DURATION + " seconds."
                ))).build();
    }

    @Override
    public boolean onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        Zombie zombie = (Zombie) player.getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);
        zombie.setBaby(false);
        zombie.setVillager(false);
        zombie.setCustomName(Color.translate("&b&lZombie Guardian"));
        zombie.setCustomNameVisible(true);
        zombie.setRemoveWhenFarAway(false);
        zombie.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 0, false, false));

        for (Entity entity : zombie.getNearbyEntities(ZOMBIE_RADIUS, ZOMBIE_RADIUS, ZOMBIE_RADIUS)) {
            if (entity instanceof Player players) {
                if (players == player)
                    continue;

                players.sendMessage(Color.translate("&b&lZombie Guardian &7has been spawned by &b" + player.getName() + "&7."));
                players.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 3, 0, false, false));
                players.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * 3, 0, false, false));
            }
        }

        Tasks.runLater(zombie::remove, 20 * ZOMBIE_DURATION);
        return true;
    }
}
