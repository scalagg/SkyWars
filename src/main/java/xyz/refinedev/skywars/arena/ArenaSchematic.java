package xyz.refinedev.skywars.arena;

import com.google.common.base.Preconditions;
import com.sk89q.worldedit.Vector;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.material.MaterialData;
import xyz.refinedev.skywars.game.state.GameType;
import xyz.refinedev.skywars.handler.ArenaHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class ArenaSchematic {

    @Getter private String name;
    @Getter @Setter private String displayName;
    @Setter private boolean enabled = false;
    @Getter @Setter private MaterialData icon = new MaterialData(Material.WOOL);
    @Getter @Setter private int gridIndex;
    @Getter @Setter private GameType gameType;
    @Getter @Setter private int positiveRatings = 0;
    @Getter @Setter private int negativeRatings = 0;

    public ArenaSchematic() {} // for gson

    public ArenaSchematic(String name) {
        this.name = Preconditions.checkNotNull(name, "name");
    }

    public File getSchematicFile() {
        return new File(ArenaHandler.WORLD_EDIT_SCHEMATICS_FOLDER, name + ".schematic");
    }

    public Vector getModelArenaLocation() {
        int xModifier = ArenaGrid.GRID_SPACING_X * gridIndex;

        return new Vector(
            ArenaGrid.STARTING_POINT.getBlockX() - xModifier,
            ArenaGrid.STARTING_POINT.getBlockY(),
            ArenaGrid.STARTING_POINT.getBlockZ()
        );
    }

    public void pasteModelArena() throws Exception {
        Vector start = getModelArenaLocation();
        WorldEditUtils.paste(this, start);
    }

    public void removeModelArena() throws Exception {
        Vector start = getModelArenaLocation();
        Vector size = WorldEditUtils.readSchematicSize(this);

        WorldEditUtils.clear(
            start,
            start.add(size)
        );
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ArenaSchematic && ((ArenaSchematic) o).name.equals(name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public boolean isEnabled() {
        return enabled;
    }
}