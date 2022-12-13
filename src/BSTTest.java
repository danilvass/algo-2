import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BSTTest {

    @Test
    void test_init() {
        BSTNode<String> root = new BSTNode<String>(0,"root",null);
        BST<String> sut = new BST<String>(root);
        assertEquals(root, sut.Root);
        assertFalse(sut.FindNodeByKey(0).ToLeft);
    }

    @Test
    void test_add() {
        BSTNode<String> root = new BSTNode<String>(0,"root",null);
        BST<String> sut = new BST<String>(root);
        sut.AddKeyValue(1, "1");
        sut.AddKeyValue(2, "2");
        sut.AddKeyValue(3, "3");

        assertEquals(sut.Root.NodeKey, 0);
        assertEquals(sut.FindNodeByKey(1).Node.NodeValue, "1");
        assertEquals(sut.FindNodeByKey(2).Node.NodeValue, "2");
        assertEquals(sut.FindNodeByKey(3).Node.NodeValue, "3");
    }

    @Test
    void test_findMax() {
        BSTNode<String> root = new BSTNode<String>(0,"root",null);
        BST<String> sut = new BST<String>(root);
        sut.AddKeyValue(1, "1");
        sut.AddKeyValue(2, "2");
        sut.AddKeyValue(3, "3");

        assertEquals(3, sut.FinMinMax(root, true).NodeKey);
        assertEquals(0, sut.FinMinMax(root, false).NodeKey);
    }

    @Test
    void test_delete() {
        BSTNode<String> root = new BSTNode<String>(3,"3",null);
        BST<String> sut = new BST<String>(root);
        sut.AddKeyValue(4, "4");
        sut.AddKeyValue(7, "7");
        sut.AddKeyValue(12, "12");
        sut.AddKeyValue(15, "15");

        assertNotNull(sut.FindNodeByKey(12).Node.RightChild);
        sut.DeleteNodeByKey(15);
        assertNull(sut.FindNodeByKey(12).Node.RightChild);

        sut.AddKeyValue(15, "15");
        sut.AddKeyValue(13, "13");
        sut.AddKeyValue(16, "16");
        sut.AddKeyValue(14, "14");

        assertEquals(sut.FindNodeByKey(13).Node.RightChild.NodeKey, 14);


        assertTrue(sut.DeleteNodeByKey(13));

        assertFalse(sut.DeleteNodeByKey(13));

        assertFalse(sut.FindNodeByKey(13).NodeHasKey);
        assertEquals(sut.FindNodeByKey(15).Node.LeftChild.NodeKey, 14);


        assertTrue(sut.DeleteNodeByKey(15));
        System.out.println(sut);

        assertEquals(6, sut.Count());
    }

    @Test
    void test_count() {
        BSTNode<String> root = new BSTNode<String>(3,"3",null);
        BST<String> sut = new BST<String>(root);
        assertEquals(1, sut.Count());
        sut.AddKeyValue(4, "4");
        assertEquals(2, sut.Count());
        sut.AddKeyValue(7, "7");
        assertEquals(3, sut.Count());
        sut.AddKeyValue(12, "12");
        assertEquals(4, sut.Count());
        sut.AddKeyValue(15, "15");
        assertEquals(5, sut.Count());

        sut.DeleteNodeByKey(15);
        assertEquals(4, sut.Count());

        sut.AddKeyValue(15, "15");
        assertEquals(5, sut.Count());

        sut.AddKeyValue(13, "13");
        assertEquals(6, sut.Count());

        sut.AddKeyValue(16, "16");
        assertEquals(7, sut.Count());

        sut.AddKeyValue(14, "14");
        assertEquals(8, sut.Count());

        sut.DeleteNodeByKey(13);
        assertEquals(7, sut.Count());
    }
}