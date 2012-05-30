package mochila;

import java.io.*;
import java.util.ArrayList;

public class InstanceFactory {

	private String stream;
	
	public InstanceFactory( String filename ){
		
		try {
			// lendo o arquivo que contem as instancias
			FileReader file = new FileReader( filename );
			char[] buff = new char[ 1024*10 ];
			file.read( buff );
			this.stream = new String( buff );
			
		} catch( IOException e ){
			
			System.out.println( "nao eh possivel abrir arquivo "+ filename );
			System.exit(0);
		}
	}
	
	public ArrayList<Mochila> produce(){
		
		ArrayList<Mochila> mochilas = new ArrayList<Mochila>();
		String[] instancias = stream.split(";");

		for( String instancia : instancias ){

			String[] partes = instancia.split(":");
			int capacidade = new Integer( partes[0].trim() );
			ArrayList<Item> itens = new ArrayList<Item>();
			String[] strItens = partes[1].trim().split( "," );
			
			for( String item : strItens ){

				String[] atributos = item.split( "-" );
				itens.add( new Item( new Integer( atributos[0].trim() ), new Integer( atributos[1].trim() ) ) );
			}
			
			mochilas.add( new Mochila( capacidade, itens ) );
		}
		
		return mochilas;
	}
	
}