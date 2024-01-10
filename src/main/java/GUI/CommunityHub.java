package GUI;

import Controller.Controller;
import Model.Utente;

import javax.swing.*;

public class CommunityHub {

    Controller controller = new Controller();
    Utente activeUtente = new Utente();

    private static JFrame frame ;
    private JButton accediAlleCollezioniPubblicheButton;
    private JButton accediAlleFotoPubblicheButton;
    private JButton ricercaAvanzataButton;
    private JButton creaUnaNuovaCollezioneButton;
    private JPanel PanelCommunity;

    public CommunityHub(Utente utente){
        this.frame = new JFrame("CommunityHub");
        frame.setContentPane(PanelCommunity);
        frame.setSize(500, 300);
        frame.setVisible(true);
        activeUtente = utente;

    }

    public CommunityHub(){

    }

}
