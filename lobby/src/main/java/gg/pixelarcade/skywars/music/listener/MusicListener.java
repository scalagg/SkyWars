package gg.pixelarcade.skywars.music.listener;

import com.xxmicloxx.NoteBlockAPI.event.SongNextEvent;
import com.xxmicloxx.NoteBlockAPI.songplayer.SongPlayer;
import gg.pixelarcade.skywars.music.MusicHandler;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.vehicle.VehicleBlockCollisionEvent;
import xyz.refinedev.phoenix.utils.Color;

import java.util.UUID;

/**
 * @author alfie
 * @project Phoenix-FORK / xyz.refinedev.phoenix.music.listener
 * @date 18/10/2023 / 17:55
 */

@RequiredArgsConstructor
public class MusicListener implements Listener {

    private final MusicHandler handler;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        handler.getRadioSongPlayer().addPlayer(event.getPlayer().getUniqueId());

        event.getPlayer().sendActionBar(Color.translate("&6&lPixel Radio &7- &fCurrent Song: &e" + handler.getRadioSongPlayer().getSong().getTitle()));
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerQuit(PlayerQuitEvent event) {
        handler.getRadioSongPlayer().removePlayer(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onSongEnd(SongNextEvent event) {
        SongPlayer songPlayer = event.getSongPlayer();
        if (songPlayer == null)
            return;

        for (UUID uuid : songPlayer.getPlayerUUIDs()) {
            Player player = Bukkit.getPlayer(uuid);
            if (player == null)
                continue;

            player.sendActionBar(Color.translate("&6&lPixel Radio &7- &fNext Song: &e" + event.getSongPlayer().getSong().getTitle()));
        }
    }


}
