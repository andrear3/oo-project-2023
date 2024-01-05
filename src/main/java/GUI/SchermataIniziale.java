package GUI;
import javax.swing.*;

public class SchermataIniziale {

    private static JFrame frame;
    private JPanel PanelIniziale;

    public static void main(String[] args) {
    frame = new JFrame("SchermataIniziale");
    frame.setContentPane(new SchermataIniziale().PanelIniziale);
    frame.setSize(500,500);
    frame.setVisible(true);
    }
}

