package org.sursmobil.touchy.core;

import org.sursmobil.touchy.api.ValueSource;
import org.sursmobil.utils.CachedFactory;
import org.sursmobil.utils.Instantiate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by CJ on 31/08/2015.
 */
public class SourceCache {
    private final Map<Class<? extends ValueSource>, ValueSourceSupplier> cache;

    public SourceCache() {
        cache = new HashMap<>();
    }

    public void load(ValueSourceSupplier supplier) {
        if(cache.containsKey(supplier.getType())) {
            ValueSourceSupplier present = cache.get(supplier.getType());
            if(!present.getPluginClass().equals(supplier.getPluginClass())) {
                throw new TouchyException("Try to load ValueSource " + supplier.getType() + " from plugin " + supplier
                        .getPluginClass() + " but it is already loaded by plugin " + present.getPluginClass());
            }
        } else {
            cache.put(supplier.getType(), supplier);
        }
    }

    public ValueSource getInstance(Class<? extends ValueSource> sourceType) {
        ValueSourceSupplier supplier = cache.get(sourceType);
        if(supplier != null) {
            return supplier.get();
        } else {
            throw new TouchyException("ValueSource " + sourceType + " is not loaded!");
        }
    }

}
