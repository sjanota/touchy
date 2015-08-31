package org.sursmobil.touchy.util;

/**
 * Created by CJ on 30/08/2015.
 */
public interface Instantiate {
    <T> T create(Class<T> type);
}
