package xyz.refinedev.skywars.arena;

import com.google.common.base.Preconditions;
import com.sk89q.worldedit.Vector;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.material.MaterialData;
import xyz.refinedev.skywars.game.state.GameType;
import xyz.refinedev.skywars.handler.ArenaHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Data
public final class ArenaSchematic {

    private String name;
    private String displayName;

    private MaterialData icon = new MaterialData(Material.WOOL);
    private GameType gameType;

    private boolean enabled = false;

    private int gridIndex;
    private int positiveRatings = 0;
    private int negativeRatings = 0;

    public ArenaSchematic() {

    }

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

}