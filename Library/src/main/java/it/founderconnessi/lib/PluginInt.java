package it.founderconnessi.lib;

public interface PluginInt {

    String getPluginVersion();

    String getServerType();

    void sendConsoleMessage(String message);

    LoggerInt getLogger();

    ConfigInt getConfig();
}
