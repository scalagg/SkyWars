package gg.pixelarcade.skywars.ability.command;

import gg.pixelarcade.skywars.SkyWarsPlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.commands.BaseCommand;
import xyz.refinedev.commands.CommandHelp;
import xyz.refinedev.commands.annotation.*;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.skywars.ability.command
 * @date 03/10/2023 / 06:32
 */

@CommandAlias("ability")
public class AbilityCommand extends BaseCommand {

    @Subcommand("give")
    @CommandPermission("skywars.ability.give")
    public void give(Player player, @Name("abilityName") String abilityName, @Optional @Name("amount") int amount) {
        ItemStack item = SkyWarsPlugin.getInstance().getAbilityHandler().getAbilityById(abilityName).getItem();
        item.setAmount(amount);

        player.getInventory().addItem(item);
        player.sendMessage("§eYou have been given the ability: §6" + abilityName);
    }

    @Subcommand("list")
    @CommandPermission("skywars.ability.list")
    public void list(CommandSender sender) {
        sender.sendMessage("§aAbility List:");
        SkyWarsPlugin.getInstance().getAbilityHandler().getCache().forEach((string, ability) -> sender.sendMessage("§e" + string));
    }

    @HelpCommand
    @Syntax("[page]")
    public void doHelp(CommandSender sender, CommandHelp help) {
        help.showHelp();
    }

}
