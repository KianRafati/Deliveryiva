package src;

import java.util.List;

class Main{
    public static void main(String[] args) {
//        PageHandler.showPage();
        int[][]w = src.Order.readGraphFromFile("graph(1).txt");
        List <Integer>s=src.Order.findShortestPath(w,1,4);
        System.out.println(s);
        System.out.println(src.Order.calculateTotalWeight(w,s));
    } 
}