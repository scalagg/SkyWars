package xyz.refinedev.skywars.profile;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import xyz.refinedev.phoenix.utils.Exclude;
import xyz.refinedev.skywars.achievement.Achievement;
import xyz.refinedev.skywars.game.state.PlayerState;
import xyz.refinedev.skywars.pxSkyWars;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * @author Creaxx
 * Created At: 5/8/2023
 */

@Getter
@Setter
@RequiredArgsConstructor
public class SkyWarsProfile {

    @Exclude
    private final pxSkyWars plugin = pxSkyWars.getInstance();

    private final UUID uniqueId;

    private String name;
    private String lowerCaseName;

    private int kills = 0;
    private int deaths = 0;
    private int wins = 0;
    private int losses = 0;
    private int killStreak = 0;
    private int highestKillStreak = 0;
    private int elo = 0;
    private int heads = 0;
    private int souls = 0;

    private List<Achievement> ownedAchievements = new ArrayList<>();

    @Exclude public PlayerState state = PlayerState.ALIVE;

    public void load() {
        CompletableFuture.runAsync(() -> {
            SkyWarsProfile profile = plugin.getProfileHandler().getFromDatabaseSync(uniqueId);

            if (profile == null) {
                save();
                return;
            }

            this.name = profile.getName();
            this.lowerCaseName = profile.getLowerCaseName();
            this.kills = profile.getKills();
            this.deaths = profile.getDeaths();
            this.killStreak = profile.getKillStreak();
            this.highestKillStreak = profile.getHighestKillStreak();
            this.wins = profile.getWins();
            this.losses = profile.getLosses();
            this.elo = profile.getElo();
            this.heads = profile.getHeads();
            this.souls = profile.getSouls();
            this.ownedAchievements = profile.getOwnedAchievements();
        });
    }

    public void save() {
        pxSkyWars.getInstance().getProfileHandler().saveToDatabase(uniqueId, this);
    }

    public void incrementKills() {
        this.kills++;
        this.killStreak++;
        if (this.killStreak > this.highestKillStreak) {
            this.highestKillStreak = this.killStreak;
        }
    }

    public void incrementDeaths() {
        this.killStreak = 0;
        this.deaths++;
    }

    public void setName(String name) {
        this.name = name;
        this.lowerCaseName = name.toLowerCase();
    }

    public void addSouls(int amount) {
        this.souls += amount;
    }

    public void removeSouls(int amount) {
        this.souls -= amount;
    }
}
