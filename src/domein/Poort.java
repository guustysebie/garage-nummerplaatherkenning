/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author wh1zx
 */
public class Poort {

    private String binnenRijdTijd ;
    private Boolean bareel;
    private static final DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

 

    public String getBinnenRijdTijd() {
        return binnenRijdTijd;
    }

    public void setBinnenRijdTijd(String binnenRijdTijd) {

       // Date date = new Date();
      //  String timestamp = sdf.format(date);

        this.binnenRijdTijd = binnenRijdTijd;
    }

    public Boolean getBareel() {
        return bareel;
    }

    public void setBareel(Boolean bareel) {
        this.bareel = bareel;
    }

    public Poort(String binnenRijdTijd, Boolean bareel) {
        this.binnenRijdTijd = binnenRijdTijd;
        this.bareel = bareel;
    }

    
    public String geefBinnenRijdTijd(){
    
    
    Date date = new Date();
      String timestamp = sdf.format(date);
    
      return timestamp;
    }
    
    
    public static void main(String[] args){
    
        Poort poort = new Poort("");
        
       String kd= poort.geefBinnenRijdTijd();
    
    System.out.print(kd);
    
    }

    public Poort(String binnenRijdTijd) {
        this.binnenRijdTijd = binnenRijdTijd;
    }
    
    
    
}
