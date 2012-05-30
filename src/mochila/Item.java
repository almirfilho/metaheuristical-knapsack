package mochila;

public class Item {
	
	private int peso;
	
	private int satisfacao;
	
	private double fator;

	public Item( int peso, int satisfacao ){
		
		this.peso = peso;
		this.satisfacao = satisfacao;
		this.fator = (double)satisfacao / (double)peso;
	}
	
	public int getPeso(){
		
		return this.peso;
	}
	
	public int getSatisfacao(){
		
		return this.satisfacao;
	}
	
	public double getFator(){
		
		return this.fator;
	}
	
	public String toString(){
		
		return "[ Peso = " + this.peso + ", Sat = " + this.satisfacao + " ]";
	}

}