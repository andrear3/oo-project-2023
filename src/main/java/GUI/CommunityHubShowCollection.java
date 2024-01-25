package GUI;

import Controller.*;
import Model.Photo;
import Model.Utente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class CommunityHubShowCollection {

    Utente activeUtente = new Utente();

    Controller controller = new Controller();
    private static JFrame frame;
    private JPanel CommunityHubPanel;
    private JLabel photoImgLabel;
    private JButton addButton;
    private JButton prevButton;
    private JButton nextButton;
    private JLabel photoOwner;
    private JLabel tagLabel;
    private JLabel soggettoPhoto;
    private JLabel soggettoLabel;
    private JButton deleteButton;

    static Integer counter = 0;
    public CommunityHubShowCollection(Utente utente, String galleryName){
        this.frame = new JFrame("CommunityHubShowCollection");
        frame.setContentPane(CommunityHubPanel);
        frame.setSize(900,500);
        frame.setVisible(true);
        activeUtente = utente;
        deleteButton.setVisible(false);


        ArrayList<Integer> photoCodeArray = new ArrayList<>();
        try {
            photoCodeArray = controller.getAllPhotoFromCollectionCTRL(galleryName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Photo> photoArray = new ArrayList<Photo>();
        try {
            photoArray = controller.getAllInfoFromPhotoCodesCTRL(photoCodeArray);
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
            if(Objects.equals(photoArray.get(0).getNickname(), utente.getNicknameUtente())){
                deleteButton.setVisible(true);
            } else {
                deleteButton.setVisible(false);
            }
        }

        /*for(Integer i=0;i<photoArray.size();i++){
            String temp;
            temp = photoArray.get(i).getDevice();
            System.out.println(temp);

        }*/

        ArrayList<Photo> finalPhotoArray = photoArray;
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(counter < finalPhotoArray.size()){
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
                    if(Objects.equals(finalPhotoArray.get(counter).getNickname(), utente.getNicknameUtente())){
                        System.out.println("IF ENTRATO");
                        deleteButton.setVisible(true);

                    }
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
                    if(Objects.equals(finalPhotoArray.get(counter).getNickname(), utente.getNicknameUtente())){
                        deleteButton.setVisible(true);
                    } else {
                        deleteButton.setVisible(false);
                    }
                }
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CommunityHubCollections communityHubCollections = new CommunityHubCollections(utente);
                frame.setVisible(false);
                frame.dispose();
            }
        });


        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.deleteSharedPhotoCTRL(finalPhotoArray.get(counter).getPhoto_code());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
