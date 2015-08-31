package org.sursmobil.touchy.system;

import org.sursmobil.touchy.api.plugin.TouchyPlugin;
import org.sursmobil.touchy.api.plugin.ValueSourceSupplier;

/**
 * Created by CJ on 31/08/2015.
 */
@TouchyPlugin
public class SystemPlugin {

    @ValueSourceSupplier
    public final SystemProperty systemProperty = new SystemProperty();

    @ValueSourceSupplier
    public final EnvVar envVar = new EnvVar();

}
