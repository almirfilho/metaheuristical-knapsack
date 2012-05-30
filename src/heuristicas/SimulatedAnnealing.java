package heuristicas;

import java.util.ArrayList;
import java.util.Random;
import mochila.Mochila;
import mochila.Solution;

public class SimulatedAnnealing extends Heuristica {

	private final int maxIterecoesI = 10;
	
	private final int maxIterecoesK = 20;
	
	private final double alpha = 0.9;
	
	private final boolean print = false;
	
	private ArrayList<Solution> solutions;
	
	public void solve( Mochila mochila ){
		
		this.solutions = new ArrayList<Solution>();
		Solution solucaoIni = null;
		
		do {
			
			solucaoIni = this.chuteInicial( mochila, true );
			
		} while( solucaoIni.estoura() );
		
		this.solutions.add( solucaoIni );
		this.printSolution( solucaoIni );
		
		Solution solucaoNew = null;
		double t = this.temperaturaInicial( mochila );
		int k = 0;
		
		do {

			int i = 0;
			
			do {

				solucaoNew = this.sucessorRandom( solucaoIni );
				int variacao = solucaoNew.z() - solucaoIni.z();
				
				if( variacao >= 0 && !solucaoNew.estoura() ){
					
					solucaoIni = solucaoNew;
					this.addSolution( solucaoIni );
					
				} else {
					
					double p = this.randomPercent();
					double pVariacao = -((double)variacao / t);
					
					if( pVariacao > p && !solucaoNew.estoura() ){
						
						solucaoIni = solucaoNew;
						this.addSolution( solucaoIni );
					}
				}
				
				i++;
				
			} while( i < this.maxIterecoesI );
			
			k++;
			t = this.alpha * t;
						
		} while( k < this.maxIterecoesK );
		
		this.printBestSolution();
	}
	
	private void addSolution( Solution solution ){
		
		this.solutions.add( solution );
		this.printSolution( solution );
	}
	
	private void printSolution( Solution solution ){
		
		if( this.print )
			System.out.println( solution.toString( true, true ) );
	}
	
	protected void printBestSolution(){
		
		Solution best = this.solutions.get(0);
		
		for( Solution solution : this.solutions )
			if( solution.z() > best.z() )
				best = solution;
		
		System.out.println( "SIMULATED ANNEALING:\n" + best.toString( true, true ) + "\n" );
	}
	
	private double temperaturaInicial( Mochila mochila ){
		
		return mochila.pesoItens();
	}
	
	private Solution sucessorRandom( Solution solution ){
		
		int index = new Random().nextInt( solution.getMochila().getNumItens() );
		
		return new Solution( solution, index );
	}
	
	private double randomPercent(){
		
		return new Random().nextDouble();
	}
	
}
