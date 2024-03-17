package gg.pixelarcade.skywars.leaderboard;

import gg.pixelarcade.skywars.profile.SkyWarsProfile;
import lombok.Getter;
import xyz.refinedev.phoenix.SharedAPI;
import xyz.refinedev.phoenix.handler.DatabaseHandler;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Getter
public abstract class Leaderboard {
    private final DatabaseHandler db = SharedAPI.getInstance().getDatabaseHandler();

    /**
     * The id of the leaderboard
     */
    private final String id;
    private final String key;

    private final String name;


    public Leaderboard(String id, String name) {
        this.id = id;
        this.name = name;
        this.key = "leaderboard:skywars:" + id;
    }

    public abstract int score(SkyWarsProfile profile);

    /**
     * Updates the leaderboard with the given profile
     *
     * @param profile the profile to update
     */
    public void update(SkyWarsProfile profile) {
        db.runRedisCommandAsync(jedis -> jedis.zadd(key, score(profile), profile.getUniqueId().toString()));
    }

    /**
     * Returns the top 10 players on the leaderboard
     *
     * @return the top 10 players
     */
    public CompletableFuture<List<UUID>> top10() {
        return CompletableFuture.supplyAsync(
                () -> db.runRedisCommand(jedis -> jedis.zrevrange(key, 0, 9))
                        .stream().map(UUID::fromString).toList()
        );
    }

    public CompletableFuture<UUID> top(int position) {
        return CompletableFuture.supplyAsync(() -> db.runRedisCommand(jedis -> {
            List<String> list = jedis.zrevrange(key, position - 1, position - 1);
            if (list.isEmpty()) {
                return null;
            }
            return UUID.fromString(list.get(0));
        }));
    }

    /**
     * Returns the position of the given profile on the leaderboard
     *
     * @param profile the profile to get the position of
     * @return the position of the profile
     */
    public CompletableFuture<Long> position(SkyWarsProfile profile) {
        return CompletableFuture.supplyAsync(() -> db.runRedisCommand(jedis -> {
            Long rank = jedis.zrevrank(key, profile.getUniqueId().toString());

            if (rank == null) {
                return -1L;
            }

            return rank + 1;
        }));
    }

}
