package algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class AStar {

    static class Node {
       int row,col;
       int value;
       int distanceFromStart;
       int distanceFromEnd;
       Node prev;
       Node(int row,int col,int value){
           this.row=row;
           this.col=col;
           this.value=value;
           distanceFromStart=Integer.MAX_VALUE;
           distanceFromEnd=Integer.MAX_VALUE;
       }

       @Override
       public boolean equals(Object node2){
           if(node2 instanceof Node)
              return ((Node)node2).row==this.row && this.col==((Node) node2).col;
           return false;
       }

        @Override
        public String toString() {
            return "Node{" +
                    "row=" + row +
                    ", col=" + col +
                    ", value=" + value +
                    ", distanceFromStart=" + distanceFromStart +
                    ", distanceFromEnd=" + distanceFromEnd +
                    ", prev=" + prev +
                    '}';
        }
    }
    private static int getManhattenDistance(Node a, Node b){
        return Math.abs(a.row-b.row)+Math.abs(a.col-b.col);
    }

    List<List<Node>> adjList;
    Node startNode;
    Node endNode;
    public static final int[][] _directions=new int[][]{{-1,0},{0,1},{1,0},{0,-1}};

    private void initializeGraph(int[][] graph,int startRow, int startCol, int endRow, int endCol){
        adjList= new ArrayList<>();
        for(int i=0;i<graph.length;i++){
            List<Node> nodes= new ArrayList<>();
            for(int j=0;j<graph[0].length;j++){
                Node node= new Node(i,j,graph[i][j]);
                nodes.add(node);
            }
            adjList.add(nodes);
        }
        startNode = adjList.get(startRow).get(startCol);
        endNode = adjList.get(endRow).get(endCol);
        startNode.distanceFromStart=0;
        startNode.distanceFromEnd=getManhattenDistance(startNode,endNode);
    }

    public int[][] aStarAlgorithm(int startRow, int startCol, int endRow, int endCol, int[][] graph) {
        int noOfRows= graph.length;
        int noOfCols=graph[0].length;
        initializeGraph(graph,startRow,startCol,endRow,endCol);
        PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>((n1,n2)->{
            int g1 =  (n1.distanceFromStart==Integer.MAX_VALUE|| n1.distanceFromEnd==Integer.MAX_VALUE)?
                     Integer.MAX_VALUE:n1.distanceFromStart+n1.distanceFromEnd;
            int g2 =  (n2.distanceFromStart==Integer.MAX_VALUE|| n2.distanceFromEnd==Integer.MAX_VALUE)?
                    Integer.MAX_VALUE:n2.distanceFromStart+n2.distanceFromEnd;
         return g2-g1;
        });
        priorityQueue.add(startNode);
        boolean pathFound=false;
        while(!priorityQueue.isEmpty()){
            Node nodeToVisit=priorityQueue.poll();
            if(nodeToVisit==endNode) {
                pathFound = true;
                break;
            }
            for(int i=0;i<4;i++){
                int nextRow=nodeToVisit.row+ _directions[i][0];
                int nextCol=nodeToVisit.col+ _directions[i][1];
                if(nextRow>=0 && nextRow<noOfRows && nextCol>=0 && nextCol<noOfCols && graph[nextRow][nextCol]!=1){
                    Node next=adjList.get(nextRow).get(nextCol);
                    int distanceFromStart = nodeToVisit.distanceFromStart+1;
                    next.distanceFromEnd = getManhattenDistance(next,endNode);
                    if(distanceFromStart<next.distanceFromStart){
                       next.distanceFromStart=distanceFromStart;
                       next.prev=nodeToVisit;
                       priorityQueue.remove(next);
                       priorityQueue.add(next);
                    }
                }
            }
        }
        if (!pathFound)
            return new int[][]{{}};
        List<Node> resultPath= new ArrayList<>();
        Node cur=endNode;
        while(cur!=null ){
            resultPath.add(cur);
            cur=cur.prev;
        }
        int[][] result = new int[resultPath.size()][2];
        int k=0;
        for(int i=resultPath.size()-1;i>=0;i--){
            Node node= resultPath.get(i);
            result[k++]= new int[]{node.row,node.col};
        }
        return result;
    }
}
