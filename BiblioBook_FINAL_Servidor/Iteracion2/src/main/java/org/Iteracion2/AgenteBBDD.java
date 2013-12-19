package org.Iteracion2;

import java.sql.*;
import java.util.Vector;
public class AgenteBBDD {
	protected static AgenteBBDD mInstancia=null;
    protected static Connection mBD;
    //Constructor
    private AgenteBBDD()throws Exception {
    	AgenteBBDD.conectar();    	
    }
    
    //Implementación del patrón Singleton
     public static AgenteBBDD getAgente() throws Exception{
          if (mInstancia==null){
          mInstancia=new AgenteBBDD();
        }
        return mInstancia;
     }
     
    private static void conectar() throws Exception {
    	//String driverName = "oracle.jdbc.driver.OracleDriver";
    	//Class.forName(driverName);
     
        DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver()); //driver de oracle 
        mBD = DriverManager.getConnection("jdbc:oracle:thin:@rujmaestro.no-ip.org:1521:orcl", "system", "oracle"); //conexion siendo orcl el nombre de la bbdd
    }

    public static void desconectar() throws Exception{mBD.close();}

    public int insert(String SQL) throws SQLException, Exception{
    	int resultado;
    	this.conectar();
    	PreparedStatement sentencia = mBD.prepareStatement(SQL);
    	resultado = sentencia.executeUpdate();
    	return resultado;
    }

   
    public Vector select(String SQL) throws SQLException,Exception{
    	int numCol = 0;
    	Vector resFinal = new Vector();
    	Vector registro;
    	this.conectar();
    	
    	PreparedStatement sentencia = mBD.prepareStatement(SQL);
    	ResultSet respuesta = sentencia.executeQuery();
    	
    	//Obtenemos metainformacion del resultado de la consulta
    	ResultSetMetaData rsmd = respuesta.getMetaData();
    	numCol = rsmd.getColumnCount(); //nos da el numero de columnas
    	
    	//con next recorremos secuencialmente respuesta, es decir linea a linea
    	while(respuesta.next()){
    		
    		//Por cada registro inicializamos el vector registro
    		registro = new Vector();
    		//Se procesa uno a uno los registros
    		for(int i=1; i<=numCol; i++){
    			//se clasifica segun el tipo de dato del registro
    			switch(rsmd.getColumnType(i)){
    			case Types.INTEGER: registro.add(new Integer(respuesta.getInt(i)));
    					break;
    			case Types.VARCHAR: registro.add(respuesta.getString(i));
    					break;
    			case Types.DOUBLE: registro.add(new Double(respuesta.getDouble(i)));
    					break;
    			case Types.CHAR: registro.add(respuesta.getString(i));
					break;
    			case Types.NUMERIC: registro.add(new Integer(respuesta.getInt(i)));
					break;	
    			}
    		}
    		//Una vez procesados los registros, lo almacenamos en el vector resFinal
    		resFinal.add(registro);
    	}

    	//Se desconecta de la base de datos
    	respuesta.close();
    	sentencia.close();
    	desconectar();
    	//se devuelve el vector con la informacion
    	return resFinal;
    }
    
   

    public int delete(String SQL) throws SQLException,Exception{
    	int resultado;
    	this.conectar();
    	PreparedStatement sentencia = mBD.prepareStatement(SQL);
    	resultado = sentencia.executeUpdate();
    	return resultado;
    }   

    public int update(String SQL) throws SQLException,Exception{
    	int resultado;
    	this.conectar();
    	PreparedStatement sentencia = mBD.prepareStatement(SQL);
    	resultado = sentencia.executeUpdate();
    	return resultado;
    }             
}