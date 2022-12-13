import java.io.*;
import java.util.*;


class BSTNode<T>
{
    public int NodeKey; // ключ узла
    public T NodeValue; // значение в узле
    public BSTNode<T> Parent; // родитель или null для корня
    public BSTNode<T> LeftChild; // левый потомок
    public BSTNode<T> RightChild; // правый потомок	

    public BSTNode(int key, T val, BSTNode<T> parent)
    {
        NodeKey = key;
        NodeValue = val;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}

// промежуточный результат поиска
class BSTFind<T>
{
    // null если в дереве вообще нету узлов
    public BSTNode<T> Node;

    // true если узел найден
    public boolean NodeHasKey = false;

    // true, если родительскому узлу надо добавить новый левым
    public boolean ToLeft = false;

    public BSTFind() { Node = null; }
}

class BST<T>
{
    BSTNode<T> Root; // корень дерева, или null

    public BST(BSTNode<T> node)
    {
        Root = node;
    }

    public BSTFind<T> FindNodeByKey(int key)
    {
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

    public boolean AddKeyValue(int key, T val)
    {
        BSTFind<T> find = FindNodeByKey(key);
        if (find.NodeHasKey) { return false; }
        if (find.ToLeft) {
            find.Node.LeftChild = new BSTNode<>(key, val, find.Node);
        } else {
            find.Node.RightChild = new BSTNode<>(key, val, find.Node);
        }
        return true;
    }

    public BSTNode<T> FinMinMax(BSTNode<T> FromNode, boolean FindMax)
    {
        BSTNode<T> node = FromNode;
        BSTNode<T> foundNode = node;
        while (node != null) {
            foundNode = node;
            node = FindMax ? node.RightChild : node.LeftChild;
        }
        return foundNode;
    }

    public boolean DeleteNodeByKey(int key)
    {
        BSTFind<T> nodeToFind = FindNodeByKey(key);
        if (!nodeToFind.NodeHasKey || nodeToFind.Node == null) {
            return false;
        }

        BSTNode<T> nodeToDelete = nodeToFind.Node;
        BSTNode<T> oldParent = nodeToDelete.Parent;
        if (nodeToDelete.Parent == null) {
            changeParentInNode(nodeToDelete.LeftChild, nodeToDelete.RightChild, true);
            Root = nodeToDelete.RightChild;
            return true;
        }

        boolean isParentLeftChild = nodeToFind.ToLeft;

        if (!hasAnyChild(nodeToDelete)) {
            if (isParentLeftChild) {
                oldParent.LeftChild = null;
            } else {
                oldParent.RightChild = null;
            }
            return true;
        }

        BSTNode<T> successor = FinMinMax(nodeToDelete.RightChild, false);
        if (!hasAnyChild(successor)) {
            changeParentInNode(nodeToDelete.LeftChild, successor, true);
            changeParentInNode(successor, oldParent, isParentLeftChild);
        } else if (successor.RightChild != null) {
            BSTNode<T> right = successor.RightChild;
            BSTNode<T> successorParent = successor.Parent;
            changeParentInNode(right, successorParent, isParentLeftChild);

            if (isParentLeftChild) {
                oldParent.LeftChild = successor;
            } else {
                oldParent.RightChild = successor;
            }
        }

        return true;
    }

    private void changeParentInNode(BSTNode<T> node, BSTNode<T> newParent, boolean isLeft) {
        if (node == null || newParent == null) return;
        node.Parent = newParent;
        if (isLeft) {
            newParent.LeftChild = node;
        } else {
            newParent.RightChild = node;
        }
    }

    public int Count()
    {
        return getCount(Root);
    }

    private int getCount(BSTNode<T> node) {
        if (node == null) return 0;
        int count = getCount(node.LeftChild) + getCount(node.RightChild);
        return count + 1;
    }

    private boolean hasAnyChild(BSTNode<T> node) {
        return node.LeftChild != null || node.RightChild != null;
    }

}