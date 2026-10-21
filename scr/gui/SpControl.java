package gui;


import business.SpModel;
import business.Sportverein;
import javafx.stage.Stage;

public class SpControl {
	private SpView spView;
	SpModel spModel = new SpModel();
	
	
	public SpControl(Stage primaryStage) {
		this.spView = new SpView(primaryStage, this, spModel);
	}

	public void nehmeSportvereinAuf(String name, String ort, int anzahlMitglieder,
	       	int anzahlEhemaligerMitglieder, String[] sportarten){
    	try{ 
    		spModel.setSportverein(new Sportverein(name, ort, anzahlMitglieder, anzahlEhemaligerMitglieder, sportarten));
    		spView.zeigeInformationsfensterAn("Der Sportverein wurde aufgenommen!");
       	}
       	catch(Exception exc){
       		spView.zeigeFehlermeldungsfensterAn(exc.getMessage());
     	}
    }
   
    public void zeigeSportvereineAn(){
    	if(spModel.getSportverein() != null){
    		spView.zeigeTextAn(spModel.getSportverein().gibSportvereinZurueck(' '));
    	}
    	else{
    		spView.zeigeInformationsfensterAn("Bisher wurde keine Sportverein aufgenommen!");
    	}
    }    
		  
    public void leseAusDatei(String typ){
    	try {
      		if("csv".equals(typ)){
      			spModel.leseAusDatei(typ);
      			spView.zeigeInformationsfensterAn(
      	  	   			"Der Sportverein wurde gelesen!");
      		}
       		else{
       			spView.zeigeInformationsfensterAn(
	   				"Noch nicht implementiert!");
	   		}
		}
		
		catch(Exception exc){
			spView.zeigeFehlermeldungsfensterAn(
				"Unbekannter Fehler beim Lesen!");
		}
	}
		
	public void schreibeSportvereineInCsvDatei() {
		try {
			spModel.schreibeSportvereineInCsvDatei();
			spView.zeigeInformationsfensterAn(
	   			"Die Sportvereine wurden gespeichert!");
		}	
		
		catch(Exception exc){
			spView.zeigeFehlermeldungsfensterAn(
				"Unbekannter Fehler beim Speichern!");
		}
	}
}
