package gg.pixelarcade.skywars.ability;

import gg.pixelarcade.skywars.SkyWarsPlugin;
import gg.pixelarcade.skywars.ability.impl.*;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.skywars.ability
 * @date 03/10/2023 / 06:09
 */

@Getter
public class AbilityHandler {

    private final Map<String, Ability> cache = new HashMap<>();

    public AbilityHandler() {
        cache.put("pumpkinpie", new PumpkinPie());
        cache.put("throwabletnt", new ThrowableTNT());
        cache.put("throwabledirt", new ThrowableDirt());
        cache.put("safeteleport", new SafeTeleport());
        cache.put("magnet", new Magnet());
        cache.put("zombieguardian", new ZombieGuardian());
    }

    public Ability getAbilityById(String id) {
        return cache.get(id.toLowerCase());
    }

    public void giveAbility(Player player, String abilityName, int amount) {
        ItemStack item = SkyWarsPlugin.getInstance().getAbilityHandler().getAbilityById(abilityName).getItem();
        item.setAmount(amount);

        player.getInventory().addItem(item);
    }

}
