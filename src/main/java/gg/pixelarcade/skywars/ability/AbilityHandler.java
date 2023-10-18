package gg.pixelarcade.skywars.ability;

import gg.pixelarcade.skywars.ability.impl.*;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.skywars.ability
 * @date 03/10/2023 / 06:09
 */

@Getter
public class AbilityHandler {

    private final Map<String, Ability> abilityByName = new HashMap<>();

    public AbilityHandler() {
        abilityByName.put("pumpkinpie", new PumpkinPie());
        abilityByName.put("throwabletnt", new ThrowableTNT());
        abilityByName.put("throwabledirt", new ThrowableDirt());
        abilityByName.put("safeteleport", new SafeTeleport());
        abilityByName.put("magnet", new Magnet());
        abilityByName.put("zombieguardian", new ZombieGuardian());
    }

    public Ability getAbilityById(String id) {
        return abilityByName.get(id.toLowerCase());
    }

}
