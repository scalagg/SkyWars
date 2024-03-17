package gg.pixelarcade.skywars.listeners;

import gg.pixelarcade.skywars.SkyWarsLobby;
import gg.pixelarcade.skywars.SkyWarsLobbyPlugin;
import gg.pixelarcade.skywars.SkyWarsShared;
import gg.pixelarcade.skywars.profile.SkyWarsProfile;
import gg.pixelarcade.skywars.queue.QueueNPCsHandler;
import lombok.RequiredArgsConstructor;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import xyz.refinedev.phoenix.utils.general.Tasks;
import org.bukkit.metadata.MetadataValue;

import java.util.List;

/**
 * @author alfie
 * @project SkyWars
 * @date 27/10/2023 / 02:50
 */

@RequiredArgsConstructor
public class LobbyListener implements Listener {

    private final SkyWarsLobby plugin;

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent e) {
        plugin.giveItems(e.getPlayer());
        SkyWarsProfile profile = SkyWarsShared.getInstance().getProfileHandler().getProfile(e.getPlayer().getUniqueId());
        SkyWarsShared.getInstance().getLeaderboardHandler().getLeaderboards().values()
                .forEach(leaderboard -> leaderboard.update(profile));
        Tasks.runLater(() -> SkyWarsLobby.getLobbyInstance().getPersonalStatsNPCHandler().spawnHologram(e.getPlayer()), 10L);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        SkyWarsLobby.getLobbyInstance().getPersonalStatsNPCHandler().destroyHologram(event.getPlayer());
    }


    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        Player player;

        if (event.getEntity() instanceof Player){
            player = (Player) event.getEntity();
        } else {
            return;
        }

        if (event.getCause() == EntityDamageEvent.DamageCause.VOID){
            event.setCancelled(true);
            player.teleport(SkyWarsLobbyPlugin.getInstance().getServer().getWorld("world").getSpawnLocation());
        }

    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerInteract(NPCRightClickEvent event) {
        Player player = event.getClicker();
        NPC npc = event.getNPC();

        SkyWarsLobby.getLobbyInstance().getQueueNPCsHandler().getMenu(npc, player);
    }
}