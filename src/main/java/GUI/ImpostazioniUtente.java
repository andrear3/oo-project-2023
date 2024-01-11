package GUI;

import Controller.Controller;
import Model.Utente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ImpostazioniUtente {
    private JLabel modificaProfilo;
    private JLabel nomeUtente;
    private JButton modificaNome;
    private JButton modificaPassword;
    private JButton eliminaProfilo;
    private JPanel impostazioniUtentePanel;
    private JTextArea modNomeArea;
    private JTextArea modPassArea;
    public JFrame frame;
    Utente activeUtente = new Utente();
    Controller controller = new Controller();



    public ImpostazioniUtente(Utente utente){
        this.frame = new JFrame("Impostazioni Utente");
        nomeUtente.setText(utente.getNicknameUtente());
        frame.setContentPane(impostazioniUtentePanel);
        frame.setSize(500, 300);
        frame.setVisible(true);
        activeUtente = utente;
        modificaPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nickname= activeUtente.getNicknameUtente();
                String password= modPassArea.getText();
                try{
                    activeUtente=controller.modPassCTRL(nickname,password);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }


}
