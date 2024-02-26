package gg.pixelarcade.skywars;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author alfie
 * @project SkyWars
 * @date 27/10/2023 / 02:28
 */

@Getter
public class SkyWarsLobbyPlugin extends JavaPlugin {

    @Getter
    private static SkyWarsLobbyPlugin instance;


    @Override
    public void onEnable() {
        instance = this;

        new SkyWarsLobby(this);
    }

    @Override
    public void onDisable() {
        SkyWarsLobby.getInstance().stop();
    }


}
