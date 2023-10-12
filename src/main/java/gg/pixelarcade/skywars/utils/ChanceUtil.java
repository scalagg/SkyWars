package gg.pixelarcade.skywars.utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author alfie
 * @project Skywars / xyz.refinedev.skywars.utils
 * @date 06/10/2023 / 02:46
 */

public class ChanceUtil {

    public static boolean percent(int chance) {
        return ThreadLocalRandom.current().nextInt(100) >= 100 - chance;
    }

    public static boolean percent(double chance) {
        return ThreadLocalRandom.current().nextDouble(100.0) < chance;
    }

}
