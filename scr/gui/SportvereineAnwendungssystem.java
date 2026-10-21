package gui;
   
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import business.Sportverein;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ownUtil.*;

public class SportvereineAnwendungssystem {
	  
    //---Anfang Attribute der grafischen Oberflaeche---
    public Pane pane     					= new  Pane();
    public Label lblEingabe    	 		= new Label("Eingabe");
    public Label lblAnzeige   	 	    	= new Label("Anzeige");
    public Label lblName 					= new Label("Name:");
    public Label lblOrt   		 			= new Label("Ort:");
    public Label lblAnzahlMitglieder  	 	= new Label("Anzahl Mitglieder:");
    public Label lblAnzahlEhemaligerMitglieder   	
     										= new Label("Anzahl ehemaliger Mitglieder:");
    public Label lblSportarten  			= new Label("Sportarten:");
    public TextField txtName 	 			= new TextField();
    public TextField txtOrt				= new TextField();
    public TextField txtAnzahlMitglieder	= new TextField();
    public TextField txtAnzahlEhemaligerMitglieder	
    										= new TextField();
    public TextField txtSportarten	 		= new TextField();
    public TextArea txtAnzeige  			= new TextArea();
    public Button btnEingabe 		 		= new Button("Eingabe");
    public Button btnAnzeige 		 		= new Button("Anzeige");
    public MenuBar mnbrMenuLeiste  		= new MenuBar();
    public Menu mnDatei             		= new Menu("Datei");
    public MenuItem mnItmCsvImport 		= new MenuItem("csv-Import");
    public MenuItem mnItmTxtImport 		= new MenuItem("txt-Import");
    public MenuItem mnItmCsvExport 		= new MenuItem("csv-Export");    
    //-------Ende Attribute der grafischen Oberflaeche-------
    
    // speichert temporaer ein Objekt vom Typ Sportverein
    public Sportverein sportverein;
    
    public SportvereineAnwendungssystem(Stage primaryStage){
    	Scene scene = new Scene(this.pane, 700, 340);
    	primaryStage.setScene(scene);
    	primaryStage.setTitle("Verwaltung von Sportvereinen");
    	primaryStage.show();
    	this.initKomponenten();
		this.initListener();
    }
    
    public void initKomponenten(){
       	// Labels
    	lblEingabe.setLayoutX(20);
    	lblEingabe.setLayoutY(40);
    	Font font = new Font("Arial", 24); 
    	lblEingabe.setFont(font);
    	lblEingabe.setStyle("-fx-font-weight: bold;"); 
    	lblAnzeige.setLayoutX(400);
    	lblAnzeige.setLayoutY(40);
      	lblAnzeige.setFont(font);
       	lblAnzeige.setStyle("-fx-font-weight: bold;"); 
       	lblName.setLayoutX(20);
    	lblName.setLayoutY(90);
    	lblOrt.setLayoutX(20);
    	lblOrt.setLayoutY(130);
    	lblAnzahlMitglieder.setLayoutX(20);
    	lblAnzahlMitglieder.setLayoutY(170);
    	lblAnzahlEhemaligerMitglieder.setLayoutX(20);
    	lblAnzahlEhemaligerMitglieder.setLayoutY(210);
    	lblSportarten.setLayoutX(20);
    	lblSportarten.setLayoutY(250);    	
       	pane.getChildren().addAll(lblEingabe, lblAnzeige, 
       		lblName, lblOrt, lblAnzahlMitglieder,
       		lblAnzahlEhemaligerMitglieder, lblSportarten);
    
    	// Textfelder
     	txtName.setLayoutX(170);
    	txtName.setLayoutY(90);
    	txtName.setPrefWidth(200);
    	txtOrt.setLayoutX(170);
    	txtOrt.setLayoutY(130);
    	txtOrt.setPrefWidth(200);
    	txtAnzahlMitglieder.setLayoutX(170);
    	txtAnzahlMitglieder.setLayoutY(170);
    	txtAnzahlMitglieder.setPrefWidth(200);
      	txtAnzahlEhemaligerMitglieder.setLayoutX(170);
    	txtAnzahlEhemaligerMitglieder.setLayoutY(210);
    	txtAnzahlEhemaligerMitglieder.setPrefWidth(200);
    	txtSportarten.setLayoutX(170);
    	txtSportarten.setLayoutY(250);
    	txtSportarten.setPrefWidth(200);
      	pane.getChildren().addAll( 
     		txtName, txtOrt, txtAnzahlMitglieder,
     		txtAnzahlEhemaligerMitglieder, txtSportarten);
     	
        // Textbereich	
        txtAnzeige.setEditable(false);
     	txtAnzeige.setLayoutX(400);
    	txtAnzeige.setLayoutY(90);
     	txtAnzeige.setPrefWidth(270);
    	txtAnzeige.setPrefHeight(185);
       	pane.getChildren().add(txtAnzeige); 
       	
        // Buttons
        btnEingabe.setLayoutX(20);
        btnEingabe.setLayoutY(290);
        btnAnzeige.setLayoutX(400);
        btnAnzeige.setLayoutY(290);
        pane.getChildren().addAll(btnEingabe, btnAnzeige); 
        
 		// Menue
  	    this.mnbrMenuLeiste.getMenus().add(mnDatei);
  	    this.mnDatei.getItems().add(mnItmCsvImport);
  	    this.mnDatei.getItems().add(mnItmTxtImport);
  	    this.mnDatei.getItems().add(new SeparatorMenuItem());
  	    this.mnDatei.getItems().add(mnItmCsvExport);
 	    pane.getChildren().add(mnbrMenuLeiste);
   }
   
   public void initListener() {
	    btnEingabe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
        	    nehmeSportvereinAuf();
            }
	    });
	    btnAnzeige.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	        public void handle(ActionEvent e) {
	            zeigeSportvereineAn();
	        } 
   	    });
	    mnItmCsvImport.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	        public void handle(ActionEvent e) {
	       	 	leseAusDatei("csv");
	    	}
	    });
	    mnItmTxtImport.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		     	leseAusDatei("txt");
		    }
    	});
	    mnItmCsvExport.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				schreibeSportvereineInCsvDatei();
			}	
	    });
    }
    
    public void nehmeSportvereinAuf(){
    	try{ 
    		this.sportverein = new Sportverein(
    			txtName.getText(), 
   	            txtOrt.getText(),
   	            Integer.parseInt(txtAnzahlMitglieder.getText()),
   	        	Integer.parseInt(txtAnzahlEhemaligerMitglieder.getText()),
    		    txtSportarten.getText().split(";"));
    		zeigeInformationsfensterAn("Der Sportverein wurde aufgenommen!");
       	}
       	catch(Exception exc){
       		zeigeFehlermeldungsfensterAn(exc.getMessage());
     	}
    }
   
    public void zeigeSportvereineAn(){
    	if(this.sportverein != null){
    		txtAnzeige.setText(
    			this.sportverein.gibSportvereinZurueck(' '));
    	}
    	else{
    		zeigeInformationsfensterAn("Bisher wurde keine Sportverein aufgenommen!");
    	}
    }    
		  
    public void leseAusDatei(String typ){
    	try {
      		if("csv".equals(typ)){
      			BufferedReader ein = new BufferedReader(new FileReader("Sportverein.csv"));
      			String[] zeile = ein.readLine().split(";");
      			this.sportverein = new Sportverein(zeile[0], 
      				zeile[1], 
      				Integer.parseInt(zeile[2]), 
      				Integer.parseInt(zeile[3]), 
      				zeile[4].split("_"));
      				ein.close();
      	  			zeigeInformationsfensterAn(
      	  	   			"Der Sportverein wurde gelesen!");
      		}
       		else{
	   			zeigeInformationsfensterAn(
	   				"Noch nicht implementiert!");
	   		}
		}
		catch(IOException exc){
			zeigeFehlermeldungsfensterAn(
				"IOException beim Lesen!");
		}
		catch(Exception exc){
			zeigeFehlermeldungsfensterAn(
				"Unbekannter Fehler beim Lesen!");
		}
	}
		
	public void schreibeSportvereineInCsvDatei() {
		try {
			BufferedWriter aus 
				= new BufferedWriter(new FileWriter("SportvereineAusgabe.csv", true));
			aus.write(sportverein.gibSportvereinZurueck(';'));
			aus.close();
   			zeigeInformationsfensterAn(
	   			"Die Sportvereine wurden gespeichert!");
		}	
		catch(IOException exc){
			zeigeFehlermeldungsfensterAn(
				"IOException beim Speichern!");
		}
		catch(Exception exc){
			zeigeFehlermeldungsfensterAn(
				"Unbekannter Fehler beim Speichern!");
		}
	}

    public void zeigeInformationsfensterAn(String meldung){
    	new MeldungsfensterAnzeiger(AlertType.INFORMATION,
    		"Information", meldung).zeigeMeldungsfensterAn();
    }	
    
    void zeigeFehlermeldungsfensterAn(String meldung){
       	new MeldungsfensterAnzeiger(AlertType.ERROR,
        	"Fehler", meldung).zeigeMeldungsfensterAn();
    }

}
