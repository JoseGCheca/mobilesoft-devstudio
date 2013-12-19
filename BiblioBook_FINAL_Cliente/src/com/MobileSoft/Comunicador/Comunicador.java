package com.MobileSoft.Comunicador;

import java.io.*;
import java.net.*;

public class Comunicador {

	public Comunicador(){

	}

	public String Conectar(String cadena){
		int PUERTO=16000;
		String result=null;
		System.out.println("Escucho el puerto " + PUERTO );
		//MANEJADOR DEL CLIENTE
		try{
			Socket skCliente= new Socket();
			skCliente.connect(new InetSocketAddress("10.123.254.149", PUERTO), 5000);

			OutputStream aux= skCliente.getOutputStream();
			DataOutputStream flujo = new DataOutputStream( aux);
			flujo.writeUTF(cadena);

			InputStream aux2 = skCliente.getInputStream();
			DataInputStream flujo2= new DataInputStream( aux2 );

			result=flujo2.readUTF();
			System.out.println(result);

			skCliente.close();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return result;

	}
}
