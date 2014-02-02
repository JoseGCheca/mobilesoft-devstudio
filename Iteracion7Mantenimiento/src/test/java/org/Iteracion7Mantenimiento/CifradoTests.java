package org.Iteracion5;

import static org.junit.Assert.*;

import java.sql.SQLException;




import org.Iteracion5.ComunicadorServidor;
import org.Iteracion5.GestionServidor;
import org.Iteracion5.GestorPrestamo;
import org.Iteracion5.Libro;
import org.Iteracion5.Prestamo;
import org.junit.Test;

public class CifradoTests {

	@Test
	public void testCifrar2pos() throws SQLException, Exception { /** cifrar mensaje con k=2***/
	
		String cif=ComunicadorServidor.cifrarMensaje("Hola mundo", 2);
		
		assertTrue(cif.equals("Fmj_kslbm"));
	}
	
	@Test
	public void testCifrar3pos() throws SQLException, Exception { /** cifrar mensaje con k=3***/
	
		String cif=ComunicadorServidor.cifrarMensaje("Hola mundo", 3);
		
		assertTrue(cif.equals("Eli^jrkal"));
	}
	
	@Test
	public void testDescifrar2pos() throws SQLException, Exception { /** descifrar con k=2***/
	
		String cif=ComunicadorServidor.descifrarMensaje("Fmj_kslbm", 2);
		
		assertTrue(cif.equals("Hola mundo"));
	}
	
	@Test
	public void testDescifrar3pos() throws SQLException, Exception { /** descifrar con k=3***/
	
		String cif=ComunicadorServidor.descifrarMensaje("Eli^jrkal", 3);
		
		assertTrue(cif.equals("Hola mundo"));
	}

}
