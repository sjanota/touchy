package org.sursmobil.touchy;

import org.sursmobil.touchy.api.Source;
import org.sursmobil.touchy.system.EnvVar;
import org.sursmobil.touchy.system.SystemProperty;

import java.util.List;

/**
 * Created by CJ on 11/08/2015.
 */
public abstract class TestConfig {

    @Source(type = EnvVar.class, name = "MY_BOOL", priority = 1)
    public abstract boolean getBoolean();

    @Source(type = EnvVar.class, name = "MY_STRING", priority = 1)
    public abstract String getString();

    @Source(type = EnvVar.class, name = "MY_INT", priority = 1)
    @Source(type = SystemProperty.class, name = "SYS_INT", priority = 2)
    public abstract int getInteger();

    @Source(type = EnvVar.class, name = "MY_DEFAULT", priority = 1)
    public String getDefault() {
        return "mama";
    }

    @Source(type = EnvVar.class, name = "MY_BOOLEANS", priority = 1)
    public abstract List<Boolean> getBooleans();

}
