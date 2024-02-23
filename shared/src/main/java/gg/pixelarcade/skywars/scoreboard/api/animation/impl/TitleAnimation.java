package gg.pixelarcade.skywars.scoreboard.api.animation.impl;

import com.google.common.base.Strings;
import gg.pixelarcade.skywars.scoreboard.api.animation.TextAnimation;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.scoreboard.api.animation.impl
 * @date 26/09/2023 / 18:21
 */

@AllArgsConstructor
public final class TitleAnimation extends TextAnimation {

    private final String title;
    private final String primaryColor;
    private final String secondaryColor;
    private final String pulseColor;

    @Override
    public String[] getCalculatedFrames() {
        List<String> frames = new ArrayList<>(Collections.nCopies(30, primaryColor + title));

        StringBuilder base = new StringBuilder(String.valueOf(primaryColor));

        int length = title.length();
        for (int index = 0; index < length; index++) {
            char character = title.charAt(index);

            int diff = (length - index - 1);
            for (int left = diff; left >= 0; left--) {
                frames.add(base + Strings.repeat(" ", left) + secondaryColor + character + Strings.repeat(" ", diff - left));

                if (left <= 0) {
                    base.append(character);
                }
            }
        }

        for (int i = 0; i < 2; i++) {
            int delay = 8;
            frames.addAll(Collections.nCopies(delay, secondaryColor + title));
            frames.addAll(Collections.nCopies(delay, pulseColor + title));
        }

        frames.addAll(Collections.nCopies(30, primaryColor + title));
        return frames.toArray(new String[0]);
    }
}