package GUI;

import Controller.Controller;
import Model.Utente;
import Model.Video;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class galleriaVideoAggiungiFoto {

    Utente activeutente;

    Video activevideo;

    ArrayList<Integer> photos;
    private JTextField textField1;
    private JButton aggiungiFotoButton;
    private JButton confermaECreaIlButton;

    Controller controller = new Controller();
    private JPanel panel1;

    private static JFrame frame;

    public galleriaVideoAggiungiFoto(Video video, Utente utente){
        frame = new JFrame("galleriaVideoAggiungiFoto");
        frame.setContentPane(panel1);
        frame.setSize(600,300);
        frame.setVisible(true);
        activevideo = video;
        activeutente = utente;

        try{
            photos = controller.foto_StessoUtente_o_PubblicheCTRL(activeutente.getNicknameUtente());
        }
        catch (SQLException ex)
        {
            throw new RuntimeException(ex);
        }

        aggiungiFotoButton.addActionListener(e -> {
            Integer photo = Integer.valueOf(textField1.getText());

            boolean check = photos.contains(photo);

            if(check) {

                try {
                    controller.aggiungiFotoVideoCTRL(activevideo.getVideoCode(), photo);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            else
            {
                System.out.println("Foto inesistente o non accessibile");
            }
        });

        confermaECreaIlButton.addActionListener(e -> {
            new galleriaVideo(activeutente);
            frame.setVisible(false);
            frame.dispose();
        });
    }
}
