package org.sursmobil.touchy.typesafe;

import org.sursmobil.touchy.api.PropertyType;

import java.util.List;

/**
 * Created by CJ on 15/08/2015.
 */
class ListPropertyGetterFactory {
    private final TypesafeConfig source;

    public ListPropertyGetterFactory(TypesafeConfig source) {
        this.source = source;
    }

    public PropertyType.Visitor<List<?>> create(String property) {
        return new ListPropertyGetter(property);
    }

    private class ListPropertyGetter implements PropertyType.Visitor<List<?>> {
        private final String propertyName;

        private ListPropertyGetter(String propertyName) {
            this.propertyName = propertyName;
        }

        @Override
        public List<?> visitBoolean() {
            return source.getConfig().getBooleanList(propertyName);
        }

        @Override
        public List<?> visitString() {
            return source.getConfig().getStringList(propertyName);
        }

        @Override
        public List<?> visitInteger() {
            return source.getConfig().getIntList(propertyName);
        }
    }
}
