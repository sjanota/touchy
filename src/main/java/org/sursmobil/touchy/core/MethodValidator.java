package org.sursmobil.touchy.core;

import org.sursmobil.touchy.api.Source;
import org.sursmobil.touchy.util.MethodUtil;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by CJ on 15/08/2015.
 */
class MethodValidator {
    private final Method method;
    private Status status;
    private int maxPriority;

    MethodValidator(Method method) {
        this.method = method;
        status = Status.NOT_VALIDATED;
    }

    boolean isConfigMethod() {
        return !Modifier.isStatic(method.getModifiers());
    }

    void validate() {
        if (status == Status.NOT_VALIDATED) {
            doValidate();
        }
    }

    int getMaxSourcePriority() {
        switch (status) {
            case NOT_VALIDATED:
                validate();
            case VALID:
                return maxPriority;
            case INVALID:
                throw new TouchyException(MethodUtil.format(method, true) + " is not valid");
            default:
                throw new TouchyException("Not valid state");
        }
    }

    private void doValidate() {
        try {
            checkReturnType();
            checkNoArguments();
            checkAnySource();
            doValidateSources();
        } catch (TouchyException e) {
            status = Status.INVALID;
            throw e;
        }
    }

    private void doValidateSources() {
        Source[] sources = method.getAnnotationsByType(Source.class);
        Set<Integer> priorities = new HashSet<>();
        for(Source source : sources) {
            if(priorities.contains(source.priority())) {
                throw new TouchyException("Duplicated priority " + source.priority() + " for " + MethodUtil.format(method));
            } else if(source.priority() < 0) {
                throw new TouchyException("Source priority has to be positive, but " + MethodUtil.format(method) +
                        "contains source with priority " + source.priority());
            } else {
                priorities.add(source.priority());
            }
        }
        maxPriority = priorities.isEmpty() ? 0 : Collections.max(priorities);
        status = Status.VALID;
    }

    private void checkAnySource() {
        Source[] sources = method.getAnnotationsByType(Source.class);
        if(sources.length == 0 && Modifier.isAbstract(method.getModifiers())) {
            throw new TouchyException(MethodUtil.format(method, true) + " does not contain at sources nor default " +
                    "value");
        }
    }

    private void checkNoArguments() {
        if(method.getParameterCount() != 0) {
            throw new TouchyException("Config method must take no parameters, but " + MethodUtil.format(method) +
                    " takes " + method.getParameterCount());
        }
    }

    private void checkReturnType() {
        if(method.getReturnType().equals(Void.TYPE)) {
            throw new TouchyException("Config method must return something, but " + MethodUtil.format(method) +
                    " does not.");
        }
    }

    private enum Status {
        NOT_VALIDATED, VALID, INVALID;
    }
}
