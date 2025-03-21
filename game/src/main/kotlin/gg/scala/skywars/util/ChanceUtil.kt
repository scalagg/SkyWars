package gg.scala.skywars.util

import java.util.concurrent.ThreadLocalRandom

/**
 * @author alfie
 * @project Skywars / gg.scala.game.utils
 * @date 06/10/2023 / 02:46
 */
object ChanceUtil {
    fun percent(chance: Int): Boolean {
        return ThreadLocalRandom.current().nextInt(100) >= 100 - chance
    }

    @JvmStatic
    fun percent(chance: Double): Boolean {
        return ThreadLocalRandom.current().nextDouble(100.0) < chance
    }
}
