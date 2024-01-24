package GUI;

import Controller.Controller;
import Model.*;
import javafx.scene.layout.Pane;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommunityHubCollections {
    //Controller controller = new Controller();
    Utente activeUtente = new Utente();
    private static JFrame frame ;
    private JPanel PanelCollections;
    private JLabel infoLabel;

    JLabel testLabel = new JLabel();

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

            JPanel collectionPanel = new JPanel();
            collectionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

            JLabel collectionLabel = new JLabel(labelName);
            collectionPanel.add(collectionLabel);

            ArrayList<Integer> photoCodes = controller.getAllPhotoFromCollectionCTRL(labelName);

            for (int i = 0; i < photoCodes.size(); i++) {
                String textLabel = String.valueOf(photoCodes.get(i));

                ImageIcon test = controller.setImgPathSize(controller.getPathCTRL(Integer.valueOf(textLabel)), 100,100);
                JLabel testLabel = new JLabel(test);

                System.out.println(testLabel);
                //JLabel photoLabel = new JLabel(textLabel);


                collectionPanel.add(testLabel);
                //collectionPanel.add(photoLabel);

                //AL TERZO ELEMENTO FINISCE LA ROW
                if ((i + 1) % 3 == 0) {
                    PanelCollections.add(collectionPanel);
                    collectionPanel = new JPanel();
                    collectionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

                    //collectionPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0));
                }
            }

            PanelCollections.add(collectionPanel);
            PanelCollections.revalidate();
            }
        }
    }


