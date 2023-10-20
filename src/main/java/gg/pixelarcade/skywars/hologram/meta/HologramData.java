package gg.pixelarcade.skywars.hologram.meta;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

import me.filoghost.holographicdisplays.api.hologram.Hologram;

import org.bukkit.Location;

import java.util.UUID;

/**
 * This Project is property of Refine Development © 2021 - 2023
 * Redistribution of this Project is not allowed
 *
 * @author Drizzy
 * Created: 3/9/2022
 * Project: PotPvPRP
 */

@Data
public class HologramData {

    /**
     * The UUID based identifier for this Hologram
     */
    @SerializedName("_id")
    private final UUID uniqueId;
    /**
     * The location of where the hologram is placed
     */
    private Location location;
    /**
     * The name of the Hologram, it's in used in commands
     */
    private String name;
    /**
     * We track our api-created hologram here, but we obviously
     * don't save it with our serialization, since it's always recreated on start
     */
    private transient Hologram hologram;
}
