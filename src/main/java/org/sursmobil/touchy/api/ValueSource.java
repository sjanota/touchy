package org.sursmobil.touchy.api;

/**
 * Created by CJ on 11/08/2015.
 */
public interface ValueSource {
    boolean isSet(String propertyName);

    Object get(String propertyName, PropertyType propertyType);

    Object getList(String propertyName, PropertyType propertyType);
}
