package gg.pixelarcade.skywars.music;

import com.xxmicloxx.NoteBlockAPI.model.Playlist;
import com.xxmicloxx.NoteBlockAPI.model.RepeatMode;
import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
import gg.pixelarcade.skywars.music.listener.MusicListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import xyz.refinedev.phoenix.Phoenix;

import java.io.File;
import java.util.ArrayList;

/**
 * @author alfie
 * @project Phoenix-FORK / xyz.refinedev.phoenix.music
 * @date 18/10/2023 / 17:47
 */

@Getter
public class MusicHandler {

    private RadioSongPlayer radioSongPlayer;

    public MusicHandler(File musicFolder) {
        setupMusic(musicFolder);
        Phoenix.getInstance().getServer().getPluginManager().registerEvents(new MusicListener(this), Phoenix.getInstance());
    }

    public void setupMusic(File musicFolder) {
        if (!musicFolder.exists())
            musicFolder.mkdirs();

        if (musicFolder.isDirectory()) {
            ArrayList<Song> songs = new ArrayList<>();

            if (musicFolder.listFiles() == null)
                return;

            for (File file : musicFolder.listFiles()) {
                if (file.isDirectory())
                    continue;

                Song song = NBSDecoder.parse(file);
                if (song == null)
                    continue;

                if (song.getTitle().isEmpty() || song.getTitle() == null) {
                    song = new Song(
                            song.getSpeed(),
                            song.getLayerHashMap(),
                            song.getSongHeight(),
                            song.getLength(),
                            file.getName().replace(".nbs", ""),
                            (song.getAuthor().isEmpty() || song.getAuthor() == null ? "Aplosh" : song.getAuthor()),
                            song.getOriginalAuthor(),
                            song.getDescription(),
                            song.getPath(),
                            song.getFirstCustomInstrumentIndex(),
                            song.getCustomInstruments(),
                            song.isStereo()
                    );
                }

                songs.add(song);
            }

            Bukkit.getLogger().info("Loaded " + songs.size() + " songs.");

            radioSongPlayer = new RadioSongPlayer(new Playlist(songs.toArray(new Song[0])));
            radioSongPlayer.setRepeatMode(RepeatMode.ALL);
            radioSongPlayer.setPlaying(true);
            radioSongPlayer.setRandom(true);
        }
    }
}
