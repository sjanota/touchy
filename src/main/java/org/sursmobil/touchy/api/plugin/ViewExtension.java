package org.sursmobil.touchy.api.plugin;

import org.sursmobil.touchy.api.ValueSource;

/**
 * Created by CJ on 31/08/2015.
 */
public @interface ViewExtension {
    Class<? extends ValueSource> value();
}
