package GUI;
import Controller.Controller;
import Model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfiloUtente {

    Controller controller = new Controller();
    Utente activeUtente = new Utente();

    public JFrame frame;
    private JPanel PanelProfiloUtente;
    private JLabel nameLabel;
    private JLabel surnameLabel;
    private JLabel birthdayLabel;
    private JLabel genderLabel;
    private JLabel nicknameLabel;
    private JButton laMiaGalleriaButton;
    private JButton impostazioniUtenteButton;
    private JButton communityHubButton;



    public ProfiloUtente(Utente utente) {
        this.frame = new JFrame("ProfiloUtente");
        frame.setContentPane(PanelProfiloUtente);
        frame.setSize(500, 300);
        frame.setVisible(true);
        activeUtente = utente;
        nameLabel.setText(utente.getNameUtente());
        surnameLabel.setText(utente.getSurnameUtente());
        genderLabel.setText(utente.getGenderUtente());
        birthdayLabel.setText(utente.getBirthdateUtente().toString());
        nicknameLabel.setText(utente.getNicknameUtente());

        laMiaGalleriaButton.addActionListener(e ->{
            MiaGalleria miagalleria = new MiaGalleria(activeUtente);
            frame.setVisible(false);
            frame.dispose();
        });

        communityHubButton.addActionListener(e ->{
            CommunityHub communityHub = new CommunityHub(activeUtente);
            frame.setVisible(false);
            frame.dispose();
        });
        impostazioniUtenteButton.addActionListener(e ->{
            ImpostazioniUtente  impostazioniUtente = new ImpostazioniUtente(activeUtente);
            frame.setVisible(false);
            frame.dispose();
        });


    }
}
