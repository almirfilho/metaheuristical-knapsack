package heuristicas;

import java.util.Random;

import mochila.Mochila;
import mochila.Solution;

public abstract class Heuristica {

	protected Solution chuteInicial( Mochila mochila, boolean random ){
		
		int[] s = new int[ mochila.getNumItens() ];
		
		if( random ){
			
			Random rand = new Random();
			boolean b;
			
			for( int i = 0; i < mochila.getNumItens(); i++ ){
				
				b = rand.nextBoolean();
				
				if( b ) s[i] = 1;
				else	s[i] = 0;
			}
			
		} else {
			
			for( int i : s )
				s[ i ] = 0;
		}
		
		return new Solution( s, mochila );
	}
	
	public void solve( Mochila mochila ){}
	
	protected void printBestSolution(){}
	
}
