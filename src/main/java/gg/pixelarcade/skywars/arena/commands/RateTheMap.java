package gg.pixelarcade.skywars.arena.commands;

import gg.pixelarcade.skywars.SkyWarsPlugin;
import gg.pixelarcade.skywars.arena.ArenaSchematic;
import gg.pixelarcade.skywars.arena.rating.RatingType;
import gg.pixelarcade.skywars.profile.SkyWarsProfile;
import xyz.refinedev.commands.BaseCommand;
import xyz.refinedev.commands.annotation.CommandAlias;
import xyz.refinedev.commands.annotation.Name;
import xyz.refinedev.commands.annotation.Private;
import xyz.refinedev.phoenix.utils.Color;
import org.bukkit.entity.Player;

public class RateTheMap extends BaseCommand {

    @Private
    @CommandAlias("ratemeplzkthanksbye")
    public void execute(Player player, @Name("name") ArenaSchematic arenaSchematic, @Name("rating") String rating) {
        SkyWarsProfile profile = SkyWarsPlugin.getInstance().getProfileHandler().getProfile(player.getUniqueId());

        if (!profile.isCanRate())
            player.sendMessage(Color.translate("&cYou have already rated this arena!"));
        else switch (rating.toLowerCase()) {
            case "terrible" -> arenaSchematic.addRating(RatingType.TERRIBLE);
            case "notgood" -> arenaSchematic.addRating(RatingType.NOT_GOOD);
            case "okay" -> arenaSchematic.addRating(RatingType.OKAY);
            case "good" -> arenaSchematic.addRating(RatingType.GOOD);
            case "amazing" -> arenaSchematic.addRating(RatingType.AMAZING);
        }

        SkyWarsPlugin.getInstance().getArenaHandler().saveSchematic(arenaSchematic);

        player.sendMessage(Color.translate("&aYou have rated the arena &e" + arenaSchematic.getName() + " &aas &e" + rating + "&a!"));
        player.sendMessage(Color.translate("&aThank you for your feedback!"));
        player.sendMessage(Color.translate("&7&oYour feedback helps us improve our arenas and make them more enjoyable for everyone!"));

        profile.setCanRate(false);
        profile.getArenasRated().add(arenaSchematic.getName());
        profile.save();
    }
}