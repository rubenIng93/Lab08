package it.polito.tdp.dizionariograph.model;

import java.util.ArrayList;
import java.util.List;


import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.dizionariograph.db.WordDAO;

public class Model {
	
	private List<String> parole;
	private Graph<String, DefaultEdge> grafo;
	private String migliore;

	public Model() {
		
	}

	public void createGraph(int numeroLettere) {
		WordDAO dao = new WordDAO();
		this.parole = dao.getAllWordsFixedLength(numeroLettere);
		grafo = new SimpleGraph<>(DefaultEdge.class);//creazione grafo
		Graphs.addAllVertices(this.grafo, this.parole);//aggiunge le parole lunghe 'numeroLettere' come vertici del grafo
		
		for(String p : parole) {
			List<String> simili = trovaSimili(p);
			for(String match : simili) {
				this.grafo.addEdge(p, match);
			}
		}

	}
	
	/**
	 * Data una stringa itera su tutte le parole di stessa lunghezza e conta quanti caratteri coincidono;
	 * se il contatore raggiunge il valore length - 1, la parolla viene aggiunta alla lista di parole simili
	 * @param p
	 * @return List<String> parole simili
	 */
	private List<String> trovaSimili(String p) {
		
		List<String> simili = new ArrayList<>();
		
		for(String parola : this.parole) {
			int cont = 0;
			for(int i = 0; i < p.length(); i++) {
				if(parola.charAt(i) == p.charAt(i)) {
					cont++;
				}
			}
			if(cont == p.length() - 1)
				simili.add(parola);
		}
		return simili;
	}

	public List<String> displayNeighbours(String parolaInserita) {
		
		List<String> vicini = Graphs.neighborListOf(this.grafo, parolaInserita);
		return vicini;
		
	}

	public int findMaxDegree() {
		int maxDegree = 0;
		for(String s : this.parole) {
			if(grafo.degreeOf(s) > maxDegree) {
				maxDegree = grafo.degreeOf(s);
				migliore = s;
			}
		}
		return maxDegree;
	}
	
	public Graph<String, DefaultEdge> getGrafo(){
		return this.grafo;
	}

	public String getMigliore() {
		return migliore;
	}
	
	
}
