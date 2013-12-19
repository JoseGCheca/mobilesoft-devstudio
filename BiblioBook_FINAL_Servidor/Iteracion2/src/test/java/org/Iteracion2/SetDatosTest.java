package org.Iteracion2;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.Iteracion2.GestionServidor;
import org.junit.Test;

public class SetDatosTest {

	@Test
	public void testDireccion() throws SQLException, Exception { /** consigue acceder a la aplicacion***/
		GestionServidor g=new GestionServidor();
		g.autenticar("prueba", "222222-y");
		g.modificarDireccion("Calle", "Juan Ramon Jimenez");
		
		Usuario u=new Usuario();
		u.leerUsuario("222222-y");
		assertTrue(u.getDireccion().equals("Calle"+ " Juan Ramon Jimenez"));
	}
	
	@Test
	public void testTelefono() throws SQLException, Exception { /** no consigue acceder a la aplicacion***/
		GestionServidor g=new GestionServidor();
		g.autenticar("prueba", "222222-y");
		g.modificarTelefono("926889900");
		
		Usuario u=new Usuario();
		u.leerUsuario("222222-y");
		assertTrue(u.getTelefono().equals("926889900"));
	}
	
	@Test
	public void testDatos() throws SQLException, Exception { /** no consigue acceder a la aplicacion***/
		GestionServidor g=new GestionServidor();
		g.autenticar("prueba", "222222-y");
		String datos=g.consultarMisDatos(); /*cadena de texto que se envia por los sockets*/
		
				
		assertTrue(datos.equals(":222222-y:prueba:cauchy:Pruebas:mizifuu:10/09/1990:926889900:false:Calle Juan Ramon Jimenez"));
	}
	
	
	

}
