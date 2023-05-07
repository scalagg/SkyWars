package xyz.refinedev.skywars.config;

import xyz.refinedev.phoenix.config.api.ConfigUtil;

import java.io.File;

/**
 * This Project is the property of Phoenix Development © 2022
 * Redistribution of this Project is not allowed
 *
 * @author Creaxx
 * Created At: 16/12/2022
 * Project: pxSkyWars
 */
public class SkyWarsConfig extends ConfigUtil {

    @Ignore
    public static final SkyWarsConfig IMP = new SkyWarsConfig();

    public void reload() {
        File file = new File("plugins/pxSkyWars/config.yml");
        load(file);
        save(file);
    }

    public void saveReload() {
        File file = new File("plugins/pxSkyWars/config.yml");
        save(file);
        load(file);
    }
}
