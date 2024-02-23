package gg.pixelarcade.skywars.profile;

import lombok.Getter;
import xyz.refinedev.phoenix.SharedAPI;
import xyz.refinedev.phoenix.utils.mongo.MongoMapRepository;

import java.util.UUID;

@Getter
public class ProfileHandler extends MongoMapRepository<UUID, SkyWarsProfile> {

    public ProfileHandler() {
        super(SkyWarsProfile.class, SharedAPI.getInstance().getDatabaseHandler()
                .getDatabase().getCollection("pxskywars-profiles"));
    }

    public SkyWarsProfile addProfile(UUID uuid) {
        SkyWarsProfile profile = new SkyWarsProfile(uuid);
        addToCache(uuid, profile);
        return profile;
    }

    public SkyWarsProfile getProfile(UUID uuid) {
        if (uuid == null)
            return null;

        if (getCache().get(uuid) != null)
            return getCache().get(uuid);

        SkyWarsProfile profile = getFromDatabaseSync(uuid);
        if (profile != null) {
            addToCache(uuid, profile);
            return profile;
        }

        return null;
    }
}
