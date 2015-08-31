package org.sursmobil.touchy.core;


import org.sursmobil.utils.CachedFactory;

/**
 * Created by CJ on 29/08/2015.
 */
class ConfigCache {

    private final TouchyContext context;
    private final CachedFactory cache;

    ConfigCache(TouchyContext context) {
        this.context = context;
        cache = CachedFactory.create(this::instantiateConfig);
    }

    public <T> T getConfig(Class<T> configClass) {
        return cache.getInstance(configClass);
    }

    private <T> T instantiateConfig(Class<T> configClass) {
        return new ConfigClassParser<>(context, configClass).createInstance();
    }
}
