package gg.pixelarcade.skywars.leaderboard;

import gg.pixelarcade.skywars.SkyWarsShared;
import gg.pixelarcade.skywars.leaderboard.impl.LevelLeaderboard;
import gg.pixelarcade.skywars.leaderboard.impl.RankedLeaderboard;
import gg.pixelarcade.skywars.profile.SkyWarsProfile;
import lombok.Getter;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import xyz.refinedev.phoenix.utils.general.Tasks;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Supplier;

public class LeaderboardNPCsHandler {
    private static final SkyWarsShared shared = SkyWarsShared.getInstance();
    private static final LevelLeaderboard GLOBAL_LEADERBOARD
            = shared.getLeaderboardHandler().getLeaderboard(LevelLeaderboard.class);
    private static final RankedLeaderboard RANKED_LEADERBOARD
            = shared.getLeaderboardHandler().getLeaderboard(RankedLeaderboard.class);

    private final Map<Integer, Supplier<CompletableFuture<SkyWarsProfile>>> npcmap;
    private final Map<Integer, Function<String, String>> nameMap;

    private final NPC
            global1, global2, global3,
            ranked1, ranked2, ranked3;

    private NPCUpdateThread thread;

    public LeaderboardNPCsHandler() {
        this.npcmap = new HashMap<>();
        this.nameMap = new HashMap<>();

        this.global1 = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "global1");
        this.global2 = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "global2");
        this.global3 = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "global3");
        this.ranked1 = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "ranked1");
        this.ranked2 = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "ranked2");
        this.ranked3 = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "ranked3");

        this.setupNpcMap();
        this.setupNameMap();


        Tasks.runLater(() -> {
            spawn();
            this.thread = new NPCUpdateThread();
        }, 20L * 5);
    }

    public void spawn() {
        global1.spawn(NPCSpawnLocation.GLOBAL_1.getLocation());
        global2.spawn(NPCSpawnLocation.GLOBAL_2.getLocation());
        global3.spawn(NPCSpawnLocation.GLOBAL_3.getLocation());

        ranked1.spawn(NPCSpawnLocation.RANKED_1.getLocation());
        ranked2.spawn(NPCSpawnLocation.RANKED_2.getLocation());
        ranked3.spawn(NPCSpawnLocation.RANKED_3.getLocation());
    }

    public void destroy() {
        global1.destroy();
        global2.destroy();
        global3.destroy();

        ranked1.destroy();
        ranked2.destroy();
        ranked3.destroy();
        this.thread.interrupt();
    }

    public void update() {
        npcmap.forEach((id, supplier) -> {
            CompletableFuture<SkyWarsProfile> future = supplier.get();
            var profile = future.join();

            NPC npc = CitizensAPI.getNPCRegistry().getById(id);
            SkinTrait skinTrait = npc.getOrAddTrait(SkinTrait.class);

            var name = profile == null ? "Anonymous" : profile.getName();

            if (name != null && !name.equalsIgnoreCase(skinTrait.getSkinName())) {
                Tasks.run(() -> {
                    skinTrait.setSkinName(name, true);

                    if (profile != null) {
                        npc.setName(nameMap.get(id).apply(name));
                    }
                });
            }
        });
    }

    private void setupNameMap() {
        nameMap.put(global1.getId(), (name) -> "&3#1 " + name);
        nameMap.put(global2.getId(), (name) -> "&6#2 " + name);
        nameMap.put(global3.getId(), (name) -> "&7#3 " + name);

        nameMap.put(ranked1.getId(), (name) -> "&3#1 " + name);
        nameMap.put(ranked2.getId(), (name) -> "&6#2 " + name);
        nameMap.put(ranked3.getId(), (name) -> "&7#3 " + name);
    }

    private void setupNpcMap() {
        npcmap.put(global1.getId(), () ->
                GLOBAL_LEADERBOARD.top(1).thenApply(uuid -> shared.getProfileHandler().getProfile(uuid))
        );
        npcmap.put(global2.getId(), () ->
                GLOBAL_LEADERBOARD.top(2).thenApply(uuid -> shared.getProfileHandler().getProfile(uuid))
        );
        npcmap.put(global3.getId(), () ->
                GLOBAL_LEADERBOARD.top(3).thenApply(uuid -> shared.getProfileHandler().getProfile(uuid))
        );

        npcmap.put(ranked1.getId(), () ->
                RANKED_LEADERBOARD.top(1).thenApply(uuid -> shared.getProfileHandler().getProfile(uuid))
        );
        npcmap.put(ranked2.getId(), () ->
                RANKED_LEADERBOARD.top(2).thenApply(uuid -> shared.getProfileHandler().getProfile(uuid))
        );
        npcmap.put(ranked3.getId(), () ->
                RANKED_LEADERBOARD.top(3).thenApply(uuid -> shared.getProfileHandler().getProfile(uuid))
        );
    }

    private class NPCUpdateThread extends Thread {
        public NPCUpdateThread() {
            super("SkyWars - NPC Update Thread");
            this.setDaemon(true);
            this.start();
        }
        public void run() {
            while (!isInterrupted()) {
                update();
                try {
                    Thread.sleep(10_000);
                } catch (InterruptedException ignored) {}
            }
        }
    }

    @Getter
    private enum NPCSpawnLocation {
        GLOBAL_1(new Location(Bukkit.getWorld("world"), -9.5, 102, 45.5, -90f, 0f)),
        GLOBAL_2(new Location(Bukkit.getWorld("world"), -8.5, 101, 47.5, -90f, 0f)),
        GLOBAL_3(new Location(Bukkit.getWorld("world"), -8.5, 100, 43.5, -90f, 0f)),

        RANKED_1(new Location(Bukkit.getWorld("world"), 10.5, 102, 45.5, 90f, 0f)),
        RANKED_2(new Location(Bukkit.getWorld("world"), 9.5, 101, 47.5, 90f, 0f)),
        RANKED_3(new Location(Bukkit.getWorld("world"), 9.5, 100, 43.5, 90f, 0f)),
        ;


        private final Location location;

        NPCSpawnLocation(Location location) {
            this.location = location;
        }
    }
}
