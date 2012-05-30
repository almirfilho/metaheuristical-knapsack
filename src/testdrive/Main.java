package testdrive;

import heuristicas.*;
import java.util.ArrayList;
import mochila.InstanceFactory;
import mochila.Mochila;

public class Main {

	public static void main( String[] args ){
		
		InstanceFactory factory = new InstanceFactory( "instancias.txt" );
		ArrayList<Mochila> instancias = factory.produce();
		
//		SubidaEncosta se = new SubidaEncosta();
//		SimulatedAnnealing sa = new SimulatedAnnealing();
//		Greedy guloso = new Greedy();
		Grasp grasp = new Grasp( 0.8, 100 );
		
		for( Mochila instancia : instancias ){

//			se.solve( instancia );
//			sa.solve( instancia );
//			guloso.solve( instancia );
			grasp.solve( instancia );
			System.out.println("-----------------------------------------");
		}
	}

}