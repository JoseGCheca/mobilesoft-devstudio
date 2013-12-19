package org.Iteracion5;

import static org.junit.Assert.*;

import java.sql.SQLException;


import org.junit.Test;

public class ConsultaPrestamoTests {

	@Test
	public void testConsultaPrestamoUser() throws SQLException, Exception { /** consigue acceder a la aplicacion***/
		GestorPrestamo gp=new GestorPrestamo();
		GestionServidor gs=new GestionServidor();
		gs.autenticar("prueba", "222222-y");
		gs.solicitarPrestamo("aaa-342-e"); /*para asegurar que hay al menos un libro*/
		
		Prestamo prest=gp.read("aaa-342-e");
		
		assertTrue(prest.getUsuario().getDNI().equals("222222-y"));
	}
	
	public void testConsultaPrestamoTitulo() throws SQLException, Exception { /** consigue acceder a la aplicacion***/
		Prestamo prest=new Prestamo();

		prest.leerPrestamo("aaa-342-e");
		
		Libro lib=new Libro();
		lib.leerLibro("aaa-342-e");
		
		assertTrue(lib.getTitulo().equals("Etica para amador3"));
	}

}
