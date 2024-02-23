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
import java.util.List;

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

    private void setupMusic(File musicFolder) {
        if (!musicFolder.exists())
            musicFolder.mkdirs();

        if (musicFolder.isDirectory()) {
            if (musicFolder.listFiles() == null)
                return;

            ArrayList<Song> songs = new ArrayList<>(getSongsInFolder(musicFolder));

            Bukkit.getLogger().info("Loaded " + songs.size() + " songs.");

            radioSongPlayer = new RadioSongPlayer(new Playlist(songs.toArray(new Song[0])));
            radioSongPlayer.setRepeatMode(RepeatMode.ALL);
            radioSongPlayer.setPlaying(true);
            radioSongPlayer.setRandom(true);
        }
    }

    private List<Song> getSongsInFolder(File folder) {
        List<Song> songs = new ArrayList<>();

        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                songs.addAll(getSongsInFolder(file));
                continue;
            }

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

        return songs;
    }
}
