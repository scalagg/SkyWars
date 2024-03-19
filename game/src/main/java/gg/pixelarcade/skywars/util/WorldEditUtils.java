package gg.pixelarcade.skywars.util;

import com.boydti.fawe.util.EditSessionBuilder;
import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.blocks.BaseBlock;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.schematic.SchematicFormat;
import com.sk89q.worldedit.world.World;
import gg.pixelarcade.skywars.util.cuboid.Cuboid;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

import java.io.File;
import java.io.IOException;

/**
 * @author alfie
 * @project SkyWars
 * @date 18/03/2024 / 21:55
 */

@UtilityClass
public class WorldEditUtils {

    private static EditSession EDIT_SESSION;
    private static World WORLD_EDIT_WORLD;

    public static void initWorldEdit() {
        if (EDIT_SESSION != null)
            return;

        WORLD_EDIT_WORLD = new BukkitWorld(Bukkit.getWorld("world"));
        EDIT_SESSION = new EditSessionBuilder(WORLD_EDIT_WORLD)
                .allowedRegionsEverywhere()
                .limitUnlimited()
                .autoQueue(true)
                .fastmode(true)
                .build();
    }

    public static CuboidClipboard pasteSchematic(File schematicFile, Vector pasteAt) throws Exception {
        initWorldEdit();

        CuboidClipboard clipboard = SchematicFormat.MCEDIT.load(schematicFile);
        clipboard.setOffset(new Vector(0, 0, 0));
        clipboard.paste(EDIT_SESSION, pasteAt, true);

        return clipboard;
    }

    public static void clear(Cuboid bounds) {
        clear(
                new Vector(bounds.getLowerX(), bounds.getLowerY(), bounds.getLowerZ()),
                new Vector(bounds.getUpperX(), bounds.getUpperY(), bounds.getUpperZ())
        );
    }

    public static void clear(Vector lower, Vector upper) {
        initWorldEdit();

        BaseBlock air = new BaseBlock(Material.AIR.getId());
        Region region = new CuboidRegion(WORLD_EDIT_WORLD, lower, upper);

        try {
            EDIT_SESSION.setBlocks(region, air);
        } catch (MaxChangedBlocksException ex) {
            // our block change limit is Integer.MAX_VALUE, so will never
            // have to worry about this happening
            throw new RuntimeException(ex);
        }
    }

    public Vector getVectorByLocation(Location location) {
        return new Vector(location.getX(), location.getY(), location.getZ());
    }

}
