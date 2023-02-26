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


public class Q1_b {
    private int n;
    private List<Integer>[] adjList;

    public Q1_b(int n, int[][] edges) {
        this.n = n;
        adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            adjList[u].add(v);
            adjList[v].add(u);
        }
    }

    public List<Integer> findImpactedDevices(int target) {
        boolean[] reachable = new boolean[n];
        dfs(0, reachable);
        reachable[target] = false;
        boolean[] impacted = new boolean[n];
        dfs(0, impacted, reachable);
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            if (!reachable[i] && impacted[i]) {
                result.add(i);
            }
        }
        return result;
    }

    private void dfs(int u, boolean[] visited) {
        visited[u] = true;
        for (int v : adjList[u]) {
            if (!visited[v]) {
                dfs(v, visited);
            }
        }
    }

    private void dfs(int u, boolean[] visited, boolean[] reachable) {
        visited[u] = true;
        for (int v : adjList[u]) {
            if (!visited[v] && reachable[v]) {
                dfs(v, visited, reachable);
            }
        }
    }

    public static void main(String[] args) {
        int[][] edges = {{0,1}, {0,2}, {1,3}, {1,6}, {2,4}, {4,6}, {4,5}, {5,7}};
        Q1_b network = new Q1_b(8, edges);
        List<Integer> impacted = network.findImpactedDevices(4);
        System.out.println(impacted);  // Output: [5, 7]
    }
}

