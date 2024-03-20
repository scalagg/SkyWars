package gg.pixelarcade.skywars.profile;

import gg.pixelarcade.skywars.SkyWarsShared;
import gg.pixelarcade.skywars.cage.Cage;
import gg.pixelarcade.skywars.game.state.GameTeamType;
import gg.pixelarcade.skywars.leveling.enums.LevelEnum;
import gg.pixelarcade.skywars.perk.Perk;
import gg.pixelarcade.skywars.quests.Quest;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import xyz.refinedev.phoenix.SharedAPI;
import xyz.refinedev.phoenix.utils.Exclude;

import java.util.concurrent.CompletableFuture;
import java.util.*;

@Data
public class SkyWarsProfile {

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

    private List<String> ownedKits = new ArrayList<>();
    private List<String> ownedPerks = new ArrayList<>();
    private List<String> selectedPerks = new ArrayList<>();

    private Map<Long, String> dailyQuests = new HashMap<>();
    private Map<GameTeamType, Cage> selectedCages = new HashMap<>();
    private Map<String, Integer> achievements = new HashMap<>();

    private boolean canRate = false;
    private List<String> arenasRated = new ArrayList<>();

    public void load() {
        SkyWarsShared.getInstance().getProfileHandler().getFromDatabase(uniqueId).whenComplete((profile, error) -> {
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
            this.ownedKits = profile.getOwnedKits();
            this.ownedPerks = profile.getOwnedPerks();
            this.selectedPerks = profile.getSelectedPerks();
            this.arenasRated = profile.getArenasRated();
            this.achievements = profile.getAchievements() == null ? new HashMap<>() : profile.getAchievements();
            this.canRate = profile.isCanRate();
            this.dailyQuests = (profile.getDailyQuests() == null ? new HashMap<>() : profile.getDailyQuests());
            this.selectedCages = profile.getSelectedCages();
        });
    }

    public void save() {
        SkyWarsShared.getInstance().getProfileHandler().saveToDatabase(uniqueId, this);
    }

    public LevelEnum getPrestige(int level) {
        LevelEnum prestige = null;

        // Iterate through the list of enums to find the one that fits the player's level
        for (LevelEnum prestiges : LevelEnum.values()) {
            if (level >= prestiges.getMinLevel()) {
                prestige = prestiges;
            }
        }

        return prestige;
    }

    public String getXPBar(LevelEnum prestige) {

        //-- Variables
        StringBuilder xpBar = new StringBuilder();
        double percentage = ((double) xp / prestige.getXpPerLevel()) * 100;
        int progress = (int) (Math.floor(percentage) / 10);

        //-- Progress
        xpBar.append("  &7[&e");
        xpBar.append("●".repeat(Math.max(0, progress)));
        xpBar.append("&7");
        xpBar.append("●".repeat(Math.max(0, 10 - progress + 1)));
        xpBar.append("] ");

        //-- Percentage to next level
        xpBar.append(Math.round(percentage)).append("%");

        return xpBar.toString();
    }

    public void findNewDailyQuest() {
        dailyQuests.clear();

        Quest newQuest = SkyWarsShared.getInstance().getQuestHandler().getRandomQuest();
        dailyQuests.put(System.currentTimeMillis(), newQuest.getId());

        this.save();
    }

    public void incrementCoins(int amount) {
        this.coins += amount;
        this.save();
    }

    public void incrementLevel() {
        level ++;
        this.save();
    }

    public void setCage(GameTeamType teamType, Cage cage) {
        selectedCages.put(teamType, cage);
        this.save();
    }

    public void removeCage(GameTeamType teamType) {
        selectedCages.remove(teamType);
        this.save();
    }

    public Cage getCurrentCage(GameTeamType teamType) {
        return selectedCages.get(teamType);
    }

    public void incrementXP(int amount) {
        int newXP = xp + amount;
        LevelEnum prestige = getPrestige(level);

        while (newXP >= prestige.getXpPerLevel()) {
            incrementLevel();
            prestige = getPrestige(level);
            newXP -= prestige.getXpPerLevel();
            if (newXP >= prestige.getXpPerLevel()) {
                newXP -= prestige.getXpPerLevel();
            }
        }

        xp = newXP;
        this.save();
    }

    public void incrementKills() {
        kills++;
        killStreak++;

        if (killStreak > highestKillStreak)
            highestKillStreak = killStreak;

        this.save();
    }

    public void incrementDeaths() {
        killStreak = 0;
        deaths++;
        this.save();
    }

    public void incrementElo(int amount) {
        this.elo += amount;
        this.save();
    }

    public void incrementWins() {
        this.wins++;
        this.save();
    }

    public void incrementLosses() {
        this.losses++;
        this.save();
    }

    public void addSouls(int amount) {
        this.souls += amount;
        this.save();
    }

    public void addHeads(int amount) {
        this.heads += amount;
        this.save();
    }

    public void removeSouls(int amount) {
        this.souls -= amount;
        this.save();
    }

    public void addSelectedPerk(Perk perk){

        // if selectedperks != null
        List<String> newSelectedPerks = new ArrayList<>(this.selectedPerks);
        newSelectedPerks.add(perk.getName());
        this.selectedPerks = newSelectedPerks;
        this.save();
    }

    public String getNiceName() {
        return SharedAPI.getInstance().getProfileHandler().getProfile(uniqueId).getNameWithColor();
    }

    public int calculateFinalRating(int currentRating, boolean wonGame, int kills) {
        int ratingGain = calculateRatingGain(currentRating);
        int ratingChange = calculateRatingChange(currentRating, wonGame, kills);
        return currentRating + (wonGame ? ratingGain : ratingChange);
    }

    /**
     * This method calculates the rating change based on the current rating, game result, and kills.
     *
     * @param elo The current rating of the player.
     * @param wonGame A boolean indicating whether the game was won.
     * @param kills The number of kills the player achieved in the game.
     * @return The rating change. If the game was won or the player achieved 2 or more kills, the change is 0.
     *         If the current rating is less than 1000 and the game was lost with less than 2 kills, the change is -1.
     *         If the current rating is 1000 or more and the game was lost with less than 2 kills, the change is -10.
     */
    private int calculateRatingChange(int elo, boolean wonGame, int kills) {
        // TODO: I do not know if you want this to be a random number between two numbers
        //  or if you want it to be a set amount - Alfie
        if (wonGame || kills >= 2)
            return 0;

        if (elo < 1000)
            return -1;

        return -10;
    }

    /**
     * This method calculates the rating gain based on the current rating.
     *
     * @param rating The current rating of the player.
     * @return The rating gain. If the current rating is less than 1000, the gain is 30.
     *         If the current rating is 2000 or more, the gain is 10.
     *         Otherwise, the gain is determined by the RATING_GAINS array.
     */
    private int calculateRatingGain(int rating) {
        if (rating < 1000)
            return 30;

        int index = (rating - 1000) / 100;
        if (index >= RATING_GAINS.length)
            return 10;

        return RATING_GAINS[RATING_GAINS.length - 1 - index];
    }

    private static final int[] RATING_GAINS = {10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30};

}
