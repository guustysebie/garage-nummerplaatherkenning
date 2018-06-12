/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AutoHerkenning;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author daanw
 */
public class Main {

    private static ArrayList<Auto> autolijst = new ArrayList<>();
    private static final DateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");

    public static void main(String[] args) {

        Main Functie = new Main();
        //Functie.addAuto();
        Functie.tijdMoosen();

        //lijst met elke persoon/wagen in  
        autolijst.add(new Auto("Kenny", "LF36SZ", 1, 2, 0));
        autolijst.add(new Auto("Barry", "BKW7165", 2, 3, 0));
        autolijst.add(new Auto("George", "IBW4531", 3, 4, 0));
        autolijst.add(new Auto("Geoffrey", "BE028AB", 4, 5, 0));
        autolijst.add(new Auto("Natasha", "1BQQ250", 5, 6, 0));
        autolijst.add(new Auto("Nico", "9EBT002", 6, 7, 0));
        autolijst.add(new Auto("Gerrie", "2TXL475", 7, 8, 0));
        autolijst.add(new Auto("Michael", "897AGN", 8, 9, 0));
        autolijst.add(new Auto("Tenisha", "TXAA674", 9, 10, 0));
        autolijst.add(new Auto("Rudy", "0ADN072", 10, 11, 0));
        autolijst.add(new Auto("Richard", "9ROY888", 11, 12, 0));
        autolijst.add(new Auto("Anna", "1CTC350", 12, 13, 0));
        autolijst.add(new Auto("Boris", "KGR502", 13, 14, 0));
        autolijst.add(new Auto("Ragnar", "TXAA344", 14, 15, 0));
        autolijst.add(new Auto("Lucy", "OBY905", 15, 16, 0));
        autolijst.add(new Auto("Freddy", "1ARB291", 16, 17, 0));

        //System.out.println(autolijst.get(0).getGebruikersnaam());
    }

    /* nieuwe auto indien niet herkend 
         --- wel nog in deftige functie steken
     */
    public void addAuto() {
        //naam en info
        Scanner input = new Scanner(System.in);
        System.out.print("Voer uw naam in : ");
        String gebruikersnaam = input.nextLine();
        System.out.print("Voer uw nummerplaat in : ");
        String nummerplaat = input.nextLine();

        //timestamp
        long bintijd = (long) (new Date().getTime()); //tijd in milliseconden
        String bintijdL = sdf.format(bintijd); // geconvert naar datum

        //Van deze info object maken i/d lijst
        autolijst.add(new Auto(gebruikersnaam, nummerplaat, bintijd, 0, 0));
        
        System.out.println("Binnenrijdtijd : " + bintijdL);

        
        
        
    }
    
    public void tijdMoosen() {
        
        // tijd vragen en omzetten naar een getal => hier long want int is te kort
        long tijd1 = (long) (new Date().getTime()); //tijd in milliseconden
        //System.out.println("Long : " + new Date().getTime());
        //System.out.println("Long date : " + new Date(new Date().getTime()));
        //System.out.println("de tijd in getal is : " + i);
        
        //formateren naar datumweergave
        String stamp1 = sdf.format(tijd1);
        System.out.println("Binnenrijdtijd : " + stamp1);
        
        //tijd converteren naar millisec
        int sec = 0 * 1000;
        int min = 30 * 60 * 1000;
        int uur = 1 * 60 * 60 * 1000;
        
        //tijd bijtellen
        long tijd2 = tijd1;
        tijd2 += uur + min + sec;
        String stamp2 = sdf.format(tijd2);
        System.out.println("Buitenrijdtijd : " + stamp2);
        
        
        //tijdsduur berekenen in min
        long tijd3 = (tijd2 - tijd1) / 60 / 1000;
        
        System.out.println("Tijdsduur : " + tijd3);

        //prijsberekening
        double tarief = 0.033333;
        double prijs = (double) tijd3 * tarief;
        
        //omzetten naar euro en cent
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        String prijsDef = numberFormat.format(prijs);
        
        
        System.out.println("Kostprijs : " + prijsDef + "€");
         
        
    }

}
