package mochila;

import java.util.ArrayList;

public class Mochila {

	private int capacidade;
	
	private ArrayList<Item> itens;
	
	public Mochila( int capacidade, ArrayList<Item> itens ){
		
		this.capacidade = capacidade;
		this.itens = itens;
	}
	
	public int getCapacidade(){
		
		return this.capacidade;
	}
	
	public Item getItem( int index ){
		
		return this.itens.get( index );
	}
	
	public ArrayList<Item> getItens(){
		
		return this.itens;
	}
	
	public int getNumItens(){
		
		return this.itens.size();
	}
	
	public String toString(){
		
		return "{ CAP = " + this.capacidade + " }";
	}
	
	public double pesoItens(){
		
		double p = 0;
		
		for( Item item : this.itens )
			p += (double)item.getPeso();
		
		return p;
	}
	
	public String toString( boolean outputItens ){
		
		String str = "";
		
		if( outputItens ){
			
			for( Item i : this.itens ){
				
				str += "\n\t" + i.toString();
			}
		}
		
		return this.toString() + str;
	}
	
}