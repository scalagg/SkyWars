package gg.pixelarcade.skywars.ability;

import gg.pixelarcade.skywars.ability.impl.ThrowableDirt;
import gg.pixelarcade.skywars.ability.impl.ThrowableTNT;
import lombok.Getter;
import gg.pixelarcade.skywars.ability.impl.PumpkinPie;

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
        abilityByName.put("pumpkinpie", new PumpkinPie());
        abilityByName.put("throwabletnt", new ThrowableTNT());
        abilityByName.put("throwabledirt", new ThrowableDirt());
    }

    public Ability getAbilityById(String id) {
        return abilityByName.get(id.toLowerCase());
    }

}
