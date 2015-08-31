package org.sursmobil.touchy.util;

/**
 * Created by CJ on 30/08/2015.
 */
public interface ThrowingSupplier<T> {
    T get() throws Exception;
}
