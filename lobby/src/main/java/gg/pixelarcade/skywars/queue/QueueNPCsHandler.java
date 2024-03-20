package gg.pixelarcade.skywars.queue;

import gg.pixelarcade.skywars.SkyWarsLobby;
import gg.pixelarcade.skywars.game.state.GameTeamType;
import gg.pixelarcade.skywars.menus.GameModeSelectorMenu;
import lombok.Getter;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import xyz.refinedev.phoenix.Phoenix;

import java.util.ArrayList;
import java.util.List;

public class QueueNPCsHandler {

    //-- Instance Variables
    private final NPC experimental, solo, doubles;
    @Getter
    private final List<NPC> npcs = new ArrayList<>();

    //-- Constructor
    public QueueNPCsHandler() {

        // Set the names of all the NPCs
        this.experimental = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, (ChatColor.AQUA + "Experimental" + ChatColor.RED + " Coming Soon"));
        this.solo = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, (ChatColor.AQUA + "Solo " + ChatColor.GRAY + "[Normal/Insane/Ranked]"));
        this.doubles = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, (ChatColor.AQUA + "Doubles " + ChatColor.GRAY + "[Normal/Insane]"));

        setSkins();

        // Add these NPCs to the npcs list for later use
        npcs.add(experimental);
        npcs.add(solo);
        npcs.add(doubles);

        spawn();
    }

    public void destroy() {
        experimental.destroy();
        solo.destroy();
        doubles.destroy();
    }

    public void getMenu(NPC npc, Player player) {
        if (npc.equals(experimental)){
            player.sendMessage(ChatColor.RED + "Sorry, this gamemode isn't out yet!");
        }
        if (npc.equals(solo)) {
            Phoenix.getInstance().getMenuHandler().openMenu(new GameModeSelectorMenu(GameTeamType.SOLO), player);
        }
        if (npc.equals(doubles)) {
            Phoenix.getInstance().getMenuHandler().openMenu(new GameModeSelectorMenu(GameTeamType.DOUBLES), player);
        }
    }

    //-- Spawn in the NPCs
    private void spawn() {
        experimental.spawn(NPCSpawnLocation.SPAWN_3.getLocation());
        solo.spawn(NPCSpawnLocation.SPAWN_2.getLocation());
        doubles.spawn(NPCSpawnLocation.SPAWN_1.getLocation());
    }


    private void setSkins() {
        SkinTrait skinTrait1 = experimental.getOrAddTrait(SkinTrait.class);
        SkinTrait skinTrait2 = solo.getOrAddTrait(SkinTrait.class);
        SkinTrait skinTrait3 = doubles.getOrAddTrait(SkinTrait.class);

        skinTrait1.setSkinName("Wuusty__", true);
        skinTrait2.setSkinName("KuudraStun", true);
        skinTrait3.setSkinName("luanpereiradev2", true);
    }

    @Getter
    private enum NPCSpawnLocation {

        SPAWN_1(new Location(Bukkit.getWorld("world"), 6.5, 100, 53.5, 180f, 0f)),
        SPAWN_2(new Location(Bukkit.getWorld("world"), 0.5, 100, 55.5, 180f, 0f)),
        SPAWN_3(new Location(Bukkit.getWorld("world"), -5.5, 100, 53.5, 180f, 0f));

        private final Location location;

        NPCSpawnLocation(Location location) {
            this.location = location;
        }
    }
}
