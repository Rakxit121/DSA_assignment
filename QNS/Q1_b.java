package QNS;//Question 1 b)
// Assume you were hired to create an application for an ISP, and there is n number of network devices,
// such as routers, that are linked together to provides internet access to home user users. You are given
// a 2D array that represents network connections between these network devices such that a[i]=[xi,yi] where
// xi is connected to yi device.
// Suppose there is a power outage on a certain device provided as int n represents
// id of the device on which power failure occurred)), Write an algorithm to return impacted network devices due
// to breakage of the link between network devices. These impacted device list assists you notify linked consumers
// that there is a power outage and it will take some time to rectify an issue. Note that: node 0 will always
// represent a source of internet or gateway to international network..
//Input: edges= {{0,1}, {0,2}, {1,3}, {1,6}, {2,4}, {4,6}, {4,5}, {5,7}}
//Target Device (On which power Failure occurred): 4
//Output (Impacted Device List) = {5,7}
//Explanation: power failure on network device 4 will disconnect 5 and 7 from internet

import java.util.*;

//public class QNS.Q1_b {





//    public static Set<Integer> findImpactedDevices(int[][] connections, int n) {
//        List<List<Integer>> adjList = new ArrayList<>(n);
//        for(int i = 0; i < n; i++) {
//            adjList.add(new ArrayList<>());
//        }
//        for(int[] connection : connections) {
//            int u = connection[0];
//            int v = connection[1];
//            adjList.get(u).add(v);
//            adjList.get(v).add(u);
//        }
//
//        Set<Integer> impactedDevices = new HashSet<>();
//        Queue<Integer> queue = new LinkedList<>();
//        impactedDevices.add(n);
//        queue.offer(n);
//
//        while(!queue.isEmpty()) {
//            int cur = queue.poll();
//            for(int neighbor : adjList.get(cur)) {
//                if(!impactedDevices.contains(neighbor)) {
//                    impactedDevices.add(neighbor);
//                    queue.offer(neighbor);
//                }
//            }
//        }
//
//        impactedDevices.remove(0);
//        return impactedDevices;
//    }
//
//    public static void main(String[] args) {
//        int[][] connections = {{0, 1}, {0, 2}, {1, 3}, {2, 3}, {3, 4}, {4, 5}, {4, 6}, {5, 6}};
//        int n = 3;
//        Set<Integer> impactedDevices = findImpactedDevices(connections, n);
//        System.out.println("Impacted devices: " + impactedDevices);
//    }
//}


// wrong \\


//public class Q1_b {
//    private int n;
//    private List<Integer>[] adjList;
//
//    public Q1_b(int n, int[][] edges) {
//        this.n = n;
//        adjList = new ArrayList[n];
//        for (int i = 0; i < n; i++) {
//            adjList[i] = new ArrayList<>();
//        }
//        for (int[] edge : edges) {
//            int u = edge[0], v = edge[1];
//            adjList[u].add(v);
//            adjList[v].add(u);
//        }
//    }
//
//    public List<Integer> findImpactedDevices(int target) {
//        boolean[] reachable = new boolean[n];
//        dfs(0, reachable);
//        reachable[target] = false;
//        boolean[] impacted = new boolean[n];
//        dfs(0, impacted, reachable);
//        List<Integer> result = new ArrayList<>();
//        for (int i = 1; i < n; i++) {
//            if (!reachable[i] && impacted[i]) {
//                result.add(i);
//            }
//        }
//        return result;
//    }
//
//    private void dfs(int u, boolean[] visited) {
//        visited[u] = true;
//        for (int v : adjList[u]) {
//            if (!visited[v]) {
//                dfs(v, visited);
//            }
//        }
//    }
//
//    private void dfs(int u, boolean[] visited, boolean[] reachable) {
//        visited[u] = true;
//        for (int v : adjList[u]) {
//            if (!visited[v] && reachable[v]) {
//                dfs(v, visited, reachable);
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        int[][] edges = {{0,1}, {0,2}, {1,3}, {1,6}, {2,4}, {4,6}, {4,5}, {5,7}};
//        Q1_b network = new Q1_b(8, edges);
//        List<Integer> impacted = network.findImpactedDevices(4);
//        System.out.println(impacted);  // Output: [5, 7]
//    }
//}



import java.util.*;

import java.util.ArrayList;

class Q1_b {

    // Add an edge between two nodes in the adjacency matrix
    static void addEdge(ArrayList<ArrayList<Integer>> am, int s, int d) {
        am.get(s).add(d);
        am.get(d).add(s);
    }

    // Remove an edge between two nodes in the adjacency matrix
    static void removeEdge(ArrayList<ArrayList<Integer>> am, int s, int d) {
        if (am.get(s).contains(d)) {
            am.get(s).remove(Integer.valueOf(d));
            System.out.println("removed edge between " + s + " and " + d);
        }
        if (am.get(d).contains(s)) {
            am.get(d).remove(Integer.valueOf(s));
            System.out.println("removed edge between " + d + " and " + s);
        }
    }

    // Remove all edges connected to a given node
    private static void printDisconnectedNodes(ArrayList<ArrayList<Integer>> am, int disconnectedNode) {
        for (int i = am.get(disconnectedNode).size() - 1; i >= 0; i--) {
            int neighbor = am.get(disconnectedNode).get(i);
            System.out.println("Removing edge between " + disconnectedNode + " and " + neighbor);
            removeEdge(am, disconnectedNode, neighbor);
        }
    }

    // Check if there is a path between two nodes using DFS
    static boolean isReachable(ArrayList<ArrayList<Integer>> am, int s, int d) {
        boolean[] visited = new boolean[am.size()];
        return dfs(am, visited, s, d);
    }

    // Recursive DFS function to check for a path between two nodes
    static boolean dfs(ArrayList<ArrayList<Integer>> am, boolean[] visited, int s, int d) {
        visited[s] = true;
        if (s == d) {
            return true;
        }
        for (int i = 0; i < am.get(s).size(); i++) {
            int v = am.get(s).get(i);
            if (!visited[v] && dfs(am, visited, v, d)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        // Set up the graph
        int V = 8;
        ArrayList<ArrayList<Integer>> am = new ArrayList<ArrayList<Integer>>(V);
        for (int i = 0; i < V; i++) {
            am.add(new ArrayList<Integer>());
        }
        addEdge(am, 0, 1);
        addEdge(am, 0, 2);
        addEdge(am, 1, 3);
        addEdge(am, 2, 4);
        addEdge(am, 1, 6);
        addEdge(am, 4, 6);
        addEdge(am, 4, 5);
        addEdge(am, 5, 7);

        // Remove all edges connected to a given node
        int disconnectedNode = 4;
        printDisconnectedNodes(am, disconnectedNode);

        // Check for disconnected nodes by checking for a path to a given node
        int nodes[] = {0, 1, 2, 3, 4, 5, 6, 7};
        int destination = 0;
        for (int i = 0; i < nodes.length; i++) {
            int source = nodes[i];
            boolean disconn = isReachable(am, source, destination);
            if (disconn == false) {
                System.out.println("disconnected node: " + nodes[i]);
            }
        }
    }
}