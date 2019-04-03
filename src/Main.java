import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Scanner;

public class Main{

    static double width = 0, height = 0;
    public static void main(String[] args) {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth();
        height = screenSize.getHeight();

        EventQueue.invokeLater(() -> {
            Screen sc = new Screen(width,height);
        });


    }
}
