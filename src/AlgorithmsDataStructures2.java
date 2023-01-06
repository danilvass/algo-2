import java.util.*;

public class AlgorithmsDataStructures2
{

    public static int[] GenerateBBSTArray(int[] a)
    {
        Arrays.sort(a);
        int[] bst = new int[a.length];
        fillBST(bst, a, 0, a.length - 1, 0);
        return bst;
    }

    private static int[] fillBST(int[] arr, int[] nums, int start, int end, int index) {
        int mid = (start + end) / 2;
        arr[index] = nums[mid];
        if (start <= mid - 1) {
            fillBST(arr, nums, start, mid - 1, (2 * index) + 1);
        }
        if (end >= mid + 1) {
            fillBST(arr, nums, mid + 1, end, (2 * index) + 2);
        }
        return arr;
    }

}