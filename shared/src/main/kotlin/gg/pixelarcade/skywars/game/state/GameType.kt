package gg.pixelarcade.skywars.game.state

import lombok.Getter
import lombok.RequiredArgsConstructor

/**
 * @author puugz
 * @since Mar 01, 2024
 */

enum class GameType(val displayName: String) {
    NORMAL("Normal"),
    INSANE("Insane"),
    RANKED("Ranked");

}
