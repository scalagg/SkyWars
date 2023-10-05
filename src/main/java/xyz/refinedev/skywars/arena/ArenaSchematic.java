package xyz.refinedev.skywars.arena;

import com.boydti.fawe.util.EditSessionBuilder;
import com.google.common.base.Preconditions;
import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;
import com.sk89q.worldedit.schematic.SchematicFormat;
import lombok.Data;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;
import org.bukkit.metadata.FixedMetadataValue;
import xyz.refinedev.skywars.arena.listener.ArenaModelEditorListener;
import xyz.refinedev.skywars.game.state.GameType;
import xyz.refinedev.skywars.pxSkyWars;
import xyz.refinedev.skywars.utils.cuboid.Cuboid;

import java.io.File;

@Data
public final class ArenaSchematic {

    private String name;
    private String displayName;

    private MaterialData icon = new MaterialData(Material.WOOL);
    private GameType gameType;

    private boolean enabled = false;
    private boolean supportsRanked = false;

    private int minPlayers = 12;
    private int maxPlayers = 24;

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

    public Vector getArenaLocation() {
        return new Vector(0, 80, 0);
    }

    public static Location vectorToLocation(Vector vector, World world) {
        return new Location(
                world,
                vector.getBlockX(),
                vector.getBlockY(),
                vector.getBlockZ()
        );
    }


    @SuppressWarnings("deprecation")
    public Cuboid pasteArena(World world) {
        try {
            Vector pasteAt = getArenaLocation();
            com.sk89q.worldedit.world.World weWorld = new BukkitWorld(world);
            EditSession editSession = new EditSessionBuilder(weWorld).allowedRegionsEverywhere().fastmode(true).limitUnlimited().build();

            CuboidClipboard clipboard = SchematicFormat.MCEDIT.load(getSchematicFile());

            // systems like the ArenaGrid assume that pastes will 'begin' directly at the Vector
            // provided. to ensure we can do this, we manually clear any offset (distance from
            // corner of schematic to player) to ensure our pastes aren't dependant on the
            // location of the player when copied
            clipboard.setOffset(new Vector(0, 0, 0));
            clipboard.paste(editSession, pasteAt, true);

            return new Cuboid(
                    vectorToLocation(pasteAt, world),
                    vectorToLocation(pasteAt.add(clipboard.getSize()), world)
            );
        } catch (Exception e) {
            Bukkit.getLogger().severe("Failed to load schematic: " + getName());
            e.printStackTrace();
            return null;
        }
    }

    public void launchEditor(Player player, Cuboid bounds) {
        player.teleport(bounds.getCenter());
        player.setGameMode(GameMode.CREATIVE);
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.setAllowFlight(true);
        player.setFlying(true);
        player.sendMessage(ChatColor.GREEN + "You are editing "+this.getName()+".");
        player.getInventory().setItem(8, ArenaModelEditorListener.exitItem);
        player.setMetadata("ModelEditor", new FixedMetadataValue(pxSkyWars.getInstance(), true));
        player.setMetadata("Model", new FixedMetadataValue(pxSkyWars.getInstance(), this));
        player.setMetadata("Build", new FixedMetadataValue(pxSkyWars.getInstance(), true));
    }

    public Vector readSchematicSize() throws Exception {
        return SchematicFormat.MCEDIT.load(getSchematicFile()).getSize();
    }

    @SuppressWarnings("deprecation")
    public boolean save(World world, Player player) throws Exception {
        WorldEditPlugin worldEdit = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
        Selection selection = worldEdit.getSelection(player);

        if (selection == null) {
            player.sendMessage(ChatColor.RED + "You must have a selection to save a schematic.");
            return false;
        }

        Location min = selection.getMinimumPoint();
        Location max = selection.getMaximumPoint();

        CuboidClipboard newSchematic = new CuboidClipboard(
                new Vector(min.getX(), min.getY(), min.getZ()),
                new Vector(max.getX(), max.getY(), max.getZ())
        );

        newSchematic.copy(new EditSessionBuilder(new BukkitWorld(world)).build());

        SchematicFormat.MCEDIT.save(newSchematic, getSchematicFile());

        return true;
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