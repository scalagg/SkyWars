package gg.pixelarcade.skywars.profile;

import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import gg.pixelarcade.skywars.SkyWarsShared;
import gg.pixelarcade.skywars.leveling.enums.LevelEnum;
import lombok.Getter;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PersonalStatsNPCHandler {

    private final static Location NPC_LOCATION = new Location(
            Bukkit.getWorld("world"), -13.5, 100, 8.5, -120f, 10f);
    private final static Location HOLOGRAM_LOCATION = new Location(
            Bukkit.getWorld("world"), -13.5, 104.5, 8.5, -120f, 0f);

    private final Map<UUID, Hologram> holograms;


    private final NPC npc;

    public PersonalStatsNPCHandler() {
        holograms = new HashMap<>();

        npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "&eRIGHT CLICK FOR MORE");
        SkinTrait skin = npc.getOrAddTrait(SkinTrait.class);
        skin.setSkinName("xulvy");
        npc.spawn(NPC_LOCATION);
    }

    public void getMenu(Player player) {
        String playerName = player.getName();
        player.sendMessage(ChatColor.UNDERLINE.toString() + ChatColor.AQUA.toString() + "www.mccade.net/u/"+playerName+"/general");
    }

    public void destroy() {
        npc.destroy();
        this.holograms.forEach((uuid, hologram) -> hologram.destroy());
    }

    public void spawnHologram(Player player) {
        if (holograms.get(player.getUniqueId()) != null) {
            holograms.get(player.getUniqueId()).destroy();
        }

        SkyWarsProfile profile = SkyWarsShared.getInstance().getProfileHandler().getProfile(player.getUniqueId());
        LevelEnum level = profile.getPrestige(profile.getLevel());

        Hologram hologram = DHAPI.createHologram("stats-" + player.getUniqueId().toString(), HOLOGRAM_LOCATION);

        DHAPI.addHologramLine(hologram, "&b&nPersonal Stats");
        DHAPI.addHologramLine(hologram, "");
        DHAPI.addHologramLine(hologram, "&fLevel: " + level.getColor() + profile.getLevel() + " ✯");
        DHAPI.addHologramLine(hologram, "&fProgress: &b" + profile.getXp() + "&7/&a" + level.getXpPerLevel());
        DHAPI.addHologramLine(hologram, "&fWins: &b" + profile.getWins());
        DHAPI.addHologramLine(hologram, "&fKills: &b" + profile.getKills());
        DHAPI.addHologramLine(hologram, "&fDeaths: &b" + profile.getDeaths());
        DHAPI.addHologramLine(hologram, "");
        hologram.setDefaultVisibleState(false);
        hologram.getShowPlayers().add(player.getUniqueId());
        hologram.show(player, 0);
        holograms.put(player.getUniqueId(), hologram);
    }

    public void destroyHologram(Player player) {
        if (holograms.get(player.getUniqueId()) != null) {
            holograms.get(player.getUniqueId()).destroy();
            holograms.remove(player.getUniqueId());
        }
    }
}
