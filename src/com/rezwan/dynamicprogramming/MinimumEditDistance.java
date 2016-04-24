package com.rezwan.dynamicprogramming;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This implements the 'Minimum Edit Distance' algorithm between two string values. For example, given two strings, 'ABC',
 * and 'AXXD', you will have to find the minimum distance of converting 'ABC' to 'AXXD'. For this problem, you will
 * have the option of either edit, insert or delete characters from the first string to convert it to the second string.
 * It's called minimum edit distance because you want to make sure that those changes you make to the first string
 * are minimum.
 *
 * So to convert 'ABC' to 'AXXD', you can
 * 1. edit B to X
 * 2. edit C to X
 * 3. insert D
 * Therefore the minimum distance is 3 (because you did 3 operations and there is no other way of converting
 * with lesser operations).
 *
 * To convert 'ABC' to 'BY', you can
 * 1. delete A
 * 2. edit C to Y
 * Therefore minimum distance is 2.
 *
 * @author Rezwan Abir
 */
public class MinimumEditDistance {
    /**
     * This uses a naive recursive implementation of the algorithm. Basically, for ABC => BC
     * Since the first letter doesn't match, it would
     * do 1 plus the minimum of the following three (F = the function name).
     * F(BC, C) => remove the first characters of both string and move forward.
     * OR
     * F(ABC, C) => remove the fist character of the second string, leave the first one alone.
     * OR
     * F(BC, BC) => remove the first character of the first string, leave the second one alone.
     *
     * The runtime complexity of this is O(3^m) where m is the length of the bigger string of the two.
     *
     * @param first the first string who will be converted
     * @param second the second string who the first string will try to convert to
     * @return the minimum edit distance between first and second
     */
    public static int getDistanceRecursive(String first, String second) {
        if (first == null || second == null) {
            return -1;
        }

        if (first.length() == 0 || second.length() == 0) {
            return Math.max(first.length(), second.length());
        }

        if (first.equals(second)) {
            return 0;
        }

        //If the character matches, simply remove both those first characters and continue with recursion.
        if (first.charAt(0) == second.charAt(0)) {
            return getDistanceRecursive(first.substring(1, first.length()), second.substring(1, second.length()));
        } else {
            //Find the minimum distance of the insert, edit, or delete scenario PLUS ONE.

            //For, F(A, BA) the following becomes F(A, A), first letter of second string removed.
            int insert = getDistanceRecursive(first, second.substring(1, second.length()));
            //For, F(BC, DC) the following becomes F(C, C), first letters of both strings are removed.
            int edit = getDistanceRecursive(first.substring(1, first.length()), second.substring(1, second.length()));
            //For, (AB, B) the following becomes F(B, B), first letter of first string removed.
            int delete = getDistanceRecursive(first.substring(1, first.length()), second);
            return 1 + minimum(insert, edit, delete);
        }
    }

    /**
     * This uses an optimal dynamic programming algorithm to solve the min edit distance problem. As it compares
     * the two strings in a matrix, it caches the comparison value and uses the cache in future comparison without doing
     * any additional computation.
     *
     * The runtime complexity of this algorithm is O(m*n) where m and n are the lengths of the strings.
     *
     * @param first the first string who will be converted
     * @param second the second string who the first string will try to convert to
     * @return the minimum edit distance between first and second
     */
    public static int getDistanceDynamic(String first, String second) {
        if (first == null || second == null) {
            return -1;
        }

        if (first.length() == 0 || second.length() == 0) {
            return Math.max(first.length(), second.length());
        }

        if (first.equals(second)) {
            return 0;
        }

        // For "ABC", "BBC" it would create a matrix of 4x4
        int[][] distanceCache = new int[second.length() + 1][first.length() + 1];

        // It fills the top row with values 0, 1, 2 ...
        for (int i = 0; i < distanceCache[0].length; i++) {
            distanceCache[0][i] = i;
        }

        // It fills the left column with values 0, 1, 2 ...
        for (int i = 0; i < distanceCache.length; i++) {
            distanceCache[i][0] = i;
        }

        /**
         * For "ABC", "BBC" it would create a matrix of 4x4
         *       A B C
         *    |0|1|2|3|
         *  B |1|X|0|0|
         *  B |2|0|0|0|
         *  C |3|0|0|0|
         *
         *  So at position X you have 'A' from the top string and 'B' from the left string, since they don't match, you take
         *  the top, left and top-left value from current X position, and find the minimum of those values and ADD ONE.
         *  Then you put into the current spot. So X will get min(0,1,1)+1 = 1.
         *  If in the case above, 'A', 'B' were equal then in position X you'd simply copy over top left value.
         */
        for (int i = 1; i < distanceCache.length; i++) {
            for (int j = 1; j < distanceCache[0].length; j++) {
                if (first.charAt(j - 1) == second.charAt(i - 1)) {
                    distanceCache[i][j] = distanceCache[i - 1][j - 1];
                } else {
                    distanceCache[i][j] = 1 + minimum(distanceCache[i - 1][j - 1], distanceCache[i - 1][j], distanceCache[i][j - 1]);
                }
            }
        }

        //This returns the bottom rightmost element from the matrix and that is the result.
        return distanceCache[distanceCache.length - 1][distanceCache[0].length - 1];
    }

    /**
     * Returns smallest of the three input values.
     *
     * @param first the first integer
     * @param second the second integer
     * @param third the third integer
     * @return the minimum of three integers
     */
    public static int minimum(int first, int second, int third) {
        int smaller = Math.min(first, second);
        return Math.min(smaller, third);
    }

    /**
     * Main method executes the test specs.
     *
     * @param args command line args
     */
    public static void main(String[] args) {
        long recursiveTimeBegin = System.currentTimeMillis();
        assertEquals(MinimumEditDistance.getDistanceRecursive(null, "sunday"), -1);
        assertEquals(MinimumEditDistance.getDistanceRecursive("sunday", null), -1);
        assertEquals(MinimumEditDistance.getDistanceRecursive("", "cut"), 3);
        assertEquals(MinimumEditDistance.getDistanceRecursive("cut", ""), 3);
        assertEquals(MinimumEditDistance.getDistanceRecursive("cut", "cut"), 0);
        assertEquals(MinimumEditDistance.getDistanceRecursive("a", "ba"), 1);
        assertEquals(MinimumEditDistance.getDistanceRecursive("ba", "a"), 1);
        assertEquals(MinimumEditDistance.getDistanceRecursive("bat", "but"), 1);
        assertEquals(MinimumEditDistance.getDistanceRecursive("saturday", "sunday"), 3);
        assertEquals(MinimumEditDistance.getDistanceRecursive("sunday", "saturday"), 3);
        assertEquals(MinimumEditDistance.getDistanceRecursive("xoooog", "xg"), 4);
        assertEquals(MinimumEditDistance.getDistanceRecursive("xg", "xoooog"), 4);
        long recursiveTimeEnd = System.currentTimeMillis();

        long dynamicTimeBegin = System.currentTimeMillis();
        assertEquals(MinimumEditDistance.getDistanceDynamic(null, "sunday"), -1);
        assertEquals(MinimumEditDistance.getDistanceDynamic("sunday", null), -1);
        assertEquals(MinimumEditDistance.getDistanceDynamic("", "cut"), 3);
        assertEquals(MinimumEditDistance.getDistanceDynamic("cut", ""), 3);
        assertEquals(MinimumEditDistance.getDistanceDynamic("cut", "cut"), 0);
        assertEquals(MinimumEditDistance.getDistanceDynamic("a", "ba"), 1);
        assertEquals(MinimumEditDistance.getDistanceDynamic("ba", "a"), 1);
        assertEquals(MinimumEditDistance.getDistanceDynamic("bat", "but"), 1);
        assertEquals(MinimumEditDistance.getDistanceDynamic("saturday", "sunday"), 3);
        assertEquals(MinimumEditDistance.getDistanceDynamic("sunday", "saturday"), 3);
        assertEquals(MinimumEditDistance.getDistanceDynamic("xoooog", "xg"), 4);
        assertEquals(MinimumEditDistance.getDistanceDynamic("xg", "xoooog"), 4);
        long dynamicTimeEnd = System.currentTimeMillis();

        long dynamicTimeDelta = dynamicTimeEnd - dynamicTimeBegin;
        long recursiveTimeDelta = recursiveTimeEnd - recursiveTimeBegin;

        assertTrue(dynamicTimeDelta < recursiveTimeDelta);
    }

}
