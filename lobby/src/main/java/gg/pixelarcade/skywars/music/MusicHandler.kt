package gg.pixelarcade.skywars.music

import com.xxmicloxx.NoteBlockAPI.model.Playlist
import com.xxmicloxx.NoteBlockAPI.model.RepeatMode
import com.xxmicloxx.NoteBlockAPI.model.Song
import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder
import gg.pixelarcade.skywars.SkyWarsLobbyPlugin
import gg.pixelarcade.skywars.music.listener.MusicListener

import org.bukkit.Bukkit
import java.io.File

/**
 * @author alfie
 * @project Phoenix-FORK / xyz.refinedev.phoenix.music
 * @date 18/10/2023 / 17:47
 */

class MusicHandler(musicFolder: File) {
    var radioSongPlayer: RadioSongPlayer? = null

    init {
        setupMusic(musicFolder)
        Bukkit.getServer().pluginManager.registerEvents(MusicListener(this), SkyWarsLobbyPlugin.instance)
    }

    private fun setupMusic(musicFolder: File) {
        if (!musicFolder.exists()) musicFolder.mkdirs()

        if (musicFolder.isDirectory) {
            if (musicFolder.listFiles() == null) return

            val songs = ArrayList(getSongsInFolder(musicFolder))


            Bukkit.getLogger().info("Loaded " + songs.size + " songs.")

            radioSongPlayer = RadioSongPlayer(Playlist(*songs.toTypedArray<Song>()))
            radioSongPlayer!!.repeatMode = RepeatMode.ALL
            radioSongPlayer!!.isPlaying = true
            radioSongPlayer!!.isRandom = true
        }
    }

    private fun getSongsInFolder(folder: File): List<Song> {
        val songs: MutableList<Song> = ArrayList()

        for (file in folder.listFiles()) {
            if (file.isDirectory) {
                songs.addAll(getSongsInFolder(file))
                continue
            }

            var song = NBSDecoder.parse(file) ?: continue

            if (song.title.isEmpty() || song.title == null) {
                song = Song(
                    song.speed,
                    song.layerHashMap,
                    song.songHeight,
                    song.length,
                    file.name.replace(".nbs", ""),
                    (if (song.author.isEmpty() || song.author == null) "Aplosh" else song.author),
                    song.originalAuthor,
                    song.description,
                    song.path,
                    song.firstCustomInstrumentIndex,
                    song.customInstruments,
                    song.isStereo
                )
            }

            songs.add(song)
        }

        return songs
    }
}
