package gg.pixelarcade.skywars.profile;

import com.google.gson.reflect.TypeToken;
import gg.pixelarcade.skywars.SkyWarsPlugin;
import gg.pixelarcade.skywars.achievement.Achievement;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import xyz.refinedev.lib.bson.Document;
import xyz.refinedev.phoenix.SharedAPI;
import xyz.refinedev.phoenix.utils.Exclude;

import java.lang.reflect.Type;
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
    private final SkyWarsPlugin plugin = SkyWarsPlugin.getInstance();
    private final UUID uniqueId;

    private String name;
    private String selectedKit = "default";

    private int kills = 0;
    private int deaths = 0;
    private int wins = 0;
    private int losses = 0;
    private int killStreak = 0;
    private int highestKillStreak = 0;
    private int elo = 0;
    private int heads = 0;
    private int souls = 0;

    private boolean canRate = false;
    private List<String> arenasRated = new ArrayList<>();

    private List<Achievement> ownedAchievements = new ArrayList<>();

    public void load() {
        CompletableFuture.runAsync(() -> {
            SkyWarsProfile profile = plugin.getProfileHandler().getFromDatabaseSync(uniqueId);

            if (profile == null) {
                save();
                return;
            }

            name = profile.getName();
            selectedKit = profile.getSelectedKit();
            kills = profile.getKills();
            deaths = profile.getDeaths();
            killStreak = profile.getKillStreak();
            highestKillStreak = profile.getHighestKillStreak();
            wins = profile.getWins();
            losses = profile.getLosses();
            elo = profile.getElo();
            heads = profile.getHeads();
            souls = profile.getSouls();
            ownedAchievements = profile.getOwnedAchievements();
            arenasRated = profile.getArenasRated();
        });
    }

    public void save() {
        SkyWarsPlugin.getInstance().getProfileHandler().saveToDatabase(uniqueId, this);
    }

    public void incrementKills() {
        kills++;
        killStreak++;

        if (killStreak > highestKillStreak)
            highestKillStreak = killStreak;
    }

    public void incrementDeaths() {
        killStreak = 0;
        deaths++;
    }

    public void incrementWins() {
        this.wins++;
    }

    public void incrementLosses() {
        this.losses++;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addSouls(int amount) {
        this.souls += amount;
    }

    public void addHeads(int amount) {
        this.heads += amount;
    }

    public void removeSouls(int amount) {
        this.souls -= amount;
    }

    public String getNiceName() {
        return SharedAPI.getInstance().getProfileHandler().getProfile(uniqueId).getNameWithColor();
    }
}
