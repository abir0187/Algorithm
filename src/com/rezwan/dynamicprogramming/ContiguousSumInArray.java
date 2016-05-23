package com.rezwan.dynamicprogramming;

import com.rezwan.model.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * This implements the contiguous sum in array algorithm. The problem is this: given an array of integers, and a target,
 * if any contiguous subset in the array adds up to the target, you need to return the begin and the end index of those
 * items. For example, if the array is {1,2,3,4,5} and the target is 9 (2+3+4) then the code should return
 * a pair object of (1,3) where 1 = index of 2 and 3 = index of 4.
 *
 * @author Rezwan Abir
 */
public class ContiguousSumInArray {

    /**
     * This method uses a map to keep track of all running sums. And in each iteration, it will check if
     * runningSum - target already exists in the map. If it does then it returns a Pair(foundIndex+1, currentIndex).
     * For example, if you have 1,2,3,4,5 and your target is 9 the map will
     * have the followings: ( a -> b means a is key and b is value).
     * 1  -> 0 (1+0)
     * 3  -> 1 (1+2)
     * 6  -> 2 (1+2+3)
     * 10 -> 3 (1+2+3+4) // at this point sum - target == 1 (i.e. 10-9), which is in the map and therefore the method
     * will return a Pair(1,3).
     *
     * The runtime complexity of this code is O(n) where n is the number of items in the array.
     *
     * @param array  given array
     * @param target given target
     * @return a pair of begin and end index
     */
    public Pair<Integer> findSumIndexes(int[] array, int target) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(0, -1);
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
            if (hashMap.containsKey(sum - target)) {
                int index = hashMap.get(sum - target);
                return new Pair(index + 1, i);
            }
            if (!hashMap.containsKey(sum))
                hashMap.put(sum, i);
        }

        return null;
    }
}

