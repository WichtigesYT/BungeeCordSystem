package de.wichtiges.commands;

import de.wichtiges.Main;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ListCommand extends Command {
    public ListCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if (sender instanceof ProxiedPlayer) {

            ProxiedPlayer player = (ProxiedPlayer)sender;

            player.sendMessage(Main.getInstance().prefix + "Es sind gerade §c" + BungeeCord.getInstance().getOnlineCount() + "§7 Spieler auf dem Netzwerk");

        } else {

            sender.sendMessage(Main.getInstance().noPlayer);

        }

    }
}
