package GUI;

import Controller.Controller;
import Model.Utente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class CommunityHubNewCollection {
    Utente activeUtente = new Utente();
    private static JFrame frame;
    private JPanel PanelNewCollection;
    private JTextField textField1;
    private JButton confermaButton;

    public CommunityHubNewCollection(Utente utente) {

        Controller controller = new Controller();
        this.frame = new JFrame("CommunityHubNewCollection");
        frame.setContentPane(PanelNewCollection);
        frame.setSize(350, 150);
        frame.setVisible(true);

        confermaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String getNewCollection =  textField1.getText();
                if(getNewCollection.length()!=0){
                    try {
                        controller.newPubCollectionCTRL(getNewCollection);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }
}