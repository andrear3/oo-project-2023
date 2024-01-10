package DAO;

import Model.Photo;

import java.sql.SQLException;

public interface FotoDAO {
    public Photo fotoStessoLuogo(String location_name ) throws SQLException;
    //void DettagliFoto(String photo_code) throws SQLException;

    //void FotoUtente(String photo_code, String username) throws SQLException;

    //void FotoPrivateUtente(String photo_code, String username) throws SQLException;

    //void FotoStessoSoggetto(String soggetto);

    //void FotoStessoLuogo(String luogo);

}
