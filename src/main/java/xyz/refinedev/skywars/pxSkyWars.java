package xyz.refinedev.skywars;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.BlockVector;
import org.bukkit.util.Vector;
import xyz.refinedev.phoenix.Phoenix;
import xyz.refinedev.phoenix.SharedAPI;
import xyz.refinedev.skywars.config.SkyWarsConfig;
import xyz.refinedev.commands.PaperCommandManager;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.refinedev.skywars.handler.*;
import xyz.refinedev.skywars.listener.HotbarListener;
import xyz.refinedev.skywars.listener.ProfileListener;
import xyz.refinedev.skywars.utils.serialization.*;

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
    @Getter public static pxSkyWars instance;

    private Gson gson;
    private PaperCommandManager commandHandler;
    private ProfileHandler profileHandler;
    private AchievementHandler achievementHandler;
    private GameHandler gameHandler;
    private ArenaHandler arenaHandler;
    private HotbarHandler hotbarHandler;


    @Override
    public void onEnable() {
        instance = this;

        SkyWarsConfig.IMP.reload();

        this.gson = new GsonBuilder()
                .registerTypeHierarchyAdapter(PotionEffect.class, new PotionEffectAdapter())
                .registerTypeHierarchyAdapter(ItemStack.class, new ItemStackAdapter())
                .registerTypeHierarchyAdapter(Location.class, new LocationAdapter())
                .registerTypeHierarchyAdapter(Vector.class, new VectorAdapter())
                .registerTypeAdapter(BlockVector.class, new BlockVectorAdapter())
                .serializeNulls()
                .create();

        this.profileHandler = new ProfileHandler(SharedAPI.getInstance());
        this.achievementHandler = new AchievementHandler();
        this.arenaHandler = new ArenaHandler(this);
        this.gameHandler = new GameHandler();
        this.hotbarHandler = new HotbarHandler();

        getServer().getPluginManager().registerEvents(new ProfileListener(this), this);
        getServer().getPluginManager().registerEvents(new HotbarListener(this), this);

        this.commandHandler = Phoenix.getInstance().getCommandHandler();
    }
}
