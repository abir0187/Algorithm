package com.rezwan.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NumberUtilTest {

    @Test
    public void testMinimumWithPositiveOnly() {
        assertEquals(NumberUtil.minimum(1, 2, 3), 1);
        assertEquals(NumberUtil.minimum(1, 1, 3), 1);
        assertEquals(NumberUtil.minimum(3, 2, 1), 1);
        assertEquals(NumberUtil.minimum(3, 1, 1), 1);
        assertEquals(NumberUtil.minimum(500, 200, 100), 100);
    }


    @Test
    public void testMinimumWithNegativeOnly() {
        assertEquals(NumberUtil.minimum(-1, -2, -3), -3);
        assertEquals(NumberUtil.minimum(-1, -1, -3), -3);
        assertEquals(NumberUtil.minimum(-3, -2, -1), -3);
        assertEquals(NumberUtil.minimum(-3, -1, -1), -3);
        assertEquals(NumberUtil.minimum(-500, -200, -100), -500);
    }

    @Test
    public void testMinimumWithPositiveAndNegative() {
        assertEquals(NumberUtil.minimum(1, -2, 3), -2);
        assertEquals(NumberUtil.minimum(1, -1, 3), -1);
        assertEquals(NumberUtil.minimum(-3, 2, -1), -3);
        assertEquals(NumberUtil.minimum(-3, 2, -3), -3);
        assertEquals(NumberUtil.minimum(-500, 200, -100), -500);
    }


}
