package GUI;

import Controller.Controller;
import Model.Utente;
import Model.Video;

import javax.swing.*;
import java.sql.SQLException;

public class GalleriaVideoModifica {
    Video currentVideo;
    Utente activeUtente;
    String photoList;
    Controller controller = new Controller();
    private JPanel panel1;
    private JTextField editTitleField;
    private JTextField editDescField;
    private JTextField addPhotoField;
    private JTextField delPhotoField;
    private JButton addPhoto;
    private JButton delPhoto;
    private JButton tornaIndietroButton;
    private JButton editTitle;
    private JButton editDesc;
    private JLabel listaFoto;

    private static JFrame frame;


    public GalleriaVideoModifica(Video video, Utente utente)
    {
        frame = new JFrame("galleriaVideoModifica");
        frame.setContentPane(panel1);
        frame.setSize(600,300);
        frame.setVisible(true);
        currentVideo = video;
        activeUtente = utente;

        try{
            photoList = controller.listafotovideoCTRL(currentVideo.getVideoCode());
        }
        catch (SQLException ex){
            throw new RuntimeException(ex);
        }
        listaFoto.setText(photoList);
        editTitle.addActionListener(e -> {
            String title = String.valueOf(editTitleField.getText());
            try {
                controller.modificaTitoloCTRL(currentVideo.getVideoCode(), title);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        editDesc.addActionListener(e -> {
            String desc = String.valueOf(editDescField.getText());
            try {
                controller.modificaDescCTRL(currentVideo.getVideoCode(), desc);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        addPhoto.addActionListener(e -> {
            Integer photo = Integer.valueOf(addPhotoField.getText());
            try {
                controller.aggiungiFotoVideoCTRL(currentVideo.getVideoCode(), photo);
                photoList = controller.listafotovideoCTRL(currentVideo.getVideoCode());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        delPhoto.addActionListener(e -> {
            Integer photo = Integer.valueOf(delPhotoField.getText());
            try {
                controller.eliminaFotoVideoCTRL(currentVideo.getVideoCode(), photo);
                photoList = controller.listafotovideoCTRL(currentVideo.getVideoCode());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        tornaIndietroButton.addActionListener(e -> {
            new GalleriaVideo(activeUtente);
            frame.setVisible(false);
            frame.dispose();
        });


    }
}
