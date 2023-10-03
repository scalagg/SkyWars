package xyz.refinedev.skywars.chests;

import lombok.Getter;
import xyz.refinedev.skywars.chests.impl.CasualChest;
import xyz.refinedev.skywars.chests.impl.InsaneChest;
import xyz.refinedev.skywars.chests.impl.RankedChest;
import xyz.refinedev.skywars.game.state.GameType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alfie
 * @project Skywars / xyz.refinedev.skywars.handler
 * @date 27/09/2023 / 20:36
 */

@Getter
public class ChestHandler {

    private final List<LootableChest> chests = new ArrayList<>();

    public ChestHandler() {
        register(new CasualChest());
        register(new InsaneChest());
        register(new RankedChest());
    }

    public LootableChest getChest(GameType gameType) {
        for (LootableChest chest : chests) {
            if (chest.getGameType().contains(gameType)) {
                return chest;
            }
        }

        return null;
    }

    public void register(LootableChest chest) {
        chests.add(chest);
    }

}
