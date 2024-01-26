package GUI;

import Model.*;
import Controller.Controller;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class RicercaAvanzata {

    JFrame frame = new JFrame();

    Controller controller = new Controller();

    Utente activeUtente;
    private JTextField textLuogo;
    private JButton buttonLuogo;
    private JLabel labelLuogo;
    private JTextField textSoggetto;
    private JButton buttonSoggetto;
    private JLabel labelSoggetto;
    private JButton buttonTop3;
    private JLabel labelTop3;
    private JPanel PanelAvanzato;

    public RicercaAvanzata(Utente utente) {
        this.frame = new JFrame("RicercaAvanzata");
        frame.setContentPane(PanelAvanzato);
        frame.setSize(700, 300);
        frame.setVisible(true);
        activeUtente = utente;


        buttonLuogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Integer> tempArray;
                try {
                    tempArray = controller.fotoStessoLuogoCTRL(textLuogo.getText());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                RicercaAvanzataSoggettoLuogo ricercaAvanzataSoggettoLuogo = new RicercaAvanzataSoggettoLuogo(activeUtente, tempArray);
                frame.setVisible(false);
                frame.dispose();
            }
        });
        buttonSoggetto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Integer> tempArray;
                try {
                    tempArray = controller.fotoStessoSoggettoCTRL(textSoggetto.getText());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                RicercaAvanzataSoggettoLuogo ricercaAvanzataSoggettoLuogo = new RicercaAvanzataSoggettoLuogo(activeUtente, tempArray);
                frame.setVisible(false);
                frame.dispose();
            }
        });
        buttonTop3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
