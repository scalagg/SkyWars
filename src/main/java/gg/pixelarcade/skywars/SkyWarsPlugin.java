package gg.pixelarcade.skywars;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gg.pixelarcade.skywars.arena.commands.ArenaCommand;
import gg.pixelarcade.skywars.game.commands.GameCommand;
import gg.pixelarcade.skywars.hologram.handler.HologramHandler;
import gg.pixelarcade.skywars.item.impl.*;
import gg.pixelarcade.skywars.kit.KitHandler;
import gg.pixelarcade.skywars.music.MusicHandler;
import gg.pixelarcade.skywars.music.commands.MusicCommand;
import gg.pixelarcade.skywars.region.RegionHandler;
import gg.pixelarcade.skywars.region.commands.RegionCommands;
import gg.pixelarcade.skywars.utils.serialization.*;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.BlockVector;
import org.bukkit.util.Vector;
import xyz.refinedev.phoenix.Phoenix;
import xyz.refinedev.phoenix.SharedAPI;
import gg.pixelarcade.skywars.ability.AbilityHandler;
import gg.pixelarcade.skywars.ability.command.AbilityCommand;
import gg.pixelarcade.skywars.ability.listener.AbilityInteractListener;
import gg.pixelarcade.skywars.achievement.AchievementHandler;
import gg.pixelarcade.skywars.arena.ArenaHandler;
import gg.pixelarcade.skywars.arena.ArenaSchematic;
import gg.pixelarcade.skywars.arena.listener.ArenaModelEditorListener;
import gg.pixelarcade.skywars.chests.ChestHandler;
import xyz.refinedev.commands.PaperCommandManager;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;
import gg.pixelarcade.skywars.game.GameHandler;
import gg.pixelarcade.skywars.game.listener.GameListener;
import gg.pixelarcade.skywars.game.listener.PreventionListener;
import gg.pixelarcade.skywars.game.listener.SpectatorListener;
import gg.pixelarcade.skywars.item.listeners.ItemListener;
import gg.pixelarcade.skywars.profile.listener.ProfileListener;
import gg.pixelarcade.skywars.profile.ProfileHandler;
import gg.pixelarcade.skywars.scoreboard.ScoreboardAdapter;
import gg.pixelarcade.skywars.scoreboard.api.Assemble;
import xyz.refinedev.phoenix.utils.general.Tasks;

import java.io.File;
import java.util.Arrays;

/**
 * This Project is the property of Phoenix Development © 2022
 * Redistribution of this Project is not allowed
 *
 * @author Creaxx
 * Created At: 16/12/2022
 * Project: SkyWarsPlugin
 */

@Getter
@Setter
public class SkyWarsPlugin extends JavaPlugin {

    @Getter
    private static SkyWarsPlugin instance;

    public static Gson GSON = new GsonBuilder()
            .registerTypeHierarchyAdapter(PotionEffect.class, new PotionEffectAdapter())
            .registerTypeHierarchyAdapter(ItemStack.class, new ItemStackAdapter())
            .registerTypeHierarchyAdapter(Location.class, new LocationAdapter())
            .registerTypeHierarchyAdapter(Vector.class, new VectorAdapter())
            .registerTypeAdapter(BlockVector.class, new BlockVectorAdapter())
            .serializeNulls()
            .setPrettyPrinting()
            .create();

    private PaperCommandManager commandHandler;
    private ProfileHandler profileHandler;
    private RegionHandler regionHandler;
    private AchievementHandler achievementHandler;
    private GameHandler gameHandler;
    private ArenaHandler arenaHandler;
    private KitHandler kitHandler;
    private ChestHandler chestHandler;
    private AbilityHandler abilityHandler;
    private MusicHandler musicHandler;
    private HologramHandler hologramHandler;

    private boolean debug = true;

    @Override
    public void onEnable() {
        instance = this;

        this.hologramHandler = new HologramHandler(this);
        this.profileHandler = new ProfileHandler(SharedAPI.getInstance());
        this.achievementHandler = new AchievementHandler();
        this.arenaHandler = new ArenaHandler();
        this.regionHandler = new RegionHandler();
        this.gameHandler = new GameHandler();
        this.kitHandler = new KitHandler(this);
        this.chestHandler = new ChestHandler();
        this.abilityHandler = new AbilityHandler();
        this.musicHandler = new MusicHandler(new File(getDataFolder(), "music"));

        Arrays.asList(
                new ProfileListener(this),
                new ItemListener(),
                new ArenaModelEditorListener(),
                new AbilityInteractListener(this),
                new GameListener(this),
                new SpectatorListener(this),
                new PreventionListener(this)
        ).forEach(listener -> this.getServer().getPluginManager().registerEvents(listener, this));

        commandHandler = Phoenix.getInstance().getCommandHandler();
        commandHandler.getCommandContexts().registerContext(ArenaSchematic.class, c -> {
            String name = c.popFirstArg();
            ArenaSchematic schematic = arenaHandler.getSchematic(name);

            if (schematic == null) {
                c.getSender().sendMessage("§cSchematic not found.");
                return null;
            }

            return schematic;
        });

        commandHandler.registerCommand(new ArenaCommand());
        commandHandler.registerCommand(new GameCommand(this));
        commandHandler.registerCommand(new AbilityCommand());
        commandHandler.registerCommand(new MusicCommand());
        commandHandler.registerCommand(new RegionCommands());

        new Assemble(this, new ScoreboardAdapter(this));
    }

    @Override
    public void onDisable() {
        regionHandler.saveRegions();
        arenaHandler.saveSchematics();
    }

    public void giveItems(Player player) {
        resetPlayer(player);

        // this is so weird but otherwise last player to die doesnt get items for some reason :/
        Tasks.runLater(() -> {
            player.getInventory().setItem(0, new QueueItem(player).getItem(player));
            player.getInventory().setItem(4, new CosmeticItem(player).getItem(player));
            player.getInventory().setItem(7, new LoadoutItem(player).getItem(player));
            player.getInventory().setItem(8, new SettingsItem(player).getItem(player));
            player.updateInventory();
        }, 2L);
    }

    public void resetPlayer(Player player) {
        player.getInventory().clear();
        player.getActivePotionEffects().clear();
        player.getInventory().setArmorContents(new ItemStack[4]);
        player.updateInventory();

        player.setGameMode(GameMode.SURVIVAL);
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setFireTicks(0);
        player.setExp(0);
        player.setLevel(0);

        player.setFlying(false);
        player.setAllowFlight(false);
    }


}
