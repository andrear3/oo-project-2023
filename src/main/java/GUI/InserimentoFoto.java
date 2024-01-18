package GUI;

import Model.Utente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InserimentoFoto {
    Utente activeUtente = new Utente();

    private static JFrame frame;
    private JPanel panel1;
    private JPanel fotoJPanel;
    private JButton selezionaFotoButton;
    private JLabel tipoJLabel;
    private JComboBox tipoJcompo;
    private JLabel soggettoJLable;
    private JComboBox comboBox1;
    private JLabel dataJLable;
    private JTextField textField1;
    private JLabel longitudineJLable;
    private JLabel latitudineJLable;
    private JLabel Luogo;
    private JTextField luogoJField;
    private JTextField latitudineJField;
    private JTextField longitudineJField;
    private JButton indietroButton;
    private JButton inserisciFotoButton;

    public InserimentoFoto (Utente utente){
        this.frame =new JFrame("inserimento foto");
        frame.setContentPane(panel1);
        frame.setSize(800,300);
        frame.setVisible(true);
        activeUtente=utente;
        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MiaGalleria miaGalleria=new MiaGalleria(activeUtente);
                frame.setVisible(false);
                frame.dispose();
            }
        });
    }


}
