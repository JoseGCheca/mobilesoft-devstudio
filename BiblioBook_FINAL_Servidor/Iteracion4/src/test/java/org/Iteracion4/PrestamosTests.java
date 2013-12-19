package org.Iteracion4;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.Iteracion4.GestionServidor;
import org.junit.Test;

public class PrestamosTests {

	@Test
	public void testSolicitar() throws SQLException, Exception { /** consigue acceder a la aplicacion***/
		GestionServidor g=new GestionServidor();
		g.autenticar("prueba", "222222-y");
		
		g.devolverLibro("aaa-342-e"); /**se elimina por si existiera en la bbdd*/
		boolean r=g.solicitarPrestamo("aaa-342-e");
		
		
		assertTrue(r==true);
	}
	
	@Test
	public void testEliminar() throws SQLException, Exception { /** consigue acceder a la aplicacion***/
		GestionServidor g=new GestionServidor();
		g.autenticar("prueba", "222222-y");
		
		g.solicitarPrestamo("aaa-342-e"); /***para comprobar que esta el libro*/
		boolean r=g.devolverLibro("aaa-342-e"); /**se elimina por si existiera en la bbdd*/
		
		
		assertTrue(r==true);
	}
}
