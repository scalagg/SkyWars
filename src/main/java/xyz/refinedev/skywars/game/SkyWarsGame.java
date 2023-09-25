package xyz.refinedev.skywars.game;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.cosmetics.cosmetics.killmessages.KillMessageType;
import xyz.refinedev.cosmetics.profile.CosmeticProfile;
import xyz.refinedev.cosmetics.pxCosmetics;
import xyz.refinedev.phoenix.utils.Color;
import xyz.refinedev.phoenix.utils.general.Tasks;
import xyz.refinedev.skywars.arena.Arena;
import xyz.refinedev.skywars.game.chest.ChestType;
import xyz.refinedev.skywars.game.chest.RefillType;
import xyz.refinedev.skywars.game.state.GameState;
import xyz.refinedev.skywars.game.state.GameType;
import xyz.refinedev.skywars.profile.SkyWarsProfile;
import xyz.refinedev.skywars.pxSkyWars;
import xyz.refinedev.skywars.utils.SpigotConstants;
import xyz.refinedev.skywars.utils.RefillUtil;
import xyz.refinedev.skywars.utils.SpawnUtil;

import java.util.*;

/**
 * @author Creaxx
 * Created At: 5/8/2023
 */

@RequiredArgsConstructor
public class SkyWarsGame {
    private final pxSkyWars plugin;

    @Getter
    private final GameType gameType;

    @Getter @Setter
    private Arena arena;

    @Getter
    private final List<Player> alivePlayers = new ArrayList<>();
    private final List<Player> spectators = new ArrayList<>();
    private final Random random = new Random();

    @Getter
    private int originalPlayers = 0;
    private RefillType refillType = RefillType.FIRST;
    private GameState state = GameState.WAITING;

    public SkyWarsGame(pxSkyWars plugin, GameType gameType, Arena arena) {
        this.plugin = plugin;
        this.gameType = gameType;
        this.arena = arena;
    }

    public void waiting() {
        Tasks.runLater(this::start, 15 * 20L);
    }

    public void start() {
        this.state = GameState.RUNNING;

        this.nextRefill();

        this.originalPlayers = alivePlayers.size();

        for (int i = 0; i < alivePlayers.size(); i++) {
            Player player = alivePlayers.get(i);
            Location spawn = arena.getSpawns().get(i);

            player.teleport(spawn);

            SkyWarsProfile profile = plugin.getProfileHandler().getProfile(player.getUniqueId());
            profile.getSelectedKit().giveKit(player);
        }
    }

    public void suddenDeath() {

    }

    public void addPlayer(Player player) {
        if (state != GameState.WAITING) {
            player.sendMessage(Color.translate(SpigotConstants.QUEUE + "&eThe game has &bstarted&e."));
            return;
        }

        if (gameType.getPlayerLimit() >= alivePlayers.size()) {
            player.sendMessage(Color.translate(SpigotConstants.QUEUE + "&eThe game you are joining is &bfull&e."));
            return;
        }

        alivePlayers.add(player);

        broadcast(Color.translate(SpigotConstants.GAME_NAME + "&e" + player.getName() + " &b has joined&e. &7(" + alivePlayers.size() + "/" + gameType.getPlayerLimit() + ")"));
    }

    public void removePlayer(Player player) {
        if (!hasStarted()) {
            alivePlayers.remove(player);
            player.sendMessage(Color.translate(SpigotConstants.QUEUE + "&eYou have &cleft &ethe &bqueue&e."));
            broadcast(Color.translate(SpigotConstants.GAME_NAME + "&e" + player.getName() + " &b has left&e. &7(" + alivePlayers.size() + "/" + gameType.getPlayerLimit() + ")"));
            return;
        }

        onDeath(player);
    }

    public void onDeath(Player player) {
        this.alivePlayers.remove(player);

        this.addSpectator(player);

        SkyWarsProfile profile = plugin.getProfileHandler().getProfile(player.getUniqueId());
        profile.incrementDeaths();
        profile.incrementLosses();
        profile.save();

        player.sendMessage(Color.translate(SpigotConstants.GAME_NAME + "&eYou have &cdied&e."));
        broadcast(Color.translate(SpigotConstants.GAME_NAME + "&e" + player.getName() + " &b has been eliminated&e. &7(" + alivePlayers.size() + "/" + gameType.getPlayerLimit() + ")"));

        if (canEnd()) onWin(alivePlayers.get(0));
    }

    public void onDeath(Player player, Player killer) {
        this.alivePlayers.remove(player);

        this.addSpectator(player);

        SkyWarsProfile profile = plugin.getProfileHandler().getProfile(player.getUniqueId());
        profile.incrementDeaths();
        profile.incrementLosses();
        profile.save();

        SkyWarsProfile killerProfile = plugin.getProfileHandler().getProfile(killer.getUniqueId());
        killerProfile.incrementKills();
        killerProfile.save();

        String type = "slain";

        CosmeticProfile cosmeticProfile = pxCosmetics.getInstance().getProfileHandler().getProfile(killer);

        if (!cosmeticProfile.getKillMessageType().equals(KillMessageType.NONE)) {
            try {
                type = KillMessageType.drawRandom(cosmeticProfile.getKillMessageType());
            } catch (Exception ignored) {
            }
        }

        player.sendMessage(Color.translate(SpigotConstants.GAME_NAME + "&eYou have &cdied&e to &b" + killer.getName() + "&e."));
        broadcast(SpigotConstants.GAME_NAME + killerProfile.getNiceName() + "&f has &d" + type + "&f " + profile.getNiceName() + "&f.");

        if (canEnd()) onWin(killer);
    }

    public void addSpectator(Player player) {
        this.spectators.add(player);

        for (Player alivePlayer : alivePlayers) {
            alivePlayer.hidePlayer(player);
        }

        for (Player spectator : spectators) {
            spectator.showPlayer(player);
        }

        player.teleport(arena.getSpectatorSpawn());
    }

    public void removeSpectator(Player player) {
        this.spectators.remove(player);

        SpawnUtil.teleportToSpawn(player);

        for (Player alivePlayer : alivePlayers) {
            alivePlayer.showPlayer(player);
        }
    }

    public void onWin(Player player) {
        this.state = GameState.ENDED;

        SkyWarsProfile profile = plugin.getProfileHandler().getProfile(player.getUniqueId());

        profile.incrementWins();
        profile.save();

        broadcast(Color.translate(SpigotConstants.GAME_NAME + "&e" + player.getDisplayName() + " &b has won the game&e."));

        SpawnUtil.teleportToSpawn(player);

        for (Player spectator : spectators) {
            SpawnUtil.teleportToSpawn(spectator);
        }

        this.spectators.clear();
        this.alivePlayers.clear();

        plugin.getArenaHandler().getWorldManager().deleteWorld(arena.getWorld());
    }

    public void broadcast(String message) {
        for (Player spectator : spectators) {
            spectator.sendMessage(Color.translate(message));
        }

        for (Player alivePlayer : alivePlayers) {
            alivePlayer.sendMessage(Color.translate(message));
        }
    }

    public void nextRefill() {
        switch (refillType) {
            case FIRST: {
                refillType = RefillType.SECOND;
                Tasks.runLater(this::nextRefill, 20 * 60 * 5L);
                break;
            }
            case SECOND: {
                refillType = RefillType.THIRD;
                Tasks.runLater(this::suddenDeath, 20 * 60 * 5L);
                break;
            }
            default: {
                return;
            }
        }

        fillChests();
    }

    public void fillChests() {
        for (Map.Entry<Location, ChestType> entry : arena.getChests().entrySet()) {
            Block block = arena.getBounds().getWorld().getBlockAt(entry.getKey());

            if (refillType == RefillType.SECOND && entry.getValue() != ChestType.MIDDLE && (!block.hasMetadata("placedByGame"))) {
                continue;
            }

            if (refillType == RefillType.THIRD && entry.getValue() == ChestType.MIDDLE && block.getType() == Material.AIR) {
                block.setType(Material.CHEST);
            }

            if (!(block instanceof Chest)) continue;

            Chest chest = (Chest) block;

            List<ItemStack> items = entry.getValue() == ChestType.SPAWN ? RefillUtil.getRandomizedSpawnItems(gameType, refillType) : RefillUtil.getRandomizedMiddleItems(gameType, refillType);

            int itemsToAdd = random.nextInt(1, 6);

            Inventory inventory = chest.getBlockInventory();

            for (int i = 0; i < itemsToAdd; i++) {
                int slot = random.nextInt(27);

                inventory.setItem(slot, items.get(random.nextInt(items.size())));
            }
        }
    }

    public boolean canEnd() {
        return alivePlayers.size() <= 1;
    }

    public boolean hasStarted() {
        return state != GameState.WAITING;
    }

    public boolean isInGame(Player player) {
        return alivePlayers.contains(player);
    }
}
