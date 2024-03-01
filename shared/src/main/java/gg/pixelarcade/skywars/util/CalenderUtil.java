package gg.pixelarcade.skywars.util;

import java.util.Calendar;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.utils
 * @date 28/09/2023 / 18:26
 */

public class CalenderUtil {

    private static final Calendar calendar = Calendar.getInstance();

    public static boolean isWeekend() {
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        return day == Calendar.SATURDAY || day == Calendar.SUNDAY;
    }

}
