package GUI;

import Controller.Controller;
import Model.Photo;
import Model.Utente;
import Model.Video;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class MiaGalleria {
    Controller controller = new Controller();
    Utente activeUtente;
    ArrayList<Photo> activePhoto;
    ArrayList<Video> activeVideo;
    Photo currentPhoto;
    String UserTag;
    String PhotoTag;
    Integer counter = 0;
    private JPanel panel1;
    private JButton fotoSuccessivaButton;
    private JButton fotoPrecedenteButton;
    private JButton modificaFotoButton;
    private JButton galleriaVideoButton;
    private JButton tornaIndietroButton;
    private JLabel photoNumLabel;

    private JLabel photo_field;
    private JLabel CodLabel;
    private JLabel VisLabel;
    private JLabel LuoLabel;
    private JLabel DisLabel;
    private JLabel DaLabel;
    private JLabel UtTag;
    private JLabel SogTag;
    private JButton aggiungiFotoButton;
    private JButton eliminaFotoButton;
    private JLabel X;
    private JLabel Y;
    private JButton insColPubJButton;
    private JButton aggiungiVideoButton;

    private static JFrame frame;
    public MiaGalleria(Utente utente){
        frame = new JFrame("MiaGalleria");
        frame.setContentPane(panel1);
        frame.setSize(750,500);
        frame.setVisible(true);
        try {
            activePhoto = controller.fotoStessoUtenteCTRL(utente.getNicknameUtente());
            activeVideo = controller.videoStessoUtenteCTRL(utente.getNicknameUtente());
            UserTag = controller.PersoneTaggateCTRL(activePhoto.get(counter).getPhotoCode());
            PhotoTag = controller.SoggettoInFotoCTRL(activePhoto.get(counter).getPhotoCode());
        }
        catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        activeUtente = utente;
        currentPhoto = activePhoto.get(0);
        System.out.println(activePhoto.get(0).getPhotoCode());


        photoNumLabel.setText(String.valueOf(activePhoto.size()));

        CodLabel.setText(activePhoto.get(counter).getPhotoCode().toString());
        VisLabel.setText(activePhoto.get(counter).getScope());
        LuoLabel.setText(activePhoto.get(counter).getLocationName());
        DisLabel.setText(activePhoto.get(counter).getDevice());
        DaLabel.setText(activePhoto.get(counter).getPhotoDate().toString());
        X.setText(activePhoto.get(counter).getXCoordinates().toString());
        Y.setText(activePhoto.get(counter).getYCoordinates().toString());
        UtTag.setText(UserTag);
        SogTag.setText(PhotoTag);
        final ImageIcon[] ph = {controller.setImgPathSize(activePhoto.get(counter).getPath(), 250, 250)};
        photo_field.setIcon(ph[0]);

        tornaIndietroButton.addActionListener(e ->{
            new ProfiloUtente(activeUtente);
            frame.setVisible(false);
            frame.dispose();
        });


        fotoSuccessivaButton.addActionListener(e -> {
            if(counter<activePhoto.size()-1)
            {
                int temp = counter+1;
                currentPhoto = activePhoto.get(temp);
                System.out.println(counter);
                ++counter;
                System.out.println(counter);
                CodLabel.setText(currentPhoto.getPhotoCode().toString());
                VisLabel.setText(currentPhoto.getScope());
                LuoLabel.setText(currentPhoto.getLocationName());
                DisLabel.setText(currentPhoto.getDevice());
                DaLabel.setText(currentPhoto.getPhotoDate().toString());
                X.setText(currentPhoto.getXCoordinates().toString());
                Y.setText(currentPhoto.getYCoordinates().toString());
                ph[0] = controller.setImgPathSize(currentPhoto.getPath(),250,250);
                photo_field.setIcon(ph[0]);
                try {
                    UserTag = controller.PersoneTaggateCTRL(currentPhoto.getPhotoCode());
                    PhotoTag = controller.SoggettoInFotoCTRL(currentPhoto.getPhotoCode());
                }
                catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                UtTag.setText(UserTag);
                SogTag.setText(PhotoTag);
            }
        });
        fotoPrecedenteButton.addActionListener(e -> {
            if(counter>0)
            {
                int temp2 = counter-1;
                currentPhoto = activePhoto.get(temp2);
                System.out.println(counter);
                --counter;
                System.out.println(counter);
                CodLabel.setText(currentPhoto.getPhotoCode().toString());
                VisLabel.setText(currentPhoto.getScope());
                LuoLabel.setText(currentPhoto.getLocationName());
                DisLabel.setText(currentPhoto.getDevice());
                DaLabel.setText(currentPhoto.getPhotoDate().toString());
                X.setText(currentPhoto.getXCoordinates().toString());
                Y.setText(currentPhoto.getYCoordinates().toString());
                ph[0] = controller.setImgPathSize(currentPhoto.getPath(),250,250);
                photo_field.setIcon(ph[0]);
                try {
                    UserTag = controller.PersoneTaggateCTRL(currentPhoto.getPhotoCode());
                    PhotoTag = controller.SoggettoInFotoCTRL(currentPhoto.getPhotoCode());
                }
                catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                UtTag.setText(UserTag);
                SogTag.setText(PhotoTag);
            }
        });
        aggiungiFotoButton.addActionListener(e -> {
            new InserimentoFoto(activeUtente);
            frame.setVisible(false);
            frame.dispose();

        });

        modificaFotoButton.addActionListener(e -> {
            new MiaGalleriaModifica(currentPhoto, activeUtente);
            frame.setVisible(false);
            frame.dispose();
        });

        galleriaVideoButton.addActionListener(e -> {
            boolean check = activeVideo.contains(activeVideo.get(0));

            if(check) {
                new GalleriaVideo(activeUtente);
                frame.setVisible(false);
                frame.dispose();
            }
        });

        aggiungiVideoButton.addActionListener(e -> {
            new GalleriaVideoAggiungi(activeUtente);
            frame.setVisible(false);
            frame.dispose();
        });

        eliminaFotoButton.addActionListener(e -> {
            try{
                controller.deletePhotoCTRL(currentPhoto.getPhotoCode());
            }
            catch(SQLException ex){
                throw new RuntimeException(ex);
            }
            new MiaGalleria(activeUtente);
            frame.setVisible(false);
            frame.dispose();
        });

        insColPubJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MiaGalleriaAddToCollection miaGalleriaAddToCollection = new MiaGalleriaAddToCollection(activeUtente, activePhoto.get(counter));
            }
        });


    }

}
