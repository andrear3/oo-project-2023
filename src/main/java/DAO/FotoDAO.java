package DAO;

import Model.Photo;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FotoDAO {
     ArrayList<Integer> fotoStessoLuogo(String location_name ) throws SQLException;

     ArrayList<Photo> fotoStessoUtente(String nickname ) throws SQLException;

    //void DettagliFoto(String photo_code) throws SQLException;

    //void FotoUtente(String photo_code, String username) throws SQLException;

    //void FotoPrivateUtente(String photo_code, String username) throws SQLException;

    //void FotoStessoSoggetto(String soggetto);

    //void FotoStessoLuogo(String luogo);

     ArrayList<Photo> getAllInfoFromPhotoCodes (ArrayList<Integer> photoCodes) throws SQLException;

}
