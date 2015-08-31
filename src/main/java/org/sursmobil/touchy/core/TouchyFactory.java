package org.sursmobil.touchy.core;

import org.sursmobil.touchy.api.plugin.TouchyPlugin;
import org.sursmobil.utils.ClasspathClassesProvider;

/**
 * Created by CJ on 31/08/2015.
 */
class TouchyFactory {
    private TouchyFactory() {}

    static Touchy create() {
        Touchy touchy = new TouchyFactory().createNewInstance();
        ClasspathClassesProvider.annotatedWith(TouchyPlugin.class).classes().forEach(touchy::usePlugin);
        return touchy;
    }

    private Touchy createNewInstance() {
        TouchyContext context = new TouchyContext();
        return new Touchy(context);
    }
}
