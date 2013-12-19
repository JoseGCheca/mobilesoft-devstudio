package org.Iteracion1;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;
import org.Iteracion1.*;

public class UserTests {


	@Test
	public void testAccede() throws SQLException, Exception { /** consigue acceder a la aplicacion***/
		GestionServidor g=new GestionServidor();
		boolean res=g.autenticar("prueba", "222222-y");
		
		assertTrue(res==true);
	}
	
	@Test
	public void testDniInc() throws SQLException, Exception { /** no consigue acceder a la aplicacion***/
		GestionServidor g=new GestionServidor();
		boolean res=g.autenticar("prueba", "2223222-y");
		
		assertTrue(res==false);
	}
	
	@Test
	public void testPassInc() throws SQLException, Exception { /** no consigue acceder a la aplicacion***/
		GestionServidor g=new GestionServidor();
		boolean res=g.autenticar("holitsa", "222222-y");
		
		assertTrue(res==false);
	}
	
	@Test
	public void testModificaPass() throws SQLException, Exception { 
		GestionServidor g=new GestionServidor();
		g.autenticar("holita", "222222-y");
		
		boolean res=g.modificarContrasena("holita", "holita");
		
		assertTrue(res==true);
	}

}
