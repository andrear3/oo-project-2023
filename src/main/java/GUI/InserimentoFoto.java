package GUI;

import Controller.Controller;
import Model.Photo;
import Model.Utente;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class InserimentoFoto {
    Utente activeUtente = new Utente();
    Controller controller=new Controller();
    private JFileChooser fileChooser;

    private static JFrame frame;
    private JPanel panel1;
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
    private JLabel tagUtenteJLabel;
    private JTextField textField2;
    private JLabel fotoVisualLabel;


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
        selezionaFotoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){

                fileChooser=new JFileChooser();
                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("foto","png","jpg"));

                if (fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
                controller.setImgPath(fileChooser.getSelectedFile().getAbsolutePath());
                    final File foto=(fileChooser.getSelectedFile());//trovare soluzione a questo problema
                    fotoVisualLabel.setIcon((Icon) foto);
                    frame.setVisible(false);
                    frame.dispose();
                    frame.pack();
                }

            }


        });
    }


}
