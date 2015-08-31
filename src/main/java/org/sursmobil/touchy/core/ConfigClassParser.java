package org.sursmobil.touchy.core;

import org.sursmobil.touchy.api.Source;
import org.sursmobil.touchy.api.ValueSource;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Created by CJ on 11/08/2015.
 */
class ConfigClassParser<T> {
    private final TouchyContext context;
    private final Class<T> configClass;

    public ConfigClassParser(TouchyContext context, Class<T> configClass) {
        this.context = context;
        this.configClass = configClass;
    }

    T createInstance() {
        Map<String, ValueReader> readers = parseReaders();
        ConfigWrapper<T> configWrapper = new ConfigWrapper<>(configClass, readers);
        return configWrapper.withProxy();
    }

    private Map<String, ValueReader> parseReaders() {
        Map<String, ValueReader> result = new HashMap<>();
        for(Method method : configClass.getDeclaredMethods()) {
            MethodValidator validator = new MethodValidator(method);
            if(validator.isConfigMethod()) {
                validator.validate();
                ValueReader valueReader = parseMethod(method, validator);
                result.put(method.getName(), valueReader);
            }
        }
        return result;
    }

    private ValueReader parseMethod(Method method, MethodValidator validator) {
        int maxPriority = validator.getMaxSourcePriority();
        Source[] sources = method.getAnnotationsByType(Source.class);
        SourceReader[] readers = new SourceReader[maxPriority];
        for(Source source : sources) {
            SourceReader sourceReader = parseSource(method, source);
            readers[source.priority() - 1] = sourceReader;
        }
        return new ValueReader(readers);
    }

    private SourceReader parseSource(Method method, Source source) {
        ValueSource valueSource = context.getSourceCache().getInstance(source.type());
        String property = source.name();
        Type valueType = method.getGenericReturnType();
        if(valueType instanceof ParameterizedType){
            ParameterizedType parameterizedType = (ParameterizedType) valueType;
            if(List.class.isAssignableFrom((Class<?>) parameterizedType.getRawType())){
                Class<?> paramType = (Class<?>) parameterizedType.getActualTypeArguments()[0];
                return new ListSourceReader(valueSource, property, paramType);
            } else {
                throw new TouchyException("Collections other then List are not supported");
            }
        } else {
            return new SingleSourceReader(valueSource, property, method.getReturnType());
        }
    }

}
