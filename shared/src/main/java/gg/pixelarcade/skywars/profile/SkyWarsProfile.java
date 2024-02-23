package gg.pixelarcade.skywars.profile;

import gg.pixelarcade.skywars.SkyWarsShared;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import xyz.refinedev.phoenix.SharedAPI;
import xyz.refinedev.phoenix.utils.Exclude;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Getter
@Setter
@RequiredArgsConstructor
public class SkyWarsProfile {

    @Exclude
    private final SkyWarsShared shared = SkyWarsShared.getInstance();
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

    public void load() {
        shared.getProfileHandler().getFromDatabase(uniqueId).whenComplete((profile, error) -> {
            if (error != null) {
                error.printStackTrace();
                return;
            }

            if (profile == null) {
                this.save();
                return;
            }

            this.name = profile.getName();
            this.selectedKit = profile.getSelectedKit();
            this.kills = profile.getKills();
            this.deaths = profile.getDeaths();
            this.killStreak = profile.getKillStreak();
            this.highestKillStreak = profile.getHighestKillStreak();
            this.wins = profile.getWins();
            this.losses = profile.getLosses();
            this.elo = profile.getElo();
            this.heads = profile.getHeads();
            this.souls = profile.getSouls();
            this.arenasRated = profile.getArenasRated();
        });
    }

    public void save() {
        shared.getProfileHandler().saveToDatabase(uniqueId, this);
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
