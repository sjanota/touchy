package org.sursmobil.touchy.api;

import org.sursmobil.touchy.core.Sources;

import java.lang.annotation.*;

/**
 * Created by CJ on 11/08/2015.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(Sources.class)
public @interface Source {
    Class<? extends ValueSource> type();
    String name();
    int priority();
}
