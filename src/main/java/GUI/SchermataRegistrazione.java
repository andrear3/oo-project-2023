package GUI;

import Controller.Controller;
import Model.Utente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import javax.swing.JPasswordField;

public class SchermataRegistrazione {
    private JPanel panel1;
    private JLabel nicknameJL;

    private JLabel cognomeJL;
    private JLabel nomeJl;
    private JLabel dataJL;
    private JLabel genederJl;
    private JComboBox comboBox1;
    private JLabel passwordJL;
    private JTextField nickNameField;
    private JTextField nomeField;
    private JTextField cognomeField;
    private JTextField dataNField;
    private JPasswordField passwordField1;
    private JCheckBox showPassCK;
    private JPanel panelPrincipale;
    private JButton invioRegistraButton;
    private JButton loginButton;
    private JLabel annoLab;
    private static JFrame frame;
    Controller controller = new Controller();
    Utente utente=new Utente();


    public SchermataRegistrazione(JFrame schermataIniziale) {
        frame = new JFrame("Registrazione");
        frame.setContentPane(panelPrincipale);

        frame.setSize(500, 300);
        frame.setVisible(true);
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

        comboBox1.addItem("M");
        comboBox1.addItem("F");

        invioRegistraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nickname = nickNameField.getText();
                String name= nomeField.getText();
                String surname=cognomeField.getText();
                Date birthdate= Date.valueOf(dataNField.getText());
                String gender=(String)comboBox1.getSelectedItem();
                String password= passwordField1.getText();
                try{
                    utente=controller.registraUCTRL( nickname ,name, surname, birthdate, gender, password);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                schermataIniziale.setVisible(true);
                frame.setVisible(false);
                frame.dispose();
            }
        });
    }


}
