package gg.pixelarcade.skywars.chests.type

import lombok.Getter
import lombok.RequiredArgsConstructor

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.chests.type
 * @date 26/09/2023 / 20:45
 */

enum class RefillType(val weight: Int = 0){
    FIRST(1),
    SECOND(2),
    THIRD(3);



    fun isHigherThan(type: RefillType): Boolean {
        return weight > type.weight
    }
}
