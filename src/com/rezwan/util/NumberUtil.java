package com.rezwan.util;

/**
 * Basic utilities regarding numbers.
 */
public class NumberUtil {
    /**
     * Returns smallest of the three input values.
     *
     * @param first  the first integer
     * @param second the second integer
     * @param third  the third integer
     * @return the minimum of three integers
     */
    public static int minimum(int first, int second, int third) {
        int smaller = Math.min(first, second);
        return Math.min(smaller, third);
    }
}
