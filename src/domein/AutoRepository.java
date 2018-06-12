/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import persistentie.AutoMapper;

/**
 *
 * @author wh1zx
 */
public class AutoRepository {

    AutoMapper am = new AutoMapper();
  

    public void registreerAuto(String gebruikersnaam, String nummerplaat, long binnenrijdtijd) {

        am.registreerAuto(gebruikersnaam, nummerplaat, binnenrijdtijd);

    }

    public Auto geefAuto(String nummerplaat) {

        return am.geefAuto(nummerplaat);

    }
    
    public void updateBinnenrijdTijd(long binnenrijduur, String nummerplaat){
    
    am.updatebinnenrijduur(binnenrijduur, nummerplaat);
    
    }
    
    public long geefBinnenRijdTijd(String nummerplaat){
    
    
    return  am.geefBinnenRijdTijd(nummerplaat);
    
    }
    
    public void updateUrenEnPrijs(double prijs, double uren , String nummerplaat){
    
    
    am.updatePrijsEnUren(nummerplaat, prijs, uren);
    
    }
    
 
            
            
            

}
