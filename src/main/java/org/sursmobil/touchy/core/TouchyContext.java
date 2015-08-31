package org.sursmobil.touchy.core;

/**
 * Created by CJ on 29/08/2015.
 */
class TouchyContext {
    private final ConfigCache configCache;
    private final SourceCache sourceCache;

    TouchyContext() {
        this.configCache = new ConfigCache(this);
        sourceCache = new SourceCache();
    }

    public ConfigCache getConfigCache() {
        return configCache;
    }

    public SourceCache getSourceCache() {
        return sourceCache;
    }
}
