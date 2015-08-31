package org.sursmobil.touchy.core;

/**
 * Created by CJ on 29/08/2015.
 */
class TouchyContext {
    private final ConfigCache configCache;
    private final SourceCache sourceCache;
    private final PluginsCache pluginsCache;

    TouchyContext() {
        sourceCache = new SourceCache();
        configCache = new ConfigCache(this);
        pluginsCache = new PluginsCache((plugin) -> PluginLoader.load(this, plugin));
    }

    public ConfigCache getConfigCache() {
        return configCache;
    }

    public SourceCache getSourceCache() {
        return sourceCache;
    }

    public PluginsCache getPluginsCache() {
        return pluginsCache;
    }
}
