package dominio.tests;

import static org.junit.Assert.*;
import dominio.Converter;
import org.junit.Test;

public class ConverterTest {

	@Test
	public void testK1() {
		Converter c=new Converter();
		double obtenido=c.convert("K", "C", -500);
		
		assertTrue(obtenido==Integer.MIN_VALUE);
	}
	
	@Test
	public void testK2() {
		Converter c=new Converter();
		double obtenido=c.convert("K", "C", 0);
		
		assertTrue(obtenido==-273);
	}
	
	@Test
	public void testK3() {
		Converter c=new Converter();
		double obtenido=c.convert("K", "C", -273);
		
		assertTrue(obtenido==Integer.MIN_VALUE);
	}
	
	
	@Test
	public void testK4() {
		Converter c=new Converter();
		double obtenido=c.convert("K", "C", 500);
		
		assertTrue(obtenido==217);
	}

}
