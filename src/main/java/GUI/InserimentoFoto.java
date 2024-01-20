package GUI;

import Controller.Controller;
import Model.Utente;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.time.LocalDate;

import static java.nio.file.Files.copy;

public class InserimentoFoto {
    Utente activeUtente = new Utente();
    Controller controller=new Controller();
    private JFileChooser fileChooser;

    private static JFrame frame;
    private JPanel panel1;
    private JButton selezionaFotoButton;
    private JLabel tipoJLabel;
    private JComboBox tipoJcombo;
    private JLabel soggettoJLable;
    private JComboBox soggettoJCombo;
    private JLabel longitudineJLable;
    private JLabel latitudineJLable;
    private JLabel Luogo;
    private JTextField luogoJField;
    private JTextField latitudineJField;
    private JTextField longitudineJField;
    private JButton indietroButton;
    private JButton inserisciFotoButton;
    private JLabel tagUtenteJLabel;
    private JTextField textField2;
    private JLabel fotoVisualLabel;
    private JLabel deviceJLable;
    private JTextField deviceJField;


    public InserimentoFoto (Utente utente){
        this.frame =new JFrame("inserimento foto");
        frame.setContentPane(panel1);
        frame.setSize(900,500);
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
        //NON FUNZIONA . DA APPROFONDIRE


        selezionaFotoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){

                fileChooser=new JFileChooser();
                fileChooser.setAcceptAllFileFilterUsed(true);//permette di filtrare i file del formato specificato
                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("foto","jpeg","jpg","png"));//specifichiamo il formato del file
                int risultato=fileChooser.showOpenDialog(null);//seleziona il file da aprire
                //int risultato=fileChooser.showSaveDialog(null);//seleziona il file da salvare
                //se vine selezionato correttamente il file filechooser ritorna 0 quindi vado a creare un if che controlli il valore di risultato
                //fileChooser.setCurrentDirectory(new File());// in new File() se inseriamo "." new File(".") andrà a cercare il file nella directory del nostro progetto

                if(risultato==JFileChooser.APPROVE_OPTION) {
                    //se viene sezionato correttamente viene crato il file che dobbiamo inserire
                    File file=fileChooser.getSelectedFile();
                    String path="src/main/resources/anteprima";//path di destinazione
                    Path destinazione=Path.of(path,file.getName());//andiamo a settare il path di destinazione e il nome del file che abbiamo caricato
                    try{
                        //copio il file passando il path del file da copiare , il path di destinazione e copia standard
                        Files.copy(file.toPath(),destinazione,StandardCopyOption.REPLACE_EXISTING);
                        System.out.println("file copiato nella cartelal di destianzione");//mi assicuto che si andata a buon fine
                        ImageIcon anteprima=new ImageIcon(String.valueOf(file.toPath()));//dichiaro un icona gli passo il path del file che in questo caso è anteprima
                        fotoVisualLabel.setIcon(anteprima);//visualizzo l`anteprima dell`immagine che voglio caricare
                    }catch (IOException ex){

                        System.out.println("la copia non è andata a buon fine ");
                    }

                }

               
                }



        });

        inserisciFotoButton.addActionListener(new ActionListener() {
            @Override  //lister per caricamento definitivo della foto
            public void actionPerformed(ActionEvent e) {
                File file=fileChooser.getSelectedFile();
                String path="src/main/resources/gallery";//path di destinazione definitivo
                Path destinazione=Path.of(path,file.getName());//andiamo a settare il path di destinazione e il nome del file che abbiamo caricato
                try{
                    //copio il file passando il path del file da copiare , il path di destinazione e copia standard
                    Files.copy(file.toPath(),destinazione,StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("file copiato nella cartelal di destianzione");
                }catch (IOException ex){

                    System.out.println("la copia non è andata a buon fine ");
                }
                //inserimento della foto nel database
                Integer phc=0;//assegno 0 all'intero perché la funzione passerà un valore di default al databse
                    String scope= (String)tipoJcombo.getSelectedItem();//faccio un casting per prendere la riga di testo per l'item del combo
                    String nickname= activeUtente.getNicknameUtente();//prendo il nickname dell'utente attivo
                    String location= luogoJField.getText();//prendo la stringa del luogo
                    String device=deviceJField.getText();//prendo la stringa del device
                    //LocalDate photo_date=LocalDate.now();
                    LocalDate data= LocalDate.now();
                    String pat=String.valueOf(file.getName());
                try {
                    controller.insertPhotoCTRL(phc,scope,nickname,location,device,data,pat);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
//inserimento coordiante
                Integer photo_count = 0;
                Double x=Double.valueOf(latitudineJField.getText());
                Double y= Double.valueOf(longitudineJField.getText());


                try {
                    controller.aggLocationPhotoCTRL(location,x,y,photo_count);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                //inserimento soggetto
                String soggetto =(String)soggettoJCombo.getSelectedItem();
                try {
                    controller.aggiungiSoggetto2CTRL(phc,soggetto);
                }catch (SQLException ex){
                    throw new RuntimeException(ex);
                }
            }
        });
    }


}
