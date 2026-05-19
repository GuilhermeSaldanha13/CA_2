package CA_2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * BinaryTree class implements a complete binary tree for organizing employees in a hierarchical structure.
 * 
 * This class supports level-order (breadth-first) insertion and traversal, ensuring balanced distribution
 * of employees throughout the tree. It calculates tree height and node count, fulfilling FR3 requirements.
 * 
 * Key Features:
 * - Level-order insertion: Inserts employees from left to right, top to bottom
 * - Level-order traversal: Returns employees in breadth-first order
 * - Height calculation: Determines the maximum depth of the tree
 * - Node counting: Counts total employees in the tree structure
 * 
 * Attributes:
 * - root: The root TreeNode of the tree (null if tree is empty)
 */
public class BinaryTree {
    private TreeNode root;

    /**
     * Inserts an employee into the tree using level-order (breadth-first) insertion.
     * This ensures the tree remains complete and balanced.
     * 
     * Algorithm:
     * 1. If tree is empty, create root node
     * 2. Otherwise, use a queue to find the first empty position (left-to-right, top-to-bottom)
     * 3. Insert new node at the first available position
     *
     * @param employee The employee to insert into the tree
     */
    public void insertLevelOrder(Employee employee) {
        if (root == null) {
            root = new TreeNode(employee);
            return;
        }

        // Use BFS (breadth-first search) with a queue to find insertion point
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.remove();
            
            // Try to insert as left child
            if (current.left == null) {
                current.left = new TreeNode(employee);
                return;
            }
            queue.add(current.left);
            
            // Try to insert as right child
            if (current.right == null) {
                current.right = new TreeNode(employee);
                return;
            }
            queue.add(current.right);
        }
    }

    /**
     * Returns all employees in level-order (breadth-first) traversal.
     * Employees are ordered from top to bottom, left to right.
     * 
     * Algorithm:
     * Uses a queue to visit nodes level by level (BFS)
     *
     * @return A list of employees in level-order
     */
    public List<Employee> levelOrder() {
        List<Employee> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            TreeNode current = queue.remove();
            result.add(current.getData());
            
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
        return result;
    }

    /**
     * Calculates the height of the binary tree.
     * Height is defined as the number of edges from root to the deepest leaf node.
     * An empty tree has height 0, a single-node tree has height 1.
     *
     * @return The height of the tree
     */
    public int getHeight() {
        return computeHeight(root);
    }

    /**
     * Counts the total number of nodes (employees) in the tree.
     *
     * @return The number of nodes in the tree
     */
    public int getNodeCount() {
        return countNodes(root);
    }

    /**
     * Checks if the tree is empty.
     *
     * @return true if the tree has no nodes, false otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Recursively computes the height of a subtree.
     * Height is the maximum distance from a node to its furthest leaf.
     * Base case: null node has height 0
     *
     * @param node The root of the subtree to measure
     * @return The height of the subtree
     */
    private int computeHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = computeHeight(node.left);
        int rightHeight = computeHeight(node.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }

    /**
     * Recursively counts nodes in a subtree.
     * Base case: null node contributes 0 to the count
     *
     * @param node The root of the subtree to count
     * @return The number of nodes in the subtree
     */
    private int countNodes(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    /**
     * Prints all employees in the tree in level-order format.
     * Each employee is displayed as: FullName | ManagerType | Department | JobTitle
     */
    public void printLevelOrder() {
        List<Employee> nodes = levelOrder();
        for (Employee employee : nodes) {
            System.out.println(employee.briefInfo());
        }
    }
}

