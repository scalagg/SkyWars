package gg.pixelarcade.skywars.gameStarter.GUI;


import gg.pixelarcade.skywars.game.state.GameType;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

import java.util.ArrayList;

public class MapGUI {

    private ArrayList<ItemStack> guiBuilder = new ArrayList<>();
    private GUIButtons guiButtons;
    protected Inventory mapSelector;

    private ItemStack space = new ItemBuilder
            (Material.STAINED_GLASS_PANE, 1, DyeColor.WHITE.getData())
            .setName("")
            .toItemStack();
    private ItemStack boarder = new ItemBuilder
            (Material.STAINED_GLASS_PANE, 1, DyeColor.YELLOW.getData())
            .setName("")
            .toItemStack();

    // TODO: Finish making the gui
    public void Create(GameType gameType){
        mapSelector = Bukkit.createInventory(null, 54, gameType.name().replace("_", " "));

        switch (gameType){
            case NORMAL ->
                CreateBoarder();

        }

    }

    private void CreateBoarder(){
        for (int i = 0; i < 9; i++){
            guiBuilder.add(boarder);
        }
    }

    // TODO: Make a way to automate placing maps in the gui
//    private void PlaceMaps(){
//        guiBuilder.add(space);
//        for (int i = 0; i < 7; i++){
//            GUIButtons.
//        }
//        guiBuilder.add(space);
//    }
}
