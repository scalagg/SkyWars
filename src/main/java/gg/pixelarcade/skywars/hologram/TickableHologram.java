package gg.pixelarcade.skywars.hologram;

import com.google.common.base.Preconditions;

import gg.pixelarcade.skywars.SkyWarsPlugin;
import gg.pixelarcade.skywars.hologram.meta.HologramData;
import gg.pixelarcade.skywars.hologram.meta.HologramType;
import gg.pixelarcade.skywars.utils.HologramUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import me.filoghost.holographicdisplays.api.HolographicDisplaysAPI;
import me.filoghost.holographicdisplays.api.hologram.Hologram;
import me.filoghost.holographicdisplays.api.hologram.HologramLines;

import org.bukkit.Location;
import xyz.refinedev.phoenix.utils.Color;


/**
 * This Project is property of Refine Development © 2021
 * Redistribution of this Project is not allowed
 *
 * @author Drizzy
 * Created: 9/15/2021
 * Project: PotPvPRP
 */

@Getter @Setter
@RequiredArgsConstructor
public abstract class TickableHologram {

    private final HologramType type;
    /**
     * What leaderboard type this Hologram is displaying?
     */
//    protected final LeaderboardType leaderboardType;
    /**
     * The hologram API information is stored in this object
     * Stuff like Location, name, hologram type etc.
     */
    protected HologramData data;
    /**
     * The update countdown
     */
    public transient int updateIn = 20;
    /**
     * The index of the string which contains the update placeholder
     * We track this so that we don't flicker and constantly update the
     * whole hologram and instead only update the text line of the placeholder
     */
    protected int updateIndex = -1;
    /**
     * We also track the raw string of the update placeholder text line
     * so that we can constantly replace the placeholder on each tick and add
     * a new line each time
     */
    protected transient String updateString;

    /**
     * Spawn the hologram for all players on the server
     * at the given location in the constructor
     */
    public Hologram spawn() {
        Preconditions.checkNotNull(this.data, "Hologram Meta can not be null!");

        Location location = this.data.getLocation();

        HolographicDisplaysAPI api = HolographicDisplaysAPI.get(SkyWarsPlugin.getInstance());
        Hologram hologram = HologramUtil.createHologram(api, location);
        this.data.setHologram(hologram);

        return hologram;
    }

    /**
     * Update the hologram and the leaderboard being displayed
     */
    public abstract void tick();

    /**
     * Update the time placeholder
     */
    public void updateTime() {
        if (this.updateIndex == -1) {
            this.tick();
            return;
        }

        Hologram hologram = this.data.getHologram();
        HologramLines lines = hologram.getLines();

        String replace = Color.translate(updateString
                .replace("<update>", Integer.toString(updateIn))
                .replace("<type>", type.name()));

        lines.remove(updateIndex);
        lines.insertText(updateIndex, replace);
    }
}
