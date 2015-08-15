package org.sursmobil.touchy.typesafe;

import org.sursmobil.touchy.api.PropertyType;

import java.util.List;

/**
 * Created by CJ on 15/08/2015.
 */
class SinglePropertyGetterFactory {
    private final TypesafeConfig source;

    public SinglePropertyGetterFactory(TypesafeConfig source) {
        this.source = source;
    }

    public PropertyType.Visitor<Object> create(String property) {
        return new SinglePropertyGetter(property);
    }

    private class SinglePropertyGetter implements PropertyType.Visitor<Object> {
        private final String propertyName;

        private SinglePropertyGetter(String propertyName) {
            this.propertyName = propertyName;
        }

        @Override
        public Object visitBoolean() {
            return source.getConfig().getBoolean(propertyName);
        }

        @Override
        public Object visitString() {
            return source.getConfig().getString(propertyName);
        }

        @Override
        public Object visitInteger() {
            return source.getConfig().getInt(propertyName);
        }
    }
}
