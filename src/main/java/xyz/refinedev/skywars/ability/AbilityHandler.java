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

    public AbilityHandler() {
        // todo: very aids way but i'm lazy
        abilityByName.put("pumpkinpie", new PumpkinPie());
        abilityByName.put("throwabletnt", new ThrowableTNT());
        abilityByName.put("throwabledirt", new ThrowableDirt());
    }

    public Ability getAbilityById(String id) {
        return abilityByName.get(id.toLowerCase());
    }

}
