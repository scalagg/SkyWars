package xyz.refinedev.skywars.queue.commands;

import com.sk89q.minecraft.util.commands.CommandAlias;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.refinedev.commands.BaseCommand;
import xyz.refinedev.commands.CommandHelp;
import xyz.refinedev.commands.annotation.Subcommand;
import xyz.refinedev.commands.annotation.Syntax;
import xyz.refinedev.phoenix.Phoenix;
import xyz.refinedev.skywars.queue.menu.QueueMenu;

/**
 * @author alfie
 * @project Skywars / xyz.refinedev.skywars.queue.commands
 * @date 01/10/2023 / 21:50
 */

@CommandAlias("queue")
public class QueueCommand extends BaseCommand {

    @Subcommand("open")
    public void open(Player player) {
        Phoenix.getInstance().getMenuHandler().openMenu(new QueueMenu(), player);
    }

    @Subcommand("help")
    @Syntax("[page]")
    public void doHelp(CommandSender sender, CommandHelp help) {
        help.showHelp();
    }

}
