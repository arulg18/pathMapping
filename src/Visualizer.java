import javax.swing.*;
import javax.swing.plaf.ButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Visualizer extends JFrame {

    JButton[][] field = new JButton[145][145];
    static GraphicsDevice device = GraphicsEnvironment
            .getLocalGraphicsEnvironment().getScreenDevices()[0];

    Dimension dim;
    private static String title = "Autonomous Mapping Algorithm";

    Font DIN = new Font("DIN Alternate", Font.BOLD, 54);
    Font DIN2 = new Font("DIN Alternate", Font.BOLD, 40);

    Font Lunar = new Font("Lunar Eclipse", 0, 90);
    Font Avenir = new Font("Avenir Next", 1, 170);
    Font Avenir2 = new Font("Avenir Next", Font.BOLD, 50);
    JButton start;
    JLabel Title;

    JLabel startBack;
    JLabel endBack;
    JLabel distanceBack;
    JLabel pathBack;

    JTextField startPointX;
    JTextField startPointY;

    JTextField endPointX;
    JTextField endPointY;

    JLabel distance;
    JScrollPane scrollPane;
    JLabel points;

    JLabel mappingPageTitle;
    int total = 0;

    public static void main (String[] args) throws  IOException{
        Window window = new Visualizer(title);
    }
    public Visualizer(String name) throws IOException {
        super(name);
        Toolkit tk = Toolkit.getDefaultToolkit();
        dim = tk.getScreenSize();
        this.setSize(dim);


        int xCenter = (dim.width/2) - (this.getWidth() / 2);
        int yCenter = (dim.height / 2) - (this.getHeight() / 2);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocation(xCenter, yCenter);

        this.getContentPane().setBackground(new Color(34, 34, 34));

        opening();

        this.setLayout(null);
        this.setVisible(true);

    }

    private void opening() {
        this.setName(title);
        this.setTitle(title);

        Title = new JLabel();
        Title.setForeground(new Color(170, 23, 40));
        Title.setText("<html>Autonomous<br>Mapping<br>Algorithm</html>");
        Title.setFont(Avenir);
        Title.setSize(Title.getPreferredSize());
        Title.setLocation(dim.width/2 - this.getWidth()/2, 20);


        start = new JButton();
        ImageIcon img = new ImageIcon(getClass().getResource("Start.png"));
        start.setIcon(img);
        start.setSize(img.getIconWidth(), img.getIconHeight());
        start.setLocation((int) dim.getWidth() - start.getWidth() - 30, this.getHeight()/2 - start.getHeight()/2);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((JButton) e.getSource() == start){
                    clearScreen();
                    mappingVisualizer();
                }
            }
        });

        addItemsToFrame(start, Title);

    }
    public void mappingVisualizer(){
        mappingPageTitle = new JLabel();
        mappingPageTitle.setForeground(new Color(52, 165, 218));
        mappingPageTitle.setText("Mapping");
        mappingPageTitle.setFont(DIN);
        mappingPageTitle.setSize(300, 100);
        mappingPageTitle.setLocation(30, 0);

        startBack = new JLabel();
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("StartPoint.png"));
        startBack.setIcon(imageIcon);
        startBack.setSize(startBack.getPreferredSize());
        startBack.setLocation(30, mappingPageTitle.getHeight() + 20);

        startPointX = new JTextField();
        startPointX.setToolTipText("Type x-coordinate of starting point here");
        startPointX.setSize(40, 20);
        startPointX.setLocation(startBack.getX() + startBack.getWidth()/2 - 40, startBack.getY() + startBack.getHeight()/2 + 20);


        startPointY = new JTextField();
        startPointY.setToolTipText("Type y-coordinate of starting point here");
        startPointY.setSize(40, 20);
        startPointY.setLocation(startBack.getX() + startBack.getWidth()/2 + 40 - startPointY.getWidth(), startBack.getY() + startBack.getHeight()/2 + 20);



        endBack = new JLabel();
        ImageIcon img = new ImageIcon(getClass().getResource("EndPoint.png"));
        endBack.setIcon(img);
        endBack.setSize(endBack.getPreferredSize());
        endBack.setLocation(30, startBack.getY() + startBack.getHeight() + 20);

        endPointX = new JTextField();
        endPointX.setToolTipText("Type x-coordinate of ending point here");
        endPointX.setSize(40, 20);
        endPointX.setLocation(endBack.getX() + endBack.getWidth()/2 - 40, endBack.getY() + endBack.getHeight()/2 + 20);


        endPointY = new JTextField();
        endPointY.setToolTipText("Type y-coordinate of ending point here");
        endPointY.setSize(40, 20);
        endPointY.setLocation(endBack.getX() + endBack.getWidth()/2 + 40 - endPointY.getWidth(), endBack.getY() + endBack.getHeight()/2 + 20);

        distanceBack = new JLabel();
        ImageIcon img3 = new ImageIcon(getClass().getResource("TotalDistance.png"));
        distanceBack.setIcon(img3);
        distanceBack.setSize(distanceBack.getPreferredSize());
        distanceBack.setLocation(30, endBack.getY() + endBack.getHeight() + 20);

        distance = new JLabel();
        distance.setText("<html><div style='text-align: center;'>" + Integer.toString(total) + "</div></html>");
        distance.setFont(DIN2);

        distance.setSize(distance.getPreferredSize());
        distance.setLocation(distanceBack.getX() + distanceBack.getWidth()/2 - distance.getWidth()/2, distanceBack.getY() + distanceBack.getHeight()/2);

        pathBack = new JLabel();
        ImageIcon img4 = new ImageIcon(getClass().getResource("Path.png"));
        pathBack.setIcon(img4);
        pathBack.setSize(pathBack.getPreferredSize());
        pathBack.setLocation(30, distanceBack.getY() + distanceBack.getHeight() + 20);

        points = new JLabel("Hei");

        scrollPane = new JScrollPane();
        scrollPane.add(points);
        scrollPane.setSize(330, 200);

        scrollPane.setLocation(pathBack.getX(), pathBack.getY());


        addItemsToFrame(mappingPageTitle, scrollPane, endPointX, endPointY, endBack, startPointX, startPointY, startBack, distance, distanceBack, pathBack);

    }

    public void clearScreen(){
        this.getContentPane().removeAll();
        this.getContentPane().validate();
        this.getContentPane().repaint();
    }
    public void addItemsToFrame(Component... components){
        for (Component c: components) {
            this.add(c);
        }
    }
    public JButton createImageButton(String imgFilename, int x, int y){
        JButton g = new JButton();
        ImageIcon img = new ImageIcon(getClass().getResource(imgFilename));
        g.setIcon(img);
        g.setSize(img.getIconWidth(), img.getIconHeight());
        g.setLocation(x, y);
        return g;
    }
    public JLabel setImage(BufferedImage img, int width, int height, int x, int y){
        JLabel g = new JLabel(new ImageIcon(img));
        g.setSize(width, height);
        g.setLocation(x, y);
        return g;
    }


}

