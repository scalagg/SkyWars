package gg.pixelarcade.skywars.region

import gg.pixelarcade.skywars.util.cuboid.Cuboid
import lombok.Getter
import lombok.Setter

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.region
 * @date 19/10/2023 / 15:45
 */
@Getter
@Setter
class Region {
    val bounds: Cuboid
    var name: String? = null

    var enabled = true
    var hunger = true
    var damage = true
    var breakBlocks = true
    var placeBlocks = true
    var dropItems = true
    var pickupItems = true
    var interact = true
    var leavesDecay = true

    constructor(bounds: Cuboid) {
        this.bounds = bounds
    }

    constructor(bounds: Cuboid, name: String?) {
        this.bounds = bounds
        this.name = name
    }
}
