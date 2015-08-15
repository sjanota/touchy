package org.sursmobil.touchy.core;

import org.sursmobil.touchy.api.ValueMissingException;

/**
 * Created by CJ on 13/08/2015.
 */
interface ReadValue {
    boolean isSet();
    Object getValue();

    static ReadValue valueMissing() {
        return new ReadValue.MissingValue();
    }

    static ReadValue valuePresent(Object value) {
        return new ReadValue.ValuePresent(value);
    }

    class MissingValue implements ReadValue {

        @Override
        public boolean isSet() {
            return false;
        }

        @Override
        public Object getValue() {
            throw new ValueMissingException();
        }
    }

    class ValuePresent implements ReadValue {
        private final Object value;

        public ValuePresent(Object value) {
            this.value = value;
        }

        @Override
        public boolean isSet() {
            return true;
        }

        @Override
        public Object getValue() {
            return value;
        }
    }
}
