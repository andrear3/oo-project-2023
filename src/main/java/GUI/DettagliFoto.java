package GUI;

import Controller.Controller;
import Model.Photo;
import Model.Utente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class DettagliFoto {
    Utente activeUtente = new Utente();
    Controller controller=new Controller();
    Photo lastPH =new Photo();
    Photo ph=new Photo();
    private static JFrame frame;
    private JPanel panel1;
    private JLabel visualPhotoIns;
    private JButton confermaECaricaFotoButton;
    private JComboBox soggettoComboBox;
    private JLabel tagJLable;
    private JTextField tagJField;
    private JLabel dettagliOpzFoto;
    private JButton insAtroTag;

    public DettagliFoto(Utente utente) {

        this.frame =new JFrame(" dettaglifoto");
        frame.setContentPane(panel1);
        frame.setSize(500,300);
        frame.setVisible(true);
        activeUtente=utente;
        //l'inserimento dei dati è stato diviso per problemi legati al photo_code della foto ceh viene creato al momento di inserimento della foto quindi era impossibile associare una fto ad un soggetto
        //nel caso in cui venisse premuto senza aver caricato una foto restituirebbe un errore e quindi non caricherebbe dati nel data base

//listener fi conferma e caricamento della foto e caricamento nel del soggetto
        confermaECaricaFotoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //inserimento soggetto
                Integer a=0;

                Integer photo_code;

                String soggetto = (String) soggettoComboBox.getSelectedItem();


                System.out.println("il soggeto preso è " + soggetto);
                try {
                    photo_code=controller.getPhoto_codeCTRL(a);
                    controller.aggiungiSoggettoCTRL(photo_code,soggetto);
                    System.out.println("photocode valore = " + photo_code);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });
//listener fi conferma e caricamento della foto e caricamento nel del tag

        insAtroTag.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer a2=0;
                Integer photo_code2;
                String tag;
                tag = tagJField.getText();
                System.out.println("il tag : "+tag);
                try {
                    photo_code2=controller.getPhoto_codeCTRL(a2);
                    controller.aggiungiUserTagCTRL(photo_code2,tag);
                }catch (SQLException ex){
                    throw new RuntimeException(ex);
                }
            }
        });
    }

}
