package GUI;

import Controller.Controller;
import Model.Photo;
import Model.Utente;

import javax.swing.*;
import java.sql.SQLException;

public class MiaGalleriaModifica {

    Controller controller = new Controller();

    Photo currentphoto;

    Utente activeUtente;
    private JRadioButton privataRadioButton;
    private JRadioButton pubblicaRadioButton;
    private JTextField textField2;
    private JTextField utentetext;
    private JButton tornaIndietroButton;
    private JPanel Panel1;
    private JButton aggiungiUserTag;
    private JButton aggiungiPhotoTag;
    private JComboBox comboBox1;

    private static JFrame frame;
    public MiaGalleriaModifica(Photo photo, Utente utente)
    {
        this.frame = new JFrame("MiaGalleriaModifica");
        frame.setContentPane(Panel1);
        frame.setSize(600,300);
        frame.setVisible(true);
        currentphoto = photo;
        activeUtente = utente;

        aggiungiPhotoTag.addActionListener(e -> {
            String checkTag = String.valueOf(comboBox1.getSelectedItem());
            try {
                currentphoto = controller.aggiungiSoggettoCTRL(currentphoto.getPhoto_code(), checkTag);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        aggiungiUserTag.addActionListener(e -> {
            try {
                currentphoto = controller.aggiungiUserTagCTRL(currentphoto.getPhoto_code(), utentetext.getText());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        tornaIndietroButton.addActionListener(e -> {
            MiaGalleria miagalleria = new MiaGalleria(activeUtente);
            frame.setVisible(false);
            frame.dispose();
        });
    }
}

