import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.Stack;

public class Mapping2PostProcessing {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public Field field;
    boolean[][] visited;
    Stack<Node> pathway;
    public double total = 0;

    public static void main(String[] args) throws IOException, URISyntaxException {
        Mapping2PostProcessing map = new Mapping2PostProcessing();

        int[] start = {134, 10, 1};
        int[] end = {20, 140, 4};
        map.search(new Mapping2PostProcessing.Node(start[0], start[1]), new Mapping2PostProcessing.Node(end[0], end[1]));

        map.printPathwayCoordinates();
        map.displayPath();


    }
    public Mapping2PostProcessing() throws IOException, URISyntaxException {
        field = new Field();
        visited = new boolean[field.field.length][field.field[0].length];
        pathway = new Stack<>();

    }
    public boolean[][] displayPath(){
        Stack<Node> nodeStack = (Stack<Node>) pathway.clone();
        boolean[][] path = new boolean[field.field.length][field.field[0].length];

        while (nodeStack.size() != 0){
            Node c = nodeStack.pop();
            path[c.x][c.y] =  true;
        }
        for (int i = 0; i < field.field.length; i++) {
            for (int j = 0; j < field.field[i].length; j++) {
                if (i == 10 && j == 134){
                    System.out.print(ANSI_GREEN + "1" + ANSI_RESET + " ");
                }else {
                    System.out.print((path[j][i] ? ANSI_RED + "1" + ANSI_RESET : "0") + " ");
                }
            }
            System.out.println();

        }
        return path;
    }
    public void printPathwayCoordinates(){
        Stack<Node> nodeStack = (Stack<Node>) pathway.clone();
        System.out.println("# of Steps: " + (pathway.size() - 1));
        double sum = 0;

        while (nodeStack.size() != 0){
            if (nodeStack.size() == 1) {
                sum = nodeStack.peek().k;
            }
            System.out.print(nodeStack.pop() + (nodeStack.size() != 0 ? ", " : ""));

        }
        System.out.println();
        System.out.println(sum);


    }

    public Stack<Node> getPathway() {
        return pathway;
    }

    public void search(Node current, Node destination){
        pathway.clear();

        LinkedList<Node> queue = new LinkedList<>();

        visited[current.x][current.y] = true;
        current.setLast(new Node(-1, -1));

        queue.add(current);



        while (queue.size() != 0 && !found){
            current = queue.poll();
            if (current.x == destination.x && current.y == destination.y){
                found = true;
                destination = current;
                continue;
            }
            for (int i = 0; i < dx.length; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                //System.out.println(new Node(nx,ny).toString());

                if(nx == 134 && ny == 8){
                    System.out.println("here");
                }
                double f = f_x(i) + current.k;
                double k = distance(new Node(nx, ny), destination) + f ;

                if (isValid(nx, ny) && !isVisited(nx, ny)){
                    visited[nx][ny] = true;

                    add(queue, k, destination, new Node(nx, ny, f, current));
                }

            }

        }

        if (found) {

            Node path = destination;
            while (path.x != -1 && path.y != -1) {
                pathway.add(path);
                path = path.last;
            }
        }



    }
    public static double f_x(int i){
        switch (i){
            case 0:
            case 1:
                return 10/3;
            case 2:
            case 3:
                return 10/7;
            case 4:
            case 5:
            case 6:
            case 7:
                return Math.sqrt(2) / (1.55984/4);
        }
        return 0;
    }
    public void add(LinkedList<Node> queue, double k, Node destination, Node adj){
        if (queue.size()==0){
            queue.add(adj);
            return;
        }
        int i = 0;
        while (i < queue.size() && distance(queue.get(i), destination) < k){
            i++;
        }
        if(i == queue.size()){
            queue.add(adj);
        }else {
            queue.add(i, adj);
        }
    }


    static int[] dx = new int[]{-1,1,0,0, 1, -1, 1, -1};
    static int[] dy = new int[]{0,0,-1,1, 1, 1, -1, -1};
    static boolean found = false;

    public boolean isValid(int x, int y){
        return x >= 0 && x < field.field.length && y >= 0 && y < field.field[x].length && field.field[x][y]!=0;

    }
    public boolean isValid2(int x, int y){
        for (int i = x-9; i < x+9; i++) {
            for (int j = y-9; j < y+9; j++) {
                if(!(i >= 0 && i < field.field.length && j >= 0 && j < field.field[i].length/* && field.field[i][j]!=0*/)){
                    return false;
                }
            }

        }
        return true;
    }

    public boolean isVisited(int x, int y) {
        return x >= 0 && x < field.field.length && y >= 0 && y < field.field[x].length && visited[x][y];
    }

    public double distance (Node c, Node d){
        return (Math.sqrt(  Math.pow(d.x - c.x, 2) + Math.pow(d.y - c.y, 2) ));

    }

    static class Node{
        int x,y;
        Node last;
        enum  orientation{
            n, ne, nw, s, se, sw, e, w, none;
        }
        orientation orient;
        double k;

        public Node(int x, int y, double k, Node last) {
            this.x = x;
            this.y = y;
            this.k = k;
            this.last = last;
        }

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }

        public Node(int x, int y, Node last){
            this.x = x;
            this.y = y;
            this.last = last;
        }

        public void setLast(Node last) {
            this.last = last;
        }

        @Override
        public String toString() {
            return "(" + this.x + ", " + this.y + ")";
        }
    }


}
