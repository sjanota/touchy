package org.sursmobil.touchy.util;

import org.sursmobil.touchy.api.PropertyType;
import org.sursmobil.touchy.core.TouchyException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by CJ on 11/08/2015.
 */
public class CommonParsers {
    private CommonParsers() {}

    public static Object parse(PropertyType type, String text) {
        Parser parser = new Parser(text);
        return type.accept(parser);
    }

    public static Object parseList(PropertyType propertyType, String plainText) {
        String separator = System.getProperty("path.separator");
        String[] parts = plainText.split(separator);
        Object[] parsed = new Object[parts.length];
        for(int i = 0; i < parts.length; ++i) {
            parsed[i] = parse(propertyType, parts[i]);
        }
        ListCreator creator = new ListCreator(parsed);
        return propertyType.accept(creator);
    }

    private static class Parser implements PropertyType.Visitor<Object> {
        private final String text;

        private Parser(String text) {
            this.text = text;
        }

        @Override
        public Object visitBoolean() {
            return Boolean.parseBoolean(text);
        }

        @Override
        public Object visitString() {
            return text;
        }

        @Override
        public Object visitInteger() {
            return Integer.parseInt(text);
        }
    }

    private static class ListCreator implements PropertyType.Visitor<List<?>> {
        private final Object[] elements;

        private ListCreator(Object[] elements) {
            this.elements = elements;
        }

        @Override
        public List<?> visitBoolean() {
            return Arrays.asList(elements).stream()
                    .map(e -> (Boolean) e)
                    .collect(Collectors.toList());
        }

        @Override
        public List<?> visitString() {
            return Arrays.asList(elements).stream()
                    .map(e -> (String) e)
                    .collect(Collectors.toList());
        }

        @Override
        public List<?> visitInteger() {
            return Arrays.asList(elements).stream()
                    .map(e -> (Integer) e)
                    .collect(Collectors.toList());
        }
    }
}
