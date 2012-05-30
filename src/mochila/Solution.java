package mochila;

import java.util.Random;

public class Solution {
	
	private int[] s;
	
	private boolean[] forbiden;
	
	private Mochila mochila;
	
	private boolean full = false;
	
	public Solution( int[] solution, Mochila mochila ){
		
		this.s = solution.clone();
		this.mochila = mochila;
		this.forbiden = new boolean[ mochila.getNumItens() ];
		
		for( int i = 0; i < this.mochila.getNumItens(); i++ )
			this.forbiden[ i ] = false;
	}
	
	public Solution( Solution solution, int index ){
		
		this.s = solution.s.clone();
		this.mochila = solution.mochila;
		this.forbiden = new boolean[ mochila.getNumItens() ];
		
		for( int i = 0; i < this.mochila.getNumItens(); i++ )
			this.forbiden[ i ] = false;
		
		if( this.s[ index ] == 0 )
			this.s[ index ] = 1;
		else
			this.s[ index ] = 0;
	}
	
	public boolean estoura(){
		
		return this.pesoAtual() > this.mochila.getCapacidade();
	}
	
	private int pesoAtual(){
		
		int peso = 0;
		
		for( int i = 0; i < this.s.length ; i++ )
			peso += this.s[i] * this.mochila.getItem( i ).getPeso();

		return peso;
	}
	
	public int size(){
		
		return this.s.length;
	}
	
 	public int z(){
		
		int z = 0;
		
		for( int i = 0; i < this.s.length ; i++ )
			z += this.s[i] * this.mochila.getItem( i ).getSatisfacao();
		
		return z;
	}
	
	public boolean selecionado( int index ){
		
		return this.s[ index ] == 1;
	}
	
	public Mochila getMochila(){
		
		return this.mochila;
	}
	
	public String toString(){
		
		String str = "( " + this.s[0];
		
		for( int i = 1; i < this.s.length; i++ )
			str += ", " + this.s[i];
		
		str += " )";

		return str;
	}
	
	public String toString( boolean printPeso, boolean printZ ){
		
		String str = this.toString();
		
		if( printPeso )
			str += " P = " + this.pesoAtual();
		
		if( printZ )
			str += " Z = " + this.z();
		
		return str;
	}

	public boolean isFull(){
		
		return this.full;
	}
	
	public void addNextItem( boolean isRandom ){
		
		if( isRandom ){
			
			Random random = new Random();
			int index;
			
			do {
				
				index = random.nextInt( this.s.length );
				
			} while( this.selecionado( index ) && !this.isForbiden( index ) );
			
			this.s[ index ] = 1;
			
			if( this.estoura() ){
				
				this.s[ index ] = 0;
				this.full = true;
			}
			
		} else {
			
			// sem ser randomico
		}
	}
	
	public void addNextBestItem(){
		
		int index = this.getBestItemIndex();
		
		if( index != -1 ){
			
			this.s[ index ] = 1;
			
			if( this.estoura() ){
				
				this.forbiden[ index ] = true;
				this.s[ index ] = 0;
			}
			
		} else
			this.full = true;
		
	}
	
	private boolean isForbiden( int index ){
		
		return this.forbiden[ index ];
	}
	
	
	private int getBestItemIndex(){
		
		int index = -1;
		double bestFator = 0;
		
		for( int i = 0; i < this.s.length; i++ )
			if( !this.selecionado( i ) && !this.isForbiden( i ) )
				if( this.mochila.getItem( i ).getFator() > bestFator ){
					
					index = i;
					bestFator = this.mochila.getItem( i ).getFator();
				}
		
		return index;
	}
	
	public void retiraItem( int index ){
		
		this.s[ index ] = 0;
		this.forbiden[ index ] = true;
		this.full = false;
	}
	
	public void tryAddOneMore(){
		
		if( !this.estoura() ){
			
			int capacidadeLivre = this.mochila.getCapacidade() - this.pesoAtual();
			int index = -1;
			Item bestItem = null;
			
			for( int i = 0; i < this.s.length; i++ ){
				
				// se nao esta selecionado
				if( !this.selecionado( i ) ){
					
					if( this.mochila.getItem( i ).getPeso() <= capacidadeLivre ){
						
						if( bestItem == null ){
							
							bestItem = this.mochila.getItem( i );
							index = i;
							
						} else if( this.mochila.getItem( i ).getPeso() < bestItem.getPeso() ) {
							
							bestItem = this.mochila.getItem( i );
							index = i;
						}
					}
				}					
			}
			
			// se ha um elemento que possa ser incluido:
			if( index != -1 ){
				
				this.s[ index ] = 1;
//				System.out.println("yes");
			}
			
//			System.out.println(capacidadeLivre);
//			System.exit(0);
		}
	}


}