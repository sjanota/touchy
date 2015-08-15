package org.sursmobil.touchy.core;

import org.sursmobil.touchy.api.PropertyType;
import org.sursmobil.touchy.api.ValueSource;

/**
 * Created by CJ on 11/08/2015.
 */
class SingleSourceReader implements SourceReader {

    private final ValueSource source;
    private final String propertyName;
    private final Class<?> propertyType;

    SingleSourceReader(ValueSource source, String propertyName, Class<?> propertyType) {
        this.source = source;
        this.propertyName = propertyName;
        this.propertyType = propertyType;
    }

    @Override
    public boolean isApplicable() {
        return source.isSet(propertyName);
    }

    @Override
    public Object getValue() {
        return source.get(propertyName, PropertyType.getSupportedType(propertyType));
    }

}
