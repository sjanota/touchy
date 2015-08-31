package org.sursmobil.touchy.util;

import static org.sursmobil.touchy.util.RunUtils.withUncheckedThrow;

/**
 * Created by CJ on 30/08/2015.
 */
public interface Instantiate {
    <T> T create(Class<T> type);

    static Instantiate newInstance() {
        return new Instantiate() {
            @Override
            public <T> T create(Class<T> type) {
                return withUncheckedThrow(type::newInstance);
            }
        };
    }
}
