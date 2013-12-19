package org.Iteracion3;

import java.sql.SQLException;
import java.util.ArrayList;

public class Libro {
	
	private String titulo;
	private String nombreAutor;
	private String editorial;
	private int edicion;
	private String signatura;
	private String codigoISBN;
	private String fechaPublicacion;
	private int numEjemplares;

	public Libro(){

	}
	
	public ArrayList<Libro> leerTodos() throws SQLException, Exception{
		GestorLibro glibro=new GestorLibro();
		glibro.readAll();
		return glibro.getListaLibros();
	}
	
	public boolean leerLibro(String signatura) throws SQLException, Exception{
		GestorLibro glibro=new GestorLibro();
		this.signatura=signatura;
		Libro l=glibro.read(signatura);
		
		boolean res=false;
		
		if (l!=null){
			codigoISBN=l.getISBN();
			titulo=l.getTitulo();
			nombreAutor=l.getNombreAutor();
			editorial=l.getEditorial();
			edicion=l.getEdicion();
			this.signatura=l.getSignatura();
			fechaPublicacion=l.getFechaPublicacion();
			numEjemplares=l.getEjemplares();
			res=true;
		}
		
		return res;
	}
	
	public boolean actualizarLibro() throws SQLException, Exception{
		GestorLibro glibro=new GestorLibro();
		return glibro.update(this);
	}
	
	public boolean leerLibroTitulo(String tituloLibro) throws SQLException, Exception{
		GestorLibro glibro=new GestorLibro();
		titulo=tituloLibro;
		Libro l=glibro.readTitle(tituloLibro);
		boolean res=false;
		
		if (l!=null){
			codigoISBN=l.getISBN();
			titulo=l.getTitulo();
			nombreAutor=l.getNombreAutor();
			editorial=l.getEditorial();
			edicion=l.getEdicion();
			signatura=l.getSignatura();
			fechaPublicacion=l.getFechaPublicacion();
			numEjemplares=l.getEjemplares();
			res=true;
		}
		
		return res;
	}
	
	/////////////////////////////////
	public String getTitulo(){
		return titulo;
	}
	
	public String getNombreAutor(){
		return nombreAutor;
	}
	
	public String getEditorial(){
		return editorial;
	}
	
	public int getEdicion(){
		return edicion;
	}
	
	public String getSignatura(){
		return signatura;
	}
	
	public String getISBN(){
		return codigoISBN;
	}
	
	public String getFechaPublicacion(){
		return fechaPublicacion;
	}
	
	public int getEjemplares(){
		return numEjemplares;
	}
	
	/////////////////////////////////////////
	
	public void setTitulo(String titulo){
		this.titulo=titulo;
	}
	
	public void setNombreAutor(String nombreAutor){
		this.nombreAutor=nombreAutor;
	}
	
	public void setEditorial(String editorial){
		this.editorial=editorial;
	}
	
	public void setEdicion(int edicion){
		this.edicion=edicion;
	}
	
	public void setSignatura(String signatura){
		this.signatura=signatura;
	}
	
	public void setISBN(String codigoISBN){
		this.codigoISBN=codigoISBN;
	}
	
	public void setFechaPublicacion(String fecha){
		fechaPublicacion=fecha;
	}
	
	public void setEjemplares(int ejemplares){
		numEjemplares=ejemplares;
	}
	
	

}
