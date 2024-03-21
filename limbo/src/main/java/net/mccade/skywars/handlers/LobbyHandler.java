package net.mccade.skywars.handlers;

import lombok.Getter;
import net.mccade.skywars.Limbo;
import net.mccade.skywars.LimboPlugin;
import net.mccade.skywars.config.LimboConfig;
import net.mccade.skywars.handlers.task.CountdownTask;
import net.mccade.skywars.util.LimboState;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import studio.pinkcloud.deploy.spigot.api.Deployer;
import xyz.refinedev.phoenix.utils.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public class LobbyHandler {

    private final Lobby lobby;

    public LobbyHandler() {
        this.lobby = new Lobby(LimboConfig.SETTINGS.gameType, LimboConfig.SETTINGS.teamType);
    }

    public void addPlayer(Player player) {
        if (lobby.getPlayers().size() >= lobby.getGameTeamType().getMaxPlayers()) {
            player.sendMessage("You aren't supposed to be here!");
        }

        // Add the player to the player list
        Set<UUID> currentPlayers = lobby.getPlayers();
        currentPlayers.add(player.getUniqueId());
        lobby.setPlayers(currentPlayers);

        int playerCount = lobby.getPlayers().size();

        // Player count checks
        if (playerCount == LimboConfig.SETTINGS.serverStartPlayers) {
            lobby.setLimboState(LimboState.DEPLOYING);
            Limbo.getLobbyInstance().getLobbyHandler().deployGame(false);
        }
        if (playerCount == LimboConfig.SETTINGS.minPlayers) {
            lobby.setLimboState(LimboState.COUNTING);
            if (lobby.getCountdown() == null) {
                lobby.setCountdown(new CountdownTask(60));
                lobby.getCountdown().runTaskTimer(LimboPlugin.getInstance(), 20, 20);
            } else {
                lobby.getCountdown().setCountdown(60);
            }
        }
        if (playerCount == LimboConfig.SETTINGS.maxPlayers) {
            lobby.setLimboState(LimboState.COUNTING);
            if (!(lobby.getCountdown().getCountdown() <= 30)) {
                lobby.getCountdown().setCountdown(30);
            }
        }
    }

    public void removePlayer(Player player) {
        // Remove the player from the player list
        Set<UUID> currentPlayers = lobby.getPlayers();
        currentPlayers.remove(player.getUniqueId());
        lobby.setPlayers(currentPlayers);

        int playerCount = lobby.getPlayers().size();
        if (playerCount < LimboConfig.SETTINGS.minPlayers && lobby.getCountdown() != null) {
            lobby.getCountdown().setStop(true);
            lobby.setLimboState(LimboState.DEPLOYED);
        }

        Bukkit.broadcastMessage("Countdown stopped (Unfullfilled requirements)");
    }

    public void deployGame(boolean sendOnDeploy) {
        //-- Instance Variables
        List<String> templates = Deployer.getTemplates();
        List<String> skywarsTemplates = new ArrayList<>();

        if (templates.isEmpty()) {
            LimboPlugin.getInstance().getLogger().warning("No templates found!");
            return;
        }

        //-- Grab a list of all the skywars game template's names
        for (String template : templates) {
            System.out.println(template);

            if (template.startsWith("skywars-"))
                skywarsTemplates.add(template);
        }

        if (skywarsTemplates.isEmpty()) {
            LimboPlugin.getInstance().getLogger().warning("No skywars templates found!");
            return;
        }

        int number = ThreadLocalRandom.current().nextInt(skywarsTemplates.size());
        String templateName = skywarsTemplates.get(number);

        Limbo.getLobbyInstance().getServerHandler()
                .createServer(templateName, serverName -> {

                    // Send all the players to the server
                    if (!sendOnDeploy) {
                        Limbo.getLobbyInstance().getLobbyHandler().getLobby().setLimboState(LimboState.DEPLOYED);
                        Limbo.getLobbyInstance().getLobbyHandler().getLobby().setServerName(serverName);
                        return;
                    }

                    for (UUID uuid : lobby.getPlayers()) {
                        Player player = LimboPlugin.getInstance().getServer().getPlayer(uuid);
                        if (player == null)
                            continue;

                        player.sendMessage("Sending you to the game!");
                        Utilities.sendPlayer(player, serverName, LimboPlugin.getInstance());
                    }
                });
    }

}

