package de.wichtiges.listener;

import de.wichtiges.Main;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ConnectedPlayer;
import net.md_5.bungee.api.plugin.Listener;

public class JoinListener implements Listener {

    public void onJoin(ConnectedPlayer event){

        ServerInfo lobby = ProxyServer.getInstance().getServerInfo(Main.getInstance().LobbyServer);

        if (!event.getServer().getInfo().equals(Main.getInstance().LobbyServer)){

            event.connect(lobby);

        }

    }

}
