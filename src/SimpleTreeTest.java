import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleTreeTest {

    @Test
    void test_add() {
        SimpleTreeNode<Integer> root = new SimpleTreeNode<Integer>(0, null);
        SimpleTree<Integer> tree = new SimpleTree<>(root);
        SimpleTreeNode<Integer> nodeToAdd = new SimpleTreeNode<Integer>(1, null);

        tree.AddChild(root, nodeToAdd);

        assertEquals(1, tree.Root.Children.size());
        assertEquals(root, nodeToAdd.Parent);
        assertTrue(assertContainsChild(root, nodeToAdd));
    }

    @Test
    void test_delete() {
        SimpleTreeNode<Integer> root = new SimpleTreeNode<Integer>(0, null);
        SimpleTree<Integer> tree = new SimpleTree<>(root);

        SimpleTreeNode<Integer> nodeToDelete = new SimpleTreeNode<Integer>(1, root);
        SimpleTreeNode<Integer> someChildNode = new SimpleTreeNode<Integer>(2, root);
        tree.AddChild(nodeToDelete, someChildNode);

        tree.DeleteNode(nodeToDelete);

        assertNull(nodeToDelete.Parent);
        assertEquals(0, tree.Root.Children.size());
    }

    @Test
    void test_getAllNodes() {
        SimpleTreeNode<Integer> root = new SimpleTreeNode<Integer>(0, null);
        SimpleTree<Integer> tree = new SimpleTree<>(root);

        SimpleTreeNode<Integer> nodeToDelete1 = new SimpleTreeNode<Integer>(1, null);
        SimpleTreeNode<Integer> nodeToDelete2 = new SimpleTreeNode<Integer>(2, null);
        SimpleTreeNode<Integer> nodeToDelete3 = new SimpleTreeNode<Integer>(3, null);

        SimpleTreeNode<Integer> nodeToDelete4 = new SimpleTreeNode<Integer>(4, null);
        SimpleTreeNode<Integer> nodeToDelete5 = new SimpleTreeNode<Integer>(5, null);
        SimpleTreeNode<Integer> nodeToDelete6 = new SimpleTreeNode<Integer>(6, null);

        SimpleTreeNode<Integer> nodeToDelete7 = new SimpleTreeNode<Integer>(7, null);
        SimpleTreeNode<Integer> nodeToDelete8 = new SimpleTreeNode<Integer>(8, null);
        SimpleTreeNode<Integer> nodeToDelete9 = new SimpleTreeNode<Integer>(9, null);

        tree.AddChild(root, nodeToDelete1);
        tree.AddChild(root, nodeToDelete2);
        tree.AddChild(root, nodeToDelete3);

        tree.AddChild(nodeToDelete1, nodeToDelete4);
        tree.AddChild(nodeToDelete2, nodeToDelete5);
        tree.AddChild(nodeToDelete3, nodeToDelete6);

        tree.AddChild(nodeToDelete1, nodeToDelete7);
        tree.AddChild(nodeToDelete1, nodeToDelete8);
        tree.AddChild(nodeToDelete1, nodeToDelete9);

        List<SimpleTreeNode<Integer>> allNodes = tree.GetAllNodes();
        assertEquals(10, allNodes.size());
        LinkedList<SimpleTreeNode<Integer>> list = new LinkedList<>();
        list.add(root);
        list.add(nodeToDelete1);
        list.add(nodeToDelete2);
        list.add(nodeToDelete3);
        list.add(nodeToDelete4);
        list.add(nodeToDelete5);
        list.add(nodeToDelete6);
        list.add(nodeToDelete7);
        list.add(nodeToDelete8);
        list.add(nodeToDelete9);

        assertTrue(assertAllNodesAreEqual(allNodes,list));
    }

    @Test
    void test_getNodeByValue() {
        SimpleTreeNode<Integer> root = new SimpleTreeNode<Integer>(0, null);
        SimpleTree<Integer> tree = new SimpleTree<>(root);

        SimpleTreeNode<Integer> nodeToDelete1 = new SimpleTreeNode<Integer>(1, null);
        SimpleTreeNode<Integer> nodeToDelete2 = new SimpleTreeNode<Integer>(2, null);
        SimpleTreeNode<Integer> nodeToDelete3 = new SimpleTreeNode<Integer>(3, null);

        SimpleTreeNode<Integer> nodeToDelete4 = new SimpleTreeNode<Integer>(4, null);
        SimpleTreeNode<Integer> nodeToDelete5 = new SimpleTreeNode<Integer>(4, null);
        SimpleTreeNode<Integer> nodeToDelete6 = new SimpleTreeNode<Integer>(4, null);

        SimpleTreeNode<Integer> nodeToDelete7 = new SimpleTreeNode<Integer>(5, null);
        SimpleTreeNode<Integer> nodeToDelete8 = new SimpleTreeNode<Integer>(5, null);
        SimpleTreeNode<Integer> nodeToDelete9 = new SimpleTreeNode<Integer>(6, null);

        tree.AddChild(root, nodeToDelete1);
        tree.AddChild(root, nodeToDelete2);
        tree.AddChild(root, nodeToDelete3);

        tree.AddChild(nodeToDelete1, nodeToDelete4);
        tree.AddChild(nodeToDelete2, nodeToDelete5);
        tree.AddChild(nodeToDelete3, nodeToDelete6);

        tree.AddChild(nodeToDelete1, nodeToDelete7);
        tree.AddChild(nodeToDelete1, nodeToDelete8);
        tree.AddChild(nodeToDelete1, nodeToDelete9);

        List<SimpleTreeNode<Integer>> foundNode = tree.FindNodesByValue(1);
        assertEquals(foundNode.size(), 1);
        assertEquals(foundNode.get(0).NodeValue, 1);
        assertEquals(nodeToDelete1, foundNode.get(0));

        List<SimpleTreeNode<Integer>> foundNode4 = tree.FindNodesByValue(4);
        assertEquals(foundNode4.size(), 3);
        foundNode4.forEach(node -> assertEquals(node.NodeValue, 4));

        LinkedList<SimpleTreeNode<Integer>> list = new LinkedList<>();
        list.add(nodeToDelete4);
        list.add(nodeToDelete5);
        list.add(nodeToDelete6);
        assertAllNodesAreEqual(foundNode4, list);
    }

    @Test
    void test_moveNode() {
        SimpleTreeNode<Integer> root = new SimpleTreeNode<Integer>(0, null);
        SimpleTree<Integer> tree = new SimpleTree<>(root);

        SimpleTreeNode<Integer> nodeToDelete1 = new SimpleTreeNode<Integer>(1, null);
        SimpleTreeNode<Integer> nodeToDelete2 = new SimpleTreeNode<Integer>(2, null);
        SimpleTreeNode<Integer> nodeToDelete3 = new SimpleTreeNode<Integer>(3, null);

        SimpleTreeNode<Integer> nodeToDelete4 = new SimpleTreeNode<Integer>(4, null);
        SimpleTreeNode<Integer> nodeToDelete5 = new SimpleTreeNode<Integer>(5, null);
        SimpleTreeNode<Integer> nodeToDelete6 = new SimpleTreeNode<Integer>(6, null);

        tree.AddChild(root, nodeToDelete1);
        tree.AddChild(root, nodeToDelete2);
        tree.AddChild(root, nodeToDelete3);

        tree.AddChild(nodeToDelete2, nodeToDelete4);
        tree.AddChild(nodeToDelete2, nodeToDelete5);
        tree.AddChild(nodeToDelete3, nodeToDelete6);

        tree.MoveNode(nodeToDelete1, nodeToDelete4);
        assertEquals(nodeToDelete4, nodeToDelete1.Parent);
        assertEquals(2, root.Children.size());
        assertTrue(nodeToDelete4.Children.contains(nodeToDelete1));
    }

    @Test
    void test_count() {
        SimpleTreeNode<Integer> root = new SimpleTreeNode<Integer>(0, null);
        SimpleTree<Integer> tree = new SimpleTree<>(root);

        SimpleTreeNode<Integer> nodeToDelete1 = new SimpleTreeNode<Integer>(1, null);
        SimpleTreeNode<Integer> nodeToDelete2 = new SimpleTreeNode<Integer>(2, null);
        SimpleTreeNode<Integer> nodeToDelete3 = new SimpleTreeNode<Integer>(3, null);

        SimpleTreeNode<Integer> nodeToDelete4 = new SimpleTreeNode<Integer>(4, null);
        SimpleTreeNode<Integer> nodeToDelete5 = new SimpleTreeNode<Integer>(5, null);
        SimpleTreeNode<Integer> nodeToDelete6 = new SimpleTreeNode<Integer>(6, null);

        SimpleTreeNode<Integer> nodeToDelete7 = new SimpleTreeNode<Integer>(7, null);
        SimpleTreeNode<Integer> nodeToDelete8 = new SimpleTreeNode<Integer>(8, null);
        SimpleTreeNode<Integer> nodeToDelete9 = new SimpleTreeNode<Integer>(9, null);

        tree.AddChild(root, nodeToDelete1);
        tree.AddChild(root, nodeToDelete2);
        tree.AddChild(root, nodeToDelete3);

        tree.AddChild(nodeToDelete1, nodeToDelete4);
        tree.AddChild(nodeToDelete2, nodeToDelete5);
        tree.AddChild(nodeToDelete3, nodeToDelete6);

        tree.AddChild(nodeToDelete1, nodeToDelete7);
        tree.AddChild(nodeToDelete1, nodeToDelete8);
        tree.AddChild(nodeToDelete1, nodeToDelete9);

        assertEquals(10, tree.Count());
    }

    @Test
    void test_leafCount() {
        SimpleTreeNode<Integer> root = new SimpleTreeNode<Integer>(0, null);
        SimpleTree<Integer> tree = new SimpleTree<>(root);

        SimpleTreeNode<Integer> nodeToDelete1 = new SimpleTreeNode<Integer>(1, null);
        SimpleTreeNode<Integer> nodeToDelete2 = new SimpleTreeNode<Integer>(2, null);
        SimpleTreeNode<Integer> nodeToDelete3 = new SimpleTreeNode<Integer>(3, null);

        SimpleTreeNode<Integer> nodeToDelete4 = new SimpleTreeNode<Integer>(4, null);
        SimpleTreeNode<Integer> nodeToDelete5 = new SimpleTreeNode<Integer>(5, null);
        SimpleTreeNode<Integer> nodeToDelete6 = new SimpleTreeNode<Integer>(6, null);

        SimpleTreeNode<Integer> nodeToDelete7 = new SimpleTreeNode<Integer>(7, null);
        SimpleTreeNode<Integer> nodeToDelete8 = new SimpleTreeNode<Integer>(8, null);
        SimpleTreeNode<Integer> nodeToDelete9 = new SimpleTreeNode<Integer>(9, null);

        tree.AddChild(root, nodeToDelete1);
        tree.AddChild(root, nodeToDelete2);
        tree.AddChild(root, nodeToDelete3);

        tree.AddChild(nodeToDelete1, nodeToDelete4);
        tree.AddChild(nodeToDelete2, nodeToDelete5);
        tree.AddChild(nodeToDelete3, nodeToDelete6);

        tree.AddChild(nodeToDelete4, nodeToDelete7);
        tree.AddChild(nodeToDelete5, nodeToDelete8);
        tree.AddChild(nodeToDelete6, nodeToDelete9);

        assertEquals(3, tree.LeafCount());
    }

    private boolean assertAllNodesAreEqual(List<SimpleTreeNode<Integer>> actual, List<SimpleTreeNode<Integer>> expected) {
        return new HashSet<>(expected).equals(new HashSet<>(actual));
    }

    private boolean assertContainsChild(SimpleTreeNode<Integer> list, SimpleTreeNode<Integer> node) {
        SimpleTreeNode<Integer> foundChild;
        foundChild = list.Children.stream()
                .filter(e -> equals(node))
                .findFirst()
                .orElse(null);

        return foundChild == null;
    }

}