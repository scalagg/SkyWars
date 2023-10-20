package gg.pixelarcade.skywars.region;

import gg.pixelarcade.skywars.utils.cuboid.Cuboid;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.skywars.region
 * @date 19/10/2023 / 15:45
 */

@Getter @Setter
public class Region {

    private final Cuboid bounds;
    private String name;

    private boolean enabled = true;
    private boolean hunger = true;
    private boolean damage = true;
    private boolean breakBlocks = true;
    private boolean placeBlocks = true;
    private boolean dropItems = true;
    private boolean pickupItems = true;
    private boolean interact = true;
    private boolean leavesDecay = true;

    public Region(Cuboid bounds) {
        this.bounds = bounds;
    }

    public Region(Cuboid bounds, String name) {
        this.bounds = bounds;
        this.name = name;
    }

}
