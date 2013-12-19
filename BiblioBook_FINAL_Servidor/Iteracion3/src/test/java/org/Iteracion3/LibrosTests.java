package org.Iteracion3;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.Iteracion3.GestionServidor;
import org.Iteracion3.Usuario;
import org.junit.Test;

public class LibrosTests {

	@Test
	public void testSignatura() throws SQLException, Exception { /** consigue acceder a la aplicacion***/
		GestionServidor g=new GestionServidor();
		g.autenticar("prueba", "222222-y");
		String r=g.consultarLibro("Etica para amador3");
		
		
		assertTrue(r.split(":")[5].equals("aaa-342-e"));
	}
	
	@Test
	public void testSinDatos() throws SQLException, Exception { /** consigue acceder a la aplicacion***/
		GestionServidor g=new GestionServidor();
		g.autenticar("prueba", "222222-y");
		String r=g.consultarLibro("Etica para amador3 hola");
		
		
		assertTrue(r.equals(""));
	}
}
