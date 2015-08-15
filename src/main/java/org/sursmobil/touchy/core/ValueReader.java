package org.sursmobil.touchy.core;

import org.sursmobil.touchy.api.ValueMissingException;

/**
 * Created by CJ on 11/08/2015.
 */
class ValueReader {

    private final SourceReader[] sources;

    protected ValueReader(SourceReader[] sources) {
        this.sources = sources;
    }

    ReadValue getValue() throws ValueMissingException {
        return getValueForPriority(sources.length - 1);
    }

    private ReadValue getValueForPriority(int i) throws ValueMissingException {
        if(i < 0) {
            return ReadValue.valueMissing();
        } else {
            SourceReader reader = sources[i];
            if (reader == null || !reader.isApplicable()) {
                return getValueForPriority(i - 1);
            } else {
                return ReadValue.valuePresent(reader.getValue());
            }
        }
    }
}
