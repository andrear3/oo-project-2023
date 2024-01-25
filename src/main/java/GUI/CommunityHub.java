package GUI;

import Controller.Controller;
import Model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommunityHub {

    Controller controller = new Controller();
    Utente activeUtente = new Utente();

    private static JFrame frame ;
    private JButton accediAlleCollezioniPubblicheButton;
    private JButton ricercaAvanzataButton;
    private JButton creaUnaNuovaCollezioneButton;
    private JPanel PanelCommunity;

    public CommunityHub(Utente utente){
        this.frame = new JFrame("CommunityHub");
        frame.setContentPane(PanelCommunity);
        frame.setSize(500, 300);
        frame.setVisible(true);
        activeUtente = utente;

        accediAlleCollezioniPubblicheButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CommunityHubCollections communityHubCollections = new CommunityHubCollections(activeUtente);
                frame.setVisible(false);
                frame.dispose();
            }
        });
        creaUnaNuovaCollezioneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CommunityHubNewCollection communityHubNewCollection = new CommunityHubNewCollection(activeUtente);
                //frame.setVisible(false);
                //frame.dispose();
            }
        });

    }

    public CommunityHub(){

    }



}
