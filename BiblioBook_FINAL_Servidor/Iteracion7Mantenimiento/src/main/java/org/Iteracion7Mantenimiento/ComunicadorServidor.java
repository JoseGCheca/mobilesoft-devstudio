package org.Iteracion5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.sql.SQLException;

import org.Iteracion5.GestionServidor;

public class ComunicadorServidor {

	public static void main(String[] args) throws IOException {
		Socket skCliente;
		int PUERTO=16009;
		ServerSocket skServidor=null;
		
		try {

			//SOCKET SERVIDOR ESCUCHANDO EN ESE PUERTO
			skServidor = new ServerSocket(PUERTO);

			System.out.println("Escucho el puerto " + PUERTO );

			while(true) {
				//MANEJADOR DEL CLIENTE
				skCliente = skServidor.accept(); // Crea objeto
				manejadorHijo(skCliente);
			}


		} catch( Exception e ) {
			System.out.println( e.getMessage());
			skServidor.close();
		}
	}


	// cifrar los mensajes

	public static String cifrarMensaje(String msg,int k){
		char c []=new char[msg.length()];
		String msgC="";
		c=msg.toCharArray();

		for(int i=0;i<c.length;i++){
			c[i]-=k;
		}
		msgC=String.valueOf(c);
		return msgC;
	}


	//descifrar los mensajes



	public static String descifrarMensaje(String msg,int k){
		char c []=new char[msg.length()];
		String msgC="";
		c=msg.toCharArray();

		for(int i=0;i<c.length;i++){
			c[i]+=k;
		}
		msgC=String.valueOf(c);
		return msgC;
	}



	public static void manejadorHijo(Socket skCliente ) throws SQLException, Exception {
		InputStream aux;
		OutputStream aux2;
		DataInputStream flujo;
		DataOutputStream flujo2;
		GestionServidor gserver=new GestionServidor();
		String informacion="",met="",resultado="";
		String [] par;
		String vars [];
		boolean res=false,salir=false;;
		
		//STREAM DEL CLIENTE, se reciben datos
		aux = skCliente.getInputStream();
		flujo= new DataInputStream( aux );
		
		
		//STREAM DEL CLIENTE, se envian datos
		aux2 = skCliente.getOutputStream();
		flujo2= new DataOutputStream( aux2 );
		
		
		while(!salir){ /***comprobar condiciones***/

			
			informacion=flujo.readUTF(); //lee
			
			System.out.println(informacion);
			
			vars=informacion.split("-");
			met=vars[0]; //nombre del metodo
			
			par=vars[1].split(":");//parametros del metodo
			
			switch(met){
			case "autenticar":
				res=gserver.autenticar(par[1],par[2]);
				break;
			case "modificarContrasena":
				res=gserver.modificarContrasena(par[1], par[2]);
				break;
			case "solicitarPrestamo":
				res=gserver.solicitarPrestamo(par[1]);
				break;
			case "renovarPrestamo":
				res=gserver.renovarPrestamo(par[1]);
				break;
			case "consultarPrestamo":
				resultado=gserver.consultarPrestamo();
				break;
			case "modificarTelefono":
				res=gserver.modificarTelefono(par[1]);
				break;
			case "modificarDireccion":
				res=gserver.modificarDireccion(par[1], par[2]);
				break;
			case "consultarMisDatos":
				resultado=gserver.consultarMisDatos();
				break;
			case "consultarLibro":
				resultado=gserver.consultarLibro(par[1]);
				break;
			case "devolverLibro":
				res=gserver.devolverLibro(par[1]);
				break;
			case "salirUsuario":
				res=gserver.salirUsuario();
				salir=true;
				break;
			}

			
			if(res==true)
				resultado="true";
			else if(res==false)
				resultado="false";
			
			
			flujo2.writeUTF(resultado);
		}
		
	}


}
