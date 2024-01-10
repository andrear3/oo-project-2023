package GUI;

import Controller.Controller;
import Model.Utente;

import javax.swing.*;

public class MiaGalleria {

    Controller controller = new Controller();
    Utente activeUtente = new Utente();

    private JPanel panel1;
    private static JFrame frame;
    public MiaGalleria(Utente utente){
        this.frame = new JFrame("MiaGalleria");
        frame.setContentPane(panel1);
        frame.setSize(500,300);
        frame.setVisible(true);
    }

    //QUA ANDRA' IL CODICE PER FAR COMPARIRE LE FOTO PERSONALI 
}
