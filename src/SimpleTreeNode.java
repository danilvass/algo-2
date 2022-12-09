import java.util.*;

public class SimpleTreeNode<T>
{
    public T NodeValue; // значение в узле
    public SimpleTreeNode<T> Parent; // родитель или null для корня
    public List<SimpleTreeNode<T>> Children; // список дочерних узлов или null

    public SimpleTreeNode(T val, SimpleTreeNode<T> parent)
    {
        NodeValue = val;
        Parent = parent;
        Children = new LinkedList<>();
    }

}

class SimpleTree<T>
{
    public SimpleTreeNode<T> Root;

    public SimpleTree(SimpleTreeNode<T> root)
    {
        Root = root;
    }

    public void AddChild(SimpleTreeNode<T> ParentNode, SimpleTreeNode<T> NewChild)
    {
        ParentNode.Children.add(NewChild);
        NewChild.Parent = ParentNode;
    }

    public void DeleteNode(SimpleTreeNode<T> NodeToDelete)
    {
        NodeToDelete.Parent.Children.removeIf(e -> e.equals(NodeToDelete));
        NodeToDelete.Parent = null;
    }

    public List<SimpleTreeNode<T>> GetAllNodes()
    {
        if (Root == null) { return null; }
        return AllNodesFor(Root);
    }

    private List<SimpleTreeNode<T>> AllNodesFor(SimpleTreeNode<T> Node) {
        LinkedList<SimpleTreeNode<T>> list = new LinkedList<>();
        if (Node.Children.size() == 0) {
            list.add(Node);
        } else {
            list.add(Node);
            Node.Children.forEach(node -> list.addAll(AllNodesFor(node)));
        }
        return list;
    }

    public List<SimpleTreeNode<T>> FindNodesByValue(T val)
    {
        if (Root == null) { return null; }

        List<SimpleTreeNode<T>> list = FindNode(Root, val);
        if (list.size() == 0) return null;
        return list;
    }

    private List<SimpleTreeNode<T>> FindNode(SimpleTreeNode<T> Node, T val) {
        LinkedList<SimpleTreeNode<T>> list = new LinkedList<>();
        if (Node.NodeValue == val) {
            list.add(Node);
        }
        Node.Children.forEach(node -> list.addAll(FindNode(node, val)));

        return list;
    }

    public void MoveNode(SimpleTreeNode<T> OriginalNode, SimpleTreeNode<T> NewParent)
    {
        DeleteNode(OriginalNode);
        AddChild(NewParent, OriginalNode);
    }

    public int Count()
    {
        if (Root == null) { return 0; }
        return GetAllNodes().size();
    }

    public int LeafCount()
    {
        if (Root == null) { return 0; }
        return LeafCountInNode(Root);
    }

    private int LeafCountInNode(SimpleTreeNode<T> Node) {
        int count = 0;
        if (Node.Children.size() == 0) {
            return 1;
        }
        for (SimpleTreeNode<T> child: Node.Children) {
            count += LeafCountInNode(child);
        }
        return count;
    }

}