package org.sursmobil.touchy.api;

import org.sursmobil.touchy.core.TouchyException;

/**
 * Created by CJ on 14/08/2015.
 */
public enum PropertyType {
    STRING {
        @Override
        public <T> T accept(Visitor<T> visitor) {
            return visitor.visitString();
        }
    }, BOOLEAN() {
        @Override
        public <T> T accept(Visitor<T> visitor) {
            return visitor.visitBoolean();
        }
    }, INTEGER {
        @Override
        public <T> T accept(Visitor<T> visitor) {
            return visitor.visitInteger();
        }
    };

    public abstract <T> T accept(Visitor<T> visitor);

    public static PropertyType getSupportedType(Class<?> type) {
        AcceptType acceptType = new AcceptType(type);
        for(PropertyType definedType : values()) {
            if(definedType.accept(acceptType)) {
                return definedType;
            }
        }
        throw new TouchyException("Type not supported " + type);
    }

    public interface Visitor<T> {
        T visitBoolean();
        T visitString();
        T visitInteger();
    }

    private static class AcceptType implements Visitor<Boolean> {
        private final Class<?> type;

        private AcceptType(Class<?> type) {
            this.type = type;
        }

        @Override
        public Boolean visitBoolean() {
            return Boolean.class.isAssignableFrom(type) || Boolean.TYPE.isAssignableFrom(type);
        }

        @Override
        public Boolean visitString() {
            return String.class.isAssignableFrom(type);
        }

        @Override
        public Boolean visitInteger() {
            return Integer.class.isAssignableFrom(type) || Integer.TYPE.isAssignableFrom(type);
        }
    }
}
