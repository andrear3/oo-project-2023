package GUI;

import Controller.Controller;
import Model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommunityHubCollections {
    //Controller controller = new Controller();
    Utente activeUtente = new Utente();
    private static JFrame frame;
    private JPanel PanelCollections;
    private JLabel infoLabel;
    private JButton backButton;

    JLabel testLabel = new JLabel();

    public CommunityHubCollections(Utente utente) {

        Controller controller = new Controller();
        //!!!!!!!!!!!!!!
        PanelCollections.setLayout(new BoxLayout(PanelCollections, BoxLayout.PAGE_AXIS));
        //!!!!!!!!!!!!!!
        JScrollPane scrollPane = new JScrollPane(PanelCollections);


        this.frame = new JFrame("CommunityHubCollections");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(scrollPane); //scrolling
        frame.setSize(500, 300);
        frame.setVisible(true);

        ArrayList<String> collectionArray;
        try {
            collectionArray = controller.getAllPubCollectionCTRL();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (String labelName : collectionArray) {
            JButton tempButton = new JButton();
            tempButton.setText(labelName);

            PanelCollections.add(tempButton);
            tempButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CommunityHubShowCollection communityHubShowCollection = new CommunityHubShowCollection(utente, labelName);
                    frame.setVisible(false);
                    frame.dispose();
                }
            });
            /*public void actionPerformed(ActionEvent e){
                if e.getSource()==tempButton()
            }*/
        }
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CommunityHub communityHub = new CommunityHub(activeUtente);
                frame.setVisible(false);
                frame.dispose();
            }
        });
    }


}