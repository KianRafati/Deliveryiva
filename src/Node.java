package src;

import java.util.ArrayList;

public class Node {
    private int number;
    private ArrayList<Edge> edges = new ArrayList<>();
    private static ArrayList<Node> nodes = new ArrayList<>(); 
    public static ArrayList<Node> occupiedNodes = new ArrayList<>();

    public int getNum(){
        return this.number;
    }

}
