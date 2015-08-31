package org.sursmobil.touchy.core;

import org.sursmobil.touchy.api.Source;
import org.sursmobil.touchy.api.ValueSource;
import org.sursmobil.touchy.util.CachedFactory;
import org.sursmobil.touchy.util.RunUtils;

/**
 * Created by CJ on 31/08/2015.
 */
public class SourceCache {
    private final CachedFactory cache;

    public SourceCache() {
        cache = CachedFactory.create(this::instantiateSource);
    }

    public <T extends ValueSource> T getInstance(Class<T> sourceType) {
        return cache.getInstance(sourceType);
    }

    private <T> T instantiateSource(Class<T> sourceType) {
        return RunUtils.withUncheckThrow(sourceType::newInstance);
    }
}
