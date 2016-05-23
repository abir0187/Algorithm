package com.rezwan.dynamicprogramming;

import com.rezwan.model.Pair;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Provides test coverage for ContiguousSumInArray class.
 */
public class ContiguousSumInArrayTest {
    ContiguousSumInArray contiguousSumInArray = new ContiguousSumInArray();

    @Test
    public void testNullWhenTargetNotThere() {
        assertNull(contiguousSumInArray.findSumIndexes(new int[]{2, 3, 4, 5}, 11));
        assertNull(contiguousSumInArray.findSumIndexes(new int[]{2, 3, 4, 5}, -1));
        assertNull(contiguousSumInArray.findSumIndexes(new int[]{}, 1));
    }

    @Test
    public void testIndexesWhenArrayHasAllPositive() {
        Pair<Integer> result = contiguousSumInArray.findSumIndexes(new int[]{1, 3, 4, 5}, 9);
        assertTrue(testPair(result, 2, 3));

        result = contiguousSumInArray.findSumIndexes(new int[]{0, 2, 3, 4}, 2);
        assertTrue(testPair(result, 0, 1));

        result = contiguousSumInArray.findSumIndexes(new int[]{1, 3, 3, 1, 5, 6}, 11);
        assertTrue(testPair(result, 4, 5));

        result = contiguousSumInArray.findSumIndexes(new int[]{1, 3, 3, 1, 5, 6}, 12);
        assertTrue(testPair(result, 1, 4));

    }

    @Test
    public void testIndexesWhenArrayHasAllNegative() {
        Pair<Integer> result = contiguousSumInArray.findSumIndexes(new int[]{-1, -3, -4, -5}, -9);
        assertTrue(testPair(result, 2, 3));

        result = contiguousSumInArray.findSumIndexes(new int[]{-0, -2, -3, -4}, -2);
        assertTrue(testPair(result, 0, 1));

        result = contiguousSumInArray.findSumIndexes(new int[]{-1, -3, -3, -1, -5, -6}, -11);
        assertTrue(testPair(result, 4, 5));

        result = contiguousSumInArray.findSumIndexes(new int[]{-1, -3, -3, -1, -5, -6}, -12);
        assertTrue(testPair(result, 1, 4));
    }

    @Test
    public void testIndexesWhenArrayHasPositiveAndNegative() {
        Pair<Integer> result = contiguousSumInArray.findSumIndexes(new int[]{1, 3, -4, -5, 6}, -6);
        assertTrue(testPair(result, 1, 3));

        result = contiguousSumInArray.findSumIndexes(new int[]{1, 3, 4, 5, -6}, 12);
        assertTrue(testPair(result, 1, 3));

        result = contiguousSumInArray.findSumIndexes(new int[]{0, -2, 3, 4}, -2);
        assertTrue(testPair(result, 0, 1));

        result = contiguousSumInArray.findSumIndexes(new int[]{-1, -3, -3, -1, 5, 6}, 11);
        assertTrue(testPair(result, 4, 5));

        result = contiguousSumInArray.findSumIndexes(new int[]{-1, -3, 3, -1, 5, -6}, 7);
        assertTrue(testPair(result, 2, 4));
    }


    @Test
    public void testTargetZero() {
        Pair<Integer> result = contiguousSumInArray.findSumIndexes(new int[]{0}, 0);
        assertTrue(testPair(result, 0, 0));

        result = contiguousSumInArray.findSumIndexes(new int[]{0, 2, 3, 4}, 0);
        assertTrue(testPair(result, 0, 0));

        result = contiguousSumInArray.findSumIndexes(new int[]{1, 2, 3, 0}, 0);
        assertTrue(testPair(result, 3, 3));
    }

    /**
     * Returns true if a pair matches the begin and end argument, else returns false.
     *
     * @param pair  a given pair
     * @param begin a given begin index
     * @param end   a given end index
     * @return the result of whether a pair of indexes matches the begin and end or not.
     */
    private boolean testPair(Pair<Integer> pair, int begin, int end) {
        return pair.equals(new Pair(begin, end));
    }


}
