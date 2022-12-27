import java.util.*;

class aBST
{
    public Integer Tree [];

    public aBST(int depth)
    {
        int tree_size = (int) (Math.pow(2, depth) - 1);
        Tree = new Integer[ tree_size ];
        for(int i=0; i<tree_size; i++) Tree[i] = null;
    }

    public Integer FindKeyIndex(int key)
    {
        Integer index = 0;
        while (index < Tree.length) {
            if (Tree[index] == null) { return (index * -1); }
            if (Tree[index] == key) { return index; }
            if (key >= Tree[index]) {
                index = rightIndexForChild(index);
            } else {
                index = leftIndexForChild(index);
            }
            if (index == null) { return null; }
        }
        return null;
    }

    public int AddKey(int key)
    {
        Integer index = FindKeyIndex(key);
        if (index == null) { return -1; }

        if (index <= 0) {
            Tree[index * -1] = key;
            return index * -1;
        }
        return index;
    }

    private Integer leftIndexForChild(int index) {
        int childIndex = (2 * index) + 1;
        if (childIndex >= Tree.length) return null;
        return childIndex;
    }

    private Integer rightIndexForChild(int index) {
        int childIndex = (2 * index) + 2;
        if (childIndex >= Tree.length) return null;
        return childIndex;
    }

}