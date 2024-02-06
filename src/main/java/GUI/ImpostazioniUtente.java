package GUI;

import Controller.Controller;
import Model.Utente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;

public class ImpostazioniUtente {
    private JLabel modificaProfilo;
    private JLabel nomeUtente;
    private JButton modificaDN;
    private JButton modificaPassword;
    private JButton eliminaProfilo;
    private JPanel impostazioniUtentePanel;
    private JTextArea modDN;
    private JTextArea modPassArea;
    private JPasswordField passwordField1;
    private JCheckBox showPassCK;
    private JButton indietroButton;
    public JFrame frame;
    Utente activeUtente = new Utente();
    Controller controller = new Controller();





    public ImpostazioniUtente(Utente utente){
        this.frame = new JFrame("Impostazioni Utente");
        nomeUtente.setText(utente.getNicknameUtente());
        frame.setContentPane(impostazioniUtentePanel);
        frame.setSize(600, 400);
        frame.setVisible(true);
        activeUtente = utente;
        //listener per la modifica della password
        modificaPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nickname= activeUtente.getNicknameUtente();
                String password= passwordField1.getText();
                try{
                    activeUtente=controller.modPassCTRL(nickname,password);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        //listener per la modifica della data di nascita

        modificaDN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nickname= activeUtente.getNicknameUtente();
                Date dataN= Date.valueOf(modDN.getText());
                try{
                    activeUtente=controller.modDNCTRL(activeUtente.getNicknameUtente(),dataN);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        //listener per eliminazione profilo

        eliminaProfilo.addActionListener( new ActionListener(){
        @Override
                public void actionPerformed(ActionEvent e){
            String nickname=activeUtente.getNicknameUtente();
            try{
                activeUtente=controller.eliminaUCTRL(nickname);

            }catch(SQLException ex){
                throw  new RuntimeException(ex);
            }
        }
});
        //listener per la visualizzazione della password in fase di inserimento

        showPassCK.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(showPassCK.isSelected()){
                    passwordField1.setEchoChar((char)0);
                }else{
                    passwordField1.setEchoChar('*');
                }
            }
        });
        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProfiloUtente profiloUtente =new ProfiloUtente(activeUtente);
                frame.setVisible(false);
                frame.dispose();
            }
        });
    }


}
