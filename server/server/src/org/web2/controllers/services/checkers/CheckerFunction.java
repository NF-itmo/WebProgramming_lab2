package org.web2.controllers.services.checkers;

@FunctionalInterface
public interface CheckerFunction {
    boolean check(float x, float y, int r);
}
