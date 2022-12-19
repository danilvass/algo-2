import java.io.*;
import java.util.*;


class BSTNode<T> {
    public int NodeKey; // ключ узла
    public T NodeValue; // значение в узле
    public BSTNode<T> Parent; // родитель или null для корня
    public BSTNode<T> LeftChild; // левый потомок
    public BSTNode<T> RightChild; // правый потомок	

    public BSTNode(int key, T val, BSTNode<T> parent) {
        NodeKey = key;
        NodeValue = val;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}

// промежуточный результат поиска
class BSTFind<T> {
    // null если в дереве вообще нету узлов
    public BSTNode<T> Node;

    // true если узел найден
    public boolean NodeHasKey = false;

    // true, если родительскому узлу надо добавить новый левым
    public boolean ToLeft = false;

    public BSTFind() {
        Node = null;
    }
}

class BST<T> {
    BSTNode<T> Root; // корень дерева, или null

    public BST(BSTNode<T> node) {
        Root = node;
    }

    public BSTFind<T> FindNodeByKey(int key) {
        BSTFind<T> find = new BSTFind<T>();
        BSTNode<T> node = Root;
        while (node != null) {
            if (node.NodeKey == key) {
                find.Node = node;
                find.NodeHasKey = true;
                return find;
            }

            find.Node = node;
            if (key >= node.NodeKey) {
                find.ToLeft = false;
                node = node.RightChild;
            } else {
                find.ToLeft = true;
                node = node.LeftChild;
            }
        }
        return find;
    }

    public boolean AddKeyValue(int key, T val) {
        if (Root == null) {
            Root = new BSTNode<>(key, val, null);
            return true;
        }
        BSTFind<T> find = FindNodeByKey(key);
        if (find.NodeHasKey) {
            return false;
        }
        if (find.ToLeft) {
            find.Node.LeftChild = new BSTNode<>(key, val, find.Node);
        } else {
            find.Node.RightChild = new BSTNode<>(key, val, find.Node);
        }
        return true;
    }

    public BSTNode<T> FinMinMax(BSTNode<T> FromNode, boolean FindMax) {
        BSTNode<T> node = FromNode;
        BSTNode<T> foundNode = node;
        while (node != null) {
            foundNode = node;
            node = FindMax ? node.RightChild : node.LeftChild;
        }
        return foundNode;
    }

    public ArrayList<BSTNode> WideAllNodes() {
        ArrayList<BSTNode> list = new ArrayList<>();
        if (Root == null) {
            return list;
        }

        ArrayList<BSTNode> queue = new ArrayList<>();
        queue.add(Root);

        while (queue.size() > 0) {
            BSTNode tempNode = queue.remove(0);
            list.add(tempNode);

            if (tempNode.LeftChild != null) {
                queue.add(tempNode.LeftChild);
            }
            if (tempNode.RightChild != null) {
                queue.add(tempNode.RightChild);
            }
        }
        return list;
    }

    public ArrayList<BSTNode> DeepAllNodes(int order) {
        if (order == 0) return inOrder(Root);
        if (order == 1) return postOrder(Root);
        if (order == 2) return preOrder(Root);

        throw new IllegalArgumentException("Incorrect order: " + order);
    }

    private ArrayList<BSTNode> inOrder(BSTNode node) {
        ArrayList<BSTNode> list = new ArrayList<>();
        if (node == null) return list;
        list.addAll(preOrder(node.LeftChild));
        list.add(node);
        list.addAll(preOrder(node.RightChild));
        return list;
    }

    private ArrayList<BSTNode> preOrder(BSTNode node) {
        ArrayList<BSTNode> list = new ArrayList<>();
        if (node == null) return list;
        list.add(node);
        list.addAll(preOrder(node.LeftChild));
        list.addAll(preOrder(node.RightChild));
        return list;
    }

    private ArrayList<BSTNode> postOrder(BSTNode node) {
        ArrayList<BSTNode> list = new ArrayList<>();
        if (node == null) return list;
        list.addAll(preOrder(node.LeftChild));
        list.addAll(preOrder(node.RightChild));
        list.add(node);
        return list;
    }

    //TODO: удалить этот костыль
    private class DeletionResult {
        boolean isDeleted = false;
    }

    public boolean DeleteNodeByKey(int key) {
        DeletionResult deletionResult = new DeletionResult();
        Root = deleteNode(Root, key, deletionResult);
        return deletionResult.isDeleted;
    }

    public BSTNode<T> deleteNode(BSTNode<T> root, int key, DeletionResult deletionResult) {
        if (root == null) {
            return null;
        }

        if (key > root.NodeKey) {
            root.RightChild = deleteNode(root.RightChild, key, deletionResult);
        } else if (key < root.NodeKey) {
            root.LeftChild = deleteNode(root.LeftChild, key, deletionResult);
        } else {
            deletionResult.isDeleted = true;

            if (root.LeftChild == null && root.RightChild == null) {
                root = null;
            } else if (root.RightChild != null) {
                root.NodeKey = successor(root);
                root.RightChild = deleteNode(root.RightChild, root.NodeKey, deletionResult);
            } else {
                root.NodeKey = predecessor(root);
                root.LeftChild = deleteNode(root.LeftChild, root.NodeKey, deletionResult);
            }
        }

        return root;
    }

    private int successor(BSTNode<T> root) {
        root = root.RightChild;
        while (root.LeftChild != null) {
            root = root.LeftChild;
        }
        return root.NodeKey;
    }

    private int predecessor(BSTNode<T> root) {
        root = root.LeftChild;
        while (root.RightChild != null) {
            root = root.RightChild;
        }
        return root.NodeKey;
    }

    public int Count() {
        return getCount(Root);
    }

    private int getCount(BSTNode<T> node) {
        if (node == null) return 0;
        int count = getCount(node.LeftChild) + getCount(node.RightChild);
        return count + 1;
    }
    
}