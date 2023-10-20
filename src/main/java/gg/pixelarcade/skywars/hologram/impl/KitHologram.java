//package gg.pixelarcade.skywars.hologram.impl;
//
//import gg.pixelarcade.skywars.kit.Kit;
//import lombok.Getter;
//
//import me.filoghost.holographicdisplays.api.hologram.Hologram;
//import me.filoghost.holographicdisplays.api.hologram.HologramLines;
//
//import gg.pixelarcade.skywars.hologram.TickableHologram;
//import gg.pixelarcade.skywars.hologram.meta.HologramType;
//import xyz.refinedev.practice.kit.Kit;
//import xyz.refinedev.practice.profile.divisions.Division;
//import xyz.refinedev.practice.profile.divisions.DivisionHandler;
//import xyz.refinedev.practice.statistics.StatsHandler;
//import xyz.refinedev.practice.statistics.leaderboard.meta.LeaderboardEntry;
//import xyz.refinedev.practice.statistics.leaderboard.meta.LeaderboardType;
//import xyz.refinedev.practice.utils.chat.CC;
//
///**
// * This Project is property of Refine Development © 2021
// * Redistribution of this Project is not allowed
// *
// * @author Drizzy
// * Created: 9/15/2021
// * Project: PotPvPRP
// */
//
//@Getter
//public class KitHologram extends TickableHologram {
//
//    private transient final Kit kit;
//
//    public KitHologram(Kit kit, LeaderboardType type) {
//        super(HologramType.KIT, type);
//
//        this.kit = kit;
//    }
//
//    /**
//     * Tick the hologram and update the leaderboard being displayed
//     */
//    public void tick() {
//        Hologram hologram = this.data.getHologram();
//        if (this.data.getHologram() == null) {
//            hologram = this.spawn();
//        }
//
//        HologramLines lines = hologram.getLines();
//
//        // Clear previous lines
//        if (lines.size() > 0) lines.clear();
//
//        StatsHandler statsHandler = Bolt.getInstance().getStatsHandler();
//        DivisionHandler divisionHandler = Bolt.getInstance().getDivisionHandler();
//        LeaderboardEntry[] kitLeaderboard = statsHandler.getLeaderboardService().getKitLeaderboard(kit, leaderboardType).getEntries();
//
//        int position = 1;
//
//        for ( String line : SettingsConfig.HOLOGRAM_KIT_LINES ) {
//            if (line.contains("<c>")) {
//                // Let's not cause null pointers
//                if (position >= 11) continue;
//                if (kitLeaderboard == null) continue;
//
//                LeaderboardEntry entry = kitLeaderboard[position - 1];
//                if (entry != null && entry.getName() != null) {
//                    Division division = divisionHandler.getByElo(entry.getGlobalElo());
//                    line = line
//                            .replace("<c>", "")
//                            .replace("<player" + position + ">", entry.getName())
//                            .replace("<value" + position + ">", Integer.toString(entry.getValue()))
//                            .replace("<division" + position + ">", division.getDisplayName());
//
//                } else {
//                    line = line
//                            .replace("<c>", "")
//                            .replace("<player" + position + ">", "TBD")
//                            .replace("<value" + position + ">", "0")
//                            .replace("<division" + position + ">", "&8Unknown");
//
//                }
//
//                position++;
//            }
//
//            if (line.contains("<update>")) {
//                if (this.updateIndex == -1) {
//                    this.updateIndex = lines.size();
//                    this.updateString = line;
//                }
//                line = line
//                        .replace("<update>", Integer.toString(updateIn));
//            }
//
//            lines.appendText(CC.translate(line
//                    .replace("<type>", leaderboardType.getName())
//                    .replace("<kit>", kit.getDisplayName()))
//            );
//        }
//    }
//}
