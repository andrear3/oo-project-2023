package GUI;
import Controller.Controller;
import Model.*;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SchermataIniziale {

    private Utente activeUtente;
    private static JFrame frame;
    private JPanel PanelIniziale;
    private JButton inviaButton;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton registraButton;
    private JCheckBox showPassCK;

    public SchermataIniziale() {

        Controller controller = new Controller();
        inviaButton.addActionListener(e -> {
            //System.out.println(textField1.getText());
            String checkNickname =  textField1.getText();
            String checkPass = String.valueOf(passwordField1.getPassword());
            boolean loginStatus;
            try {
                loginStatus = controller.checkUtenteExistsCTRL(checkNickname, checkPass);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            if(loginStatus){
                System.out.println("Login effettuato");
                try {
                    activeUtente = controller.getUtenteDBCTRL(checkNickname);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                ProfiloUtente profiloUtente = new ProfiloUtente(activeUtente);
                frame.setVisible(false);
                frame.dispose();

            } else {
                System.out.println("Credenziali incorrette");
            }
        });
        registraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SchermataRegistrazione schermataRegistrazione=new SchermataRegistrazione();

                frame.setVisible(false);
                frame.dispose();
            }
        });
        showPassCK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
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
    }

    public static void main(String[] args) {
    frame = new JFrame("SchermataIniziale");
    frame.setContentPane(new SchermataIniziale().PanelIniziale);
    frame.setSize(500,300);
    frame.setVisible(true);
    }
}

