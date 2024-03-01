package gg.pixelarcade.skywars.items;

import gg.pixelarcade.skywars.item.Item;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.Phoenix;
import xyz.refinedev.phoenix.server.ServerData;
import xyz.refinedev.phoenix.utils.Color;
import xyz.refinedev.phoenix.utils.XMaterial;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

import java.util.List;
import java.util.Set;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.item.impl
 * @date 25/09/2023 / 22:45
 */

public class QueueItem extends Item {

    private final Player player;

    public QueueItem(Player player) {
        super(player);
        this.player = player;
    }

    @Override
    public ItemStack getItem(Player player) {
        return new ItemBuilder(XMaterial.BOOK, 1)
                .setName(Color.translate("&aQueue SkyWars &7(Right Click)"))
                .toItemStack();
    }

    @Override
    public void click(Action action) {

        Set<String> worldNames = Phoenix.getInstance().getApi().getNetworkHandler().getWorldNames
         Set<ServerData> test = Phoenix.getInstance().getApi().getNetworkHandler().getServersInGroup("GAME-SERVER");

         for (ServerData server: test){
             String region = server.getWorldName();
             String name = server.getWorldName();
             player.sendMessage(server.getWorld);
         }
    }
}
