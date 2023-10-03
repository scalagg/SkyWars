package xyz.refinedev.skywars;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.BlockVector;
import org.bukkit.util.Vector;
import xyz.refinedev.phoenix.Phoenix;
import xyz.refinedev.phoenix.SharedAPI;
import xyz.refinedev.skywars.ability.AbilityHandler;
import xyz.refinedev.skywars.ability.command.AbilityCommand;
import xyz.refinedev.skywars.ability.listener.AbilityInteractListener;
import xyz.refinedev.skywars.achievement.AchievementHandler;
import xyz.refinedev.skywars.arena.ArenaHandler;
import xyz.refinedev.skywars.arena.ArenaSchematic;
import xyz.refinedev.skywars.arena.listener.ArenaModelEditorListener;
import xyz.refinedev.skywars.chests.ChestHandler;
import xyz.refinedev.skywars.commands.ArenaCommand;
import xyz.refinedev.skywars.commands.GameCommand;
import xyz.refinedev.commands.PaperCommandManager;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.refinedev.skywars.game.GameHandler;
import xyz.refinedev.skywars.game.listener.BuildModeListener;
import xyz.refinedev.skywars.game.listener.GameListener;
import xyz.refinedev.skywars.item.impl.*;
import xyz.refinedev.skywars.item.listeners.ItemListener;
import xyz.refinedev.skywars.kit.KitHandler;
import xyz.refinedev.skywars.profile.listener.ProfileListener;
import xyz.refinedev.skywars.profile.ProfileHandler;
import xyz.refinedev.skywars.queue.commands.QueueCommand;
import xyz.refinedev.skywars.scoreboard.ScoreboardAdapter;
import xyz.refinedev.skywars.scoreboard.api.Assemble;
import xyz.refinedev.skywars.utils.serialization.*;

import java.util.Arrays;
import java.util.Random;

/**
 * This Project is the property of Phoenix Development © 2022
 * Redistribution of this Project is not allowed
 *
 * @author Creaxx
 * Created At: 16/12/2022
 * Project: pxSkyWars
 */

@Getter
@Setter
public class pxSkyWars extends JavaPlugin {
    @Getter
    public static pxSkyWars instance;

    private Gson gson;
    private PaperCommandManager commandHandler;
    private ProfileHandler profileHandler;
    private AchievementHandler achievementHandler;
    private GameHandler gameHandler;
    private ArenaHandler arenaHandler;
    private KitHandler kitHandler;
    private ChestHandler chestHandler;
    private AbilityHandler abilityHandler;

    private Random random;

    @Override
    public void onEnable() {
        instance = this;

        this.gson = new GsonBuilder()
                .registerTypeHierarchyAdapter(PotionEffect.class, new PotionEffectAdapter())
                .registerTypeHierarchyAdapter(ItemStack.class, new ItemStackAdapter())
                .registerTypeHierarchyAdapter(Location.class, new LocationAdapter())
                .registerTypeHierarchyAdapter(Vector.class, new VectorAdapter())
                .registerTypeAdapter(BlockVector.class, new BlockVectorAdapter())
                .serializeNulls()
                .create();

        this.random = new Random();

        this.profileHandler = new ProfileHandler(SharedAPI.getInstance());
        this.achievementHandler = new AchievementHandler();
        this.arenaHandler = new ArenaHandler();
        this.gameHandler = new GameHandler();
        this.kitHandler = new KitHandler(this);
        this.chestHandler = new ChestHandler();
        this.abilityHandler = new AbilityHandler();

        Arrays.asList(
                new ProfileListener(this),
                new ItemListener(),
                new ArenaModelEditorListener(),
                new AbilityInteractListener(this),
                new GameListener(this),
                new BuildModeListener(this)
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
        commandHandler.registerCommand(new QueueCommand());
        commandHandler.registerCommand(new AbilityCommand());

        new Assemble(this, new ScoreboardAdapter(this));
    }

    @Override
    public void onDisable() {
        arenaHandler.saveSchematics();
    }

    public void giveItems(Player player) {
        player.getInventory().clear();
        player.getActivePotionEffects().clear();

        player.setHealth(20);
        player.setFoodLevel(20);
        player.setFireTicks(0);
        player.setExp(0);
        player.setLevel(0);

        player.setFlying(false);
        player.setAllowFlight(false);

        player.getInventory().setItem(0, new QueueItem(player).getItem(player));
        player.getInventory().setItem(1, new ProfileItem(player).getItem(player));
        player.getInventory().setItem(4, new CosmeticItem(player).getItem(player));
        player.getInventory().setItem(7, new LoadoutItem(player).getItem(player));
        player.getInventory().setItem(8, new SettingsItem(player).getItem(player));
    }


}
