package org.sursmobil.touchy;

import org.sursmobil.touchy.api.PropertyType;
import org.sursmobil.touchy.api.ValueSource;
import org.sursmobil.touchy.api.plugin.ValueView;
import org.sursmobil.touchy.api.plugin.ViewExtension;
import org.sursmobil.touchy.api.plugin.TouchyPlugin;
import org.sursmobil.touchy.api.plugin.ValueSourceSupplier;

import java.util.Collection;

/**
 * Created by CJ on 31/08/2015.
 */
@TouchyPlugin
public class TestPlugin {

    @ValueSourceSupplier
    public MyValueSource getMyValueSource() {
        return new MyValueSource();
    }

    @ViewExtension(MyValueSource.class)
    public String formatAsString(Collection<ValueView> values) {
        return "";
    }

    private class MyValueSource implements ValueSource{
        @Override
        public boolean isSet(String propertyName) {
            return false;
        }

        @Override
        public Object get(String propertyName, PropertyType propertyType) {
            return null;
        }

        @Override
        public Object getList(String propertyName, PropertyType propertyType) {
            return null;
        }
    }
}
