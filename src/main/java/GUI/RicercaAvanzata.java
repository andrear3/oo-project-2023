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
    private JLabel firstLuogo;
    private JLabel secondLuogo;
    private JLabel thirdLuogo;
    private JButton indietroButton;

    public RicercaAvanzata(Utente utente) {
        this.frame = new JFrame("RicercaAvanzata");
        frame.setContentPane(PanelAvanzato);
        frame.setSize(700, 300);
        frame.setVisible(true);
        activeUtente = utente;

        ArrayList<String> array3;
        try {
            array3 = controller.top3LuoghiCTRL();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        firstLuogo.setText(array3.get(0));
        secondLuogo.setText(array3.get(1));
        thirdLuogo.setText(array3.get(2));

        buttonLuogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> allTags;
                boolean tagContained = false;
                try {
                    allTags = controller.getAllLocations();
                    if (allTags.contains(textLuogo.getText())) {
                        tagContained = true;
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                ArrayList<Integer> tempArray = new ArrayList<>();
                if (tagContained == true) {
                    try {
                        tempArray = controller.fotoStessoLuogoCTRL(textLuogo.getText());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                if (tagContained == true) {
                    RicercaAvanzataSoggettoLuogo ricercaAvanzataSoggettoLuogo = new RicercaAvanzataSoggettoLuogo(activeUtente, tempArray);
                    frame.setVisible(false);
                    frame.dispose();
                    }
                }
        });
        buttonSoggetto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> allTags;
                boolean tagContained = false;
                try {
                    allTags = controller.getAllTags();
                    if(allTags.contains(textSoggetto.getText())){
                        tagContained = true;
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                ArrayList<Integer> tempArray = new ArrayList<>();
                if(tagContained == true) {
                    try {
                        tempArray = controller.fotoStessoSoggettoCTRL(textSoggetto.getText());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                if(tagContained == true) {
                    RicercaAvanzataSoggettoLuogo ricercaAvanzataSoggettoLuogo = new RicercaAvanzataSoggettoLuogo(activeUtente, tempArray);
                    frame.setVisible(false);
                    frame.dispose();
                }
            }
        });

        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CommunityHub communityHub = new CommunityHub(activeUtente);
                frame.setVisible(false);
                frame.dispose();
            }
        });
    }
}
