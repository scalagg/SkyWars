package xyz.refinedev.skywars.achievement;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Creaxx
 * Created At: 5/8/2023
 */

@Getter
@RequiredArgsConstructor
public enum Achievement {

    TOP_OF_THE_LADDER("Top of the Ladder", "Achieve the highest rank in SkyWars.", Collections.emptyList()),
    FIRST_BLOOD("First Blood", "Get the first kill in a SkyWars game.", Collections.emptyList()),
    CHICKEN_DINNER("Chicken Dinner", "Win a game of SkyWars.", Collections.emptyList()),
    KILLSTREAK("Killstreak", "Get 10 kills in a single SkyWars game.", Collections.emptyList()),
    TEAM_PLAYER("Team Player", "Win a game of Team SkyWars with a full team of players.", Collections.emptyList()),
    IRON_MAN("Iron Man", "Win a game of SkyWars without taking any damage.", Collections.emptyList()),
    SNIPER("Sniper", "Get a kill from at least 20 blocks away.", Collections.emptyList()),
    THE_JUGGERNAUT("The Juggernaut", "Win a game of SkyWars without taking any knockback.", Collections.emptyList()),
    SURVIVOR("Survivor", "Stay alive for 5 minutes of time in a SkyWars game without getting a kill.", Collections.emptyList()),
    SPEED_DEMON("Speed Demon", "Win a game of SkyWars in under 2 minutes.", Collections.emptyList()),
    PICKPOCKET("Pickpocket", "Steal a 5 items from other players.", Collections.emptyList()),
    COLLECTOR("Collector", "Collect 15 resources in a single SkyWars game.", Collections.emptyList()),
    THE_GREAT_ESCAPE("The Great Escape", "Survive for 10 minutes in a SkyWars game while on low health.", Collections.emptyList()),
    TACTICAL_TACTICIAN("Tactical Tactician", "Win a game of SkyWars without directly engaging in combat.", Collections.emptyList());
    
    private final String displayName;
    private final String condition;
    private final List<String> commands;

}
