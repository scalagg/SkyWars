package xyz.refinedev.skywars.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import xyz.refinedev.cosmetics.cosmetics.killmessages.KillMessageType;
import xyz.refinedev.cosmetics.profile.CosmeticProfile;
import xyz.refinedev.cosmetics.pxCosmetics;
import xyz.refinedev.phoenix.SharedAPI;
import xyz.refinedev.phoenix.profile.Profile;
import xyz.refinedev.phoenix.rank.Rank;
import xyz.refinedev.phoenix.utils.Color;
import xyz.refinedev.skywars.profile.SkyWarsProfile;
import xyz.refinedev.skywars.pxSkyWars;

public class PlayerListener implements Listener {
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Profile px = SharedAPI.getInstance().getProfileHandler().getProfile(player.getUniqueId());
        String killMsg = px.getNameWithColor() + "&f has died a horrible death.";
        Player killer = player.getKiller();
        CosmeticProfile cp = pxCosmetics.getInstance().getProfileHandler().getProfile(killer.getUniqueId());
        Profile pxKiller = SharedAPI.getInstance().getProfileHandler().getProfile(player.getKiller().getUniqueId());
        SkyWarsProfile killerProfile = pxSkyWars.instance.getProfileHandler().getProfile(killer.getUniqueId());
        if (!cp.getKillMessageType().equals(KillMessageType.NONE)) {
            killMsg = px.getNameWithColor() + "&f " + KillMessageType.drawRandom(cp.getKillMessageType()) + "&f " + pxKiller.getNameWithColor() + "&f.";
        } else {
            killMsg = px.getNameWithColor() + "&f was &aslain&f by " + pxKiller.getNameWithColor() + "&f.";

        }
//      TODO: Add souls  to the kill idk if i did it right or not but i tried!
        double experienceToAdd = 3D * getMultiplier(pxKiller.getHighestRank());
        double soulsToAdd = getMultiplier(pxKiller.getHighestRank());
        killer.sendMessage(Color.translate("&eYou have obtained &f" + soulsToAdd + " &esouls for killing " + player.getName() + "."));
        SharedAPI.getInstance().getLevelHook().addExperience(pxKiller, (int) experienceToAdd, "Killed a player");
        killerProfile.addSouls((int) soulsToAdd);
    }
    public double getMultiplier(Rank rank) {
        if (rank.hasPermission("skywars.moneymultiplier")) {
            String perm = "";
            for (String s : rank.getAvailablePermissions()) {
                if (s.toLowerCase().contains("skywars.moneymultiplier.")) {
                    perm = s;
                    break;
                }
            }
            perm = perm.replace("skywars.moneymultiplier.", "");
            if (!perm.equalsIgnoreCase("")) {
                return Double.parseDouble(perm);
            }
        }

        return 1;
    }
}
