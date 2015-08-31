package org.sursmobil.touchy.core;

import org.sursmobil.touchy.api.ValueSource;
import org.sursmobil.utils.CachedFactory;
import org.sursmobil.utils.Instantiate;

/**
 * Created by CJ on 31/08/2015.
 */
public class SourceCache {
    private final CachedFactory cache;

    public SourceCache() {
        cache = CachedFactory.create(Instantiate.newInstance());
    }

    public <T extends ValueSource> ValueSource getInstance(Class<T> sourceType) {
        return cache.getInstance(sourceType);
    }

}
