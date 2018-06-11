import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Stack;

public class Mapping2NewHeuristic {

    private static final double TURNRADIUS = 18 * Math.sqrt(2);
    public Field field;
    boolean[][] visited;
    Stack<Node> pathway;

    public static void main(String[] args) throws IOException, URISyntaxException {
        Mapping2NewHeuristic map = new Mapping2NewHeuristic();

        map.search(new Node(26, 62, 0, 0, 0), new Node(26, 60, 2, Integer.MAX_VALUE,0));

        map.printPathwayCoordinates();


    }
    public Mapping2NewHeuristic() throws IOException, URISyntaxException {
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
        current.setLast(new Node(-1, -1, current.direction, 0, 0));

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
                int nr = (current.direction + dr[i]) % 8;
                if(nx == 13 && ny == 13){
                    System.out.println("here");
                }
                double distance = current.distanceFromStart;
                if (i > 7) {
                    distance += f_x(i - current.direction);
                }
                else {
                    distance += r_x(dr[i]);

                }
                double k = distance(new Node(nx, ny, nr), destination, nr) + distance;
                if (isValid2(nx, ny) && !isVisited(nx, ny)){
                    visited[nx][ny] = true;
                    add(queue, k, destination, new Node(nx, ny, nr, distance, k, current));
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
        if (i % 4 == 0){
            return 10/7;
        }
        if ((i - 1) % 2 == 0){
            return Math.sqrt(2) / (1.55984/4);
        }
        if ((i - 2) % 4 == 0){
            return 10/3;
        }

        return 0;
    }
    public static double r_x(int i){

        return (4 - Math.abs(i - 4)) * Math.PI/4 + TURNRADIUS;
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


    static int[] dx = new int[]{0, 1, 1, 1, 0,-1,-1,-1, 0, 0, 0, 0, 0, 0, 0};
    static int[] dy = new int[]{1, 1, 0,-1,-1,-1, 0, 1, 0, 0, 0, 0, 0, 0, 0};
    static int[] dr = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7};



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

    public double distance (Node c, Node d, int i){
        // ADD DIAGONAL OPTIMIZED CALCULATION
        //return (Math.sqrt(  Math.pow((d.x - c.x) * f_x(i - 2), 2) + Math.pow((d.y - c.y) * f_x(i), 2) ));

    }

    static class Node{
        int x,y;
        Node last;
        double distanceFromStart;
        double k;
        enum  orientation{
            n, ne, nw, s, se, sw, e, w, none;
        }
        orientation orient;
        int direction;

        public Node(int x, int y, int direction){
            this.x = x;
            this.y = y;
        }
        public Node(int x, int y, int direction, double distanceFromStart, double k){
            this.x = x;
            this.y = y;
            this.k = k;
            this.distanceFromStart = distanceFromStart;
        }

        public Node(int x, int y, int direction, double distanceFromStart, double k, Node last){
            this.x = x;
            this.y = y;
            this.distanceFromStart = distanceFromStart;
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
