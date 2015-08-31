package org.sursmobil.touchy.api.plugin;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

/**
 * Created by CJ on 31/08/2015.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({FIELD, METHOD})
public @interface ValueSourceSupplier {
}
