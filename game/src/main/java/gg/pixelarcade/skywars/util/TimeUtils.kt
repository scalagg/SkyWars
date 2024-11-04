package gg.pixelarcade.skywars.util

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.utils
 * @date 03/10/2023 / 05:45
 */
object TimeUtils {
    private val mmssBuilder: ThreadLocal<java.lang.StringBuilder> = ThreadLocal.withInitial { StringBuilder() }

    fun formatLongIntoHHMMSS(secs: Long): String {
        val unconvertedSeconds = secs.toInt()
        return formatIntoMMSS(unconvertedSeconds)
    }

    @JvmStatic
    fun formatLongIntoMMSS(secs: Long): String {
        val unconvertedSeconds = secs.toInt()
        return formatIntoMMSS(unconvertedSeconds)
    }

    fun formatIntoMMSS(secs: Int): String {
        val seconds = secs % 60
        val minutesCount = ((secs - seconds) / 60).toLong()
        val minutes = minutesCount % 60L
        val hours = (minutesCount - minutes) / 60L
        val result = mmssBuilder.get()
        result.setLength(0)
        if (hours > 0L) {
            if (hours < 10L) {
                result.append("0")
            }
            result.append(hours)
            result.append(":")
        }
        if (minutes < 10L) {
            result.append("0")
        }
        result.append(minutes)
        result.append(":")
        if (seconds < 10) {
            result.append("0")
        }
        result.append(seconds)
        return result.toString()
    }
}
