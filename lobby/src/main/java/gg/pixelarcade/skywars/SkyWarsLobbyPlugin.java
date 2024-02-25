package gg.pixelarcade.skywars;


import gg.pixelarcade.skywars.util.registry.ServiceRegistry;
import gg.pixelarcade.skywars.util.registry.ServiceRegistryImpl;

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

    private final ServiceRegistry serviceRegistry = new ServiceRegistryImpl();


    @Override
    public void onEnable() {
        instance = this;

        new SkyWarsLobby(this);
    }

    @Override
    public void onDisable() {
        SkyWarsLobby.getInstance().stop();
    }

    public static SkyWarsLobbyPlugin get() {
        return instance;
    }

    public static <T> T install(Class<T> key, T service) {
        return instance.serviceRegistry.put(key, service);
    }

    public static <T> T get(Class<T> tClass) {
        return instance.serviceRegistry.get(tClass);
    }

}
