package gg.pixelarcade.skywars.perk;

import gg.pixelarcade.skywars.perk.impl.kitPerks.*;
import gg.pixelarcade.skywars.perk.impl.globalPerks.*;
import gg.pixelarcade.skywars.perk.impl.purchasedPerks.*;
import lombok.Data;

import java.util.*;

@Data
public class PerkHandler {

    private final Map<String, Perk> perks = new HashMap<>();

    public PerkHandler() {
        this.registerPerk(new ArmorerPerk());
        this.registerPerk(new BlacksmithPerk());
        this.registerPerk(new ChampionPerk());
        this.registerPerk(new HealerPerk());
        this.registerPerk(new HoundPerk());
        this.registerPerk(new MagicianPerk());
        this.registerPerk(new PaladinPerk());
        this.registerPerk(new PyromancerPerk());
        this.registerPerk(new ScoutPerk());
        this.registerPerk(new SniperPerk());
        this.registerPerk(new LastStandPerk());
        this.registerPerk(new JuggernautPerk());
        this.registerPerk(new BulldozerPerk());
    }

    public void registerPerk(Perk perk) {
        perks.put(perk.getName().toLowerCase(), perk);
    }

    public Perk getPerkByName(String name) {
        return perks.get(name.toLowerCase());
    }

    public Collection<Perk> getPerks() {
        return perks.values();
    }

}