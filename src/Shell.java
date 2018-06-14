import java.io.IOException;
import java.net.URISyntaxException;

public class Shell {
    public static void main(String[] args) throws IOException, URISyntaxException {


        int[] start = {134, 10, 1};
        int[] end = {20, 140, 4};


        Mapping2NewHeuristic map = new Mapping2NewHeuristic();

        map.search(new Mapping2NewHeuristic.Node(start[0], start[1], start[2], 0, 0), new Mapping2NewHeuristic.Node(end[0], end[1], end[2], Integer.MAX_VALUE,0));


        map.printPathwayCoordinates();

        map.displayPath();

        Mapping2 map2 = new Mapping2();

        map2.search(new Mapping2.Node(start[0], start[1]), new Mapping2.Node(end[0], end[1]));

        map2.printPathwayCoordinates();
        map2.displayPath();

    }


}
