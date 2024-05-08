package Readers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Colecciones {

	//vector
	int[] vec = new int[5];
	
	//agregar eficiencia CERO
	//lectura muy eficiente
	ArrayList<String> frutas = new ArrayList<>();
	ArrayList verduras = new ArrayList<>();
	
	List nombres  = new ArrayList<>();
	List<String> apellido = new ArrayList<>();
	
	//agregar maxima eficiencia
	//lectura cero eficiente
	LinkedList<String> componentesPc = new LinkedList<>();
	List precios = new LinkedList<>();
	
	LinkedHashMap<String, String> listaEspañolIngles = new LinkedHashMap<>();
	Map<Integer,Integer> valores = new LinkedHashMap<>();
	
	LinkedHashSet<Integer> listaInglesEspañol = new LinkedHashSet<>();
	Set<Integer> valores2 = new LinkedHashSet<>();	
	
		 //    k   ,   v
	HashMap<String , String> españolRAE = new HashMap<>();
	Map<String,Integer> españolIngles = new HashMap<>();
	
	HashSet<String> claves = new HashSet<>();
	Set<Integer> DNI = new HashSet<>();
	
	public void Array()
	{
		frutas.add("");
		verduras.add(  5  ) ;
		verduras.add( "s" ) ;
		
		frutas.clear(); //vacia por por completo
		
		frutas.remove(5); //lo que este en la pocicion 5
		frutas.remove("manzana");  /// busca y borra ese dato particular
		
		verduras.contains( "tomate" );  // exite
		verduras.containsAll( new ArrayList<>() );
		
		frutas.get( 5 );  //devuele lo que esta en la posicion 5
		frutas.indexOf( "banana" ); //buscar banana y devuelve la posicion
		
		verduras.isEmpty();
		verduras.size();
		
		
	}
	
}
