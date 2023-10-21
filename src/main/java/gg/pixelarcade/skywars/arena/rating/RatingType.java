package gg.pixelarcade.skywars.arena.rating;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author alfie
 * @project Skywars
 * @date 21/10/2023 / 00:34
 */

@RequiredArgsConstructor
@Getter
public enum RatingType {

    TERRIBLE("&cTerrible"),
    NOT_GOOD("&6Not Good"),
    OKAY("&eOkay"),
    GOOD("&aGood"),
    AMAZING("&2Amazing");

    private final String displayName;

}
