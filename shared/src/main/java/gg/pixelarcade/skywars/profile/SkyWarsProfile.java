package gg.pixelarcade.skywars.profile;

import gg.pixelarcade.skywars.SkyWarsShared;
import gg.pixelarcade.skywars.leveling.enums.LevelEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.ChatColor;
import xyz.refinedev.phoenix.SharedAPI;
import xyz.refinedev.phoenix.utils.Exclude;

import java.util.ArrayList;
import java.util.Arrays;
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

    private int level = 0;
    private int xp = 0;
    private int coins = 0;
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
            this.level = profile.getLevel();
            this.xp = profile.getXp();
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

    public LevelEnum getPrestige(int level){
        LevelEnum prestige = null;

        // Iterate through the list of enums to find the one that fits the player's level
        for (LevelEnum prestiges : LevelEnum.values()){
            if (level >= prestiges.getMinLevel()){
                prestige = prestiges;
            }
        }

        return prestige;
    }

    public String getXPBar(LevelEnum prestige){

        //-- Variables
        StringBuilder xpBar = new StringBuilder();
        double percentage = ((double) xp / prestige.getXpPerLevel()) * 100;
        int progress = (int) (Math.floor(percentage)/10);

        //-- Progress
        xpBar.append("  &7[&e");
        for (int i = 0; i < progress; i++){
            xpBar.append("♚");
        }
        xpBar.append("&7");
        for (int i = 0; i <= 10 - progress; i++){
            xpBar.append("♚");
        }
        xpBar.append("] ");

        //-- Percentage to next level
        xpBar.append(Math.round(percentage)).append("%");

        return xpBar.toString();
    }

    public void incrementCoins(int amount) {
        this.coins += amount;
    }

    public void incrementLevel() {
        this.level++;
    }

    public void incrementXP(int amount) {

        int newXP = xp + amount;
        LevelEnum prestige = getPrestige(level);

        // If the new xp is higher than the minimum amount to level up
        if (newXP >= prestige.getXpPerLevel()){
            incrementLevel();
            xp = newXP - prestige.getXpPerLevel();
        } else xp = newXP;
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
