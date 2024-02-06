package GUI;

import Controller.Controller;
import Model.Utente;
import Model.Video;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class GalleriaVideoAggiungi {

    Utente activeUtente;
    ArrayList<Video> activeVideo;

    Video newVideo;
    Controller controller = new Controller();
    private JButton tornaIndietroButton;
    private JButton confermaEAggiungiFotoButton;
    private JTextField textField1;
    private JTextField textField2;
    private JPanel panel1;
    private static JFrame frame;

    public GalleriaVideoAggiungi(Utente utente){
        frame = new JFrame("galleriaVideo");
        frame.setContentPane(panel1);
        frame.setSize(600,300);
        frame.setVisible(true);

        activeUtente = utente;

        try{
            activeVideo = controller.videoStessoUtenteCTRL(utente.getNicknameUtente());
        }
        catch(SQLException ex){
            throw new RuntimeException(ex);
        }
        confermaEAggiungiFotoButton.addActionListener(e -> {
            String title = String.valueOf(textField1.getText());
            String desc = String.valueOf(textField2.getText());
            try {
                controller.nuovoVideoCTRL(desc, title, activeUtente.getNicknameUtente());
                activeVideo = controller.videoStessoUtenteCTRL(utente.getNicknameUtente());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            newVideo = activeVideo.get(activeVideo.size()-1);
            System.out.println(newVideo.getVideoCode());
            new GalleriaVideoAggiungiFoto(newVideo, activeUtente);
            frame.setVisible(false);
            frame.dispose();

        });

        tornaIndietroButton.addActionListener(e -> {
            boolean check = activeVideo.contains(utente.getNicknameUtente());

            if(check) {
                new GalleriaVideo(activeUtente);
            }
            else {
                new MiaGalleria(activeUtente);
            }
            frame.setVisible(false);
            frame.dispose();
        });
    }
}
