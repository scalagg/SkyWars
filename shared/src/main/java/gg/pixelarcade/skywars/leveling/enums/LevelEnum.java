package gg.pixelarcade.skywars.leveling.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LevelEnum {

    ROOKIE (0, 0, "&7", 50),
    VOYAGER (1, 10, "&f", 100),
    Sentinel (2, 20, "&6", 250),
    Phantom (3, 30, "&b", 500),
    Ascendant (4, 40, "&2", 1000),
    Nebula (5, 50, "&3", 1500),
    Apex (6, 60, "&4", 2500),
    Eclipse (7, 70, "&d", 5000),
    Chrono (8, 80, "&9", 10000),
    Celestial (9, 90, "&5", 20000);

    private final int priority;
    private final int minLevel;
    private final String color;
    private final int xpPerLevel;


}
