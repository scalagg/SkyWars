package gg.pixelarcade.skywars.utils;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.skywars.utils
 * @date 03/10/2023 / 05:45
 */

public class TimeUtils {

    private static final ThreadLocal<StringBuilder> mmssBuilder = ThreadLocal.withInitial(StringBuilder::new);

    public static String formatLongIntoHHMMSS(long secs) {
        int unconvertedSeconds = (int)secs;
        return TimeUtils.formatIntoMMSS(unconvertedSeconds);
    }

    public static String formatLongIntoMMSS(long secs) {
        int unconvertedSeconds = (int)secs;
        return TimeUtils.formatIntoMMSS(unconvertedSeconds);
    }

    public static String formatIntoMMSS(int secs) {
        int seconds = secs % 60;
        long minutesCount = (secs - seconds) / 60;
        long minutes = minutesCount % 60L;
        long hours = (minutesCount - minutes) / 60L;
        StringBuilder result = mmssBuilder.get();
        result.setLength(0);
        if (hours > 0L) {
            if (hours < 10L) {
                result.append("0");
            }
            result.append(hours);
            result.append(":");
        }
        if (minutes < 10L) {
            result.append("0");
        }
        result.append(minutes);
        result.append(":");
        if (seconds < 10) {
            result.append("0");
        }
        result.append(seconds);
        return result.toString();
    }

}
