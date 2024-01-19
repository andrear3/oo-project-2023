package GUI;

import Controller.Controller;
import Model.*;
import javafx.scene.layout.Pane;

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

    public CommunityHubCollections(Utente utente) throws SQLException {

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
            JLabel label = new JLabel(labelName);
            System.out.println(label.getText());
            ArrayList<Integer> photoCodes = controller.getAllPhotoFromCollectionCTRL(label.getText());
            System.out.println(photoCodes);
            ArrayList<Photo> photoModelArray = controller.getAllInfoFromPhotoCodesCTRL(photoCodes);
            System.out.println(photoModelArray);

            for(int i = 0; i<photoModelArray.size(); i++){
                String textLabel = String.valueOf(photoCodes.get(i));
                JLabel label2 = new JLabel(textLabel);
                PanelCollections.add(label2);
                PanelCollections.add(label);
                this.PanelCollections.add(new JButton("Button"));
                this.PanelCollections.revalidate();
            }
        }
    }
}

