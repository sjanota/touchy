package org.sursmobil.touchy.typesafe;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.sursmobil.touchy.api.PropertyType;
import org.sursmobil.touchy.api.ValueSource;

/**
 * Created by CJ on 14/08/2015.
 */
public class TypesafeConfig implements ValueSource {

    private final Config config;
    private final ListPropertyGetterFactory listPropertyGetterFactory;
    private final SinglePropertyGetterFactory singlePropertyGetterFactory;

    public TypesafeConfig() {
        config = ConfigFactory.load();
        listPropertyGetterFactory = new ListPropertyGetterFactory(this);
        singlePropertyGetterFactory = new SinglePropertyGetterFactory(this);
    }

    @Override
    public boolean isSet(String propertyName) {
        return config.hasPath(propertyName);
    }

    @Override
    public Object get(String propertyName, PropertyType propertyType) {
        PropertyType.Visitor visitor = singlePropertyGetterFactory.create(propertyName);
        return propertyType.accept(visitor);
    }

    @Override
    public Object getList(String propertyName, PropertyType propertyType) {
        PropertyType.Visitor visitor = listPropertyGetterFactory.create(propertyName);
        return propertyType.accept(visitor);
    }

    public Config getConfig() {
        return config;
    }

}
