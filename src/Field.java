import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;

public class Field {

    public int field[][] = new int[145][145];

    public Field() throws IOException, URISyntaxException {
        File file = new File(getClass().getResource("MappingCSV.csv").toURI());
        BufferedReader f = new BufferedReader(new FileReader(file));

        for (int i = 0; i < 145; i++) {
            String[] line = f.readLine().split(",");
            for (int j = 0; j < 145; j++) {
                field[j][i] = Integer.parseInt(line[j]);
            }
        }
    }
    public static void main(String[] args) throws IOException, URISyntaxException{
        System.out.println(new Field().field[40][44]);
    }


}
