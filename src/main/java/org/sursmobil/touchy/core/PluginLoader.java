package org.sursmobil.touchy.core;

import org.sursmobil.touchy.api.ValueSource;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by CJ on 31/08/2015.
 */
class PluginLoader {
    private final TouchyContext context;
    private final Object instance;

    private PluginLoader(TouchyContext context, Object instance) {
        this.context = context;
        this.instance = instance;
    }

    public static void load(TouchyContext context, Object instance) {
        new PluginLoader(context, instance).load();
    }

    private void load() {
        loadValueSources();
    }

    private void loadValueSources() {
        loadValueSourcesFromFields();
        loadValueSourcesFromMethods();
    }

    private void loadValueSourcesFromMethods() {
        for(Method method : instance.getClass().getDeclaredMethods()) {
            if(method.isAnnotationPresent(org.sursmobil.touchy.api.plugin.ValueSourceSupplier.class)) {
                validateMethodAsValueSourceSupplier(method);
                ValueSourceSupplier supplier = ValueSourceSupplier.create(method, instance);
                context.getSourceCache().load(supplier);
            }
        }
    }

    private void validateMethodAsValueSourceSupplier(Method method) {
        if(!ValueSource.class.isAssignableFrom(method.getReturnType())) {
            throw new TouchyException("Method " + method.getName() + " in plugin " + instance.getClass() + " is " +
                    "annotated with ValueSourceSupplier but its return type is not subtype of ValueSource");
        }
        if(method.getParameterCount() != 0) {
            throw new TouchyException("Method " + method.getName() + " in plugin " + instance.getClass() + " is " +
                    "annotated with ValueSourceSupplier so it shouldn't take nay parameters");
        }
    }

    private void loadValueSourcesFromFields() {
        for(Field field : instance.getClass().getDeclaredFields()) {
            if(field.isAnnotationPresent(org.sursmobil.touchy.api.plugin.ValueSourceSupplier.class)) {
                validateFieldAsValueSourceSupplier(field);
                ValueSourceSupplier supplier = ValueSourceSupplier.create(field, instance);
                context.getSourceCache().load(supplier);
            }
        }
    }

    private void validateFieldAsValueSourceSupplier(Field field) {
        if(!ValueSource.class.isAssignableFrom(field.getType())) {
            throw new TouchyException("Field " + field.getName() + " in plugin " + instance.getClass() + " is " +
                    "annotated with ValueSourceSupplier but its type is not subtype of ValueSource");
        }
    }
}
