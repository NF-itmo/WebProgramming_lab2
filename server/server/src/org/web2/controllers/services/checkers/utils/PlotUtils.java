package org.web2.controllers.services.checkers.utils;

public class PlotUtils {
    public static PlotQuarters getQuarter(final float x, final float y) {
        boolean xSign = x >= 0;
        boolean ySign = y >= 0;

        if (xSign && ySign) return PlotQuarters.FIRST_QUADRANT;
        else if (xSign && !ySign) return PlotQuarters.SECOND_QUADRANT; // читабельность важнее
        else if (!xSign && !ySign) return PlotQuarters.THIRD_QUADRANT; // читабельность важнее
        return PlotQuarters.FOURTH_QUADRANT;
    }
}
