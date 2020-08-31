package de.wichtiges.commands;

import de.wichtiges.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class IpCommand extends Command {
    public IpCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if (sender instanceof ProxiedPlayer) {

            ProxiedPlayer player = (ProxiedPlayer)sender;

            if (player.hasPermission(Main.getInstance().IpCommandPerms)) {

                if (args.length == 0) {

                    player.sendMessage(Main.getInstance().prefix + "§cBenutze §b/ip <SpielerName>");

                } else if (args.length == 1) {

                    if (player.hasPermission(Main.getInstance().IpCommandOtherPerms)) {

                        ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);

                        if (target == null) {

                            player.sendMessage(Main.getInstance().isOffline);

                        } else {

                            player.sendMessage(Main.getInstance().prefix + "Die ip von §c" + target.getName() + "§7 ist §c" + target.getPendingConnection().getAddress());

                        }

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
