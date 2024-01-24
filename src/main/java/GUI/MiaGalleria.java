package GUI;

import Controller.Controller;
import Model.Photo;
import Model.Utente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class MiaGalleria {

    Controller controller = new Controller();
    Utente activeUtente;
    ArrayList<Photo> activePhoto;
    Photo currentphoto;

    String UserTag;

    String PhotoTag;
    Integer counter = 0;

    private JPanel panel1;

    private JButton fotoSuccessivaButton;
    private JButton fotoPrecedenteButton;
    private JButton modificaFotoButton;
    private JButton galleriaVideoButton;
    private JButton tornaIndietroButton;
    private JButton eliminaFotoButton;
    private JLabel photonumLabel;

    private JLabel CodLabel;
    private JLabel VisLabel;
    private JLabel LuoLabel;
    private JLabel DisLabel;
    private JLabel DaLabel;
    private JLabel UtTag;
    private JLabel SogTag;
    private JLabel photo_field;
    private JButton aggiungiFotoButton;
    private JLabel X;
    private JLabel Y;

    private static JFrame frame;
    public MiaGalleria(Utente utente){
        this.frame = new JFrame("MiaGalleria");
        frame.setContentPane(panel1);
        frame.setSize(650,400);
        frame.setVisible(true);
        try {
            activePhoto = controller.fotoStessoUtenteCTRL(utente.getNicknameUtente());
            UserTag = controller.PersoneTaggateCTRL(activePhoto.get(counter).getPhoto_code());
            PhotoTag = controller.SoggettoInFotoCTRL(activePhoto.get(counter).getPhoto_code());
        }
        catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        activeUtente = utente;
        currentphoto = activePhoto.get(0);
        System.out.println(activePhoto.get(0).getPhoto_code());


        photonumLabel.setText(String.valueOf(activePhoto.size()));

        CodLabel.setText(activePhoto.get(counter).getPhoto_code().toString());
        VisLabel.setText(activePhoto.get(counter).getScope());
        LuoLabel.setText(activePhoto.get(counter).getLocation_name());
        DisLabel.setText(activePhoto.get(counter).getDevice());
        DaLabel.setText(activePhoto.get(counter).getPhoto_date().toString());
        X.setText(activePhoto.get(counter).getX_coordinates().toString());
        Y.setText(activePhoto.get(counter).getY_coordinates().toString());
        UtTag.setText(UserTag);
        SogTag.setText(PhotoTag);
        final ImageIcon[] ph = {controller.setImgPathSize(activePhoto.get(counter).getPath(), 250, 250)};
        photo_field.setIcon(ph[0]);

        tornaIndietroButton.addActionListener(e ->{
            ProfiloUtente profiloutente = new ProfiloUtente(activeUtente);
            frame.setVisible(false);
            frame.dispose();
        });


        fotoSuccessivaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(counter<activePhoto.size()-1)
                {
                    Integer temp = counter+1;
                    currentphoto = activePhoto.get(temp);
                    System.out.println(counter);
                    counter = ++counter;
                    System.out.println(counter);
                    CodLabel.setText(currentphoto.getPhoto_code().toString());
                    VisLabel.setText(currentphoto.getScope());
                    LuoLabel.setText(currentphoto.getLocation_name());
                    DisLabel.setText(currentphoto.getDevice());
                    DaLabel.setText(currentphoto.getPhoto_date().toString());
                    X.setText(currentphoto.getX_coordinates().toString());
                    Y.setText(currentphoto.getY_coordinates().toString());
                    ph[0] = controller.setImgPathSize(currentphoto.getPath(),250,250);
                    photo_field.setIcon(ph[0]);
                    try {
                        UserTag = controller.PersoneTaggateCTRL(currentphoto.getPhoto_code());
                        PhotoTag = controller.SoggettoInFotoCTRL(currentphoto.getPhoto_code());
                    }
                    catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    UtTag.setText(UserTag);
                    SogTag.setText(PhotoTag);
                }
            }
        });
        fotoPrecedenteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(counter>0)
                {
                    Integer temp2 = counter-1;
                    currentphoto = activePhoto.get(temp2);
                    System.out.println(counter);
                    counter = --counter;
                    System.out.println(counter);
                    CodLabel.setText(currentphoto.getPhoto_code().toString());
                    VisLabel.setText(currentphoto.getScope());
                    LuoLabel.setText(currentphoto.getLocation_name());
                    DisLabel.setText(currentphoto.getDevice());
                    DaLabel.setText(currentphoto.getPhoto_date().toString());
                    X.setText(currentphoto.getX_coordinates().toString());
                    Y.setText(currentphoto.getY_coordinates().toString());
                    ph[0] = controller.setImgPathSize(currentphoto.getPath(),250,250);
                    photo_field.setIcon(ph[0]);
                    try {
                        UserTag = controller.PersoneTaggateCTRL(currentphoto.getPhoto_code());
                        PhotoTag = controller.SoggettoInFotoCTRL(currentphoto.getPhoto_code());
                    }
                    catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    UtTag.setText(UserTag);
                    SogTag.setText(PhotoTag);
                }
            }
        });
        aggiungiFotoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            InserimentoFoto inserimentoFoto=new InserimentoFoto(activeUtente);
                frame.setVisible(false);
                frame.dispose();

            }
        });

        modificaFotoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MiaGalleriaModifica miagalleriamodifica =new MiaGalleriaModifica(currentphoto, activeUtente);
                frame.setVisible(false);
                frame.dispose();
            }
        });

        galleriaVideoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                galleriaVideo video =new galleriaVideo(activeUtente);
                frame.setVisible(false);
                frame.dispose();
            }
        });
    }


    //QUA ANDRA' IL CODICE PER FAR COMPARIRE LE FOTO PERSONALI
}
