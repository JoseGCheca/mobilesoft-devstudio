package prueba;

package Mapas;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openstreetmap.osmosis.core.container.v0_6.EntityContainer;
import org.openstreetmap.osmosis.core.domain.v0_6.Entity;
import org.openstreetmap.osmosis.core.domain.v0_6.Node;
import org.openstreetmap.osmosis.core.domain.v0_6.Tag;
import org.openstreetmap.osmosis.core.domain.v0_6.Way;
import org.openstreetmap.osmosis.core.domain.v0_6.WayNode;
import org.openstreetmap.osmosis.core.task.v0_6.RunnableSource;
import org.openstreetmap.osmosis.core.task.v0_6.Sink;
import org.openstreetmap.osmosis.xml.common.CompressionMethod;
import org.openstreetmap.osmosis.xml.v0_6.XmlReader;

public class PracticaMapa {

	static GrafoMapa gm=new GrafoMapa();
	
	static public void main(String[] args) throws IOException{
		
		/*
		 * <fichero> <nombre problema> <origen> <destino> <estrategia> <profundidad> <opt>
		 */
		String rutaF="",nombrePro="",estr="",origen="",destino="";
		int maxProf=100;
		long tiempoTotal=0,totalNodos=0;
		boolean opt=false;
		File file;
		Busqueda busq=null;
		
		try{
			rutaF=args[0];
			origen=args[1];
			destino=args[2];
			estr=args[3];
			maxProf=Integer.parseInt(args[4]);
		}
		catch(java.lang.ArrayIndexOutOfBoundsException e){
			System.err.println("Error en la linea de comandos: <fichero> <origen> <destino> <estrategia> <profundidad> <opt>  <nombre problema> ");
			System.exit(1);
		}
		catch (java.lang.NumberFormatException e){
			System.err.println("No se ha introducido un tipo entero en el limite de la profundidad.");
			System.err.println("<fichero> <origen> <destino> <estrategia> <profundidad> <opt>  <nombre problema>");
			System.exit(1);
		}
		
		
		file=new File(rutaF);
		if(!file.exists()){//se comprueba si existe el fichero
			System.err.println("Error. No se ha encontrado el fichero.");
			System.err.println("<fichero> <origen> <destino> <estrategia> <profundidad> <opt>  <nombre problema>");
			System.exit(1); //salida del programa erronea
		}
		
		if(args.length>5)//opcion de optimizacion
			opt=args[5].equals("opt");	
		
		//para obtener el nombre del fichero gpx, que es opcional
		if(!opt && args.length>5)
			nombrePro=args[args.length-1];
		else if(opt && args.length>6)
			nombrePro=args[6];
		else
			nombrePro="Tarea6";
		
		inicializarGrafo(rutaF);//se carga el grafo con la parte del mapa de ciudad real
		
		//System.out.println(gm.toString()); //se imprime el grafo
		
		ProblemaMapa<Arista,String> pro=new ProblemaMapa<Arista,String>(nombrePro,gm); //se crea el problema
		pro.asignarEstadoInicial(origen);
		pro.setEstadoFinal(destino);
		
		try{
			busq=new Busqueda(pro,estr,maxProf,opt); //se crea la clase con lo necesario para obtener la solucion
			busq.ejecutarBusqueda(); //se obtiene la solucion
		}
		catch (java.lang.OutOfMemoryError e){
			System.err.println("No se ha podido obtener la solucion por desbordamiento de memoria.");
			System.exit(1);
		}
		catch (Exception e){
			System.err.println("Error. No se ha obtenido la solucion.");
			System.exit(1);
		}
		
		System.out.println(busq.solucionToString());
		
		busq.solucionToGPX();
		
		if(busq.getSolucion()!=null) //si no hay solucion el fichero no se crea
			System.out.println("Archivo creado correctamente.");
		
		//se muestra el costo temporal en segundos
		tiempoTotal=busq.getTiempoTotal();
		System.out.println("Tiempo total en el proceso de busqueda: "+tiempoTotal*0.000000001+" segundos.");
		
		totalNodos=busq.getTotalNodos(); //numero total de nodos generados
		System.out.println("Numero total de nodos generados: "+totalNodos);
		
		if(opt)
			System.out.println("Con optimizacion.");
		else
			System.out.println("Sin optimizacion.");
	}
	
	
	static public void inicializarGrafo(String pathFile){
		File file= new File(pathFile); 
		
		Sink sinkImplementation = new Sink() {
			public void initialize(Map<String, Object> metaData){};
			public void process (EntityContainer entityContainer){
				Entity entity = entityContainer.getEntity();
				if (entity instanceof Node ){ //se trabajan sobre los nodos
					//se van anadiendo los nodos
					gm.anadirNodo(String.valueOf(entity.getId()), ((Node) entity).getLatitude(), ((Node) entity).getLongitude());
				} else if (entity instanceof Way){ //se trabajan sobre las calles
					
					String nombreCalle="",tipoCalle="",unSentido="",idOrigen,idDestino,idWay;
					boolean valida=false;
					double p1lon,p1lat,p2lon,p2lat,distancia;
					Nodo node;
					List<WayNode> lW;
					Iterator<Tag> tags=entity.getTags().iterator(); //todas las etiquetas disponibles sobre la calle
					
					idWay=String.valueOf(entity.getId());//Id de la calle
					
					if(tags.hasNext()){//Tipo de calle
						
						tipoCalle=tags.next().getValue();
						valida=tipoCalle.equals("residential") || tipoCalle.equals("pedestrian") || tipoCalle.equals("trunk");//para obtener los dos tipos
						
					}
					if(valida){//se comprueba si son calles validas para incluirlas en el grafo
						if(tags.hasNext())//Nombre de la calle
							nombreCalle=tags.next().getValue();
						if(tags.hasNext())//Sentido de la calle
							unSentido=tags.next().getValue();
						
						//para saber si la calle tiene la informacion correcta
						if(unSentido.equals("yes") || unSentido.equals("no") || unSentido.equals("-1")){
							lW=((Way) entity).getWayNodes(); //se obtiene la lista de nodos que forman la calle
							
							for(int i=0, j=1;(i<lW.size()-1)&&(j<lW.size());i++,j++){
								idOrigen=String.valueOf(lW.get(i).getNodeId());
								idDestino=String.valueOf(lW.get(j).getNodeId());
								
								node=gm.getNodo(idOrigen);
								p1lon=node.getLongitud();
								p1lat=node.getLatitud();
								
								node=gm.getNodo(idDestino);
								p2lon=node.getLongitud();
								p2lat=node.getLatitud();
								distancia=dist(p1lon,p1lat,p2lon,p2lat);//se calcula a distancia entre los dos puntos
								//se anade la arista una vez claculada la metrica
								gm.anadirArista(idOrigen, idDestino, idWay, distancia, tipoCalle, nombreCalle);
								
								if(unSentido.equals("no") || unSentido.equals("-1"))//calle en doble sentido
									gm.anadirArista(idDestino, idOrigen, idWay, distancia, tipoCalle, nombreCalle);
							}
						}
					}//if de info valida
				}//fin de la inicializacion de las calles
			}
			public void release(){}
			public void complete(){}
			
		};
		
		CompressionMethod compression = CompressionMethod.None;
		RunnableSource reader= new XmlReader(file, false, compression);
		
		reader.setSink(sinkImplementation);
		
		Thread readerThread = new Thread(reader);
		readerThread.start();
		
		while (readerThread.isAlive()) {
		    try {
		        readerThread.join();
		    } catch (InterruptedException e) {
		        /* No hacer nada */
		    }
		}
		
		limpiarNodos(); //se quitan los nodos que no forman parte de ninguna calle
	}//Fin de inicializarGrafo
	
	public static double  mercX(double lon) {
		double R_MAJOR = 6378137.0;
        return R_MAJOR * Math.toRadians(lon);
    }
 
    public static double mercY(double lat) {
    	double R_MAJOR = 6378137.0;
    	double R_MINOR = 6356752.3142;
        if (lat > 89.5) {
            lat = 89.5;
        }
        if (lat < -89.5) {
            lat = -89.5;
        }
        double temp = R_MINOR / R_MAJOR;
        double es = 1.0 - (temp * temp);
        double eccent = Math.sqrt(es);
        double phi = Math.toRadians(lat);
        double sinphi = Math.sin(phi);
        double con = eccent * sinphi;
        double com = 0.5 * eccent;
        con = Math.pow(((1.0-con)/(1.0+con)), com);
        double ts = Math.tan(0.5 * ((Math.PI*0.5) - phi))/con;
        double y = 0 - R_MAJOR * Math.log(ts);
        return y;
    }
    
    //metodo para calcular la distancia entre dos nodos del mapa
    public static double dist(double p1lon,double p1lat,double p2lon,double p2lat){
		double x1=mercX(p1lon);
		
		double x2=mercX(p2lon);
		double y1=mercY(p1lat);
		double y2=mercY(p2lat);
		
		return Math.sqrt(Math.pow((x1-x2),2)+Math.pow((y1-y2),2));
	}
    
    //metodo que limpia los nodos que no pertenecen a ninguna calle
    public static void limpiarNodos(){
    	/*se recorren los nodos de la tabla hash 
    	para borrar los que tengan la lista de adyacencia vacia*/
    	ArrayList<Nodo> lNodos=gm.listaNodos();
    	for(int i=0;i<lNodos.size();i++){
    		if(lNodos.get(i).numAdyacentes()==0){
    			gm.eliminarNodo(lNodos.get(i).getId());//se borra de la tabla hash
    		}
    	}
    	
    }
}
