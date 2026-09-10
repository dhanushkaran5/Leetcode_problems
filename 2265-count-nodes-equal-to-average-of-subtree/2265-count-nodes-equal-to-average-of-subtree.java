class Solution {

    private int n = 0;

    public int averageOfSubtree(TreeNode root) {
        rec(root);
        return n;
    }

    public ave rec(TreeNode root) {
        if (root == null) return new ave(0, 0);
        var l = rec(root.left);
        var r = rec(root.right);
        var ave = new ave(root.val + l.s() + r.s(), 1 + l.n() + r.n());
        if (root.val == ave.s() / ave.n()) n++;
        return ave;
    }

    private record ave(int s, int n) {};
}