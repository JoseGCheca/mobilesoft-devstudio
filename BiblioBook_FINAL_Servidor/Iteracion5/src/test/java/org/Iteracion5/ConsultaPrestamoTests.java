package org.Iteracion5;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.Iteracion4.GestionServidor;
import org.junit.Test;

public class ConsultaPrestamoTests {

	@Test
	public void testConsulta() throws SQLException, Exception { /** consigue acceder a la aplicacion***/
		GestionServidor g=new GestionServidor();
		g.autenticar("prueba", "222222-y");
		
		g.solicitarPrestamo("aaa-342-e");
		String p=g.consultarPrestamo();
		
		
		
		assertTrue();
	}

}
