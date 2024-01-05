package GUI;
import Controller.Controller;
import Model.*;

import javax.swing.*;

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


    public ProfiloUtente(){

    }
    public ProfiloUtente(Utente utente){
        JFrame frame = new JFrame("ProfiloUtente");
        frame.setContentPane(PanelProfiloUtente);
        frame.setSize(500,300);
        frame.setVisible(true);

        nameLabel.setText(utente.getNameUtente());
        surnameLabel.setText(utente.getSurnameUtente());
        genderLabel.setText(utente.getGenderUtente());
        birthdayLabel.setText(utente.getBirthdateUtente().toString());
        nicknameLabel.setText(utente.getNicknameUtente());
    }
}
