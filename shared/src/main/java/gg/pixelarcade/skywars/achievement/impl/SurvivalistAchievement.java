package gg.pixelarcade.skywars.achievement.impl;

import gg.pixelarcade.skywars.achievement.Achievement;
import gg.pixelarcade.skywars.game.Game;
import gg.pixelarcade.skywars.game.event.GameEndEvent;
import gg.pixelarcade.skywars.game.event.PlayerDamagePlayerEvent;
import lombok.Getter;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Getter
public class SurvivalistAchievement implements Achievement {
    private final Set<UUID> opened = new HashSet<>();

    private final String id = "survivalist";
    private final String name = "Survivalist";
    private final String[] description = new String[] {
            "Win a game without opening a single chest"
    };

    @Override
    public int getGoal() {
        return 1;
    }

    @EventHandler
    public void onChestOpen(InventoryOpenEvent event) {
        Player player = (Player) event.getPlayer();
        Inventory inventory = event.getInventory();

        if (inventory.getHolder() instanceof Chest chest) {
            if (chest.getLocation() == null) return;
            opened.add(player.getUniqueId());
        }
    }

    @EventHandler
    public void onGameEnd(GameEndEvent event) {
        Game game = event.getGame();

        for (UUID winner : game.getAlivePlayers()) {
            if (opened.contains(winner)) continue;
            if (isAchieved(winner)) continue;
            increaseProgress(winner);
        }
    }
}
