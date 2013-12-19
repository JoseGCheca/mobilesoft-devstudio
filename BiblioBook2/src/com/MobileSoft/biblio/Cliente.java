package com.MobileSoft.biblio;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Cliente {
	public Cliente(){
		try{
			Socket skCliente = new Socket( "10.123.254.129", 16000);
			
			OutputStream aux= skCliente.getOutputStream();
			DataOutputStream flujo = new DataOutputStream( aux);
			flujo.writeUTF("Hola mundo!");
			
			skCliente.close();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		
	}
	

}
