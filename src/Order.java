package src;

import java.io.*;
import java.util.*;


public class Order {
    private int ID;
    private Restaurant restaurant;
    private Customer customer;
    private ArrayList<Food> cart = new ArrayList<>();
    private String status;
    public static String[] statuses = {
            "Waiting for restuarant's approval..",
            "Processing your order",
            "Delivery on the way",
            "Your order has been delivered"
    };

    Order(int ID, Restaurant restaurant, Customer customer, ArrayList<Food> cart) {
        this.ID = ID;
        this.restaurant = restaurant;
        this.customer = customer;
        this.cart = cart;
        this.status = statuses[0];
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public int getID() {
        return this.ID;
    }

    public Restaurant getRest() {
        return this.restaurant;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public ArrayList<Food> getCart() {
        return this.cart;
    }

    public void EditOrder() {

    }

    public void getTotalCost() {

    }

    public String showEstTime(){
        int[][]graph=readGraphFromFile("graph.txt");
        List<Integer> path= findShortestPath(graph,restaurant.getLoc().getNum(),this.customer.getLoc().getNum());
        int weight = calculateTotalWeight(graph,path);
        return Integer.toString(weight);
    }

    public List<Integer> ShowBestPath(){
        int[][]graph=readGraphFromFile("graph.txt");
        List<Integer> path= findShortestPath(graph,restaurant.getLoc().getNum(),this.customer.getLoc().getNum());
        return path;
    }

    public List<Integer> ShowPath(){
        int[][]graph=readGraphFromFile("graph.txt");
        List<Integer> path= findShortestPath(graph, restaurant.getLoc().getNum(),this.customer.getLoc().getNum());
        return path;
    }
    public void showcart() {
        for (Food food : cart) {
            System.out.println(food.getName());
            System.out.println(food.getPrice());
            System.out.println("--------------------------");
        }
    }

    public void DisplayStatus() {

    }

    public void ConfirmOrder() {

    }
    public static int calculateTotalWeight(int[][] weights, List<Integer> Path) {
        int[][] graph = weights;
        int totalWeight = 0;
        int[] path = Path.stream().mapToInt(Integer::intValue).toArray();
        for (int i = 0; i < path.length - 1; i++) {
            int source = path[i];
            int destination = path[i + 1];

            if (graph[source][destination] == 0) {
                throw new IllegalArgumentException("Path is not valid. There is no edge between " + source + " and " + destination);
            }

            totalWeight += graph[source][destination];
        }

        return totalWeight;
    }
    public static int[][] readGraphFromFile(String fileName) {
        try {
            File file = new File(fileName);
            FileReader fReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fReader);
            //String input = bufferedReader.readLine();

//            Scanner scanner = src.PageHandler.scanner(file);


            //int n = scanner.nextInt();
            String input = bufferedReader.readLine();
            String[] nums = input.split(" ");
            int n = Integer.parseInt(nums[0]);
            //int m = scanner.nextInt();
            int m = Integer.parseInt(nums[1]);


            int[][] graph = new int[n+1][n+1];

            for (int i = 0; i < m; i++) {
                input = bufferedReader.readLine();
                input = bufferedReader.readLine();
                nums = input.split("\\s");
                int source = Integer.parseInt(nums[0]);
                int destination = Integer.parseInt(nums[1]);
                int weight = Integer.parseInt(nums[2]);

                graph[source-1][destination-1] = weight;
                graph[destination-1][source-1] = weight;
            }

            return graph;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    public static List<Integer> findShortestPath(int[][] graph, int startNode, int endNode) {
        final int INF = Integer.MAX_VALUE;
        int numNodes = graph.length;
        int[] distances = new int[numNodes];
        boolean[] visited = new boolean[numNodes];
        int[] previous = new int[numNodes];

        Arrays.fill(distances, INF);
        Arrays.fill(previous, -1);

        distances[startNode] = 0;

        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
        queue.add(new Node(startNode, 0));

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            int nodeIndex = currentNode.index;
            visited[nodeIndex] = true;

            if (nodeIndex == endNode) {
                break;
            }

            for (int neighbor = 0; neighbor < numNodes; neighbor++) {
                int weight = graph[nodeIndex][neighbor];
                if (weight > 0 && !visited[neighbor]) {
                    int distance = distances[nodeIndex] + weight;
                    if (distance < distances[neighbor]) {
                        distances[neighbor] = distance;
                        previous[neighbor] = nodeIndex;
                        queue.add(new Node(neighbor, distance));
                    }
                }
            }
        }

        List<Integer> shortestPath = new ArrayList<>();
        int currentNode = endNode;
        while (currentNode != -1) {
            shortestPath.add(0, currentNode);
            currentNode = previous[currentNode];
        }

        return shortestPath;
    }

    private static class Node {
        private int index;
        private int distance;

        public Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }
    }
}

