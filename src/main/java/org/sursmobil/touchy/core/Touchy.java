package org.sursmobil.touchy.core;

import org.sursmobil.touchy.core.ConfigClassParser;

/**
 * Created by CJ on 11/08/2015.
 */
public class Touchy {
    private Touchy() {}

    public static <T> T getConfig(Class<T> configClass){
        return new ConfigClassParser<>(configClass).createInstance();
    }
}
