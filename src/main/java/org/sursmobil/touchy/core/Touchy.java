package org.sursmobil.touchy.core;

/**
 * Created by CJ on 11/08/2015.
 */
public class Touchy {
    private static final Touchy instance = new Touchy();

    private final TouchyContext context;

    private Touchy() {
        context = new TouchyContext();
    }

    public static <T> T getConfig(Class<T> configClass){
        return instance.getConfigInstance(configClass);
    }

    public <T> T getConfigInstance(Class<T> configClass) {
        return context.getConfigCache().getConfig(configClass);
    }
}
