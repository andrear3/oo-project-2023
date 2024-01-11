package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LocationDAO {
    ArrayList<String> top3Luoghi()throws SQLException;

}
