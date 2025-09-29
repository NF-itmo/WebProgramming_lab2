package org.web2.controllers.services.checkers;

@FunctionalInterface
public interface CheckerFunction {
    boolean test(float x, float y, int r);
}
