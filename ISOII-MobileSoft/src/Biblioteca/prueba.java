package Biblioteca;

package Mapas;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

public class GrafoMapa {

	//la tabla hash contendra todos los nodos del mapa seleccionado
	private Hashtable<String,Nodo> tablaNodos;
	
	
	public GrafoMapa(){
		tablaNodos=new Hashtable<String,Nodo>();
	}
	
	//anadir los nodos del mapa en la tabla hash
	public boolean anadirNodo(String id, double latitud,double longitud){
		boolean res=false;
		Nodo n=new Nodo(id,latitud,longitud);
		if(!existeNodo(n.getId())){
			tablaNodos.put(n.getId(), n);
			res=true;
		}
		return res;
	}
	
	public boolean eliminarNodo(String id){
		Iterator<Nodo> ite=tablaNodos.values().iterator();
		Nodo n;
		boolean res=false;
		if(existeNodo(id)){
			n=tablaNodos.get(id);
			//se elimina el vertice de las listas de adyacencia de los nodos
			while(ite.hasNext())
				ite.next().eliminarVert(n);
		
			//se elimina de la tabla hash
			res=tablaNodos.remove(n.getId()).equals(n);
			
		}
		return res;
	}
	
	public boolean existeNodo(String idNodo){
		return tablaNodos.containsKey(idNodo);
	}
	
	public Nodo getNodo(String id){
		return tablaNodos.get(id);
	}
	//con los id de los nodos origen y destino obtenemos la arista 
	public Arista getArista(String idO,String idD){
		Nodo nodo1,nodo2;
		Arista a=null;
		if(existeNodo(idO)&&existeNodo(idD)){
			nodo1=tablaNodos.get(idO);
			nodo2=tablaNodos.get(idD);
			a=nodo1.getArista(nodo2);
		}
		return a;
	}
	
	public ArrayList<Nodo> listaNodos(){
		ArrayList<Nodo> lNodos=new ArrayList<Nodo>();
		Iterator<Nodo> it=tablaNodos.values().iterator();
		while(it.hasNext())
			lNodos.add(it.next());
		return lNodos;
	}
	//a traves del id del nodo obtenemos todos sus nodos adyacentes
	public ArrayList<Nodo> getNodosAdyacentes(String id){
		ArrayList<Nodo> l=null;
		Nodo nodo;
		if(existeNodo(id)){
			nodo=tablaNodos.get(id);
			l=nodo.getNodosAdyacentes();
		}
		return l;
	}
	
	public ArrayList<Arista> getAristasIncidentes(String id){
		ArrayList<Arista> l=null;
		Nodo nodo;
		if(existeNodo(id)){
			nodo=tablaNodos.get(id);
			l=nodo.getAristaIncidentes();
		}
		return l;
	}
	
	//se van anadiendo las aristas
	public boolean anadirArista(String idO,String idD,String id, double dis, String t,String n){
		//se comprueba si existen los nodos en la tabla hash
		boolean res=false;
		Nodo nodo1,nodo2;
		boolean repetido=idO.equals(idD);//por si se inserta el mismo nodo origen y destino
		if(existeNodo(idO) && existeNodo(idD)&&!repetido){
			nodo1=tablaNodos.get(idO);
			nodo2=tablaNodos.get(idD);
			//nodo1 es el origen y se inserta el nodo2 que es el adyacente
			res=nodo1.anadirVert(nodo2,id, dis, t, n); //se anade la arista
		}
		return res;
	}
	
	public boolean eliminarArista(String idO,String idD){
		boolean res1=false,res2=false;;
		Nodo nodo1,nodo2;
		if(existeNodo(idO) && existeNodo(idD)){
			nodo1=tablaNodos.get(idO);
			nodo2=tablaNodos.get(idD);
			res1=nodo1.eliminarVert(nodo2);
			//se elimina por si la arista es no dirigida
			res2=nodo2.eliminarVert(nodo1);
		}
		return res1 || res2;
	}
	
	public boolean existeAristaDirigida(String idO, String idD){
		boolean res=false;
		Nodo nodo1,nodo2;
		if(existeNodo(idO) && existeNodo(idD)){
			nodo1=tablaNodos.get(idO);
			nodo2=tablaNodos.get(idD);
			res=nodo1.existeVert(nodo2)!=-1;
		}
		return res;
	}
	
	public boolean existeAristaNoDirigida(String idO,String idD){
		boolean res=false;
		Nodo nodo1,nodo2;
		if(existeNodo(idO) && existeNodo(idD)){
			nodo1=tablaNodos.get(idO);
			nodo2=tablaNodos.get(idD);
			res=(nodo1.existeVert(nodo2)!=-1) && (nodo2.existeVert(nodo1)!=-1);
		}
		return res;
	}
	
	//se imprime el grafo
	public String toString(){
		String res="";
		Iterator<Nodo> it=tablaNodos.values().iterator();
		while(it.hasNext())
			res+=it.next().toString();
		return res;
	}
	
}
