package org.sursmobil.touchy.core;

/**
 * Created by CJ on 14/08/2015.
 */
interface SourceReader {
    boolean isApplicable();

    Object getValue();
}
