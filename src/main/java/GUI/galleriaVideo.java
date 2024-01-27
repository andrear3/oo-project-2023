package GUI;

import Controller.Controller;
import Model.Photo;
import Model.Utente;
import Model.Video;

import javax.swing.*;
import java.sql.SQLException;
import java.util.*;
import java.util.Timer;

public class galleriaVideo {

    Controller controller = new Controller();
    Utente activeUtente;
    Integer counter = 0;

    Integer video_counter = 0;

    ArrayList<Photo> photoinvideo;

    ArrayList<Video> activeVideo;

    Video currentVideo;

    String lista_foto;
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
    private JLabel ListaFoto;

    private static JFrame frame;
    public galleriaVideo(Utente utente)
    {
        frame = new JFrame("galleriaVideo");
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

        currentVideo = activeVideo.get(video_counter);

        try{
            photoinvideo = controller.fotoInVideoCTRL(currentVideo.getvideocode());
            lista_foto = controller.listafotovideoCTRL(currentVideo.getvideocode());
        }
        catch (SQLException ex){
            throw new RuntimeException(ex);
        }



        CodLabel.setText(currentVideo.getvideocode().toString());
        ListaFoto.setText(lista_foto);
        Title.setText(currentVideo.getvideotitle());
        Desc.setText(currentVideo.getvideodesc());
        final ImageIcon[] ph = {controller.setImgPathSize(photoinvideo.get(counter).getPath(), 250, 250)};
        videoshow.setIcon(ph[0]);

        System.out.println(photoinvideo.size());


        int i = 0;
        Timer timer = new Timer();
        while(i<photoinvideo.size()) {
            Integer finalI = i;
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    ph[0] = controller.setImgPathSize(photoinvideo.get(finalI).getPath(), 250, 250);
                    videoshow.setIcon(ph[0]);
                    System.out.println("Codice attuale:" + photoinvideo.get(finalI).getPhoto_code());
                    System.out.println("Valore di I:" + finalI);
                }
            };
            timer.schedule(task, 3000L * i++);
        }

        videoSuccessivoButton.addActionListener(e -> {
            if(counter<activeVideo.size()-1)
            {
                video_counter = video_counter+1;
                currentVideo = activeVideo.get(video_counter);

                try{
                    photoinvideo = controller.fotoInVideoCTRL(currentVideo.getvideocode());
                    lista_foto = controller.listafotovideoCTRL(currentVideo.getvideocode());
                }
                catch (SQLException ex){
                    throw new RuntimeException(ex);
                }

                CodLabel.setText(currentVideo.getvideocode().toString());
                ListaFoto.setText(lista_foto);
                Title.setText(currentVideo.getvideotitle());
                Desc.setText(currentVideo.getvideodesc());

                final ImageIcon[] ph1 = {controller.setImgPathSize(photoinvideo.get(counter).getPath(), 250, 250)};
                videoshow.setIcon(ph1[0]);

                System.out.println(photoinvideo.size());


                int i1 = 0;
                Timer timer1 = new Timer();
                while(i1 <photoinvideo.size()) {
                    //String value = photoinvideo.next();
                    Integer finalI = i1;
                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            ph1[0] = controller.setImgPathSize(photoinvideo.get(finalI).getPath(), 250, 250);
                            videoshow.setIcon(ph1[0]);
                            System.out.println("Codice attuale:" + photoinvideo.get(finalI).getPhoto_code());
                            System.out.println("Valore di I:" + finalI);
                        }
                    };
                    timer1.schedule(task, 3000L * i1++);
                }
            }
        });

        videoPrecedenteButton.addActionListener(e -> {
            if(video_counter>0)
            {
                video_counter = video_counter-1;
                currentVideo = activeVideo.get(video_counter);

                try{
                    photoinvideo = controller.fotoInVideoCTRL(currentVideo.getvideocode());
                    lista_foto = controller.listafotovideoCTRL(currentVideo.getvideocode());
                }
                catch (SQLException ex){
                    throw new RuntimeException(ex);
                }

                CodLabel.setText(currentVideo.getvideocode().toString());
                ListaFoto.setText(lista_foto);
                Title.setText(currentVideo.getvideotitle());
                Desc.setText(currentVideo.getvideodesc());

                final ImageIcon[] ph12 = {controller.setImgPathSize(photoinvideo.get(counter).getPath(), 250, 250)};
                videoshow.setIcon(ph12[0]);

                System.out.println(photoinvideo.size());


                int i12 = 0;
                Timer timer12 = new Timer();
                while(i12 <photoinvideo.size()) {
                    //String value = photoinvideo.next();
                    Integer finalI = i12;
                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            ph12[0] = controller.setImgPathSize(photoinvideo.get(finalI).getPath(), 250, 250);
                            videoshow.setIcon(ph12[0]);
                            System.out.println("Codice attuale:" + photoinvideo.get(finalI).getPhoto_code());
                            System.out.println("Valore di I:" + finalI);
                        }
                    };
                    timer12.schedule(task, 3000L * i12++);
                }
            }
        });

        aggiungiVideoButton.addActionListener(e -> {
            new galleriaVideoAggiungi(activeUtente);
            frame.setVisible(false);
            frame.dispose();
        });


        tornaIndietroButton.addActionListener(e -> {
            new MiaGalleria(activeUtente);
            frame.setVisible(false);
            frame.dispose();
        });



        modificaVideoButton.addActionListener(e -> {
            new galleriaVideoModifica(currentVideo, activeUtente);
            frame.setVisible(false);
            frame.dispose();
        });

        eliminaVideoButton.addActionListener(e -> {

            try{
                controller.deleteVideoCTRL(currentVideo.getvideocode());
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


