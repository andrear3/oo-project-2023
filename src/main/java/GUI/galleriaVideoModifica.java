package GUI;

import Controller.Controller;
import Model.Utente;
import Model.Video;

import javax.swing.*;
import java.sql.SQLException;

public class galleriaVideoModifica {

    Video currentvideo;
    Utente activeutente;

    String listafoto;

    Controller controller = new Controller();
    private JPanel panel1;
    private JTextField edit_title_field;
    private JTextField edit_desc_field;
    private JTextField add_photo_field;
    private JTextField del_photo_field;
    private JButton add_photo;
    private JButton del_photo;
    private JButton tornaIndietroButton;
    private JButton edit_title;
    private JButton edit_desc;
    private JLabel lista_foto;

    private static JFrame frame;


    public galleriaVideoModifica(Video video, Utente utente)
    {
        frame = new JFrame("galleriaVideoModifica");
        frame.setContentPane(panel1);
        frame.setSize(600,300);
        frame.setVisible(true);
        currentvideo = video;
        activeutente = utente;

        try{
            listafoto = controller.listafotovideoCTRL(currentvideo.getVideoCode());
        }
        catch (SQLException ex){
            throw new RuntimeException(ex);
        }
        lista_foto.setText(listafoto);
        edit_title.addActionListener(e -> {
            String title = String.valueOf(edit_title_field.getText());
            try {
                controller.modificaTitoloCTRL(currentvideo.getVideoCode(), title);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        edit_desc.addActionListener(e -> {
            String desc = String.valueOf(edit_desc_field.getText());
            try {
                controller.modificaDescCTRL(currentvideo.getVideoCode(), desc);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        add_photo.addActionListener(e -> {
            Integer photo = Integer.valueOf(add_photo_field.getText());
            try {
                controller.aggiungiFotoVideoCTRL(currentvideo.getVideoCode(), photo);
                listafoto = controller.listafotovideoCTRL(currentvideo.getVideoCode());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        del_photo.addActionListener(e -> {
            Integer photo = Integer.valueOf(del_photo_field.getText());
            try {
                controller.eliminaFotoVideoCTRL(currentvideo.getVideoCode(), photo);
                listafoto = controller.listafotovideoCTRL(currentvideo.getVideoCode());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        tornaIndietroButton.addActionListener(e -> {
            new galleriaVideo(activeutente);
            frame.setVisible(false);
            frame.dispose();
        });


    }
}
