package gg.pixelarcade.skywars.items;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.item.impl
 * @date 25/09/2023 / 22:24
 */

//TODO: @Alfie if you wanna redo this or fix it  phoenix has changes and wat not

//public class ProfileItem extends Item {

//    private final Player player;
//
//    public ProfileItem(Player player) {
//        super(player);
//        this.player = player;
//    }
//
//    @Override
//    public ItemStack getButtonItem(Player player) {
//        return new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
//                .name(Color.translate("&aProfile &7(Right Click)"))
//                .addToLore(Color.translate("&7Click to view your profile."))
//                .setSkullOwner(player.getName())
//                .build();
//    }
//
//
//    @Override
//    public void click(Action action, UUID uuid) {
//        Profile profile = SharedAPI.getInstance().getProfileHandler().getProfile(player.getUniqueId());
////        Phoenix.getInstance().getMenuHandler().openMenu(new ProfileMenu(profile.getName(), true));
//    }
//}
