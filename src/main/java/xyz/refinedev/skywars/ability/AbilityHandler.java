package xyz.refinedev.skywars.ability;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.skywars.ability.impl.PumpkinPie;

import java.util.HashMap;
import java.util.Map;

/**
 * @author alfie
 * @project Skywars / xyz.refinedev.skywars.ability
 * @date 03/10/2023 / 06:09
 */

@Getter
public class AbilityHandler {

    private final Map<String, Ability> abilityByName = new HashMap<>();
    private final Map<ItemStack, Ability> abilityByItem = new HashMap<>();

    public AbilityHandler() {
        // todo: very aids way but i'm lazy
        abilityByName.put("pumpkinpie", new PumpkinPie());
        abilityByItem.put(abilityByName.get("pumpkinpie").getItem(), abilityByName.get("pumpkinpie"));
    }

    public Ability getAbility(String name) {
        return abilityByName.get(name.toLowerCase());
    }

    public Ability getAbility(ItemStack itemStack) {
        return abilityByItem.get(itemStack);
    }

}
