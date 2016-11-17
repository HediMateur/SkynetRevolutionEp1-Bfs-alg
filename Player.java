/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skynetrevolutionbfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

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
/**
 *
 * @author Hedi Mateur
 */
public class SkynetRevolutionBfs {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int N=4;
        ArrayList<Node> nodes = new ArrayList<Node>();
        for(int i=0;i<N;i++)
            nodes.add(new Node(i));
        int L=4;
        int E=1;
        nodes.get(1).addChild(nodes.get(3));
        nodes.get(3).addChild(nodes.get(1));
        nodes.get(2).addChild(nodes.get(3));
        nodes.get(3).addChild(nodes.get(2));
        nodes.get(0).addChild(nodes.get(1));
        nodes.get(1).addChild(nodes.get(0));
        nodes.get(0).addChild(nodes.get(2));
        nodes.get(2).addChild(nodes.get(0));
        
        System.out.println(nodes.get(1).childs.get(1).number);
        ArrayList<Node> goals = new ArrayList<Node>();
        
        goals.add(nodes.get(3));
        
        Bfs bfs = new Bfs(nodes.get(2),goals,nodes);
        Node node = bfs.compute();
        ArrayList<Node> path = new ArrayList<Node>();
        path.add(node);
        while(node.parent!=null&&node.number!=nodes.get(0).number){
            node=node.parent;
            path.add(node);
        }
            System.out.println(path.get(path.size()-1).number+" "+path.get(path.size()-2).number);
    }
}
