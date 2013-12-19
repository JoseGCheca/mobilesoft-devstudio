package org.Iteracion4;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class Prestamo {

	private Usuario usuario;
	private String fechaPrestamo;
	private String fechaDevolucion;
	private boolean sobrepasado;
	private Libro libro;
	private String idPrestamo;

	public Prestamo() {

	}

	public String getfechaPrestamo() {
		return fechaPrestamo;
	}

	public String getfechaDevolucion() {
		return fechaDevolucion;
	}

	public String getidPrestamo() {
		return idPrestamo;
	}

	public Usuario getUsuario(){
		return usuario;
	}

	public Libro getLibro() {
		return libro;
	}
	
	public boolean getSobrepasado(){
		return sobrepasado;
	}

	public void setfechaPrestamo(String fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	public void setfechaDevolucion(String fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public void setidPrestamo(String idPrestamo) {
		this.idPrestamo = idPrestamo;
	}
	public void setUsuario(Usuario usuario){
		this.usuario=usuario;
	}
	public void setLibro(Libro libro){
		this.libro=libro;
	}
	
	public void setSobrepasado(boolean sobrepasado){
		this.sobrepasado=sobrepasado;
	}

	
	/////////////////////////////
	public  ArrayList<Prestamo> leerTodos(Usuario usuario) throws SQLException, Exception {
		GestorPrestamo gprest=new GestorPrestamo();
		gprest.readAll(usuario);
		return gprest.getListaPrestamo();
	}

	public boolean leerPrestamo(String id) throws SQLException, Exception {
		GestorPrestamo gprest=new GestorPrestamo();
		Prestamo p=gprest.read(id);
		boolean res=false;
		
		if(p!=null){
			usuario=p.getUsuario();
			fechaPrestamo=p.getfechaPrestamo();
			fechaDevolucion=p.getfechaDevolucion();
			sobrepasado=p.getSobrepasado();
			libro=p.getLibro();
			idPrestamo=p.getidPrestamo();
			res=true;
		}
		
		return res;
	}

	public boolean insertarPrestamo() throws SQLException, Exception {
		GestorPrestamo gprest=new GestorPrestamo();
		return gprest.insert(this);
	}

	public boolean eliminarPrestamo() throws SQLException, Exception {
		GestorPrestamo gprest=new GestorPrestamo();
		return gprest.delete(this);
	}

	public boolean actualizarPrestamo() throws SQLException, Exception {
		GestorPrestamo gprest=new GestorPrestamo();
		return gprest.update(this);
	}

	public boolean aumentarPlazo(int dias) throws SQLException, Exception {
		Date fecha; //fecha actual
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		fecha=sdf.parse(fechaDevolucion);
	
		Calendar c=Calendar.getInstance();
		c.setTime(fecha);
		c.add(Calendar.DAY_OF_MONTH, dias);
		fecha=c.getTime();
		
		fechaDevolucion=fecha.getDate()+"-"+(fecha.getMonth()+1)+"-"+(fecha.getYear()+1900);
		
		return actualizarPrestamo();
	}
	
	
	public boolean equals(Object o){
		boolean iguales=false;
		if (o instanceof Prestamo){
			Prestamo n=(Prestamo) o;
			iguales=idPrestamo.equals(n.getidPrestamo());
		}
		return iguales;
	}

}
