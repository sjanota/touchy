package org.sursmobil.touchy.core;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import org.sursmobil.touchy.api.ValueMissingException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by CJ on 11/08/2015.
 */
class ConfigWrapper<T> implements MethodHandler {
    private final Class<T> configClass;
    private final Map<String, ValueReader> valueReaderMap;

    ConfigWrapper(Class<T> configClass, Map<String, ValueReader> valueReaderMap) {
        this.configClass = configClass;
        this.valueReaderMap = valueReaderMap;
    }

    @SuppressWarnings("unchecked")
    public T withProxy() {
        ProxyFactory factory = new ProxyFactory();
        factory.setSuperclass(configClass);
        try {
            return (T) factory.create(new Class<?>[0], new Object[0], this);
        } catch (NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new TouchyException("Cannot implement class " + configClass, e);
        }
    }

    @Override
    public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
        String methodName = thisMethod.getName();
        ValueReader reader = valueReaderMap.get(methodName);
        ReadValue value = reader.getValue();
        if(value.isSet()) {
            return value.getValue();
        } else if(proceed != null){
            return proceed.invoke(self);
        } else {
            throw new ValueMissingException();
        }
    }
}
