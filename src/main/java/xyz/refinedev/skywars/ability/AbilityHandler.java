package xyz.refinedev.skywars.ability;

import lombok.Getter;
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
    private final Map<String, Ability> abilityByDisplayName = new HashMap<>();

    public AbilityHandler() {
        // todo: very aids way but i'm lazy
        abilityByName.put("pumpkinpie", new PumpkinPie());
        abilityByDisplayName.put("Pumpkin Pie", new PumpkinPie());
    }

    public Ability getAbilityById(String id) {
        return abilityByName.get(id.toLowerCase());
    }

    public Ability getAbilityByName(String displayName) {
        return abilityByDisplayName.get(displayName);
    }

}
