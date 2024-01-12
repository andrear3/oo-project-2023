package GUI;

import Controller.Controller;
import Model.*;

import javax.swing.*;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommunityHubCollections {
    //Controller controller = new Controller();
    Utente activeUtente = new Utente();
    private static JFrame frame ;
    private JPanel PanelCollections;
    private JLabel infoLabel;

    public CommunityHubCollections(Utente utente){

        Controller controller = new Controller();
        //!!!!!!!!!!!!!!
        PanelCollections.setLayout(new BoxLayout(PanelCollections, BoxLayout.PAGE_AXIS));
        //!!!!!!!!!!!!!!


        this.frame = new JFrame("CommunityHubCollections");
        frame.setContentPane(PanelCollections);
        frame.setSize(500, 300);
        frame.setVisible(true);





        ArrayList<String> collectionArray = new ArrayList<>();
        try {
            collectionArray = controller.getAllPubCollectionCTRL();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (String labelName : collectionArray) {
            JLabel label = new JLabel(labelName);
            PanelCollections.add(label);
            this.PanelCollections.add(new JButton("Button"));
            this.PanelCollections.revalidate();
        }
    }
}

