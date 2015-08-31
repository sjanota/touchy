package org.sursmobil.touchy.core;

import org.sursmobil.utils.CachedFactory;
import org.sursmobil.utils.ClassesProvider;
import org.sursmobil.utils.Instantiate;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Created by CJ on 31/08/2015.
 */
class PluginsCache {
    private final CachedFactory cache;
    private final Consumer<Object> pluginLoader;

    PluginsCache(Consumer<Object> pluginLoader) {
        this.pluginLoader = pluginLoader;
        cache = CachedFactory.create(Instantiate.newInstance());
    }

    public void usePlugin(Class<?> pluginClass) {
        if(cache.createInstance(pluginClass)) {
            Object instance = cache.getInstance(pluginClass);
            pluginLoader.accept(instance);
        }
    }

}
