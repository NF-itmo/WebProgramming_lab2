package org.web2.controllers.services.checkers;

import org.web2.controllers.services.checkers.utils.PlotQuarters;
import org.web2.controllers.services.checkers.utils.PlotUtils;

public class Checker implements CheckerFunction{
    public boolean check(
            final float x,
            final float y,
            final int r
    ) {
        final PlotQuarters quarter = PlotUtils.getQuarter(x, y);

        if (quarter == PlotQuarters.FIRST_QUADRANT) return firstQuarterTester(x, y, r);
        else if (quarter == PlotQuarters.SECOND_QUADRANT) return secondQuarterTester(x, y, r);
        else if (quarter == PlotQuarters.THIRD_QUADRANT) return thirdQuarterTester(x, y, r);
        return forthQuarterTester(x, y, r);
    }

    private boolean firstQuarterTester(final float x, final float y, final float r) {
        return y <= (r - x);
    }

    private boolean secondQuarterTester(final float x, final float y, final float r) {
        return Math.sqrt(x * x + y * y) <= r / 2;
    }

    private boolean thirdQuarterTester(final float x, final float y,final float r) {
        return false;
    }

    private boolean forthQuarterTester(final float x, final float y, final float r) {
        return x >= -r / 2 && y <= r;
    }
}