import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Screen extends JFrame implements MouseMotionListener, MouseListener {

    private List<Integer> list = new ArrayList<>();
    private double width, height, centimeter = 0, value = 0.026458333;
    private int pos = 0, posX = 0, posY = 0, max = 0, min = 0, length = 0;
    private JTextField field, field0, field1, field2, field3, field4, field5, field6;
    private JFrame window, window2;
    private JButton jButton;

    Screen (double x, double y){
        this.width = x;
        this.height = y;

        window = new JFrame("mouse");
        Container con = window.getContentPane();
        con.setLayout(new FlowLayout());

        field  = new JTextField(30); //first
        field0 = new JTextField(30); //first
        field1 = new JTextField(30); //first
        field2 = new JTextField(30); //hybrid
        field3 = new JTextField(30);
        field4 = new JTextField(30);
        field5 = new JTextField(30);
        field6 = new JTextField(30);

        field0.setText("resolution = " + (int)x + "x" + (int)y); //second

        con.addMouseMotionListener(this);
        con.addMouseListener(this);

        con.add(field);
        con.add(field1);
        con.add(field2);

        //transparent:
        window.setUndecorated(true);
        window.setBackground(new Color(1.0f,1.0f,1.0f,0.5f));

        window.setSize((int)x,(int)y - 20);
        window.setVisible(true);

        jButton = new JButton("exit");
        window.add(jButton);

        jButton.addActionListener(e -> {
            window.setVisible(false);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });

    }

    @Override
    public void mouseDragged(MouseEvent e) {

        pos = e.getX();
        list.add(pos);

        field1.setText("" + pos);

    }

    @Override
    public void mouseMoved(MouseEvent e) {

        posX = e.getX();
        posY = e.getY();

        field.setText("width = " + posX + "\nheight = " + posY);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        list = new ArrayList<>();

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        JFrame parent = new JFrame();
        JTextField name = new JTextField();
        parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        parent.setBounds(200, 100, 400, 200);

        Container container = parent.getContentPane();
        container.setLayout(null);

        JLabel jLabel = new JLabel("Are You done?");
        jLabel.setBounds(60,5,250,30);

        name.setBounds(65,30,250,30);

        JButton jButton1 = new JButton("Yes");
        jButton1.setBounds(200,90,100,30);
        container.add(jButton1);

        JButton jButton2 = new JButton("No");
        jButton2.setBounds(80,90,100,30);
        container.add(jButton2);

        parent.setTitle("Are You done?");

        parent.setVisible(true);

        jButton1.addActionListener(e1 -> {
            windowSecond();
            parent.setVisible(false);
            parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        });

        jButton2.addActionListener(e12 -> {
            parent.setVisible(false);
            parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }

    private void windowSecond(){
        //Zamknięcie poprzedniego okna
        window.setVisible(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //tworzenie nowego okna
        window2 = new JFrame("wynik");
        window2.setSize(400,400);
        window2.setVisible(true);

        JButton button = new JButton("Exit");

        Container con2 = window2.getContentPane();
        con2.setLayout(new FlowLayout());

        con2.add(field0);

        //pobrane próbki
        field2.setText("" + list);
        con2.add(field2);

        //wyniki

        //maksymalna próbka piksela
        max = Collections.max(list);
        field3.setText("max = " + max + " pixels");

        //minimalna próbka piksela
        min = Collections.min(list);
        field4.setText("min = " + min + " pixels");

        //długość w pikselach
        length = max - min;
        field5.setText("length = " + length + " pixels");

        //długość w centymetrach
        centimeter = length*value;
        field6.setText("length = " + new DecimalFormat("###.##").format(centimeter) + " cm");

        con2.add(field3);
        con2.add(field4);
        con2.add(field5);
        con2.add(field6);

        con2.add(button);

        button.addActionListener(e -> {

            window2.setVisible(false);

            window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        });
    }

    @Override
    public void mouseEntered(MouseEvent e) {

        field2.setText("w obszarze");

    }

    @Override
    public void mouseExited(MouseEvent e) {

        field2.setText("poza obszarem");

    }
}
