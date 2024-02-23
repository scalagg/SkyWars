package gg.pixelarcade.skywars.chests;

import gg.pixelarcade.skywars.chests.type.RefillType;

import java.util.List;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.chests
 * @date 17/10/2023 / 15:50
 */

public abstract class ChestStage {

    public abstract RefillType getRefillType();
    public abstract List<ChestItem> getSpawnLoot();
    public abstract List<ChestItem> getMiddleLoot();

}
