package gg.scala.skywars.scoreboard.api.animation;

/**
 * @author alfie
 * @project Skywars / gg.scala.game.scoreboard.api.animation
 * @date 26/09/2023 / 18:20
 */

public abstract class TextAnimation {

    public abstract String[] getCalculatedFrames();

    public String getCurrentFrame() {
        String[] frames = getCalculatedFrames();
        int currentFrame = (int) (System.currentTimeMillis() / 50L % frames.length);
        return frames[currentFrame];
    }

    @Override
    public String toString() {
        return getCurrentFrame();
    }

}
