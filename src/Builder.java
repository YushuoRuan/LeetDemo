import java.util.ArrayList;
import java.util.List;

public class Builder{
    public TreeNode buildTree1(){

        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(6);
        TreeNode node11 = new TreeNode(2);
        TreeNode node12 = new TreeNode(4);
        node1.left = node11;
        node1.right = node12;
        root.left = node1;
        root.right = node2;

        return root;

    }

    public TreeNode buildTree2(){

        TreeNode root = new TreeNode(2);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(3);
        TreeNode node12 = new TreeNode(4);
        TreeNode node22 = new TreeNode(7);
        node1.right = node12;
        node2.right = node22;
        root.left = node1;
        root.right = node2;

        return root;
    }

    public Node buildNTree(){

        Node n5 = new Node(5, null);
        Node n6 = new Node(6, null);
        List<Node> c3 = new ArrayList<>();
        c3.add(n5); c3.add(n6);

        Node n3 = new Node(3, c3);
        Node n2 = new Node(2, null);
        Node n4 = new Node(4, null);
        List<Node> c1 = new ArrayList<>();
        c1.add(n3); c1.add(n2); c1.add(n4);
        Node n1 = new Node(1, c1);
        return n1;
    }
}
