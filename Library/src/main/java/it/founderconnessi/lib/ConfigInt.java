package it.founderconnessi.lib;

import java.util.List;

public interface ConfigInt {

    boolean getBoolean(String path);

    String getString(String path);

    int getInt(String path);

    List<String> getStringList(String path);
}
