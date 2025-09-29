package org.web2.jstlUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JstlFunctions {
    public static <T> List<T> reverse(List<T> list) {
        if (list == null) {
            return null;
        }
        List<T> reversed = new ArrayList<>(list);
        Collections.reverse(reversed);
        return reversed;
    }
}