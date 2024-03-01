package gg.pixelarcade.skywars.chests.impl.casual;

import gg.pixelarcade.skywars.chests.ChestStage;
import gg.pixelarcade.skywars.chests.LootableChest;
import gg.pixelarcade.skywars.chests.impl.casual.stages.FirstCasualStage;
import gg.pixelarcade.skywars.chests.impl.casual.stages.SecondCasualStage;
import gg.pixelarcade.skywars.chests.impl.casual.stages.ThirdCasualStage;
import gg.pixelarcade.skywars.game.state.GameType;

import java.util.Arrays;
import java.util.List;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.chests.impl.casual
 * @date 17/10/2023 / 16:04
 */

public class CasualChest extends LootableChest {

    @Override
    public GameType getGameType() {
        return GameType.NORMAL;
    }

    @Override
    public List<ChestStage> getStages() {
        return Arrays.asList(
                new FirstCasualStage(),
                new SecondCasualStage(),
                new ThirdCasualStage()
        );
    }
}
