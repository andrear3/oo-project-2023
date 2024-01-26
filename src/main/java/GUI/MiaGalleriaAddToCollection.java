package GUI;


import Model.Photo;
import Model.Utente;
import Controller.Controller;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class MiaGalleriaAddToCollection {

    JFrame frame = new JFrame();
    private JPanel AddPanel;
    private JComboBox comboBox1;
    private JButton addToCollection;
    private JButton tornaIndietroButton;
    Utente activeUtente;

    Controller controller = new Controller();

    public MiaGalleriaAddToCollection(Utente utente, Photo photo){
        this.frame = new JFrame("MiaGalleriaAddToCollection");
        frame.setContentPane(AddPanel);
        frame.setSize(900,500);
        frame.setVisible(true);
        activeUtente = utente;

        try {
            ArrayList<String> pubCollections = controller.getAllPubCollectionCTRL();
            for (String temp : pubCollections) {
                comboBox1.addItem(makeObj(temp));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        addToCollection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object temp = comboBox1.getSelectedItem();
                String tmp = temp.toString();
                try {
                    controller.insertPhotoInCollectionCTRL(photo.getPhoto_code(),tmp);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        tornaIndietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                frame.dispose();
            }
        });
    }
    private Object makeObj(final String item)  {
        return new Object() { public String toString() { return item; } };
    }
}

