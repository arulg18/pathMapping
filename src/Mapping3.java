import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class Mapping3 {

    public Field field;
    boolean[][] visited;
    Stack<Node> pathway;

    public static void main(String[] args) throws IOException, URISyntaxException {

        Mapping3 map = new Mapping3();

        map.search(new Node(26, 62, 0), new Node(26, 60, Integer.MAX_VALUE));

        map.printPathwayCoordinates();


    }
    public Mapping3() throws IOException, URISyntaxException {
        field = new Field();
        visited = new boolean[field.field.length][field.field[0].length];
        pathway = new Stack<>();

    }
    public void printPathwayCoordinates(){
        ListIterator<Node> nodeListIterator = pathway.listIterator();
        System.out.println("# of Steps: " + (pathway.size() - 1));

        while (nodeListIterator.hasNext()){
            System.out.print(nodeListIterator.next() + (nodeListIterator.hasNext() ? ", " : ""));
        }
    }

    public Stack<Node> getPathway() {
        return pathway;
    }

    public void search(Node current, Node destination){
        pathway.clear();


        LinkedList<Node> queue = new LinkedList<>();

        visited[current.x][current.y] = true;
        current.setLast(new Node(-1, -1, 0));

        queue.add(current);

        while (queue.size() != 0){
            current = queue.poll();
            if (current.x == destination.x && current.y == destination.y){
                found = true;
                destination = current;
                continue;
            }
            for (int i = 0; i < dx.length; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                double k = f_x(i) + current.k;
                if (isValid2(nx, ny)){
                    visited[nx][ny] = true;
                    add(queue, k, destination, new Node(nx, ny, k, current));
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
    public int binaryInsert(LinkedList<Node> queue, int l, int r, double k) {

        while (true) {
            int curIn = (r + l) / 2;
            if (l == curIn) {
                if (queue.get(curIn).k > k) {
                    return curIn;
                }
            }
            if (queue.get(curIn).k < k) {
                l = curIn + 1; // its in the upper
                if (l > r) {
                    return curIn += 1;
                }
            } else if (l > r) {
                return curIn;
            } else {
                r = curIn - 1; // its in the lower
            }
        }
    }
    public void add(LinkedList<Node> queue, double k, Node destination, Node adj){
        if (queue.size()==0){
            queue.add(adj);
            return;
        }
        queue.add(binaryInsert(queue, 0, queue.size() - 1, adj.k), adj);
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
                if(!(i >= 0 && i < field.field.length && j >= 0 && j < field.field[i].length && field.field[i][j]!=0)){
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
        double k;
        enum  orientation{
            n, ne, nw, s, se, sw, e, w, none;
        }
        orientation orient;

        public void setK(double k) {
            this.k = k;
        }

        public Node(int x, int y, int k){
            this.x = x;
            this.y = y;
            this.k = k;
        }

        public Node(int x, int y, double k, Node last){
            this.x = x;
            this.y = y;
            this.k = k;
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
