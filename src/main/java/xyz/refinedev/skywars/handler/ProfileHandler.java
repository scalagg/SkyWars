package xyz.refinedev.skywars.handler;

import lombok.Getter;
import xyz.refinedev.phoenix.SharedAPI;
import xyz.refinedev.phoenix.utils.mongo.MongoMapRepository;
import xyz.refinedev.skywars.profile.SkyWarsProfile;

import java.util.UUID;

/**
 * @author Creaxx
 * Created At: 5/8/2023
 */

@Getter
public class ProfileHandler extends MongoMapRepository<UUID, SkyWarsProfile> {

    private final SharedAPI api;

    public ProfileHandler(SharedAPI api) {
        super(SkyWarsProfile.class, api.getDatabaseHandler().getDatabase().getCollection("pxskywars-profiles"));
        this.api = api;

    }

    public SkyWarsProfile addProfile(UUID uuid) {
        SkyWarsProfile profile = new SkyWarsProfile(uuid);
        addToCache(uuid, profile);
        return profile;
    }

    public SkyWarsProfile getProfile(UUID uuid) {
        if (uuid == null) return null;

        if (getCache().get(uuid) != null) {
            return getCache().get(uuid);
        }

        SkyWarsProfile profile = getFromDatabaseSync(uuid);
        if (profile != null) {
            addToCache(uuid, profile);
            return profile;
        }

        return null;
    }

    public SkyWarsProfile getLocalProfileByName(String playerName) {
        for (SkyWarsProfile profile : getCache().values()) {
            if (profile.getName().equalsIgnoreCase(playerName)) return profile;
        }
        return null;
    }
}
