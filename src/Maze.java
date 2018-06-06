/**
 * Write a description of class Maze here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Maze {
    // instance variables - replace the example below with your own
    static String[][] grid =
            {{"_", "_", "_", "_", "_", "_", "_", "_"},
                    {"_", "X", "X", "_", "X", "_", "X", "_"},
                    {"_", "X", "_", "_", "X", "_", "X", "_"},
                    {"X", "X", "X", "X", "X", "_", "X", "_"},
                    {"_", "X", "_", "X", "X", "_", "_", "_"},
                    {"_", "X", "_", "_", "_", "_", "X", "_"},
                    {"_", "X", "X", "X", "X", "_", "X", "X"},
                    {"_", "_", "_", "_", "_", "_", "_", "_"}};


    static boolean[][] visited = new boolean[8][8];
    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};
    static boolean done = false;

    /**
     * Constructor for objects of class gfg
     */
    public Maze() {
        //leave blank

    }

    public static void main(String[] args) {
        search(0, 0, 7, 7);
        System.out.println(done);
        for (boolean[] x : visited) {
            for (boolean i : x) {
                System.out.print(i ? "0 " : "1 ");
            }
            System.out.println();
        }
    }

    public void printMaze() {

    }

    public boolean move(int row, int column) {
        return true;
    }

    public static void search(int x, int y, int destX, int destY) {
        if (destX == 7 && destY == 6) {
            System.out.println("here");
        }
        if (x == destX && y == destY) {
            done = true;
        }
        if (isVisited(x, y)) return;
        visited[x][y] = true;
        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (isValid(nx, ny)) {
                search(nx, ny, destX, destY);
            }
        }
    }

    public static boolean isValid(int x, int y) {
        return x >= 0 && x < visited.length && y >= 0 && y < visited[x].length && !grid[x][y].equals("X");

    }

    public static boolean isVisited(int x, int y) {
        return x >= 0 && x < visited.length && y >= 0 && y < visited[x].length && visited[x][y];
    }
} 