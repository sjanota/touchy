package org.sursmobil.touchy.api.plugin;

import org.sursmobil.touchy.api.PropertyType;

/**
 * Created by CJ on 31/08/2015.
 */
public class ValueView {
    private final String propertyName;
    private final PropertyType propertyType;
    private final boolean isList;
    private final Object value;

    public ValueView(String propertyName, PropertyType propertyType, boolean isList, Object value) {
        this.propertyName = propertyName;
        this.propertyType = propertyType;
        this.isList = isList;
        this.value = value;
    }
}
