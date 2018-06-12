/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.Auto;
import java.sql.Connection;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author wh1zx
 */
public class AutoMapper {

    private static final String REGISTREER1 = "insert into ID222177_g38.Auto(gebruikersnaam, nummerplaat) values (? , ? )";// maakt in databank object auto aan
    private static final String REGISTREER2 = "insert into ID222177_g38.Autologger(idAuto, bintijd )values ((select idAuto from ID222177_g38.Auto where nummerplaat = ? ),  ?)";//maakt autologger aan houd tijd bij en is verbonden met auto
    private static final String REGISTREER3 = "insert into ID222177_g38.PRIJS(uren,prijs, AutoID) values (0,0,(select idAuto from ID222177_g38.Auto where nummerplaat= ?))"; // maakt PRIJS aan verbonden met auto 
    // registratie gedeelte
    private static final String VRAAG_NAAM = "select gebruikersnaam from ID222177_g38.Auto where nummerplaat = ? ";
    //auto object opvragen aan db
    private static final String UPDATE_BINNENRIJDTIJD = "update ID222177_g38.Autologger set bintijd = ? where idAuto = (select idAuto from ID222177_g38.Auto where nummerplaat = ? )";
    //binnenrijdtijd updaten
    private static final String GEEFBINNENRIJDTIJD = "select bintijd from ID222177_g38.Autologger where idAuto = (select idAuto from ID222177_g38.Auto where nummerplaat = ?) ";
    // vraagt binnenrij tijd op aan db
    private static final String UPDATE_UREN_PRIJS = "update ID222177_g38.PRIJS  set PRIJS.prijs= prijs + ?, PRIJS.uren = uren +? where PRIJS.autoID= (select idAuto from ID222177_g38.Auto where nummerplaat = ?)";
    //update de uren en de prijs
    private static final String BETAAL = "update ID222177_g38.PRIJS set prijs = prijs - ? where autoID=(select idAuto from ID222177_g38.Auto where nummerplaat = ?)";
    
    
    public void registreerAuto(String gebruikersnaam, String nummerplaat, long binnenrijdtijd) {

        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(AutoMapper.REGISTREER1)) {

            query.setString(1, gebruikersnaam);
            query.setString(2, nummerplaat);

            query.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(AutoMapper.REGISTREER2)) {

            query.setString(1, nummerplaat);
            query.setLong(2, binnenrijdtijd);

            query.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(AutoMapper.REGISTREER3)) {

            query.setString(1, nummerplaat);

            query.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public Auto geefAuto(String nummerplaat) {
        Auto auto = null;

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(VRAAG_NAAM)) {
            query.setString(1, nummerplaat);
            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                    String naam = rs.getString("gebruikersnaam");

                    auto = new Auto(naam, nummerplaat);

                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return auto;

    }

    public void updatebinnenrijduur(long binnenrijduur, String nummerplaat) {

        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(UPDATE_BINNENRIJDTIJD)) {
            query.setLong(1, binnenrijduur);
            query.setString(2, nummerplaat);

            query.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public long geefBinnenRijdTijd(String nummerplaat) {
        long binnenrijdtijd = 0;

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(GEEFBINNENRIJDTIJD)) {
            query.setString(1, nummerplaat);
            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                    long bintijd = rs.getLong("bintijd");

                    binnenrijdtijd = bintijd;

                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return binnenrijdtijd;
    }

    public void updatePrijsEnUren(String nummerplaat, double prijs, double uren) {

        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(UPDATE_UREN_PRIJS)) {
            query.setDouble(1, prijs);
            query.setDouble(2, uren);
            query.setString(3, nummerplaat);

            query.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public void betaal(String nummerplaat, double betalen){
    
    
         try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(BETAAL)) {
            query.setDouble(1, betalen);
            query.setString(2, nummerplaat);

            query.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        
        
    }
    
    
}
