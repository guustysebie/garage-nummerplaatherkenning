/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author wh1zx
 */
public class domeincontroller {

    AutoRepository ar = new AutoRepository();
    ParkingRepository pr = new ParkingRepository();
    private static final DateFormat timestamp = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
    private Auto binnengeredenauto;

    public void registreerAuto(String gebruikersnaam, String nummerplaat) {

        binnengeredenauto = new Auto(gebruikersnaam, nummerplaat);
        ar.registreerAuto(gebruikersnaam, nummerplaat, geefActueletijd());
        //test

    }

    public int rijBinnen(String nummerplaat) {

        if ("null".equals(geefNaam(nummerplaat))) {

            return 1; //niet herkend

        } else {

            updatBinnenrijdtijd(nummerplaat);
            return 2;//herkent

        }

    }

    public String geefNaam(String nummerplaat) {

        if (ar.geefAuto(nummerplaat) == null) {

            return "null";

        } else {

            return ar.geefAuto(nummerplaat).getGebruikersnaam();

        }
    }

    public void updatBinnenrijdtijd(String nummerplaat) {

        ar.updateBinnenrijdTijd(geefActueletijd(), nummerplaat);

    }

    public void rijdBuiten() {

    }

    public double geefTebetalenPrijsSessie(String nummerplaat) {

        long tijd2 = ar.geefBinnenRijdTijd(nummerplaat);

        double tijd3;
        double prijs;

        tijd3 = (geefActueletijd() - tijd2) / 60 / 1000;

        prijs = tijd3 * 0.03333;

        return prijs;

    }

    public double geefUrenSessie(String nummerplaat) {

        long tijd2 = ar.geefBinnenRijdTijd(nummerplaat);

        double tijd3;
        tijd3 = (geefActueletijd() - tijd2) / 60 / 1000;
        return tijd3;
    }

    public long geefBinnenRijdTijd(String nummerplaat) {

        return ar.geefBinnenRijdTijd(nummerplaat);

    }

    public void updateUrenEnPrijs(String nummerplaat, double prijs, double uren) {

        ar.updateUrenEnPrijs(prijs, uren, nummerplaat);

    }

    public static long geefActueletijd() {

        return (long) (new Date().getTime());

    }

    public String geefActueleTijdInStampVorm() {

        return timestamp.format(geefActueletijd());

    }

    public void voegAutoToeInParking(String nummerplaat) {

        pr.voegAutoToeAanParking(nummerplaat);

    }

    public void verwijderAutoUitParking(String nummerplaat) {

        pr.verwijderAutoUitParking(nummerplaat);

    }

    public int geefAantalAutosInParking() {

        return pr.geefAantalAutosInParking();

    }

    public boolean isParkingVol() {

        return geefAantalAutosInParking() < 101;

    }

    public String geefAutosInDeParking() {
    

        return pr.geefAutoInParking();

    }
    
    
    //Herkenning zonder foto
    public void herkenAuto() throws IOException {
        
        /*
        domeincontroller obj = new domeincontroller();

        String alprcmd = "alpr -c eu plate2.jpg -n 5";

        
        //in windows
        String output = obj.executeCommand(alprcmd);

        System.out.println(output);
        */
    }
    
    public String executeCommand(String command) {

        StringBuffer output = new StringBuffer();

        Process p;
        try {
            
            //String nfoto = "Raspistill -o auto.jpg"; // op de RPI
            String nfoto = "streamer -f jpeg -o auto.jpeg";
            p = Runtime.getRuntime().exec(nfoto);
            p.waitFor();
            //doe bovenste 3regels weg vr hardcoded foto verwerken => WERKT
            
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader
                    = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();
        
    }
    
    

}
