package GUI;

import javax.swing.*;
import Model.*;
import Controller.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;


public class RicercaAvanzataSoggettoLuogo {

    JFrame frame = new JFrame();

    Controller controller = new Controller();

    Utente activeUtente;

    Integer counter = 0;

    private JLabel photoImgLabel;
    private JButton backButton;
    private JButton prevButton;
    private JButton nextButton;
    private JLabel photoOwner;
    private JLabel tagLabel;
    private JLabel soggettoPhoto;
    private JLabel soggettoLabel;
    private JPanel PanelSL;

    public RicercaAvanzataSoggettoLuogo(Utente utente, ArrayList<Integer> listaFoto){
        this.frame = new JFrame("RicercaAvanzataSoggettoLuogo");
        frame.setContentPane(PanelSL);
        frame.setSize(700, 300);
        frame.setVisible(true);
        activeUtente = utente;

        ArrayList<Photo> photoArray = new ArrayList<Photo>();
        try {
            photoArray = controller.getAllInfoFromPhotoCodesCTRL(listaFoto);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(photoArray.get(0)!=null){
            ImageIcon temp = controller.setImgPathSize(photoArray.get(0).getPath(),400,300);
            photoImgLabel.setIcon(temp);
            photoOwner.setText(photoArray.get(0).getNickname());
            String tempText;
            try {
                tempText = controller.PersoneTaggateCTRL(photoArray.get(0).getPhoto_code());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            tagLabel.setText(tempText);
            String tempSoggetto;
            try {
                tempSoggetto = controller.SoggettoInFotoCTRL(photoArray.get(0).getPhoto_code());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            soggettoLabel.setText(tempSoggetto);

        }

        ArrayList<Photo> finalPhotoArray = photoArray;
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(counter < finalPhotoArray.size()-1){
                    counter = counter + 1;
                    ImageIcon temp = controller.setImgPathSize(finalPhotoArray.get(counter).getPath(),400,300);
                    photoImgLabel.setIcon(temp);
                    photoOwner.setText(finalPhotoArray.get(counter).getNickname());
                    String tempText;
                    try {
                        tempText = controller.PersoneTaggateCTRL(finalPhotoArray.get(counter).getPhoto_code());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    tagLabel.setText(tempText);
                    String tempSoggetto;
                    try {
                        tempSoggetto = controller.SoggettoInFotoCTRL(finalPhotoArray.get(counter).getPhoto_code());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    soggettoLabel.setText(tempSoggetto);
                    System.out.println(finalPhotoArray.get(counter).getNickname());
                    System.out.println(utente.getNicknameUtente());

                }
            }
        });


        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(counter > 0){
                    counter = counter - 1;
                    ImageIcon temp = controller.setImgPathSize(finalPhotoArray.get(counter).getPath(),400,300);
                    photoImgLabel.setIcon(temp);
                    photoOwner.setText(finalPhotoArray.get(counter).getNickname());
                    String tempText;
                    try {
                        tempText = controller.PersoneTaggateCTRL(finalPhotoArray.get(counter).getPhoto_code());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    tagLabel.setText(tempText);
                    String tempSoggetto;
                    try {
                        tempSoggetto = controller.SoggettoInFotoCTRL(finalPhotoArray.get(counter).getPhoto_code());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    soggettoLabel.setText(tempSoggetto);

                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RicercaAvanzata ricercaAvanzata = new RicercaAvanzata(activeUtente);
                frame.setVisible(false);
                frame.dispose();
            }
        });
    }
}
