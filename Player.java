/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skynetrevolutionbfs;

//LINK TO THE PROBLEM : https://www.codingame.com/ide/621004200344cf077a64c74fc25de124b39679b
//LINK TO THE PROBLEM : https://www.codingame.com/ide/621004200344cf077a64c74fc25de124b39679b
//LINK TO THE PROBLEM : https://www.codingame.com/ide/621004200344cf077a64c74fc25de124b39679b
//LINK TO THE PROBLEM : https://www.codingame.com/ide/621004200344cf077a64c74fc25de124b39679b

import java.util.*;
import java.io.*;
import java.math.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Hedi Mateur
 */
class Node{
    int number;
    ArrayList<Node> childs;
    Node parent;
    Node(int number){
        this.parent  = null;
        this.number=number;
        this.childs=new ArrayList<Node>();
    }
    Node(int number,Node parent){
        this.parent  = parent;
        this.number=number;
        this.childs=new ArrayList<Node>();
    }
    public void addChild(Node node){
        this.childs.add(node);
    }
    public ArrayList<Node> getChildren(){
        ArrayList<Node> childrens=new ArrayList<Node>();
        for(int i=0;i<childs.size();i++)
            childrens.add(new Node(childs.get(i).number,this));
        return childrens;
    }
}
class Bfs{
    Node startNode;
    Node goalNode;
    ArrayList<Node> nodes;
    ArrayList<Node> goals;
    Bfs(Node startNode, ArrayList<Node> goals,ArrayList<Node> nodes){
        this.startNode=startNode;
        this.goals=goals;
        this.nodes=nodes;
    }
    public Node compute(){
        Queue<Node> queue= new LinkedList<>();
        queue.add(startNode);
        while(!queue.isEmpty()){
            Node c = queue.remove();
            Node current=nodes.get(c.number);
            current.parent=c.parent;
            for(int i=0;i<goals.size();i++){
                if(current.number==goals.get(i).number){
                    return c;
                }
                else
            {    if(current.getChildren().isEmpty()){
                    System.out.println(current.number);
                    return null;
                }
                else{
                    queue.addAll(current.getChildren());
                    
                }
            }
            }
            
        }
        return null;
    }
}
//LINK TO THE PROBLEM : https://www.codingame.com/ide/621004200344cf077a64c74fc25de124b39679b
//LINK TO THE PROBLEM : https://www.codingame.com/ide/621004200344cf077a64c74fc25de124b39679b
//LINK TO THE PROBLEM : https://www.codingame.com/ide/621004200344cf077a64c74fc25de124b39679b
//LINK TO THE PROBLEM : https://www.codingame.com/ide/621004200344cf077a64c74fc25de124b39679b
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // the total number of nodes in the level, including the gateways
        ArrayList<Node> nodes = new ArrayList<Node>();
        for(int i=0;i<N;i++)
            nodes.add(new Node(i));
        int L = in.nextInt(); // the number of links
        int E = in.nextInt(); // the number of exit gateways
        for (int i = 0; i < L; i++) {
            int N1 = in.nextInt(); // N1 and N2 defines a link between these nodes
            int N2 = in.nextInt();
            nodes.get(N1).addChild(nodes.get(N2));
            nodes.get(N2).addChild(nodes.get(N1));
        }
        ArrayList<Node> goals = new ArrayList<Node>();
        for (int i = 0; i < E; i++) {
            goals.add(nodes.get(in.nextInt())); // the index of a gateway node
        }

        // game loop
        while (true) {
            int SI = in.nextInt(); // The index of the node on which the Skynet agent is positioned this turn
            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");
            Bfs bfs = new Bfs(nodes.get(SI),goals,nodes);
            Node node = bfs.compute();
            ArrayList<Node> path = new ArrayList<Node>();
            path.add(node);
            while(node.parent!=null&&node.number!=nodes.get(SI).number){
                node=node.parent;
                path.add(node);
                    
            }
            System.out.println(path.get(path.size()-1).number+" "+path.get(path.size()-2).number);

            // Example: 0 1 are the indices of the nodes you wish to sever the link between
            
        }
    }
}
