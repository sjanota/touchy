package org.sursmobil.touchy.core;

import org.sursmobil.touchy.api.PropertyType;
import org.sursmobil.touchy.api.ValueSource;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;

/**
 * Created by CJ on 11/08/2015.
 */
class ListSourceReader implements SourceReader {

    private final ValueSource source;
    private final String propertyName;
    private final Class<?> propertyType;

    ListSourceReader(ValueSource source, String propertyName, Class<?> propertyType) {
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
        return source.getList(propertyName, PropertyType.getSupportedType(propertyType));
    }

    public static void main(String... args) throws NoSuchMethodException {
        ParameterizedType ret = (ParameterizedType) ListSourceReader.class.getMethod("method").getGenericReturnType();
        System.out.println(Arrays.toString(ret.getActualTypeArguments()));
        System.out.println(ret.getRawType());
    }
}
