/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

/**
 *
 * @author daanw
 */
public class Auto {

    private String gebruikersnaam;
    private String nummerplaat;
    private int bintijd;
    private int buittijd;
    private int prijs;

    public Auto(String gebruikersnaam, String nummerplaat) {
        this.gebruikersnaam = gebruikersnaam;
        this.nummerplaat = nummerplaat;

    }
// set & get vr alles

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public String getNummerplaat() {
        return nummerplaat;
    }

    public void setNummerplaat(String nummerplaat) {
        this.nummerplaat = nummerplaat;
    }

}
