package QNS;
// 7b)	Write multithreaded web crawler
//
//
import java.util.*;
import java.util.concurrent.*;
//
//public class Q7_b {
//
//    private final ExecutorService executorService;
//    private final Set<String> visitedUrls;
//    private final Queue<String> unvisitedUrls;
//
//    public Q7_b(int numThreads) {
//        this.executorService = Executors.newFixedThreadPool(numThreads);
//        this.visitedUrls = ConcurrentHashMap.newKeySet();
//        this.unvisitedUrls = new ConcurrentLinkedQueue<>();
//    }
//
//    public void crawl(String startUrl) {
//        unvisitedUrls.offer(startUrl);
//        while (!unvisitedUrls.isEmpty() && visitedUrls.size() < 1000) {
//            String url = unvisitedUrls.poll();
//            if (!visitedUrls.contains(url)) {
//                visitedUrls.add(url);
//                executorService.execute(() -> {
//                    List<String> links = getLinks(url);
//                    for (String link : links) {
//                        if (!visitedUrls.contains(link)) {
//                            unvisitedUrls.offer(link);
//                        }
//                    }
//                });
//            }
//        }
//        executorService.shutdown();
//    }
//
//    private List<String> getLinks(String url) {
//        // code to fetch links from url
//        return null;
//    }
//
//    public static void main(String[] args) {
//        Q7_b crawler = new Q7_b(10);
//        crawler.crawl("https://boxnovel.com");
//    }
//}


import java.util.*;

public class Q7_b {
    Queue<String> queue = new LinkedList<>();
    Set<String> visited = new HashSet<>();
    int workingThreads = 0;

    public void crawl() {
        OUTER_LOOP: while(true) {
            String nextUrl;
            synchronized(this) {
                while(queue.isEmpty()) {
                    if(workingThreads == 0) {
                        break OUTER_LOOP;
                    }
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                nextUrl = queue.poll();
                workingThreads++;
            }
            List<String> URLs = getLinks(nextUrl);

            synchronized(this) {
                for(String newUrl: URLs) {  // 'URLs' instead of 'urls'
                    if(!visited.contains(newUrl)) {
                        queue.offer(newUrl);
                        visited.add(newUrl);
                    }
                }
                workingThreads--;
                notifyAll();
            }
        }
    }

    // Sample method to get a list of links on a webpage
    public List<String> getLinks(String url) {
        List<String> links = new ArrayList<>();
        // Code to fetch links goes here
        return links;
    }

    public static void main(String[] args) {
        // Create a new instance of Q7_b
        Q7_b webCrawler = new Q7_b();

        // Add a starting URL to the queue
        String startingUrl = "https://http://www.flyfrontier.com";
        webCrawler.queue.offer(startingUrl);
        webCrawler.visited.add(startingUrl);

        // Create an array of worker threads
        Thread[] workers = new Thread[5];
        for (int i = 0; i < workers.length; i++) {
            // Each worker runs the crawl() method of the webCrawler instance
            workers[i] = new Thread(webCrawler::crawl);
            workers[i].start();
        }

        // Wait for all worker threads to finish
        for (Thread worker : workers) {
            try {
                worker.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Print the URLs that were visited
        System.out.println("Visited URLs:");
        for (String url : webCrawler.visited) {
            System.out.println(url);
        }
    }

}
