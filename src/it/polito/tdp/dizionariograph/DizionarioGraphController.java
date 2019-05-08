package it.polito.tdp.dizionariograph;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.dizionariograph.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioGraphController {
	
	private Model model = new Model();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtNumChar;

    @FXML
    private TextField txtWord;

    @FXML
    private Button btnGeneraGrafo;

    @FXML
    private Button btnVicini;

    @FXML
    private Button btnGrado;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;

    @FXML
    void doGeneraGrafo(ActionEvent event) {
    	
    	txtResult.clear();
    	int numLettere = Integer.parseInt(txtNumChar.getText());
    	model.createGraph(numLettere);
    	//txtResult.appendText(model.getGrafo().edgeSet().toString());

    }

    @FXML
    void doReset(ActionEvent event) {
    	
    	txtNumChar.clear();
    	txtResult.clear();
    	txtWord.clear();

    }

    @FXML
    void doTrovaGradoMax(ActionEvent event) {
    	txtResult.clear();
    	int maxDegree = model.findMaxDegree();
    	String verticeTop = model.getMigliore();
    	txtResult.appendText("Il grado massimo per un grafo con parole da "+
    				Integer.parseInt(txtNumChar.getText())+" lettere è "+ maxDegree+"\n");
    	txtResult.appendText("La parola con più simili è: "+verticeTop.toUpperCase()+"\n");
    	List<String> vicini = model.displayNeighbours(verticeTop);
		for(String string : vicini) {
			txtResult.appendText(string+"\n");
		}

    }

    @FXML
    void doTrovaVicini(ActionEvent event) {
    	
    	txtResult.clear();
    	int numLettere = Integer.parseInt(txtNumChar.getText());
    	String parolaInserita = txtWord.getText();
    	if(parolaInserita.length() != numLettere) {
    		txtResult.appendText("Numero di lettere nella parola errato");
    	}else {
    		List<String> vicini = model.displayNeighbours(parolaInserita);
    		for(String string : vicini) {
    			txtResult.appendText(string+"\n");
    		}
    	}
    		
    	

    }

    @FXML
    void initialize() {
        assert txtNumChar != null : "fx:id=\"txtNumChar\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtWord != null : "fx:id=\"txtWord\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnGeneraGrafo != null : "fx:id=\"btnGeneraGrafo\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnVicini != null : "fx:id=\"btnVicini\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnGrado != null : "fx:id=\"btnGrado\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
