package de.wichtiges;

import de.wichtiges.commands.EndCommand;
import de.wichtiges.commands.IpCommand;
import de.wichtiges.commands.ListCommand;
import de.wichtiges.commands.PingCommand;
import de.wichtiges.listener.JoinListener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Main extends Plugin {

    ///Messages
    public String prefix;
    public String noPerms;
    public String noPlayer;
    public String isOffline;

    ///PlayerJoin
    public String LobbyServer;

    ///EndCommand
    public String EndCommandPerms;
    public int EndCommandSec;

    ///PingCommand
    public String PingCommandPerms;
    public String PingCommandOtherPerms;

    ///IpCommand
    public String IpCommandPerms;
    public String IpCommandOtherPerms;

    public static Main instance;
    Main plugin = this;

    public static Main getInstance() {

        return instance;

    }

    @Override
    public void onEnable() {

        instance = this;

        File file = new File("plugins/BungeeCordPlugin//config.yml");
        File ordner = new File("plugins//BungeeCordPlugin");

        if (!ordner.exists()){

            ordner.mkdirs();

        }

        Configuration cfg;
        if (!file.exists()) {

            try {

                file.createNewFile();
                cfg = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);

                cfg.set("prefix", "&8» &aBungeeCordSystem &8× &7");
                cfg.set("message.noplayer", "%prefix% &cDu bist kein Spieler!");
                cfg.set("message.noPermissions", "%prefix% &cDu hast keine Rechte!");
                cfg.set("message.isoffline", "%prefix% Der &cSpieler ist offline!");

                ///PlayerJoin
                cfg.set("server.lobbyserver", "Lobby-1");

                ///EndCommand
                cfg.set("commands.endcommand.permissions", "bungeecordsystem.command.end");
                cfg.set("commands.endcommand.seconds", "10");

                ///PingCommand
                cfg.set("command.pingcommand.permissions", "bungeecordsystem.command.ping");
                cfg.set("commands.pingcommand.other.permissions", "bungeecordsystem.command.ping.other");

                ///IpCommand
                cfg.set("command.ipcommand.permissions", "bungeecordsystem.command.ip");
                cfg.set("commands.ipcommand.other.permissions", "bungeecordsystem.command.ip.other");

                ConfigurationProvider.getProvider(YamlConfiguration.class).save(cfg, file);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        try {

            cfg = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);

            this.prefix = cfg.getString("prefix").replace("&", "§");
            this.noPerms = cfg.getString("message.noPermissions").replace("&", "§").replaceAll("%prefix", this.prefix);
            this.noPlayer = cfg.getString("message.noplayer").replace("&", "§").replaceAll("%prefix%", this.prefix);
            this.isOffline = cfg.getString("message.isoffline").replace("&", "§").replaceAll("%prefix%", this.prefix);

            ///PlayerJoin
            this.LobbyServer = cfg.getString("server.lobbyserver");

            ///EndCommand
            this.EndCommandPerms = cfg.getString("commands.endcommand.permissions");
            this.EndCommandSec = cfg.getInt("commands.endcommand.seconds");

            ///PingCommand
            this.PingCommandPerms = cfg.getString("commands.pingcommand.permissions");
            this.PingCommandOtherPerms = cfg.getString("commands.pingcommand.other.permissions");

            ///IpCommand
            this.IpCommandPerms = cfg.getString("commands.ipcommand.permissions");
            this.IpCommandOtherPerms = cfg.getString("commands.ipcommand.other.permissions");

        } catch (Exception e) {
            e.printStackTrace();
        }

        this.init();

    }

    @Override
    public void onDisable() {

        this.getProxy().getConsole().sendMessage(this.prefix + "Stoppe System...");

    }

    private void init() {

        this.getProxy().getConsole().sendMessage(this.prefix + "Starte System...");

        try {

            this.getProxy().getConsole().sendMessage(this.prefix + "Lade config.yml");

            registerCommand();
            registerEvent();

        } catch (Exception e) {



        }

    }

    private void registerCommand() {

        this.getProxy().getPluginManager().registerCommand(this, new EndCommand("end"));

        this.getProxy().getPluginManager().registerCommand(this, new PingCommand("ping"));

        this.getProxy().getPluginManager().registerCommand(this, new ListCommand("list"));

        this.getProxy().getPluginManager().registerCommand(this, new IpCommand("ip"));

    }

    private void registerEvent() {

        this.getProxy().getPluginManager().registerListener(this, new JoinListener());

    }

}
