package gg.pixelarcade.skywars.chests;

import gg.pixelarcade.skywars.chests.impl.casual.CasualChest;
import gg.pixelarcade.skywars.chests.impl.insane.InsaneChest;
import gg.pixelarcade.skywars.chests.impl.ranked.RankedChest;
import lombok.Getter;
import gg.pixelarcade.skywars.game.state.GameType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.skywars.chests
 * @date 27/09/2023 / 20:36
 */

@Getter
public class ChestHandler {

    private final List<LootableChest> chests = new ArrayList<>();

    public ChestHandler() {
        register(new CasualChest());
        register(new RankedChest());
        register(new InsaneChest());
    }

    public LootableChest getChest(GameType gameType) {
        for (LootableChest chest : chests) {
            if (chest.getGameType().contains(gameType))
                return chest;
        }

        return null;
    }

    public void register(LootableChest chest) {
        chests.add(chest);
    }

}
