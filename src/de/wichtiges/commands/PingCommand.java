package de.wichtiges.commands;

import de.wichtiges.Main;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class PingCommand extends Command {
    public PingCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if (sender instanceof ProxiedPlayer) {

            ProxiedPlayer player = (ProxiedPlayer) sender;

            if (player.hasPermission(Main.getInstance().PingCommandPerms)) {

                if (args.length == 0) {

                    player.sendMessage(Main.getInstance().prefix + "Dein Ping beträgt §c" + player.getPing() + "ms");

                } else if (args.length == 1) {

                    if (player.hasPermission(Main.getInstance().PingCommandOtherPerms)) {

                        ProxiedPlayer target = BungeeCord.getInstance().getPlayer(args[0]);

                        if (target == null) {

                            player.sendMessage(Main.getInstance().isOffline);

                            return;

                        }

                        player.sendMessage(Main.getInstance().prefix + "Der Ping von §c" + target.getName() + " §7beträgt§c " + target.getPing() + "ms");

                    } else {

                        player.sendMessage(Main.getInstance().noPerms);

                    }

                }

            } else {

                player.sendMessage(Main.getInstance().noPerms);

            }

        } else {

            sender.sendMessage(Main.getInstance().noPlayer);

        }

    }
}
