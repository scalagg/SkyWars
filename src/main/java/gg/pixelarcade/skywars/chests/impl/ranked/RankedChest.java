package gg.pixelarcade.skywars.chests.impl.ranked;

import gg.pixelarcade.skywars.chests.ChestStage;
import gg.pixelarcade.skywars.chests.LootableChest;
import gg.pixelarcade.skywars.chests.impl.ranked.stages.FirstRankedStage;
import gg.pixelarcade.skywars.chests.impl.ranked.stages.SecondRankedStage;
import gg.pixelarcade.skywars.chests.impl.ranked.stages.ThirdRankedStage;
import gg.pixelarcade.skywars.game.state.GameType;

import java.util.Arrays;
import java.util.List;



public class RankedChest extends LootableChest {

    @Override
    public List<GameType> getGameType() {
        return Arrays.asList(
                GameType.RANKED_SOLO,
                GameType.RANKED_DOUBLES
        );
    }

    @Override
    public List<ChestStage> getStages() {
        return Arrays.asList(
                new FirstRankedStage(),
                new SecondRankedStage(),
                new ThirdRankedStage()
        );
    }
}
