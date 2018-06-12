/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.List;
import persistentie.ParkingMapper;

/**
 *
 * @author guust
 */
public class ParkingRepository {

    ParkingMapper pm = new ParkingMapper();

    public void voegAutoToeAanParking(String nummerplaat) {

        pm.stopAutoInParking(nummerplaat);

    }

    public void verwijderAutoUitParking(String nummerplaat) {

        pm.verwijderAutoUitParking(nummerplaat);

    }

    public int geefAantalAutosInParking() {

        return pm.geefAantalWagensInParking();

    }

    public String geefAutoInParking(){
    
        
        return pm.geefAutosInDeParking();
        
    
    
    }
    
    
    
}
