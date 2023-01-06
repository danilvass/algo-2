import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AlgorithmsDataStructures2Test {

    @Test
    void test_bst() {
        int[] bst = AlgorithmsDataStructures2.GenerateBBSTArray(new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 });
        assertTrue(Arrays.equals(bst, new int[]{7, 3, 11, 1, 5, 9, 13, 0, 2, 4, 6, 8, 10, 12, 14}));
    }
    
}