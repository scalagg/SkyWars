package xyz.refinedev.skywars.ability.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.refinedev.commands.BaseCommand;
import xyz.refinedev.commands.CommandHelp;
import xyz.refinedev.commands.annotation.*;
import xyz.refinedev.skywars.pxSkyWars;

/**
 * @author alfie
 * @project Skywars / xyz.refinedev.skywars.ability.command
 * @date 03/10/2023 / 06:32
 */

@CommandAlias("ability")
public class AbilityCommand extends BaseCommand {

    @Subcommand("give")
    @CommandPermission("skywars.ability.give")
    public void give(Player player, @Name("abilityName") String abilityName) {
        player.getInventory().addItem(pxSkyWars.getInstance().getAbilityHandler().getAbility(abilityName).getItem());
        player.sendMessage("§aYou have been given the ability: §e" + abilityName);
    }

    @HelpCommand
    @Syntax("[page]")
    public void doHelp(CommandSender sender, CommandHelp help) {
        help.showHelp();
    }

}
