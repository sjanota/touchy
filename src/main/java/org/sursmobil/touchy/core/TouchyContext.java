package org.sursmobil.touchy.core;

/**
 * Created by CJ on 29/08/2015.
 */
class TouchyContext {
    private final ConfigCache configCache;

    TouchyContext() {
        this.configCache = new ConfigCache();
    }

    public ConfigCache getConfigCache() {
        return configCache;
    }
}
