package de.wichtiges.commands;

import de.wichtiges.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class BungeeComand extends Command {
    public BungeeComand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if (sender instanceof ProxiedPlayer) {

            ProxiedPlayer player = (ProxiedPlayer)sender;

            player.sendMessage(Main.getInstance().prefix + "Du bist auf dem Server: §c" + player.getServer().getInfo().getName());

        } else {

            sender.sendMessage(Main.getInstance().noPlayer);

        }

    }
}
