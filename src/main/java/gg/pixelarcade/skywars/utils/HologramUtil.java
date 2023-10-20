package gg.pixelarcade.skywars.utils;

import lombok.experimental.UtilityClass;
import me.filoghost.holographicdisplays.api.HolographicDisplaysAPI;
import me.filoghost.holographicdisplays.api.hologram.Hologram;
import me.filoghost.holographicdisplays.api.hologram.VisibilitySettings;
import org.bukkit.Location;

@UtilityClass
public class HologramUtil {

    /**
     * Use the {@link HolographicDisplaysAPI api} to spawn a hologram at the given
     * {@link Location} and load its chunks if not loaded
     *
     * @param api      {@link HolographicDisplaysAPI api}
     * @param location {@link Location location}
     * @return         {@link Hologram hologram}
     */
    public Hologram createHologram(HolographicDisplaysAPI api, Location location) {
        Hologram hologram = api.createHologram(location);
        // We automatically show the hologram to everyone.
        hologram.getVisibilitySettings().setGlobalVisibility(VisibilitySettings.Visibility.VISIBLE);

        // If chunk isn't loaded then we back up
        if (!location.getChunk().isLoaded()) {
            location.getChunk().load();
        }
        return hologram;
    }
}