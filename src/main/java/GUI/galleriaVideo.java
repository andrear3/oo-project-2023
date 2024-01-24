package GUI;

import Controller.Controller;
import Model.Photo;
import Model.Utente;
import Model.Video;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class galleriaVideo {

    Controller controller = new Controller();
    Utente activeUtente;
    Integer counter = 0;

    ArrayList<Integer> photoinvideo;

    ArrayList<Video> activeVideo;

    Video currentVideo;
    private JLabel videoshow;
    private JButton aggiungiVideoButton;
    private JButton eliminaVideoButton;
    private JButton modificaVideoButton;
    private JButton tornaIndietroButton;
    private JButton videoSuccessivoButton;
    private JButton videoPrecedenteButton;
    private JPanel panel1;
    private JLabel CodLabel;
    private JLabel Title;
    private JLabel Desc;
    private JLabel fotovideo;

    private static JFrame frame;
    public galleriaVideo(Utente utente)
    {
        this.frame = new JFrame("galleriaVideo");
        frame.setContentPane(panel1);
        frame.setSize(600,300);
        frame.setVisible(true);
        activeUtente = utente;
        try {
            activeVideo = controller.videoStessoUtenteCTRL(utente.getNicknameUtente());
        }
        catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        currentVideo = activeVideo.get(0);

        try{
            photoinvideo = controller.fotoInVideoCTRL(currentVideo.getvideocode());
        }
        catch (SQLException ex){
            throw new RuntimeException(ex);
        }
        /*
        photoinvideo.forEach(test -> {
            fotovideo.setText(fotovideo + test.toString());
        });
         */
        CodLabel.setText(currentVideo.getvideocode().toString());
        Title.setText(currentVideo.getvideotitle());
        Desc.setText(currentVideo.getvideodesc());
        /*
        final ImageIcon[] ph = {controller.setImgPathSize(photoinvideo.get(counter).getPath(), 250, 250)};
        videoshow.setIcon(ph[0]);
         */

        tornaIndietroButton.addActionListener(e -> {
            MiaGalleria miagalleria = new MiaGalleria(activeUtente);
            frame.setVisible(false);
            frame.dispose();
        });
    }
}


