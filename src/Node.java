package src;

import java.util.ArrayList;

public class Node {
    //===========================================================================
    private int number;
    private ArrayList<Edge> edges = new ArrayList<>();
    public static ArrayList<Node> nodes = new ArrayList<>(); 
    public static ArrayList<Node> occupiedNodes = new ArrayList<>();
    private Restaurant node_holder = null;
    //===========================================================================
   
    public void setNumber(int number){
        this.number = number;
    }
    
    public void setNodeHolder(Restaurant restaurant){
        this.node_holder = restaurant;
    }
    
    public int getNum(){
        return this.number;
    }

    public Restaurant getNodeHolder(){
        return this.node_holder;
    }

}
