package org.sursmobil.touchy;

import org.sursmobil.touchy.core.Touchy;

/**
 * Created by CJ on 11/08/2015.
 */
public class TouchyTests {

    public static void main(String... args) {
        TestConfig config = Touchy.getConfig(TestConfig.class);

        System.out.println(config.getBoolean());
        System.out.println(config.getInteger());
        System.out.println(config.getString());
        System.out.println(config.getDefault());
        System.out.println(config.getBooleans());
    }
}
