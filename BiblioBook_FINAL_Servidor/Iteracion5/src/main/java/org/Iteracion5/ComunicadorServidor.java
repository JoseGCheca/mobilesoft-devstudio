package org.Iteracion5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.sql.SQLException;

public class ComunicadorServidor {

	public static void main(String[] args) {
		Socket skCliente;
		int PUERTO=16000;

		try {

			//SOCKET SERVIDOR ESCUCHANDO EN ESE PUERTO
			@SuppressWarnings("resource")
			ServerSocket skServidor = new ServerSocket(PUERTO);


			System.out.println("Escucho el puerto " + PUERTO );

			while(true) {
				//MANEJADOR DEL CLIENTE
				skCliente = skServidor.accept(); // Crea objeto
				manejadorHijo(skCliente);
			}


		} catch( Exception e ) {
			System.out.println( e.getMessage()+"dsffkjshdlf");
		}
	}

	public static void manejadorHijo(Socket skCliente ) throws SQLException, Exception {
		InputStream aux;
		OutputStream aux2;
		DataInputStream flujo;
		DataOutputStream flujo2;
		GestionServidor gserver=new GestionServidor();
		String informacion="",met="",resultado="";
		String [] par;
		boolean res=false,salir=false;;
		
		while(!salir){ /***comprobar condiciones***/

			//STREAM DEL CLIENTE, se reciben datos
			aux = skCliente.getInputStream();
			flujo= new DataInputStream( aux );
			
			informacion=flujo.readUTF();
			met=informacion.split("-")[0];
			par=informacion.split("-")[1].split(":");
			
			switch(met){
			case "autenticar":
				res=gserver.autenticar("holita","222222-y");
				
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
			
			//STREAM DEL CLIENTE, se envian datos
			aux2 = skCliente.getOutputStream();
			flujo2= new DataOutputStream( aux2 );
			flujo2.writeUTF(resultado);
		}
		
	}


}
