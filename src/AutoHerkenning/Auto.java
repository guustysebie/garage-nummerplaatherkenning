/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AutoHerkenning;

/**
 *
 * @author daanw
 */

public class Auto {
    private String  gebruikersnaam; 
    private String nummerplaat;
    private long bintijd;
    private long buittijd;
    private int prijs;

    public Auto(String gebruikersnaam, String nummerplaat, long bintijd, long buittijd, int prijs) {
        this.gebruikersnaam = gebruikersnaam;
        this.nummerplaat = nummerplaat;
        this.bintijd = bintijd;
        this.buittijd = buittijd;
        this.prijs = prijs;
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

    public long getBintijd() {
        return bintijd;
    }

    public void setBintijd(long bintijd) {
        this.bintijd = bintijd;
    }

    public long getBuittijd() {
        return buittijd;
    }

    public void setBuittijd(long buittijd) {
        this.buittijd = buittijd;
    }

    public int getPrijs() {
        return prijs;
    }

    public void setPrijs(int prijs) {
        this.prijs = prijs;
    }
    
}
