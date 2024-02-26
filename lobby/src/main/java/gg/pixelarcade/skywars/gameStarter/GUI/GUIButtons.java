package gg.pixelarcade.skywars.gameStarter.GUI;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.Color;
import xyz.refinedev.phoenix.utils.XMaterial;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

public class GUIButtons {

    public static class ExitButton {
        public ItemStack getButton(){
            return new ItemBuilder(XMaterial.BARRIER)
                    .setName(Color.translate("&cClose"))
                    .toItemStack();
        }
    }

    public static class BackButton {
        public ItemStack getButton(){
            return new ItemBuilder(XMaterial.ARROW)
                    .setName(Color.translate("&aGo Back"))
                    .addLoreLine(Color.translate("&7To Play Skywars"))
                    .toItemStack();
        }
    }

    public static class PageButton {

        public ItemStack getButton(int pageNumber, boolean isForward){
            if (pageNumber == 1 && !isForward) return null;

            if(isForward){
                return new ItemBuilder(XMaterial.ARROW)
                        .setName(Color.translate("&aNext Page"))
                        .addLoreLine(Color.translate("&ePage " + pageNumber++))
                        .toItemStack();
            } else {
                return new ItemBuilder(XMaterial.ARROW)
                        .setName(Color.translate("&Previous Page"))
                        .addLoreLine(Color.translate("&ePage " + pageNumber--))
                        .toItemStack();
            }
        }
    }

    public static class MapButton {

        public ItemStack getButton(String mapName, boolean isFavorite){
            if (mapName.equals(null) || mapName.equals("")) return null;

            if(isFavorite){
                return new ItemBuilder(XMaterial.OAK_SIGN)
                        .setName(Color.translate("&c❤ &a" + mapName))
                        .addLoreLine("")
                        .addLoreLine(Color.translate("&aClick to Play"))
                        .addLoreLine(Color.translate("&eRight click to toggle favorites"))
                        .toItemStack();
            } else {
                return new ItemBuilder(XMaterial.OAK_SIGN)
                        .setName(Color.translate("&a" + mapName))
                        .addLoreLine("")
                        .addLoreLine(Color.translate("&aClick to Play"))
                        .addLoreLine(Color.translate("&eRight click to toggle favorites"))
                        .toItemStack();
            }
        }
    }

    public static class MapSelectorButton {
        public ItemStack getButton(){
            return new ItemBuilder(XMaterial.OAK_SIGN)
                    .setName(Color.translate("&aMap Selector"))
                    .addLoreLine(Color.translate("&7Pick which map you want to play from"))
                    .addLoreLine(Color.translate("&7a list of available games."))
                    .addLoreLine("")
                    .addLoreLine(Color.translate("&eClick to browse!"))
                    .toItemStack();
        }
    }

    public static class RandomSoloNormal {
        public ItemStack getButton(){
            return new ItemBuilder(XMaterial.ENDER_PEARL)
                    .setName(Color.translate("&aSkywars (Normal Mode)"))
                    .addLoreLine(Color.translate("&7Play Solo Normal Skywars!"))
                    .addLoreLine("")
                    .addLoreLine(Color.translate("&eClick to play!"))
                    .toItemStack();
        }
    }

    public static class RandomSoloInsane {
        public ItemStack getButton(){
            return new ItemBuilder(XMaterial.ENDER_EYE)
                    .setName(Color.translate("&aSkywars (Insane Mode)"))
                    .addLoreLine(Color.translate("&7Play Solo Insane Skywars!"))
                    .addLoreLine("")
                    .addLoreLine(Color.translate("&eClick to play!"))
                    .toItemStack();
        }
    }

    public static class RandomDoublesNormal {
        public ItemStack getButton(){
            return new ItemBuilder(XMaterial.ENDER_PEARL)
                    .setName(Color.translate("&aSkywars (Normal Mode)"))
                    .addLoreLine(Color.translate("&7Play Doubles Normal Skywars!"))
                    .addLoreLine("")
                    .addLoreLine(Color.translate("&eClick to play!"))
                    .toItemStack();
        }
    }

    public static class RandomDoublesInsane {
        public ItemStack getButton(){
            return new ItemBuilder(XMaterial.ENDER_EYE)
                    .setName(Color.translate("&aSkywars (Insane Mode)"))
                    .addLoreLine(Color.translate("&7Play Doubles Insane Skywars!"))
                    .addLoreLine("")
                    .addLoreLine(Color.translate("&eClick to play!"))
                    .toItemStack();
        }
    }

}
