package GUI;

import Controller.Controller;
import Model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CommunityHub {

    Controller controller = new Controller();
    Utente activeUtente = new Utente();

    private static JFrame frame ;
    private JButton accediAlleCollezioniPubblicheButton;
    private JButton ricercaAvanzataButton;
    private JButton creaUnaNuovaCollezioneButton;
    private JPanel PanelCommunity;
    private JButton tornaIndietroButton;

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

        ricercaAvanzataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RicercaAvanzata ricercaAvanzata = new RicercaAvanzata(activeUtente);
                frame.setVisible(false);
                frame.dispose();
            }
        });

        tornaIndietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProfiloUtente profiloUtente = new ProfiloUtente(activeUtente);
                frame.setVisible(false);
                frame.dispose();
            }
        });

    }




}
