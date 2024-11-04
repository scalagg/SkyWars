package gg.pixelarcade.skywars.leaderboard

import gg.pixelarcade.skywars.profile.SkyWarsProfile

import net.evilblock.cubed.ScalaCommonsSpigot
import java.util.*
import java.util.concurrent.CompletableFuture


abstract class Leaderboard(
    /**
     * The id of the leaderboard
     */
    val id: String, val name: String
) {
    private val key: String = "leaderboard:skywars:$id"

    /**
     * Returns the score of the given profile
     *
     * @param profile the profile to get the score of
     * @return the score of the profile
     */
    abstract fun score(profile: SkyWarsProfile): Int

    /**
     * Updates the leaderboard with the given profile
     *
     * @param profile the profile to update
     */
    fun update(profile: SkyWarsProfile) {
        ScalaCommonsSpigot.instance.kvConnection.sync()
            .zadd(key, score(profile).toDouble(), profile.identifier.toString())
    }

    /**
     * Returns the top 10 players on the leaderboard
     *
     * @return the top 10 players
     */
    fun top10(): CompletableFuture<List<UUID>> {
        return CompletableFuture.supplyAsync {
            ScalaCommonsSpigot.instance.kvConnection.sync().zrevrange(key, 0, 9)
                .stream().map { name: String? -> UUID.fromString(name) }.toList()
        }
    }

    /**
     * Returns the top X player on the leaderboard
     *
     * @return the top X player
     */
    fun top(position: Int): CompletableFuture<UUID> {
        return CompletableFuture.supplyAsync {
            val list: List<String> = ScalaCommonsSpigot.instance.kvConnection.sync()
                .zrevrange(key, (position - 1).toLong(), (position - 1).toLong())
            if (list.isEmpty()) {
                return@supplyAsync null
            }
            UUID.fromString(list[0])
        }
    }

    /**
     * Returns the position of the given profile on the leaderboard
     *
     * @param profile the profile to get the position of
     * @return the position of the profile
     */
    fun position(profile: SkyWarsProfile): CompletableFuture<Long> {
        return CompletableFuture.supplyAsync {
            val rank: Long =
                ScalaCommonsSpigot.instance.kvConnection.sync().zrevrank(key, profile.identifier.toString())
                    ?: return@supplyAsync -1L
            rank + 1
        }
    }
}
