package com.rezwan.dynamicprogramming;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Provides coverage for MinimumEditDistance
 */
public class MinimumEditDistanceTest {
    MinimumEditDistance minimumEditDistance = new MinimumEditDistance();

    @Test
    public void testNullRecursive() {
        assertEquals(minimumEditDistance.getDistanceRecursive(null, "sunday"), -1);
        assertEquals(minimumEditDistance.getDistanceRecursive("sunday", null), -1);
    }

    @Test
    public void testNullDynamic() {
        assertEquals(minimumEditDistance.getDistanceDynamic(null, "sunday"), -1);
        assertEquals(minimumEditDistance.getDistanceDynamic("sunday", null), -1);
    }

    @Test
    public void testOneEmptyStringRecursive() {
        assertEquals(minimumEditDistance.getDistanceRecursive("", "cut"), 3);
        assertEquals(minimumEditDistance.getDistanceRecursive("cut", ""), 3);
    }


    @Test
    public void testOneEmptyStringDynamic() {
        assertEquals(minimumEditDistance.getDistanceDynamic("", "cut"), 3);
        assertEquals(minimumEditDistance.getDistanceDynamic("cut", ""), 3);
    }

    @Test
    public void testMinimumEditDistanceCountRecursive() {
        assertEquals(minimumEditDistance.getDistanceRecursive("cut", "cut"), 0);
        assertEquals(minimumEditDistance.getDistanceRecursive("a", "ba"), 1);
        assertEquals(minimumEditDistance.getDistanceRecursive("ba", "a"), 1);
        assertEquals(minimumEditDistance.getDistanceRecursive("bat", "but"), 1);
        assertEquals(minimumEditDistance.getDistanceRecursive("saturday", "sunday"), 3);
        assertEquals(minimumEditDistance.getDistanceRecursive("sunday", "saturday"), 3);
        assertEquals(minimumEditDistance.getDistanceRecursive("xoooog", "xg"), 4);
        assertEquals(minimumEditDistance.getDistanceRecursive("xg", "xoooog"), 4);
    }

    @Test
    public void testMinimumEditDistanceCountDynamic() {
        assertEquals(minimumEditDistance.getDistanceDynamic("cut", "cut"), 0);
        assertEquals(minimumEditDistance.getDistanceDynamic("a", "ba"), 1);
        assertEquals(minimumEditDistance.getDistanceDynamic("ba", "a"), 1);
        assertEquals(minimumEditDistance.getDistanceDynamic("bat", "but"), 1);
        assertEquals(minimumEditDistance.getDistanceDynamic("saturday", "sunday"), 3);
        assertEquals(minimumEditDistance.getDistanceDynamic("sunday", "saturday"), 3);
        assertEquals(minimumEditDistance.getDistanceDynamic("xoooog", "xg"), 4);
        assertEquals(minimumEditDistance.getDistanceDynamic("xg", "xoooog"), 4);
    }

}
