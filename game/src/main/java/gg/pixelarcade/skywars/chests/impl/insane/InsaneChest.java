package gg.pixelarcade.skywars.chests.impl.insane;

import gg.pixelarcade.skywars.chests.ChestStage;
import gg.pixelarcade.skywars.chests.LootableChest;

import gg.pixelarcade.skywars.chests.impl.insane.stages.FirstInsaneStage;
import gg.pixelarcade.skywars.chests.impl.insane.stages.SecondInsaneStage;
import gg.pixelarcade.skywars.chests.impl.insane.stages.ThirdInsaneStage;
import gg.pixelarcade.skywars.game.state.GameType;

import java.util.Arrays;
import java.util.List;



public class InsaneChest extends LootableChest {

    @Override
    public GameType getGameType() {
        return GameType.INSANE;
    }

    @Override
    public List<ChestStage> getStages() {
        return Arrays.asList(
                new FirstInsaneStage(),
                new SecondInsaneStage(),
                new ThirdInsaneStage()
        );
    }
}
