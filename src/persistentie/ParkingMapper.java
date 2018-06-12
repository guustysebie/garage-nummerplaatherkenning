/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author guust
 */
public class ParkingMapper {

    private static final String VOEG_AUTO_TOE_IN_PARKING = "insert into ID222177_g38.Parking set Parking.auto = (select nummerplaat from ID222177_g38.Auto where nummerplaat = ? ) ";
    private static final String VERWIJDER_UIT_PARKING = "delete from ID222177_g38.Parking where Auto = ? limit 1";
    private static final String GEEF_AANTAL_AUTOS_IN_PARKING = "select count(*) from ID222177_g38.Parking ";
    private static final String GEEF_AUTOS_IN_PARKING = "select Auto, gebruikersnaam from  ID222177_g38.Parking,  ID222177_g38.Auto where Parking.auto = Auto.nummerplaat ";

    public void stopAutoInParking(String nummerplaat) {

        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(VOEG_AUTO_TOE_IN_PARKING)) {

            query.setString(1, nummerplaat);

            query.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public void verwijderAutoUitParking(String nummerplaat) {
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(VERWIJDER_UIT_PARKING)) {

            query.setString(1, nummerplaat);

            query.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public int geefAantalWagensInParking() {
        int hoeveelheid = 0;
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(GEEF_AANTAL_AUTOS_IN_PARKING)) {

            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                    int hoeveel = rs.getInt("count(*)");
                    hoeveelheid = hoeveel;
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return hoeveelheid;

    }

    public String geefAutosInDeParking() {

        List<String> autos = new ArrayList<>();
        String auto ="  ";

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(GEEF_AUTOS_IN_PARKING)) {

            try (ResultSet rs = query.executeQuery()) {

                while (rs.next()) {
                    String nummerplaat = rs.getString("Auto");
                    String gebruikersnaam = rs.getString("gebruikersnaam");
                   
                    auto += String.format("* %s - %s *%n  ", nummerplaat, gebruikersnaam);

                    

                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return auto;
    }

 
   
    
}


