package gg.pixelarcade.skywars.chests.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.skywars.chests.type
 * @date 26/09/2023 / 20:45
 */

@RequiredArgsConstructor
@Getter
public enum RefillType {

    FIRST(1),
    SECOND(2),
    THIRD(3);

    private final int weight;

    public boolean isHigherThan(RefillType type) {
        return weight > type.getWeight();
    }

}
