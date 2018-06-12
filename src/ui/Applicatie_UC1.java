/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.openalpr.jni.Alpr;
import com.openalpr.jni.AlprException;
import com.openalpr.jni.AlprPlate;
import com.openalpr.jni.AlprPlateResult;
import com.openalpr.jni.AlprResults;
import java.util.Scanner;
import domein.domeincontroller;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author wh1zx
 */
public class Applicatie_UC1 {
    
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLACK = "\u001B[33m";
    
    private static final Applicatie_UC1 app1 = new Applicatie_UC1();
    private static final DateFormat timestamp = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
    domeincontroller dc = new domeincontroller();
    
    public static void main(String[] args) {
        app1.maakKeuze();
    }
    
    public void maakKeuze() {
        int keuze;
        Scanner input = new Scanner(System.in);
        System.out.printf(ANSI_BLACK + "*************************************************************************%n"
                + ANSI_BLACK + "*                                                                       *%n"
                + ANSI_BLACK + "*          hallo en welkom bij de Autoherkenning van Daan               *%n"
                + ANSI_BLACK + "*                                                                       *%n"
                + ANSI_BLACK + "*************************************************************************%n"
                + ANSI_BLACK + "*                                                                       *%n"
                + ANSI_BLACK + "*                             maak een keuze                             %n"
                + ANSI_BLACK + "*                                                                       *%n"
                + ANSI_BLACK + "*************************************************************************%n"
                + ANSI_BLACK + "*                                                                       *%n"
                + ANSI_BLACK + "*                 auto rijd binnen druk1:                               *%n"
                + ANSI_BLACK + "*                                                                       *%n"
                + ANSI_BLACK + "*                 auto rijd buiten druk2:                               *%n"
                + ANSI_BLACK + "*                                                                       *%n"
                + ANSI_BLACK + "*                 Admin pagina druk 3:                                  *%n"
                + ANSI_BLACK + "*                                                                       *%n"
                + ANSI_BLACK + "*                 betalen druk 4:                                       *%n"
                + ANSI_BLACK + "*                                                                       *%n"
                + ANSI_BLACK + "*************************************************************************%n"
                + ANSI_BLACK + "*                                                                       *%n"
                + ANSI_BLACK + "*                          keuze:");
        
        keuze = input.nextInt();
        System.out.printf(ANSI_BLACK + "*                                                                       *%n"
                + ANSI_BLACK + "*************************************************************************%n%n%n");
        
        switch (keuze) {
            case 1:
                
                app1.rijdBinnen();
                
                break;
            case 2:
                
                app1.rijdBuiten();
                app1.maakKeuze();
                break;
            case 3:
               
                System.out.println(dc.geefAantalAutosInParking());
                System.out.println(dc.geefAutosInDeParking());
                app1.maakKeuze();
                break;
            default:
                throw new IllegalArgumentException("vult iet deftig in neger!");
        }
        
    }
    
    public void rijdBinnen() {
        
        Scanner input = new Scanner(System.in);
        String nummerplaat;
        System.out.printf("*************************************************************************%n"
                + "*                                                                       *%n"
                + "*                     " + "geef nummerplaat:");// hier moet de inlezing komen van de rasberry
        nummerplaat = input.next();
        System.out.printf("*                                                                       *%n"
                + "*************************************************************************%n");
        if ("null".equals(dc.geefNaam(nummerplaat))) {
            System.out.printf("%n%n*************************************************************************%n"
                    + "*                                                                       *%n"
                    + "*        nummer plaat niet herkent gelieve je te registreren            *%n"
                    + "*                                                                       *%n"
                    + "*************************************************************************%n");
            
            app1.registreer();
            
        } else {
            long tijd1 = (long) (new Date().getTime());
            String stamp1 = timestamp.format(tijd1);
            
            dc.updatBinnenrijdtijd(nummerplaat);
            System.out.printf(ANSI_GREEN + "%n%n" + ANSI_GREEN + "*************************************************************************%n"
                    + ANSI_GREEN + "*                                                                       *%n"
                    + ANSI_GREEN + "*                 welkom: %s u mag doorijden                          *%n"
                    + ANSI_GREEN + "*                 uur: %s                             *%n"
                    + ANSI_GREEN + "*                                                                       *%n"
                    + ANSI_GREEN + "*************************************************************************%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n",
                    dc.geefNaam(nummerplaat), stamp1);
            
        }
        dc.voegAutoToeInParking(nummerplaat);
        app1.maakKeuze();
        
    }
    
    public void rijdBuiten() {
        
        Scanner input = new Scanner(System.in);
        String nummerplaat;
        
        System.out.printf("*************************************************************************%n"
                + "*                                                                       *%n"
                + "*                     " + "geef nummerplaat:");// hier moet de inlezing komen van de rasberry
        nummerplaat = input.next();
        System.out.printf("*                                                                       *%n"
                + "*************************************************************************%n");
        
        dc.updateUrenEnPrijs(nummerplaat, dc.geefTebetalenPrijsSessie(nummerplaat), dc.geefTebetalenPrijsSessie(nummerplaat));
        double prijs = dc.geefTebetalenPrijsSessie(nummerplaat);
        double uren = dc.geefUrenSessie(nummerplaat);
        
        System.out.printf(ANSI_GREEN + "%n%n" + ANSI_GREEN + "*************************************************************************%n"
                + ANSI_GREEN + "*                                                                       *%n"
                + ANSI_GREEN + "*                 Bedankt: %s u mag doorijden                          *%n"
                + ANSI_GREEN + "*                 prijs: %.2f euro                         %n"
                + ANSI_GREEN + "*                 uren: %.0f minuten                             %n"
                + ANSI_GREEN + "*                                                                       *%n"
                + ANSI_GREEN + "*                                                                       *%n"
                + ANSI_GREEN + "*************************************************************************%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n",
                dc.geefNaam(nummerplaat), prijs, uren);
        
        dc.verwijderAutoUitParking(nummerplaat);
        
    }
    
    public void registreer() {
        String gebruikersnaam;
        String nummerplaat;
        
        Scanner input = new Scanner(System.in);
        
        System.out.printf(ANSI_RED + "*************************************************************************%n"
                + ANSI_RED + "*                                                                       *%n"
                + ANSI_RED + "*                 geef je naam in:");
        gebruikersnaam = input.next();
        System.out.printf(
                ANSI_RED + "*                 geef je nrplaat in:");
        nummerplaat = input.next();
        
        System.out.printf(ANSI_RED + "*                                                                       *%n");
        System.out.printf(ANSI_RED + "*************************************************************************%n");
        
        long tijd1 = (long) (new Date().getTime());
        String stamp1 = timestamp.format(tijd1);
        dc.registreerAuto(gebruikersnaam, nummerplaat);
        
        System.out.printf(ANSI_GREEN + "%n%n" + ANSI_GREEN + "*************************************************************************%n"
                + ANSI_GREEN + "*                                                                       *%n"
                + ANSI_GREEN + "*                 welkom: %s u mag doorijden                          %n"
                + ANSI_GREEN + "*                 uur: %s                             *%n"
                + ANSI_GREEN + "*                                                                       *%n"
                + ANSI_GREEN + "*************************************************************************%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n%n",
                gebruikersnaam, stamp1);
        
    }

//
    //
    //
    //
    //
    //
    //
    //
    // //
    //
    //
    // //
    //
    //
    // //
    //
    //
    //

    /*
    public static void main(String[] args) throws AlprException {

        Alpr alpr = new Alpr("eu", "/home/wh1zx/openalpr/src/build/config/openalpr.config", "/home/wh1zx/openalpr/runtime_data");

// Set top N candidates returned to 20
        alpr.setTopN(1);

// Set pattern to Maryland
        alpr.setDefaultRegion("eu");

        AlprResults results = alpr.recognize("C:/Users/daanw/Documents/ProjectParking/Nummerplaten/plate11.jpg");
        hier moet ge foto laten nemen
    
       
        
        
        System.out.format("  %-15s%-8s\n", "Plate Number", "Confidence");
        for (AlprPlateResult result : results.getPlates()) {
            for (AlprPlate plate : result.getTopNPlates()) {
                if (plate.isMatchesTemplate()) {
                    System.out.print("  * ");
                } else {
                    System.out.print("  - ");
                }
                System.out.format("%-15s%-8f\n", plate.getCharacters(), plate.getOverallConfidence());
            }
        }

// Make sure to call this to release memory
        alpr.unload();

   
    
    
    }
    
    
     */
}
