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

            } else {
                System.out.println("Credenziali incorrette");
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

