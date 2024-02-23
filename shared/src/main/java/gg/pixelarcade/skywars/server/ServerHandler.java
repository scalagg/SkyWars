package gg.pixelarcade.skywars.server;


import gg.pixelarcade.skywars.SkyWarsShared;
import gg.pixelarcade.skywars.server.thread.ServerCheckerThread;
import lombok.Getter;
import org.bukkit.entity.Player;
import xyz.refinedev.phoenix.utils.Utilities;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * @author alfie
 * @project Skywars
 * @date 27/10/2023 / 00:12
 */

@Getter
public class ServerHandler {

    private final Map<String, Server> servers = new HashMap<>();

    private final ServerCheckerThread checkerThread;

    private boolean cloudNet;

    public ServerHandler() {
        try {
            Class.forName("eu.cloudnetservice.driver.CloudNetVersion");

            cloudNet = true;
        } catch (ClassNotFoundException e) {
            System.out.println("CloudNet not found, disabling server handler");

            cloudNet = false;
        }

        checkerThread = new ServerCheckerThread(this);
    }

    public void createServer(String templateName, BiConsumer<Integer, String> consumer) {
        if (!cloudNet)
            return;

        ServiceTaskProvider serviceTaskProvider = InjectionLayer.ext().instance(ServiceTaskProvider.class);
        ServiceTask serviceTask = serviceTaskProvider.serviceTask(templateName);
        if (serviceTask == null)
            throw new IllegalArgumentException("ServiceTask with name '" + templateName + "' not found");

        ServiceConfiguration.builder(serviceTask)
                .autoDeleteOnStop(true)
                .build()
                .createNewServiceAsync()
                .whenComplete(((serviceCreateResult, throwable) -> {
                            serviceCreateResult.serviceInfo().provider().startAsync();

                            ServiceInfoSnapshot serviceInfo = serviceCreateResult.serviceInfo();
                            String host = serviceInfo.address().host();
                            int port = serviceInfo.address().port();

                            consumer.accept(port, host);
                        })
                );
    }

    public ServiceInfoSnapshot getBestLobby() {
        if (!cloudNet)
            return null;

        CloudServiceProvider cloudServiceProvider = InjectionLayer.ext().instance(CloudServiceProvider.class);

        ServiceInfoSnapshot bestLobby = null;
        double x = 0;

        for (ServiceInfoSnapshot runningService : cloudServiceProvider.runningServices()) {
            if (!runningService.name().startsWith("skywars-lobby"))
                continue;

            double percentage = SmartUtil.playerPercentage(runningService);

            if (percentage < x) {
                bestLobby = runningService;
                x = percentage;
            }
        }

        return bestLobby;
    }

    public ServiceInfoSnapshot getService(int port) {
        if (!cloudNet)
            return null;

        for (ServiceInfoSnapshot runningService : InjectionLayer.ext().instance(CloudServiceProvider.class).runningServices()) {
            if (runningService.address().port() == port) {
                return runningService;
            }
        }

        return null;
    }

    public ServiceInfoSnapshot getService(String name) {
        if (!cloudNet)
            return null;

        return InjectionLayer.ext().instance(CloudServiceProvider.class).serviceByName(name);
    }

    public void sendToLobby(Player player) {
        ServiceInfoSnapshot server = this.getBestLobby();
        if (server == null) {
            player.kickPlayer("§cThere are no available servers, please try again later.");
            return;
        }

        Utilities.sendPlayer(player, server.name(), SkyWarsShared.getInstance().getPlugin());
    }

    public void stopThreads() {
        checkerThread.interrupt();
    }
}
