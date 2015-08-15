package org.sursmobil.touchy;

import org.sursmobil.touchy.api.Source;
import org.sursmobil.touchy.core.Touchy;
import org.sursmobil.touchy.source.EnvVar;
import org.sursmobil.touchy.typesafe.TypesafeConfig;

import java.util.Collections;
import java.util.List;

/**
 * Created by CJ on 15/08/2015.
 */
abstract class TestConfig {

    @Source(type = TypesafeConfig.class, name = "app.values.strings", priority = 1)
    public List<String> getStrings() {
        return Collections.singletonList("string");
    }

    @Source(type = EnvVar.class, name = "MY_BOOL", priority = 2)
    @Source(type = TypesafeConfig.class, name = "app.values.bool", priority = 1)
    public abstract boolean getBoolean();

    public static void main(String... args) {
        TestConfig config = Touchy.getConfig(TestConfig.class);

        System.out.println(config.getStrings());
        System.out.println(config.getBoolean());
    }
}
