package GUI;

import Controller.Controller;
import Model.Photo;
import Model.Utente;
import Model.Video;

import javax.swing.*;
import java.sql.SQLException;
import java.util.*;
import java.util.Timer;

public class GalleriaVideo {

    Controller controller = new Controller();
    Utente activeUtente;
    Integer counter = 0;
    Integer videoCounter = 0;

    ArrayList<Photo> photoInVideo;
    ArrayList<Video> activeVideo;
    Video currentVideo;
    String listaFoto;
    private JLabel videoShow;
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
    private JLabel ListaFoto;

    private static JFrame frame;
    public GalleriaVideo(Utente utente)
    {
        frame = new JFrame("GalleriaVideo");
        frame.setContentPane(panel1);
        frame.setSize(600,400);
        frame.setVisible(true);
        activeUtente = utente;
        try {
            activeVideo = controller.videoStessoUtenteCTRL(utente.getNicknameUtente());
        }
        catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        currentVideo = activeVideo.get(videoCounter);

        try{
            photoInVideo = controller.fotoInVideoCTRL(currentVideo.getVideoCode());
            listaFoto = controller.listafotovideoCTRL(currentVideo.getVideoCode());
        }
        catch (SQLException ex){
            throw new RuntimeException(ex);
        }



        CodLabel.setText(currentVideo.getVideoCode().toString());
        ListaFoto.setText(listaFoto);
        Title.setText(currentVideo.getVideoTitle());
        Desc.setText(currentVideo.getVideoDesc());
        final ImageIcon[] ph = {controller.setImgPathSize(photoInVideo.get(counter).getPath(), 250, 250)};
        videoShow.setIcon(ph[0]);

        System.out.println(photoInVideo.size());


        int i = 0;
        Timer timer = new Timer();
        while(i< photoInVideo.size()) {
            Integer finalI = i;
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    ph[0] = controller.setImgPathSize(photoInVideo.get(finalI).getPath(), 250, 250);
                    videoShow.setIcon(ph[0]);
                    System.out.println("Codice attuale:" + photoInVideo.get(finalI).getPhotoCode());
                    System.out.println("Valore di I:" + finalI);
                }
            };
            timer.schedule(task, 3000L * i++);
        }

        videoSuccessivoButton.addActionListener(e -> {
            if(counter<activeVideo.size()-1)
            {
                videoCounter = videoCounter +1;
                currentVideo = activeVideo.get(videoCounter);

                try{
                    photoInVideo = controller.fotoInVideoCTRL(currentVideo.getVideoCode());
                    listaFoto = controller.listafotovideoCTRL(currentVideo.getVideoCode());
                }
                catch (SQLException ex){
                    throw new RuntimeException(ex);
                }

                CodLabel.setText(currentVideo.getVideoCode().toString());
                ListaFoto.setText(listaFoto);
                Title.setText(currentVideo.getVideoTitle());
                Desc.setText(currentVideo.getVideoDesc());

                final ImageIcon[] ph1 = {controller.setImgPathSize(photoInVideo.get(counter).getPath(), 250, 250)};
                videoShow.setIcon(ph1[0]);

                System.out.println(photoInVideo.size());


                int i1 = 0;
                Timer timer1 = new Timer();
                while(i1 < photoInVideo.size()) {
                    //String value = photoinvideo.next();
                    Integer finalI = i1;
                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            ph1[0] = controller.setImgPathSize(photoInVideo.get(finalI).getPath(), 250, 250);
                            videoShow.setIcon(ph1[0]);
                            System.out.println("Codice attuale:" + photoInVideo.get(finalI).getPhotoCode());
                            System.out.println("Valore di I:" + finalI);
                        }
                    };
                    timer1.schedule(task, 3000L * i1++);
                }
            }
        });

        videoPrecedenteButton.addActionListener(e -> {
            if(videoCounter >0)
            {
                videoCounter = videoCounter -1;
                currentVideo = activeVideo.get(videoCounter);

                try{
                    photoInVideo = controller.fotoInVideoCTRL(currentVideo.getVideoCode());
                    listaFoto = controller.listafotovideoCTRL(currentVideo.getVideoCode());
                }
                catch (SQLException ex){
                    throw new RuntimeException(ex);
                }

                CodLabel.setText(currentVideo.getVideoCode().toString());
                ListaFoto.setText(listaFoto);
                Title.setText(currentVideo.getVideoTitle());
                Desc.setText(currentVideo.getVideoDesc());

                final ImageIcon[] ph12 = {controller.setImgPathSize(photoInVideo.get(counter).getPath(), 250, 250)};
                videoShow.setIcon(ph12[0]);

                System.out.println(photoInVideo.size());


                int i12 = 0;
                Timer timer12 = new Timer();
                while(i12 < photoInVideo.size()) {
                    //String value = photoinvideo.next();
                    Integer finalI = i12;
                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            ph12[0] = controller.setImgPathSize(photoInVideo.get(finalI).getPath(), 250, 250);
                            videoShow.setIcon(ph12[0]);
                            System.out.println("Codice attuale:" + photoInVideo.get(finalI).getPhotoCode());
                            System.out.println("Valore di I:" + finalI);
                        }
                    };
                    timer12.schedule(task, 3000L * i12++);
                }
            }
        });

        aggiungiVideoButton.addActionListener(e -> {
            new GalleriaVideoAggiungi(activeUtente);
            frame.setVisible(false);
            frame.dispose();
        });


        tornaIndietroButton.addActionListener(e -> {
            new MiaGalleria(activeUtente);
            frame.setVisible(false);
            frame.dispose();
        });



        modificaVideoButton.addActionListener(e -> {
            new GalleriaVideoModifica(currentVideo, activeUtente);
            frame.setVisible(false);
            frame.dispose();
        });

        eliminaVideoButton.addActionListener(e -> {

            try{
                controller.deleteVideoCTRL(currentVideo.getVideoCode());
            }
            catch(SQLException ex){
                throw new RuntimeException(ex);
            }

            new MiaGalleria(activeUtente);
            frame.setVisible(false);
            frame.dispose();
        });


    }
}


