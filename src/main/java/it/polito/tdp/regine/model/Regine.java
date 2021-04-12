package it.polito.tdp.regine.model;

import java.util.ArrayList;
import java.util.List;

public class Regine
{
	private int N;
	private List<Integer> soluzione;
	
	
	public List<Integer> risolvi(int N) 
	{
		this.N = N;
		@SuppressWarnings("unused")
		List<Integer> soluzione = new ArrayList<>();
		
		List<Integer> soluzioneParziale = new ArrayList<>();
		cerca(soluzioneParziale, 0);
		
		return this.soluzione;
	}
	
	//scacchiera N x N
	//righe e colonne numerate da 0 a N-1
	//ad ogni livello posizioniamo una regina in una nuova riga
	//soluzione parziale: lista delle colonne in cui mettere le regine (prime righe)
	//	List<Integer>
	// livello = quante righe sono gia' piene
	// livello = 0 => nessuna riga piena
	// livello = 3 => righe piene (0, 1, 2), devo mettere la regina nella riga 3
	// [0]
	// 		[0, 2]
	// 				[0, 2, 1] ....
	
	//cerca == true : trovato; cerca == false : cerca ancora
	private boolean cerca(List<Integer> parziale, int livello)
	{
		if(livello == N)
		{
			this.soluzione = new ArrayList<Integer>(parziale);
			return true;
		}
		else 
		{
			for(int colonna=0; colonna<N; colonna++)
			{
				//if la mossa nella casella [livello][colonna] e' valida
				// se sì, aggiungi a parziale e fai ricorsione
				
				if(posizioneValida(parziale,colonna))
				{
					parziale.add(colonna);	//[0, 6, 4, 6, XXX]
					boolean trovato = cerca(parziale, livello+1);
					
					if(trovato)
						return true;
					else
						parziale.remove(parziale.size()-1); //backtracking
				}
			}
			return false;
		}
	}
	
	private boolean posizioneValida(List<Integer> soluzioneParziale, int colonnaNuovaRegina)
	{
		if(soluzioneParziale.contains(colonnaNuovaRegina))
			return false;
		
		//controllo le diagonali: confronto la posizione (livello, colonna) con (r,c)
		//delle regine esistenti
		//Osservazione: le celle sulle diagonali inclinate a pi/4 hanno somma (riga+colonna) costante;
		//				le celle sulle diagonali inclinate a 3pi/4 hanno differenza (riga-colonna) costante;
		
		int rigaNuovaRegina = soluzioneParziale.size();
		
		for(int riga = 0; riga < rigaNuovaRegina; riga++)
		{
			int colonna = soluzioneParziale.get(riga);
			
			if( rigaNuovaRegina+colonnaNuovaRegina == riga+colonna ||
				rigaNuovaRegina-colonnaNuovaRegina == riga-colonna )
				return false; 			//la nuova regina è in conflitto con una diagonale 										
		}								//già occupata da un'altra regina precedente
		return true;
	}
	
	
}
