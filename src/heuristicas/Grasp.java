package heuristicas;

import java.util.Random;

import mochila.Mochila;
import mochila.Solution;

public class Grasp extends Heuristica {

	private double alpha;
	
	private int interations;
	
	private Solution solucao;
	
	private Random random = new Random();
	
	public Grasp( double alpha, int interations ){
		
		this.alpha = alpha;
		this.interations = interations;
	}
	
	public void solve( Mochila mochila ){
		
		Solution solucaoAlternativa;
		
		for( int i = 0; i < this.interations; i++ ){
			
			// atrbuindo uma solucao inicial gulosa
			this.solucao = this.solucaoInicial( mochila );
			
			// iterando a cada dupla da solucao
			solucaoAlternativa = this.buscaLocal( mochila );
			
			if( solucaoAlternativa.z() > this.solucao.z() )
				this.solucao = solucaoAlternativa;
		}
		
		System.out.println( this.solucao.toString(false,true) );
	}
	
	protected Solution solucaoInicial( Mochila mochila ){
		
		this.solucao = this.chuteInicial( mochila, false );

		while( !this.solucao.isFull() ){
			
			if( this.random.nextDouble() <= this.alpha )
				this.solucao.addNextBestItem();
			else
				this.solucao.addNextItem( true );
		}
		
		return solucao;
	}
	
	private Solution buscaLocal( Mochila mochila ){
		
		Solution solution, newSolution, bestSolution = this.solucao;
		
		if( this.random.nextDouble() < this.alpha ){
			
			// executa 2-opt em 20% da quantidades de itens da mochila randomicamente e escolhe a melhor solucao dentre elas
			int numSolucoes = (int) (0.2 * this.solucao.size()) + 1;
			
			solution = this.solucao;
			
			for( int i = 0; i < numSolucoes; i++ ){
				
				newSolution = this.opt2( solution );
				newSolution.tryAddOneMore();
				
				if( newSolution.z() > solution.z() ){
				
					solution = newSolution;
					
					if( !newSolution.estoura() )
						bestSolution = newSolution;
				}
			}
			
		} else {
			
			// executa 2-opt apenas uma vez randomicamente
			newSolution = this.opt2( this.solucao );
			
			if( !newSolution.estoura() ){
				
				bestSolution = newSolution;
				bestSolution.tryAddOneMore();
			}
		}
		
		return bestSolution;
	}
	
	private Solution opt2( Solution solution ){
		
		int index1, index2;
		
		// escolhendo randomicamente um item selecionado
		do {
			
			index1 = this.random.nextInt( solution.size() );
			
		} while( solution.selecionado( index1 ) );
		
		// retirando este item da mochila
		solution.retiraItem( index1 );
		
		// adiciona um outro item nao selecionado ainda
		do {
			
			index2 = this.random.nextInt( solution.size() );
			
		} while( !solution.selecionado( index2 ) );

		return new Solution( solution, index2 );
	}
	
}