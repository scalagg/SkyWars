package xyz.refinedev.skywars.ability;

import lombok.Getter;
import xyz.refinedev.skywars.ability.impl.PumpkinPie;
import xyz.refinedev.skywars.ability.impl.ThrowableDirt;
import xyz.refinedev.skywars.ability.impl.ThrowableTNT;

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
    private final Map<String, Ability> abilityByDisplayName = new HashMap<>();

    public AbilityHandler() {
        // todo: very aids way but i'm lazy
        abilityByName.put("pumpkinpie", new PumpkinPie());
        abilityByDisplayName.put("Pumpkin Pie", new PumpkinPie());

        abilityByName.put("throwabletnt", new ThrowableTNT());
        abilityByDisplayName.put("Throwable TNT", new ThrowableTNT());

        abilityByName.put("throwabledirt", new ThrowableDirt());
        abilityByDisplayName.put("Throwable Dirt", new ThrowableDirt());
    }

    public Ability getAbilityById(String id) {
        return abilityByName.get(id.toLowerCase());
    }

    public Ability getAbilityByName(String displayName) {
        return abilityByDisplayName.get(displayName);
    }

}
