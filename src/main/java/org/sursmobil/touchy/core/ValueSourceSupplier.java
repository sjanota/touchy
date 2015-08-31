package org.sursmobil.touchy.core;

import org.sursmobil.touchy.api.ValueSource;
import org.sursmobil.utils.RunUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.function.Supplier;

/**
 * Created by CJ on 31/08/2015.
 */
class ValueSourceSupplier implements Supplier<ValueSource>{
    private final Class<? extends ValueSource> type;
    private final Class<?> pluginClass;
    private final Supplier<ValueSource> supplier;

    public ValueSourceSupplier(Class<? extends ValueSource> type, Class<?> pluginClass, Supplier<ValueSource> supplier) {
        this.type = type;
        this.pluginClass = pluginClass;
        this.supplier = supplier;
    }

    @Override
    public ValueSource get() {
        return supplier.get();
    }

    public Class<? extends ValueSource> getType() {
        return type;
    }

    public Class<?> getPluginClass() {
        return pluginClass;
    }

    static ValueSourceSupplier create(Field field, Object instance) {
        return RunUtils.uncheckedSupply(() -> {
            ValueSource valueSource = (ValueSource) field.get(instance);
            return new ValueSourceSupplier(
                    field.getType().asSubclass(ValueSource.class),
                    instance.getClass(),
                    () -> valueSource
            );
        });
    }

    static ValueSourceSupplier create(Method method, Object instance) {
        return RunUtils.uncheckedSupply(() -> new ValueSourceSupplier(
                method.getReturnType().asSubclass(ValueSource.class),
                instance.getClass(),
                () -> RunUtils.uncheckedSupply(() -> (ValueSource) method.invoke(instance))
        ));
    }
}
