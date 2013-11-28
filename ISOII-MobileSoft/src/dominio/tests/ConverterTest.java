package dominio.tests;

import static org.junit.Assert.*;
import dominio.Converter;
import org.junit.Test;

public class ConverterTest {

	@Test
	public void testK1() {
		Converter c=new Converter();
		double obtenido=c.convert("K", "C", -500);
		
		assertTrue("Esperaba -500 y he obtenido "+obtenido,obtenido==Integer.MIN_VALUE);
	}
	
	@Test
	public void testK2() {
		Converter c=new Converter();
		double obtenido=c.convert("K", "C", 0);
		
		assertTrue("Esperaba 0 y he obtenido "+obtenido,obtenido==-273);
	}
	
	@Test
	public void testK3() {
		Converter c=new Converter();
		double obtenido=c.convert("K", "C", -273);
		
		assertTrue("Esperaba -273 y he obtenido "+obtenido,obtenido==Integer.MIN_VALUE);
	}
	
	
	@Test
	public void testK4() {
		Converter c=new Converter();
		double obtenido=c.convert("K", "C", 500);
		
		assertTrue("Esperaba 500 y he obtenido "+obtenido,obtenido==217);
	}
	
	@Test
	public void testK5() {
		Converter c=new Converter();
		double obtenido=c.convert("P", "KG", 0);
		
		assertTrue("Esperaba 500 y he obtenido "+obtenido,obtenido==0);
	}
	
	@Test
	public void testAKilogramos() {
		Converter c=new Converter();
		double obtenido=c.convert("K", "C", 500);
		
		assertTrue("Esperaba 500 y he obtenido "+obtenido,obtenido==217);
	}
	
	@Test
	public void testConversionErronea() {
		Converter c=new Converter();
		double obtenido=c.convert("KM", "KG", 0);
		
		assertTrue("Esperaba 500 y he obtenido "+obtenido,obtenido==Integer.MIN_VALUE);
	}

}
