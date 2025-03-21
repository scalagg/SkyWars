package gg.scala.skywars.region

import gg.scala.skywars.util.cuboid.Cuboid



/**
 * @author alfie
 * @project Skywars / gg.scala.game.region
 * @date 19/10/2023 / 15:45
 */


class Region {
    val bounds: _root_ide_package_.gg.scala.skywars.util.cuboid.Cuboid
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

    constructor(bounds: _root_ide_package_.gg.scala.skywars.util.cuboid.Cuboid) {
        this.bounds = bounds
    }

    constructor(bounds: _root_ide_package_.gg.scala.skywars.util.cuboid.Cuboid, name: String?) {
        this.bounds = bounds
        this.name = name
    }
}
