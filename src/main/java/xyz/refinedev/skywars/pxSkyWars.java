package xyz.refinedev.skywars;

import xyz.refinedev.phoenix.Phoenix;
import xyz.refinedev.phoenix.SharedAPI;
import xyz.refinedev.skywars.config.SkyWarsConfig;
import xyz.refinedev.commands.PaperCommandManager;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.refinedev.skywars.handler.ProfileHandler;
import xyz.refinedev.skywars.listener.ProfileListener;

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
    @Getter private static pxSkyWars instance;

    private PaperCommandManager commandHandler;
    private ProfileHandler profileHandler;

    @Override
    public void onEnable() {
        instance = this;

        SkyWarsConfig.IMP.reload();

        this.profileHandler = new ProfileHandler(SharedAPI.getInstance());

        getServer().getPluginManager().registerEvents(new ProfileListener(this), this);

        this.commandHandler = Phoenix.getInstance().getCommandHandler();
    }
}
