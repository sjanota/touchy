package org.sursmobil.touchy.util;

import java.util.function.Supplier;

/**
 * Created by CJ on 30/08/2015.
 */
public class RunUtils {
    private RunUtils() {}

    public static <T> T withUncheckThrow(ThrowingSupplier<T> supplier) {
        try {
            return supplier.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
