package org.sursmobil.touchy.source;

import org.sursmobil.touchy.api.PropertyType;
import org.sursmobil.touchy.api.ValueSource;
import org.sursmobil.touchy.util.CommonParsers;

/**
 * Created by CJ on 11/08/2015.
 */
public class EnvVar implements ValueSource {
    @Override
    public boolean isSet(String propertyName) {
        return System.getenv(propertyName) != null;
    }

    @Override
    public Object get(String propertyName, PropertyType propertyType) {
        String plainText = System.getenv(propertyName);
        return parse(propertyName, propertyType, plainText);
    }

    @Override
    public Object getList(String propertyName, PropertyType propertyType) {
        String plainText = System.getenv(propertyName);
        return parseList(propertyName, propertyType, plainText);
    }

    @SuppressWarnings("unchecked")
    protected Object parse(String propertyName, PropertyType propertyType, String plainText) {
        return CommonParsers.parse(propertyType, plainText);
    }

    @SuppressWarnings("unchecked")
    protected Object parseList(String propertyName, PropertyType propertyType, String plainText) {
        return CommonParsers.parseList(propertyType, plainText);
    }
}
