/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx_basicdetails;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import domein.domeincontroller;

/**
 *
 * @author proxc
 */
public class FXMLDocumentController implements Initializable {

    domeincontroller dc = new domeincontroller();
    
    
    //Programma Afsluiten
    @FXML
    private Button closeButton;

    @FXML
    public void handleCloseButtonAction(MouseEvent event) {
        if (event.getTarget() == closeButton) {
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        }

    }

    /**
     * HOMESCHERM MET ALLE BUTTONS
     */
    @FXML
    private ImageView btn_home, btn_addUser, btn_info;

    @FXML
    private AnchorPane h_home, h_addUser, h_info;

    @FXML
    private void handleButtonAction(MouseEvent event) { 
        if (event.getTarget() == btn_home) { //home
            h_home.setVisible(true);
            h_addUser.setVisible(false);
            h_info.setVisible(false);
            h_popup.setVisible(false);
            h_popupBuit.setVisible(false);
            herkendeNummerplaat.setText("");
        } else if (event.getTarget() == btn_addUser) {
            h_addUser.setVisible(true); //addUSer
            h_home.setVisible(false);
            h_info.setVisible(false);
            h_popup.setVisible(false);
            h_popupBuit.setVisible(false);
        } else if (event.getTarget() == btn_info) {
            h_info.setVisible(true); //info
            h_home.setVisible(false);
            h_addUser.setVisible(false);
            h_popup.setVisible(false);
            h_popupBuit.setVisible(false);
            
            //aantal bezette plaatsen updaten
            int vrij = dc.geefAantalAutosInParking();
            String plaats = "" + vrij;
            aantalAutos.setText(plaats);
            
            //lijst met parkeerders weergeven
            
            String display = dc.geefAutosInDeParking();
            wieGeparkeerd.setText(display);
            
        }
    }

    //home infoscherm
    @FXML
    private Label infoScherm;

    //binnenrijden
    @FXML
    private Button binrijd;

    @FXML
    public void handleBinRijd(MouseEvent event) {
        if (event.getTarget() == binrijd) {
            h_home.setVisible(false);
            h_addUser.setVisible(false);
            h_info.setVisible(false);
            h_popup.setVisible(true);
            messagePopup.setVisible(false);
            nrplaatPopup.setText("");

        }
    }

    //buitenrijden
    @FXML
    private Button buitrijd;

    @FXML
    public void handleBuitRijd(MouseEvent event) {
        if (event.getTarget() == buitrijd) {
            h_home.setVisible(false);
            h_addUser.setVisible(false);
            h_info.setVisible(false);
            messagePopup.setVisible(false);
            h_popupBuit.setVisible(true);
            messagePopupBuit.setVisible(false);
            nrplaatPopupBuit.setText("");

        }

    }


    //foto nemen
    @FXML
    private Button neemFoto;
    
    @FXML
    private Label herkendeNummerplaat;

    @FXML
    public void handleNeemFoto(MouseEvent event) {
        if (event.getTarget() == neemFoto) {
            
            //verander auto.jpg door naam van bestand => WERKT
            
            //String alprcmd = "alpr -c eu auto.jpg -n 5"; //rpi
            String alprcmd = "alpr -c eu auto.jpeg -n 5"; //linuxwebcam
            String display = dc.executeCommand(alprcmd);
            herkendeNummerplaat.setVisible(true);
            herkendeNummerplaat.setText(display);
            
        }

    }

    /**
     * POPUPSCREEN BINNERIJDEN
     */
    @FXML
    AnchorPane h_popup;
    //openen
    @FXML
    public void handlePopup(MouseEvent event) {
        if (event.getTarget() == binrijd) {
            h_home.setVisible(true);
            h_addUser.setVisible(false);
            h_info.setVisible(false);
            h_popup.setVisible(true);
        }
    }
    //sluiten
    @FXML
    private Button closePopupBtn;
    
    @FXML
    public void closePopup(MouseEvent event) {
        if (event.getTarget() == closePopupBtn) {
            h_home.setVisible(true);
            h_addUser.setVisible(false);
            h_info.setVisible(false);
            h_popup.setVisible(false);
        }
    }
    
    //nrplaat opvragen
    @FXML
    private JFXTextField nrplaatPopup;
   
    
    
    //submit button
    @FXML
    private Button submitPopup;
    
    //dialog Venster
    @FXML
    private Label messagePopup;
    
    @FXML
    public void submitPopup(MouseEvent event){
        if(event.getTarget() == submitPopup){
            String nrplaat = nrplaatPopup.getText();
            dc.updatBinnenrijdtijd(nrplaat);
            messagePopup.setVisible(true);
            String stamp = dc.geefActueleTijdInStampVorm();
            String naam = dc.geefNaam(nrplaat);
            dc.voegAutoToeInParking(nrplaat);
            messagePopup.setText("Welkom : " + naam + "\n" + "U mag door rijden." + "\n" + stamp );
            
        }
    }
    
    /**
     * POPUP BUITENRIJDEN
     */
     @FXML
     AnchorPane h_popupBuit;
     
     @FXML
     public void handlePopupBuit(MouseEvent event) {
        if (event.getTarget() == binrijd) {
            h_home.setVisible(true);
            h_addUser.setVisible(false);
            h_info.setVisible(false);
            h_popup.setVisible(false);
            h_popupBuit.setVisible(true);
        }
     }
     
     //sluiten
    @FXML
    private Button closePopupBuitBtn;
    
    @FXML
    public void closePopupBuit(MouseEvent event) {
        if (event.getTarget() == closePopupBuitBtn) {
            h_home.setVisible(true);
            h_addUser.setVisible(false);
            h_info.setVisible(false);
            h_popup.setVisible(false);
            h_popupBuit.setVisible(false);
            
        }
    }
     
    
    //nrplaat opvragen
    @FXML
    private JFXTextField nrplaatPopupBuit;
    
    //submit button
    @FXML
    private Button submitPopupBuit;
    
    //dialog Venster
    @FXML
    private Label messagePopupBuit;
    
    @FXML
    public void submitPopupBuit(MouseEvent event){
        if(event.getTarget() == submitPopupBuit){
            String nrplaat = nrplaatPopupBuit.getText();
            dc.updateUrenEnPrijs(nrplaat, dc.geefTebetalenPrijsSessie(nrplaat),dc.geefUrenSessie(nrplaat) );
            messagePopupBuit.setVisible(true);
            double prijs = dc.geefTebetalenPrijsSessie(nrplaat);
            double uren = dc.geefUrenSessie(nrplaat);
            String naam = dc.geefNaam(nrplaat);
            dc.verwijderAutoUitParking(nrplaat);
            String display = "";
            display += String.format("Vaarwel : %s%nU mag door rijden. %nTe betalen bedrag : %.2f€%nU heeft %.0f minuten geparkeerd.", naam, prijs, uren );
            messagePopupBuit.setText(display);
            
        }
    }
    
    /**
     * LOGIN/ ADD USER
     */
    //Login info krijgen
    @FXML
    private JFXTextField Naam;
    @FXML
    private JFXTextField Nrplaat;

    @FXML
    private void handleLogin(ActionEvent event) {
        System.out.println(Naam.getText());
        System.out.println(Nrplaat.getText());
    }

    //submitten & gevalideerde info weergeven
    @FXML
    private Button submit;
    
    //outprintscherm
    @FXML
    private Label vInfo;
    
    @FXML
    public void handleSubmit(MouseEvent event) {
        if (event.getTarget() == submit) {
            String gnaam = Naam.getText();
            String nplaat = Nrplaat.getText();
            String userInfo = "Uw gebruikersnaam is : " + gnaam + "\n" + "uw nummerplaat : " + nplaat;
            vInfo.setText(userInfo);
            dc.registreerAuto(gnaam, nplaat);
            submit.setVisible(false);

        }
    }
    
    
    @FXML
    private Button Reset;
    
    @FXML
    public void handleReset(MouseEvent event) {
        if (event.getTarget() == Reset) {
            Naam.setText("");
            Nrplaat.setText("");
            submit.setVisible(true);
        }
    }
    
    
    
    /**
     * INFOSCHERM
     */
    
    @FXML
    private Label aantalAutos;
    @FXML private Label wieGeparkeerd;
    
    //wordt uitgeprint wnnr je op info klikt.
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
