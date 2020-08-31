package de.wichtiges.commands;

import de.wichtiges.Main;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.concurrent.TimeUnit;

public class EndCommand extends Command {

    private int sec = Main.getInstance().EndCommandSec;

    public EndCommand(String name) {
        super(name);
    }


    @Override
    public void execute(CommandSender sender, String[] args) {

        if (sender instanceof ProxiedPlayer) {

            ProxiedPlayer player = (ProxiedPlayer) sender;

            if (player.hasPermission(Main.getInstance().EndCommandPerms)) {

                if (args.length == 0) {

                    BungeeCord.getInstance().getScheduler().schedule(Main.getInstance(), new Runnable() {
                        @Override
                        public void run() {
                            BungeeCord.getInstance().broadcast(Main.getInstance().prefix + "§lDer Proxy stoppt in §c" + sec + "Sekunden!");
                            sec--;
                            if (sec == 0) {

                                BungeeCord.getInstance().stop();

                            }
                        }
                    }, 1, 1, TimeUnit.MINUTES);

                } else
                    player.sendMessage(Main.getInstance().prefix + "Benutze /end");

            } else
                player.sendMessage(Main.getInstance().noPerms);

        } else
            BungeeCord.getInstance().stop();

    }

}
