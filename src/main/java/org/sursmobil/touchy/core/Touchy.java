package org.sursmobil.touchy.core;

/**
 * Created by CJ on 11/08/2015.
 */
public class Touchy {
    private static Touchy instance;
    public static Touchy defaultInstance() {
        if(instance == null) {
            instance = TouchyFactory.create();
        }
        return instance;
    }

    private final TouchyContext context;

    Touchy(TouchyContext context) {
        this.context = context;
    }

    public static <T> T getConfig(Class<T> configClass){
        return defaultInstance().getConfigInstance(configClass);
    }

    public <T> T getConfigInstance(Class<T> configClass) {
        return context.getConfigCache().getConfig(configClass);
    }

    public void usePlugin(Class<?> pluginClass) {
        context.getPluginsCache().usePlugin(pluginClass);
    }
}
