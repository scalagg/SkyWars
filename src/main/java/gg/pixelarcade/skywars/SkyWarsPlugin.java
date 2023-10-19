package gg.pixelarcade.skywars;

import ac.sparky.api.SparkyAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gg.pixelarcade.skywars.item.impl.*;
import gg.pixelarcade.skywars.kit.KitHandler;
import gg.pixelarcade.skywars.music.MusicHandler;
import gg.pixelarcade.skywars.music.commands.MusicCommand;
import gg.pixelarcade.skywars.region.RegionHandler;
import gg.pixelarcade.skywars.utils.serialization.*;
import org.bukkit.Location;
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
import gg.pixelarcade.skywars.commands.ArenaCommand;
import gg.pixelarcade.skywars.commands.GameCommand;
import xyz.refinedev.commands.PaperCommandManager;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;
import gg.pixelarcade.skywars.game.GameHandler;
import gg.pixelarcade.skywars.game.listener.GameListener;
import gg.pixelarcade.skywars.game.listener.PreventionListener;
import gg.pixelarcade.skywars.game.listener.SpectatorListener;
import gg.pixelarcade.skywars.item.impl.*;
import gg.pixelarcade.skywars.item.listeners.ItemListener;
import gg.pixelarcade.skywars.profile.listener.ProfileListener;
import gg.pixelarcade.skywars.profile.ProfileHandler;
import gg.pixelarcade.skywars.scoreboard.ScoreboardAdapter;
import gg.pixelarcade.skywars.scoreboard.api.Assemble;
import gg.pixelarcade.skywars.utils.serialization.*;

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
    public static SkyWarsPlugin instance;

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

    @Override
    public void onEnable() {
        instance = this;

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

        new Assemble(this, new ScoreboardAdapter(this));
    }

    @Override
    public void onDisable() {
        regionHandler.saveRegions();
        arenaHandler.saveSchematics();
    }

    public void giveItems(Player player) {
        resetPlayer(player);

        player.getInventory().setItem(0, new QueueItem(player).getItem(player));
//        player.getInventory().setItem(1, new ProfileItem(player).getItem(player));
        player.getInventory().setItem(4, new CosmeticItem(player).getItem(player));
        player.getInventory().setItem(7, new LoadoutItem(player).getItem(player));
        player.getInventory().setItem(8, new SettingsItem(player).getItem(player));
    }

    public void resetPlayer(Player player) {
        player.getInventory().clear();
        player.getActivePotionEffects().clear();

        player.setHealth(20);
        player.setFoodLevel(20);
        player.setFireTicks(0);
        player.setExp(0);
        player.setLevel(0);

        player.setFlying(false);
        player.setAllowFlight(false);
    }


}
