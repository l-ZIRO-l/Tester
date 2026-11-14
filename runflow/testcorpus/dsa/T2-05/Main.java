public class Main {
    static class TreeNode { int val; TreeNode left, right; TreeNode(int v) { val = v; } }
    static TreeNode insert(TreeNode root, int v) {
        if (root == null) return new TreeNode(v);
        if (v < root.val) root.left = insert(root.left, v);
        else root.right = insert(root.right, v);
        return root;
    }
    static void inorder(TreeNode n) {
        if (n == null) return;
        inorder(n.left);
        System.out.print(n.val + " ");
        inorder(n.right);
    }
    public static void main(String[] args) {
        TreeNode root = null;
        for (int v : new int[]{5, 3, 8, 1, 4}) root = insert(root, v);
        inorder(root);
    }
}
