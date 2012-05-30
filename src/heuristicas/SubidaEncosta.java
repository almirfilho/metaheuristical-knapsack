package heuristicas;

import mochila.Mochila;
import mochila.Solution;

public class SubidaEncosta extends Heuristica {

	private Solution atual;
	
	public void solve( Mochila mochila ){
		
		do {
			this.atual = this.chuteInicial( mochila, true );
		} while( this.atual.estoura() );
		
		while( true ){
			
			Solution vizinho = this.melhorSucessor( atual );
			
			if( vizinho.z() <= atual.z() )
				break;
			
			atual = vizinho;
		}
		
		this.printBestSolution();
	}
	
	private Solution melhorSucessor( Solution atualSolution ){
		
		Solution newSolution = null;
		Solution bestSolution = atualSolution;
		int bestZ = bestSolution.z();
		int length = atualSolution.getMochila().getNumItens();
		
		for( int i = 0; i < length; i++ ){

			if( !atualSolution.selecionado( i ) ){
				
				newSolution = new Solution( atualSolution, i );
				int newZ = newSolution.z();
				
				if( newZ >= bestZ && !newSolution.estoura() ){
					
					bestZ = newZ;
					bestSolution = newSolution;
				}
			}
		}

		return bestSolution;
	}

	protected void printBestSolution(){
		
		System.out.println( "SUBIDA DA ENCOSTA:\n" + this.atual.toString( true, true ) + "\n" );
	}
	
}