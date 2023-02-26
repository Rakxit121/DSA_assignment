package QNS;

// 2b)
//Given an array of even numbers sorted in ascending order and an integer k,
//Find the k^th missing even number from provided array
//Input a[] ={0, 2, 6, 18, 22} k=6
//Output: 16 examples:
//Explanation: Missing even numbers on the list are 4, 8, 10, 12, 14, 16, 20 and so on and
// kth missing number is on 6th place of the list i.e. 16


public class Q2_b {
    int res = 0;

    public int minCameraCover(TreeNode root) {
        return (dfs(root) < 1 ? 1 : 0) + res;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public int dfs(TreeNode root) {
        if (root == null) return 2;
        int left = dfs(root.left), right = dfs(root.right);
        if (left == 0 || right == 0) {
            res++;
            return 1;
        }
        return left == 1 || right == 1 ? 2 : 0;
    }

    public static void main(String[] args) {
        // Create the binary tree from the given input
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0);
        root.left.left = null;
        root.left.right = new TreeNode(0);
        root.left.right.left = null;
        root.left.right.right = new TreeNode(0);
        root.right = null;

        // Instantiate the QNS.Q2_b object and call the minCameraCover method
        Q2_b solution = new Q2_b();
        int result = solution.minCameraCover(root);

        // Print the result
        System.out.println(result);  // Output: 2
    }
}
